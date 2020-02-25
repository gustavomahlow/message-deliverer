package br.mahlow.message.deliverer.server.application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@ApplicationPath("/api")
public class ServerApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        List<ClassLoader> classLoadersList = new LinkedList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("br.mahlow.message.deliverer.server.rest.resources"))));

        Set<Class<?>> result = reflections.getSubTypesOf(Object.class);
        result.add(JacksonFeature.class);

        return result;
    }
}
