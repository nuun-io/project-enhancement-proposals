package io.nuun.kernel.fluent;

public class MyImplementationSpecification implements Specification<Class<? extends Factory>>{
    @Override
    public boolean isSatisfiedBy(Class<? extends Factory> aClass) {
        return false;
    }

    @Override
    public Specification<Class<? extends Factory>> and(Specification<? super Class<? extends Factory>> specification) {
        return null;
    }

    @Override
    public Specification<Class<? extends Factory>> or(Specification<? super Class<? extends Factory>> specification) {
        return null;
    }

    @Override
    public Specification<Class<? extends Factory>> not() {
        return null;
    }
}
