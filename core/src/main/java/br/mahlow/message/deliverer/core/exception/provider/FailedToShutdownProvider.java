package br.mahlow.message.deliverer.core.exception.provider;

public class FailedToShutdownProvider extends ProviderException {

    private static final long serialVersionUID = 3305807421623849293L;

    public FailedToShutdownProvider() {
    }

    public FailedToShutdownProvider(String message) {
        super(message);
    }

    public FailedToShutdownProvider(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToShutdownProvider(Throwable cause) {
        super(cause);
    }

    public FailedToShutdownProvider(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
