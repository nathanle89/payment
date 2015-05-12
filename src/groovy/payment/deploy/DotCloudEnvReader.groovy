package payment.deploy

/**
 * User: dahoopster
 * Date: 12/9/12
 * Time: 8:33 PM
 */
class DotCloudEnvReader extends JsonEnvReader {

    DotCloudEnvReader() {
        super('/home/dotcloud/environment.json')
    }
}
