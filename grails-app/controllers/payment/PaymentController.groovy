package payment

class PaymentController extends AbstractController {

    static allowedMethods = [
            charge: 'POST',
            getTestToken: 'GET',
            createCustomer: 'POST',
            captureCharge: 'POST'
    ]

    def paymentService

    def getTestToken() {
        try{
            def token = paymentService.getCardToken()
            renderResponse([
                    token: token.id
            ])
        }
        catch(e) {
            def errMsg = "Error while creating Stripe Test Card Token"
            log.error(errMsg, e)
            renderError(errMsg)
        }
    }

    def createCustomer() {
        try {
            def args = params[Constants.ARGS_PARAM]
            def customer = paymentService.createCustomer([
                    card: args.token,
                    email: args.email,
                    description: "Create customer: ${args.name}"
            ])

            renderResponse([
                 customerId: customer.id
            ])
        }
        catch (e) {
            def errMsg = "Error while creating Stripe Customer"
            log.error(errMsg, e)
            renderError(errMsg)
        }
    }

    def charge() {
        def args = params[Constants.ARGS_PARAM]

        try {
            def charge = paymentService.createNewStripeCharge(args.customerId, args.amount, args.capture)
            renderResponse([
                    chargeId: charge.id
            ])
        }
        catch(e) {
            def errMsg = "Error while creating Stripe Charge for CustomerId: ${args.customerId}"
            log.error(errMsg, e)
            renderError(errMsg)
        }
    }

    def captureCharge() {
        def args = params[Constants.ARGS_PARAM]

        try {
            def charge = paymentService.captureStripeCharge(args.chargeId, args.amount, args.emailReceipt)

            renderResponse([
                    chargeId: charge.id,
                    capture: charge.captured
            ])
        }
        catch(e) {
            def errMsg = "Error while capturing Stripe Charge for Id: ${args.chargeId}"
            log.error(errMsg, e)
            renderError(errMsg)
        }
    }
}
