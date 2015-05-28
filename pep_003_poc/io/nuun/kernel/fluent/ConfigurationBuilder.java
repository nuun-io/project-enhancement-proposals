package io.nuun.kernel.fluent;

public interface ConfigurationBuilder {
    ConfigurationBuilder after(Class<?> theClass);

    ConfigurationBuilder before(Class<?> theClass);

    ConfigurationBuilder scanClasses(Specification<?>... specification);

    ConfigurationBuilder scanResources(String s);

    ConfigurationBuilder bindClasses(Specification<?>... specification);

    ConfigurationBuilder addPackageRoot(String s);

    ConfigurationBuilder nextRound();
}
