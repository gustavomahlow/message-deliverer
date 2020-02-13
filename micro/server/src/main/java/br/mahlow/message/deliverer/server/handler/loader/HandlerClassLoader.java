package br.mahlow.message.deliverer.server.handler.loader;

public class HandlerClassLoader extends ClassLoader {

    private ClassLoader customerParent;

    public HandlerClassLoader(ClassLoader parent, ClassLoader ext) {
        super(ext);
        this.customerParent = parent;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.contains("br.mahlow.message.deliverer.api") || name.startsWith("javax.json"))
            return customerParent.loadClass(name);

        return super.findClass(name);
    }
}
