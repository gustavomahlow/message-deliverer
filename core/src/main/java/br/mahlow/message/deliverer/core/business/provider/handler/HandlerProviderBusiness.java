package br.mahlow.message.deliverer.core.business.provider.handler;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.HandlerInitializationFailed;
import br.mahlow.message.deliverer.core.exception.provider.handler.FailedToLoadHandler;
import br.mahlow.message.deliverer.core.exception.provider.handler.InvalidHandlerException;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

public class HandlerProviderBusiness {

    public MessageHandler lookupInJar(URL jarUrl) throws
            InvalidHandlerException,
            HandlerInitializationFailed {
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jarUrl}, getClass().getClassLoader());

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(jarUrl)
                        .addClassLoader(urlClassLoader)
        );

        Set<Class<? extends MessageHandler>> subTypes = reflections.getSubTypesOf(MessageHandler.class);

        if (subTypes.size() > 1)
            throw new InvalidHandlerException(String.format("More than one MessageHandler for jar %s", jarUrl.getPath()));

        Class<? extends MessageHandler> subType = subTypes.iterator().next();

        try {
            MessageHandler handler = subType.newInstance();
            handler.initializeResources();

            return handler;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new HandlerInitializationFailed(e);
        }
    }

    public MessageHandler lookupInJar(String filePath) throws
            InvalidHandlerException,
            HandlerInitializationFailed,
            FailedToLoadHandler {
        try {
            return lookupInJar(
                    new File(filePath)
                            .toURI()
                            .toURL()
            );
        } catch (MalformedURLException e) {
            throw new FailedToLoadHandler("Failed to load handler for specified path", e);
        }
    }
}
