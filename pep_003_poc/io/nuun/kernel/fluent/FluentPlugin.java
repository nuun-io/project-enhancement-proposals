package io.nuun.kernel.fluent;

public interface FluentPlugin {
    void configure(ConfigurationBuilder configurationBuilder);

    void init(InitContext initContext, ModuleInstaller moduleInstaller);

    void start();

    void stop();
}
