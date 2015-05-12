package payment.deploy

/**
 * User: dahoopster
 * Date: 12/9/12
 * Time: 3:51 PM
 */
class DefaultEnvReader implements EnvReader {

    @Override
    def getEnv(key) {
        return System.getenv(key)
    }
}
