package payment

class Constants {
    static final def ARGS_PARAM = 'args'
    static final def BASIC_AUTH_USERNAME = 'dryv3app@k3y'
    static final def BASIC_AUTH_PASSWORD = 'G3ner@tedPwd!4DryveApp'
    static final def HTTP_HEADER_AUTHORIZATION = 'Authorization'

    // for relay
    static final def BASIC_AUTH_VALUE = 'ZHJ5djNhcHBAazN5OkczbmVyQHRlZFB3ZCE0RHJ5dmVBcHA='

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

    static final def NEW_ACCOUNT_SIGNUP_EMAIL_SUBJECT = 'New Account Signup'

    static final def REDIS_STRIPE_CUSTOMER_DATE_TIME_FORMAT = 'yyyyMMdd_HHmmss'

    static final def PAYMENT_FAILURE_REMINDER_EMAIL_COUNT_PAGE = 'paymentFailureReminderEmailCountPage'
    static final def DEFAULT_CURRENCY = "usd"
    static final def REFUND_SUCCESS_EMAIL_SUBJECT = 'Successful Refund With Stripe on Cancellation'
    static final def REFUND_FAILURE_EMAIL_SUBJECT = 'Failed Refund With Stripe on Cancellation'
    static final def ADDITIONAL_CHARGE_FAILURE_EMAIL_SUBJECT = 'Failed Charge With Stripe on Cancellation'
    static final def ADDITIONAL_CHARGE_SUCCESS_EMAIL_SUBJECT = 'Successful Charge With Stripe on Cancellation'
}
