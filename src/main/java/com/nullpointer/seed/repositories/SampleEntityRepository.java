package com.nullpointer.seed.repositories;

import com.nullpointer.seed.entities.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lihongzheng
 */
public interface SampleEntityRepository extends JpaRepository<SampleEntity, Integer> {
    SampleEntity findSampleEntityById(int id);
}