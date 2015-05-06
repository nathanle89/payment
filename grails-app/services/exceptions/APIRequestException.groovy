package exceptions

class APIRequestException extends RuntimeException {

    APIRequestException(apiProvider, request) {
        super("API request <${request}> to <${apiProvider}> failed")
    }
}

