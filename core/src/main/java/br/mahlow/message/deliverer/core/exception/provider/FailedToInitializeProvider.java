package br.mahlow.message.deliverer.core.exception.provider;

public class FailedToInitializeProvider extends ProviderException {

    private static final long serialVersionUID = 5591072320258573993L;

    public FailedToInitializeProvider() {
    }

    public FailedToInitializeProvider(String message) {
        super(message);
    }

    public FailedToInitializeProvider(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToInitializeProvider(Throwable cause) {
        super(cause);
    }

    public FailedToInitializeProvider(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
