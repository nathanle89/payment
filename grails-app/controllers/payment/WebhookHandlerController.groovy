package payment

class WebhookHandlerController extends AbstractController {

    static allowedMethods = [
            orderCompleted: 'POST'
    ]

    def orderCompleted() {
        try {

        }
        catch(e) {
            def errMsg = "Error processing webhook request"
            log.error(errMsg, e)
            renderError(errMsg)
        }
    }

}
