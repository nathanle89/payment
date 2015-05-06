package payment

import grails.converters.JSON
import payment.Constants

import static payment.Constants.*

abstract class AbstractController {

    def grailsApplication

    def renderError(message) {
        def error = [
                error: [
                        message: message
                ]
        ]
        renderMessage(error, 500)
    }

    def renderSuccess(message) {
        def success = [
                success: [
                        message: message
                ]
        ]
        renderMessage(success, 200)
    }

    def renderAuthError(message) {
        def error = [
                unauthorized: [
                        message: message
                ]
        ]
        renderMessage(error, 401)
    }

    def renderBadRequestError(messages, data) {
        def error = [
                error: [
                        messages: messages,
                        data: data
                ]
        ]
        renderMessage(error, 400)
    }

    def renderResponse(model) {
        if (params.callback) {
            render(contentType: CONTENT_TYPE_JSON, status: 200,
                    text: "${params.callback}(${model as JSON})")
        }
        else {
            render(contentType: CONTENT_TYPE_JSON, status: 200) {
                return model
            }
        }
    }

    /**
     *
     * @param messageData a map of message to render
     * @param statusCode http status code
     */
    private def renderMessage(messageData, statusCode) {
        if (params.callback) {
            render(contentType: CONTENT_TYPE_JSON, status: 200,
                    text: "${params.callback}(${messageData as JSON})"
            )
        }
        else {
            render(contentType: CONTENT_TYPE_JSON, status: statusCode) {
                return messageData
            }
        }
    }
}
