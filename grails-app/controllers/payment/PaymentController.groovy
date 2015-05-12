package payment

class PaymentController extends AbstractController {

    static allowedMethods = [
            charge: 'POST',
            getTestToken: 'POST',
            createCustomer: 'POST'
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
        renderResponse([
                token: args.token
        ])
    }
}
