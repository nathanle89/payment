package exceptions

class PaymentDeclinedException extends RuntimeException {

    def reason

    static final ERROR_CODE_MAPPING = [
            'card_declined': 'Your card has been declined.',
            'incorrect_zip': 'The billing address zip code failed validation.',
            'incorrect_number': 'The card number is incorrect.',
            'invalid_expiry_month': 'The expiration month is incorrect.',
            'invalid_expiry_year': 'The expiration year is incorrect.',
            'invalid_cvc': 'The card security code is incorrect'
    ]

    PaymentDeclinedException(message, errCode, throwable) {
        super(message, throwable)
        this.reason = ERROR_CODE_MAPPING[(errCode)]
    }
}

