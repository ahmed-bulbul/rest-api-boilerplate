package com.restboilarplate.repository.generic;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    @Query(value ="Select * from dual" ,nativeQuery = true)
    String customQuery(String query);
}
