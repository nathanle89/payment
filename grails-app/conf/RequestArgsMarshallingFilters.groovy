import payment.Constants
import groovy.json.JsonSlurper

class RequestArgsMarshallingFilters {
    def dependsOn = [RequestAuthenticationFilters]

    def filters = {
        all(controller: 'error|ping|facebookTabAppTracking', action: '*', invert: true) {
            before = {
                if (request.method == 'GET') {
                    return true
                }
                else if (request.method == 'POST') {
                    def payloadBodyText = request.reader.text
                    if (payloadBodyText.length() != 0) {
                        if (request.contentType != Constants.CONTENT_TYPE_FORM_SUBMISSION) {
                            unmarshalArgsJson(payloadBodyText, params)
                        }
                    }
                    return true
                }
                log.error('args query param missing from request')
                return false
            }
        }
    }

    // unmarshal the JSON args into object
    def unmarshalArgsJson(jsonArgs, params) {
        def jsonObj = new JsonSlurper().parseText(jsonArgs)
        params[(Constants.ARGS_PARAM)] = jsonObj
    }
}
