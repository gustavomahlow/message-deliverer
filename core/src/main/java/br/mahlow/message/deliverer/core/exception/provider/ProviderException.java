package br.mahlow.message.deliverer.core.exception.provider;

public class ProviderException extends Exception {

    private static final long serialVersionUID = -660142181440833007L;

    public ProviderException() {
    }

    public ProviderException(String message) {
        super(message);
    }

    public ProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProviderException(Throwable cause) {
        super(cause);
    }

    public ProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
