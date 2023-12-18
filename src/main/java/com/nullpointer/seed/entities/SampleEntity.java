package com.nullpointer.seed.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description
 */
@Entity
@Table(name = "sample_entity")
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
     * sample age
     */
    @Column
    private int age;
    /**
     * sample birthday
     */
    @Column
    private Date birthday;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt = Date.valueOf(LocalDate.now());

    public SampleEntity(int id, String name, int age, Date birthday, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SampleEntity() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
