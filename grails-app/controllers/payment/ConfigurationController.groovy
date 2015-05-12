package payment

class ConfigurationController extends AbstractController {

    static allowedMethods = [
            getEnvironment: 'GET'
    ]

    def grailsApplication

    def getEnvironment() {
        try {
            renderResponse([
                environment: grailsApplication.config.grails.app.conf.appEnv
            ])
        }
        catch (e) {
            def errMsg = "Error getting Grails Application enviroment"
            log.error(errMsg, e)
            renderError(errMsg)
        }
    }
}
