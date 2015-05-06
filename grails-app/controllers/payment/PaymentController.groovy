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
}
