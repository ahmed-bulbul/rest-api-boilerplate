package com.restboilarplate.repository.generic;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
