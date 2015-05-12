package payment.deploy

import groovy.json.JsonSlurper

/**
 * User: dahoopster
 * Date: 12/9/12
 * Time: 3:53 PM
 */
class JsonEnvReader implements EnvReader {

    def jsonEnv

    JsonEnvReader(jsonPath) {
        def jsonFileReader = new FileReader(new File(jsonPath))
        jsonEnv = new JsonSlurper().parse(jsonFileReader)
        jsonFileReader.close()
    }

    @Override
    def getEnv(final Object key) {
        return jsonEnv[(key)]
    }
}
