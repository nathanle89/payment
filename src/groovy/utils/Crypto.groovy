package utils

import java.security.InvalidKeyException
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class Crypto {
    static def hmac_sha256(data, secretKey) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256")
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256")
            mac.init(secretKeySpec)
            byte[] digest = mac.doFinal(data.getBytes())
            return digest.toString()
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key exception while converting to HMac SHA256")
        }
    }

    static def hmac_sha1(data, secretKey) {
        try {
            Mac mac = Mac.getInstance("HmacSHA1")
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA1")
            mac.init(secretKeySpec)
            byte[] digest = mac.doFinal(data.getBytes())
            return digest.toString()
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key exception while converting to HMac SHA1")
        }
    }
}
