package payment

class WebhookHandlerController extends AbstractController {

    static allowedMethods = [
            orderCompleted: 'POST'
    ]

    def bringgService
    def paymentService

    def orderCompleted() {
        try {
            def args = params[Constants.ARGS_PARAM]
            def task = bringgService.getTask(args.id)
            // TODO read the chargeId and amount to capture
//            def chargeId = task.task_notes[0].note.str.split("\\s+")[1]
//            paymentService.captureStripeCharge(chargeId)

            renderResponse(args)
        }
        catch(e) {
            def errMsg = "Error processing webhook request"
            log.error(errMsg, e)
            renderError(errMsg)
        }
    }

}
