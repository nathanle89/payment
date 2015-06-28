package payment

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.joda.time.DateTime
import utils.Crypto

class BringgService {

    static transactional = false

    private static def REST_CLIENT = new HTTPBuilder('http://developer-api.bringg.com')

    def grailsApplication

    def getTask(taskId) {
        // By default Groovy creates a linkedHashMap
        def params = [
                company_id  : grailsApplication.config.grails.app.conf.bringg.companyId,
                timestamp   : DateTime.now().millis,
                access_token: grailsApplication.config.grails.app.conf.bringg.accessToken
        ]

        params.signature = signRequestParams(params)

        return sendRequest("/partner_api/tasks/${taskId}", params, ContentType.JSON, Method.GET)
    }

    private def signRequestParams(params) {
        Crypto.hmac_sha1(params.collect { it }.join('&'), grailsApplication.config.grails.app.conf.bringg.secret)
    }

    private def sendRequest(path, params, contentType, method = Method.POST) {
        try {

            return REST_CLIENT.request(method, contentType) {
                uri.path = path
                if (params) {
                    uri.query = params
                }
                headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'
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
