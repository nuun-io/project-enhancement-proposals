package io.nuun.kernel;

import io.nuun.kernel.fluent.AbstractFluentPlugin;
import io.nuun.kernel.fluent.Factory;
import io.nuun.kernel.fluent.SomeTrait;
import io.nuun.kernel.fluent.AbstractModule;
import io.nuun.kernel.fluent.ConfigurationBuilder;
import io.nuun.kernel.fluent.InitContext;
import io.nuun.kernel.fluent.ModuleInstaller;
import io.nuun.kernel.fluent.MyImplementationSpecification;
import io.nuun.kernel.fluent.MyInterfaceSpecification;
import io.nuun.kernel.fluent.PrivateModule;
import io.nuun.kernel.fluent.SomeOtherTrait;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

// Plugin name is inferred from class name. i.e. complex-plugin from ComplexPlugin
public class ComplexPlugin extends AbstractFluentPlugin {
    public static final String PROPS = ".*\\.props";
    public static final MyInterfaceSpecification MY_INTERFACE_SPECIFICATION = new MyInterfaceSpecification();
    public static final MyImplementationSpecification MY_IMPLEMENTATION_SPECIFICATION = new MyImplementationSpecification();

    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        configurationBuilder
                .after(SomeTrait.class) // replaces requiredPlugins
                .before(SomeOtherTrait.class) // replaces dependentPlugins
                .scanClasses(MY_INTERFACE_SPECIFICATION) // can use spec-DSL to build it
                .scanResources(PROPS)
                .addPackageRoot("org.seedstack")

                .nextRound()

                .scanClasses(MY_IMPLEMENTATION_SPECIFICATION); // can use spec-DSL to build it
    }

    @Override
    public void init(final InitContext initContext, final ModuleInstaller moduleInstaller) {
        final Map<Class<? extends Factory>, Set<Class<? extends Factory>>> factories = new HashMap<>();

        // Rounds can be externalized to named classes and removing a lot of code from the plugin

        initContext.round(() -> {
            final Properties properties = new Properties();

            initContext.trait(SomeTrait.class).doSomething();

            initContext.resources(PROPS).forEach((url) -> {
                try {
                    properties.load(url.openStream());
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to load URL " + url);
                }
            });

            initContext.classes(MY_INTERFACE_SPECIFICATION).forEach((factoryInterface) -> factories.put(factoryInterface, new HashSet<>()));

            moduleInstaller.install(new AbstractModule() {
                @Override
                protected void configure() {
                    bind(Properties.class).toInstance(properties);
                }
            });
        });


        initContext.round(() -> {
            initContext.classes(MY_IMPLEMENTATION_SPECIFICATION).forEach((factoryImplementation) -> factories.get(findInterfaceForImpl(factoryImplementation)).add(factoryImplementation));

            moduleInstaller.install(new PrivateModule() {
                @Override
                protected void configure() {
                    factories.forEach((factoryInterface, factoryImplementations)
                            -> factoryImplementations.forEach((factoryImpl)
                            -> bind(factoryInterface).to(factoryImpl)));
                }
            });
        });
    }

    @Override
    public void start() {
        // do something to start
    }

    @Override
    public void stop() {
        // do something to stop
    }

    private Class<? extends Factory> findInterfaceForImpl(Class<? extends Factory> implClass) {
        return null;
    }
}