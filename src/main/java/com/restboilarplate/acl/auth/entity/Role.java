package com.restboilarplate.acl.auth.entity;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "acl_roles")
public class Role extends BaseEntity {
    private String roleName;
    private String description;
}
