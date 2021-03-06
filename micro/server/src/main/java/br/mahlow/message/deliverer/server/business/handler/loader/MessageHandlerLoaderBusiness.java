package br.mahlow.message.deliverer.server.business.handler.loader;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerInitializationFailed;
import br.mahlow.message.deliverer.server.exception.handler.FailedToLoadHandler;
import br.mahlow.message.deliverer.server.exception.handler.InvalidHandlerException;
import br.mahlow.message.deliverer.server.handler.loader.HandlerClassLoader;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import javax.enterprise.context.Dependent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

@Dependent
public class MessageHandlerLoaderBusiness {

    private MessageHandler lookupInJar(URL jarUrl) throws
            InvalidHandlerException,
            HandlerInitializationFailed {
        URLClassLoader urlClassLoader = new URLClassLoader(
                new URL[]{jarUrl},
                new HandlerClassLoader(getClass().getClassLoader(), getClass().getClassLoader().getParent())
        );

        ConfigurationBuilder configuration = new ConfigurationBuilder()
                .setUrls(jarUrl);

        configuration.setClassLoaders(new ClassLoader[]{urlClassLoader});

        Reflections reflections = new Reflections(configuration);

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
            File file = new File(filePath);

            if (!file.exists())
                throw new FailedToLoadHandler("The specified path not exists");

            return lookupInJar(file.toURI().toURL());
        } catch (MalformedURLException e) {
            throw new FailedToLoadHandler("Failed to load handler for specified path", e);
        }
    }
}
