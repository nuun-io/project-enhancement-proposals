package io.nuun.kernel.fluent;

public interface Specification<T> {
    boolean isSatisfiedBy(Class<? extends Factory> aClass);

    Specification<Class<? extends Factory>> and(Specification<? super Class<? extends Factory>> specification);

    Specification<Class<? extends Factory>> or(Specification<? super Class<? extends Factory>> specification);

    Specification<Class<? extends Factory>> not();
}
