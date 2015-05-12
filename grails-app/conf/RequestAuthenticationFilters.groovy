import payment.Constants
import grails.converters.JSON

class RequestAuthenticationFilters {

    def filters = {
        // ping check
        ping(controller: 'ping', action: 'check') {
            before = {
                def basicAuth = request.getHeader(Constants.HTTP_HEADER_AUTHORIZATION)
                def authValue = (basicAuth - 'Basic').trim()
                return authValue == Constants.PING_AUTH_VALUE
            }
        }

        //check for Basic Authentication when requesting authentication params
        apiAuth(controller: '*', action: '*'){
            before = {
                try {
                    def basicAuth = request.getHeader(Constants.HTTP_HEADER_AUTHORIZATION)
                    def authValue = (basicAuth - 'Basic').trim()
                    def correctAuthValue = (authValue == Constants.BASIC_AUTH_VALUE)
                    if (!correctAuthValue){
                        def returnModel = [
                                status: 'Unauthorized',
                                reason: 401,
                                message: 'Invalid Credentials'
                        ]
                        render(contentType: Constants.CONTENT_TYPE_JSON, status: 401,
                                text: "${returnModel as JSON}")
                        return false
                    }
                    else{
                        return true
                    }
                }
                catch(Exception e) {
                    def returnModel = [
                            status: 'Unauthorized',
                            reason: 401,
                            message: 'Invalid Credentials'
                    ]
                    render(contentType: Constants.CONTENT_TYPE_JSON, status: 401,
                            text: "${returnModel as JSON}")
                    return false
                }
            }
        }
    }
}
