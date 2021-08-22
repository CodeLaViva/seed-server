package com.nullpointer.seed.repositories;

import com.nullpointer.seed.entities.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lihongzheng
 * @date 2021/6/6
 * @description
 */
@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Integer> {
    SampleEntity findSampleEntityByIdAndName(int id, String name);
}
