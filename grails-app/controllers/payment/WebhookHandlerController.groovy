package payment

class WebhookHandlerController extends AbstractController {

    static allowedMethods = [
            orderCompleted: 'POST'
    ]

    def orderCompleted() {
        try {
            def args = params[Constants.ARGS_PARAM]
            //For debugging purpose
            println args
            renderResponse(args)
        }
        catch(e) {
            def errMsg = "Error processing webhook request"
            log.error(errMsg, e)
            renderError(errMsg)
        }
    }

}
