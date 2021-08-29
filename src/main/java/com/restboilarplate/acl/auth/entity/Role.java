package com.restboilarplate.acl.auth.entity;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACL_ROLES")
public class Role extends BaseEntity {
    private String roleName;
    private String description;
}
