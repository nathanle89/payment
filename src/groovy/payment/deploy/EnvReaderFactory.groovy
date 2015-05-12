package payment.deploy

/**
 * User: dahoopster
 * Date: 3/7/13
 * Time: 1:27 PM
 */
class EnvReaderFactory {

    static def envReader() {
        new DefaultEnvReader()
    }
}
