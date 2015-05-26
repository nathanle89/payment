package payment

import com.stripe.exception.CardException
import com.stripe.model.Charge
import com.stripe.model.Customer
import com.stripe.model.Token
import com.stripe.net.RequestOptions
import exceptions.APIRequestException
import exceptions.PaymentDeclinedException
import groovy.util.logging.Log4j

@Log4j
class PaymentService {

    static transactional = false

    def grailsApplication
    def emailBlenderService
    def awsEmailSender
    def dataSourceSpy

    /**
     *
     * @param email customer email
     * @param card a stripe token obtained by Stripe.js OR a map with credit card info (test only)
     * @param planId subscription plan ID
     * @param couponCode (optional) coupon code to apply
     * @return a Stripe customer obj
     */
    def createCustomer(customerData) {
        try {
            return Customer.create(customerData, getRequestOptions())
        }
        catch (CardException cde) {
            log.error("Card declined when creating a payment customer with params: <${customerData}>", cde)
            throw new PaymentDeclinedException('Your credit card was declined.', cde.code, cde)
        }
        catch (e) {
            log.error("Failed to create a customer with prarms: <${customerData}>", e)
            throw new APIRequestException('Stripe', 'createCustomer');
        }
    }

    /**
     *
     * @param customerId a Stripe customer ID
     * @return true if successful and false otherwise
     */
    def deleteCustomer(customerId) {
        def apiKey = grailsApplication.config.grails.app.conf.stripe.key as String
        try {
            def customer = Customer.retrieve(customerId as String, getRequestOptions())
            def deletedCustomer = customer.delete(getRequestOptions())
            return deletedCustomer.deleted
        }
        catch (e) {
            log.error("Failed to delete customer with ID <${customerId}>", e)
            return false
        }
    }

    /**
     *
     * @param customerId a Stripe customer ID
     * @return a Stripe card obj
     */
    def getCard(customerId) {
        try {
            def customer = Customer.retrieve(customerId as String, getRequestOptions())
            if (customer.cards.count == 0) {
                log.info("Payment customer with ID <${customerId}> has no credit card")
                return null
            }
            else {
                return customer.cards.data[0]
            }
        }
        catch (e) {
            log.error("Failed to retrieve card for payment customer with ID <${customerId}>", e)
            throw new APIRequestException('Stripe', 'getCard')
        }
    }

    /**
     *
     * @param customerId a Stripe customer ID
     */
    def deleteCard(customerId) {
        try {
            def customer = Customer.retrieve(customerId as String, getRequestOptions())
            if (customer.cards.count == 0) {
                log.info("Payment customer with ID <${customerId}> has no credit card")
                return false
            }
            else {
                return customer.cards.data[0].delete(getRequestOptions()).deleted
            }
        }
        catch (e) {
            log.error("Failed to delete card for payment customer with ID <${customerId}>", e)
            return false
        }
    }

    /**
     *
     * @param customerId a Stripe customer ID
     * @param card either a Stripe.js token OR a map with credit card info (test only)
     * @return a card obj
     */
    def newCard(customerId, card) {
        try {
            def customer = Customer.retrieve(customerId as String, getRequestOptions())
            if (customer.cards.count != 0) {
                customer.cards.data.each {
                    customerCard ->
                        customerCard.delete(getRequestOptions())
                }
            }
            return customer.createCard([card: card], getRequestOptions())
        }
        catch (e) {
            log.error("Failed to create a card for payment customer with ID <${customerId}>, card <${card}>", e)
            throw new APIRequestException('Stripe', 'newCard')
        }
    }

    /**
     *
     * @param customerId
     * @return stripe customer
     */

    def customerById(customerId) {
        try {
            return Customer.retrieve(customerId as String, getRequestOptions())
        }
        catch (e) {
            log.error("Failed to retrieve customer with ID <${customerId}>", e)
            throw new APIRequestException('Stripe', 'retrieveCustomer')
        }
    }

    /**
     *
     * @param customerId - Id of stripe customer String
     * @param cardId - id of stripe card String
     * @param amount - the amount to charge the card in cents ie: 9900 ($99)
     * @return Charge object
     *
     * This method will create a charge on the card without creating an invoice
     */
    def createNewStripeCharge(customerId, amount, capture = false) {
        try {
            def charge = Charge.create([
                    customer: customerId,
                    amount: amount,
                    currency: Constants.DEFAULT_CURRENCY,
                    capture: capture
            ], getRequestOptions())

            return charge
        }
        catch (e) {
            log.error("Failed to charge customer with ID <${customerId}>", e)
            throw new APIRequestException('Stripe', 'createNewCharge')
        }
    }


    def captureStripeCharge(chargeId, amount, emailReceipt) {
        try {
            def charge = Charge.retrieve(chargeId, getRequestOptions())
            def params = [
                amount: amount
            ]
            if (emailReceipt) {
                params['receipt_email'] = emailReceipt
            }

            charge.capture(params)

            return charge
        }
        catch (e) {
            log.error("Failed to capture charge with ID <${chargeId}>", e)
            throw new APIRequestException('Stripe', 'createNewCharge')
        }
    }

    /**
     *
     * @param chargeId
     * @param amount
     * @return
     */
    def issueRefund(chargeId, amount) {
        try {
            def charge = Charge.retrieve(chargeId as String, getRequestOptions())
            return charge.refund([
                    amount: amount
            ], getRequestOptions())
        }
        catch (e) {
            log.error("Failed to issue refund with ID <${chargeId}>", e)
            throw new APIRequestException('Stripe', 'createRefund')
        }
    }

    /**
        This is a debug method
     */
    def getCardToken() {
        try {
            def tokenParams = [
               "card": [
                       "number": "4242424242424242",
                       "exp_month": 5,
                       "exp_year": 2025,
                       "cvc": "314"
               ]
            ]

            Token.create(tokenParams, getRequestOptions());
        }
        catch(e) {
            log.error("Failed to get card Token", e)
            throw new APIRequestException('Stripe', 'createCard')
        }
    }


    def getRequestOptions() {
        def apiKey = grailsApplication.config.grails.app.conf.stripe.key as String
        def requestOptionsBuilder = new RequestOptions.RequestOptionsBuilder()

        requestOptionsBuilder.apiKey = apiKey

        return requestOptionsBuilder.build()
    }

}
