package io.nuun.kernel.fluent;

import java.net.URL;
import java.util.Collection;

public interface InitContext {
    <T> T trait(Class<T> traitClass);

    Collection<URL> resources(String regex);

    <T> Collection<Class<? extends T>> classes(Specification<Class<? extends T>> spec);

    void round(Round round);
}
