package payment

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.joda.time.DateTime
import utils.Crypto

class BringgClientService {
    private static def REST_CLIENT = new HTTPBuilder(uri: 'http://developer-api.bringg.com/partner_api')

    def grailsApplication

    def getTask(taskId) {
        // By default Groovy creates a linkedHashMap
        def params = [
                company_id  : grailsApplication.config.grails.app.conf.bringg.companyId,
                access_token: grailsApplication.config.grails.app.conf.bringg.accessToken,
                timestamp   : DateTime.now().millis
        ]

        params['signature'] = signRequestParams(params)

        return sendRequest("/tasks/${taskId}", params, ContentType.JSON, Method.GET)
    }

    def signRequestParams(params) {
        Crypto.hmac_sha1(params.collect { it }.join('&'), grailsApplication.config.grails.app.conf.bringg.secret)
    }

    def sendRequest(path, params, contentType, method = Method.POST) {
        try {
            return REST_CLIENT.request(method, contentType) {
                uri.path = path
                uri.query = params
            }
        } catch (groovyx.net.http.HttpResponseException ex) {
            ex.printStackTrace()
            return null
        } catch (java.net.ConnectException ex) {
            ex.printStackTrace()
            return null
        }
    }
}
