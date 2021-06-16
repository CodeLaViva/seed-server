package com.nullpointer.seed.models;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description
 */
public class Sample {
    /**
     * sample id
     */
    private String id;
    /**
     * sample name
     */
    private String name;
    /**
     * sample description
     */
    private String description;

    public Sample() {
    }

    public Sample(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
