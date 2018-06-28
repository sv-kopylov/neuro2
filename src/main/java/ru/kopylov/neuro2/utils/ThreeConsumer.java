package ru.kopylov.neuro2.utils;

/**
 * Created by se on 28.06.2018.
 */
@FunctionalInterface
public interface ThreeConsumer<T, U, V>{
    void accept(T t, U u, V v);
}
