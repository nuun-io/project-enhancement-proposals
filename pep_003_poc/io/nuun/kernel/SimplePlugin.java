package io.nuun.kernel;

import io.nuun.kernel.fluent.AbstractFluentPlugin;
import io.nuun.kernel.fluent.MyInterfaceSpecification;
import io.nuun.kernel.fluent.SomeTrait;
import io.nuun.kernel.fluent.ConfigurationBuilder;
import io.nuun.kernel.fluent.MyImplementationSpecification;

public class SimplePlugin extends AbstractFluentPlugin {
    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        configurationBuilder.after(SomeTrait.class).bindClasses(new MyInterfaceSpecification(), new MyImplementationSpecification());
    }
}
