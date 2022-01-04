package com.energizeglobal.bankservice.infrastructure;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractTransformer<E extends AbstractPersistence, V extends AbstractValueObject> {


    protected Class<? extends AbstractValueObject> getValueObjectClass() {
        return (Class<V>) GenericUtils.extract(this.getClass(), 1);
    }

    protected Class<? extends AbstractPersistence> getEntityClass() {
        return (Class<E>) GenericUtils.extract(this.getClass(), 0);
    }

    public E newEntity() {
        try {
            return (E) getEntityClass().newInstance();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public V newValueObject() {
        try {
            return (V) getValueObjectClass().newInstance();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public E createEntityFromValueObject(V v) {
        E e = newEntity();
        transformValueObjectToEntity(v, e);
        return e;
    }

    public V createValueObjectFromEntity(E e) {
        V v = newValueObject();
        transformEntityToValueObject(e, v);
        return v;
    }

    protected void setSystematicField(V v,E e) {
        e.setId(v.getId());
        e.setVersion(v.getVersion());
        e.setInsertDate(v.getInsertDate());
        e.setModifyDate(v.getModifyDate());
    }

    protected void setSystematicField(E e, V v) {
        v.setId(e.getId());
        v.setVersion(e.getVersion());
        v.setInsertDate(e.getInsertDate());
        v.setModifyDate(e.getModifyDate());
    }

    public abstract void transformValueObjectToEntity(V input, E output);

    public abstract void transformEntityToValueObject(E input, V output);


    // TODO Fix bug
    public List<V> transformEntitiesToValueObjects(List<E> entityList) {
        return CollectionUtils.isEmpty(entityList)
                ? Collections.emptyList()
                : entityList.stream().map(this::createValueObjectFromEntity).collect(Collectors.toList());
    }

    // TODO Fix bug
    public List<E> transformValueObjectsToEntities(List<V> valueObjectList) {
        return CollectionUtils.isEmpty(valueObjectList)
                ? Collections.emptyList()
                : valueObjectList.stream().map(this::createEntityFromValueObject).collect(Collectors.toList());
    }
}
