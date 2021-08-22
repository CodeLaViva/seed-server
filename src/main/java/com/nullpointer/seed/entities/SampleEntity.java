package com.nullpointer.seed.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description
 */
@Entity
public class SampleEntity {
    /**
     * sample id
     */
    @Id
    @Column
    private int id;
    /**
     * sample name
     */
    @Column
    private String name;
    /**
     * sample description
     */
    @Column
    private String description;

    public SampleEntity() {
    }

    public SampleEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
