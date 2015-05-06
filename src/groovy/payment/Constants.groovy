package payment

class Constants {
    static final def ARGS_PARAM = 'args'
    static final def DST_EMAIL_KEY = 'd$tem@ilk3y'
    static final def HTTP_HEADER_AUTHORIZATION = 'Authorization'
    // base 64 encoded value of "dstping:p!ngt3st"
    static final def PING_AUTH_VALUE = 'ZHN0cGluZzpwIW5ndDNzdA=='
    // for relay
    static final def BASIC_AUTH_VALUE = 'ZHN0YXV0aDprQHJCL2tiUHNQYU5oXTA5LVprZQ=='
    // for making requests to waterfall
    static final def BASIC_AUTH_WATERFALL_USER = 'system.user'
    static final def BASIC_AUTH_WATERFALL_PWD = 'G3ner@tedPwd!'
    // HEX valud of sha-512 of /relay/to
    static final def RELAY_ORIGIN_PROP = 'relayFrom'
    static final def RELAY_ORIGIN_VALUE = '21b871cba84951750a6c536c59bce124814f66f1d73d49d4babaf50412441f03b847717fb587bbf781287261750c456256e595e487719764c575ccd697de68c5'
    static final def DEFAULT_PASSWORD = 'P@ssworD!'

    static final def USER_AGENT = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21'
    static final def CONTENT_TYPE_FORM_SUBMISSION = 'application/x-www-form-urlencoded'
    static final def CONTENT_TYPE_JSON = 'application/json'
    static final def CONTENT_TYPE_XML = 'application/xml'
    static final def CONTENT_TYPE_STREAM = 'application/octet-stream'
    static final def CONTENT_TYPE_CSV = 'text/csv'
    static final def CONTENT_TYPE_TEXT = 'text/plain'
    static final def CONTENT_TYPE_EXCEL = 'application/vnd.ms-excel'
    static final def HTTP_GET = 'GET';
    static final def HTTP_POST = 'POST';
    static final def HTTP_PUT = 'PUT';
    static final def HTTP_DELETE = 'DELETE';

    static final def KEY_MANUALJOB_TRIGGER = 'manualJobTrigger'
    // the value is the base64 encoded, SHA-512 hashed value of "manualJobTrigger"
    static final def KEY_MANUAL_JOB_TRIGGER_VALUE = 'WMXgtjd5nQGWZEZt0a5tJ+rI/xaYs/P0kUqstf9qgrHCmWIB52XuLP9+hzYnT3yQ/Rx3eP3AgLKAGXd3nJRvDQ=='

    static final def ADD_TIME_FILTER = 'addTime'
    static final def EXPIRE_TIME_FILTER = 'expireTime'
    static final def LOCK_KEY_PHONE_IN = 'lockKey:phoneIn'
    static final def KEY_PHONE_IN = 'phoneIn'

    static final def NEW_ACCOUNT_SIGNUP_EMAIL_SUBJECT = 'New Account Signup'

    static final def REDIS_STRIPE_CUSTOMER_DATE_TIME_FORMAT = 'yyyyMMdd_HHmmss'

    static final def PAYMENT_FAILURE_REMINDER_EMAIL_COUNT_PAGE = 'paymentFailureReminderEmailCountPage'

    static final def ENGINEERING_EMAIL = 'engineering@demandstreet.com'

    static final def NUMBER_OF_FANS_BUFFER = 10
    static final def DEFAULT_CURRENCY = "usd"

    static final def REFUND_SUCCESS_EMAIL_SUBJECT = 'Successful Refund With Stripe on Cancellation'
    static final def REFUND_FAILURE_EMAIL_SUBJECT = 'Failed Refund With Stripe on Cancellation'
    static final def ADDITIONAL_CHARGE_FAILURE_EMAIL_SUBJECT = 'Failed Charge With Stripe on Cancellation'
    static final def ADDITIONAL_CHARGE_SUCCESS_EMAIL_SUBJECT = 'Successful Charge With Stripe on Cancellation'
}
