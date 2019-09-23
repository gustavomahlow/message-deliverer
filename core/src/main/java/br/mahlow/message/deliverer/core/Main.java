package br.mahlow.message.deliverer.core;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.core.manager.provider.ProviderManager;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ProviderManager providerManager = new ProviderManager();
        providerManager.initialize();

        providerManager.getInstance(MessageHandler.class, "test");

        providerManager.getInstance(MessageHandler.class, "test").onNotification();
        providerManager.getInstance(MessageHandler.class, "test").shutdown();

        System.out.println();
    }
}
