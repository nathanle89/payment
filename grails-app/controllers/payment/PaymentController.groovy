package payment

class PaymentController extends AbstractController {

    static allowedMethods = [
            charge: 'POST'
    ]

    def paymentService

    def charge() {
        def args = params[Constants.ARGS_PARAM]
        renderResponse([
                token: args.token
        ])
    }

    def createCustomer() {
        def args = params[Constants.ARGS_PARAM]
        def customer = paymentService.createCustomer([
                card: args.token,
                email: args.email,
                name: args.name
        ])

        renderResponse([
             customerId: customer.id
        ])
    }
}
