package io.nuun.kernel.fluent;

public abstract class PrivateModule {
    protected BindToClass bind(final Class<?> aClass) {
        return new BindToClass();
    }

    protected abstract void configure();

    protected static class BindToClass {
        public void to(final Class<?> anotherClass) {}
    }
}
