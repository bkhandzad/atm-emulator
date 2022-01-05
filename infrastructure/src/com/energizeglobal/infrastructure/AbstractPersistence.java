package com.energizeglobal.infrastructure;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class AbstractPersistence implements Serializable {

    public static final int ALLOCATION_SIZE = 1;

    protected Long id;

    protected LocalDateTime insertDate;

    protected LocalDateTime modifyDate;

    protected Integer version;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return insert date
     */
    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    /**
     * @param insertDate
     */
    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }
    /**
     * @return modify date
     */
    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate
     */
    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
    /**
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    public abstract boolean equals(Object o);

    public abstract int hashCode();

    public abstract String toString();
}
