package com.energizeglobal.bankservice.infrastructure;
/**
 * when a enum wants to convert to database column and vice versa, use this interface.
 *
 * @param <T> type
 */
public interface Convertible<T> {
    /**
     * value accessor
     *
     * @return type
     */
    T getValue();
}