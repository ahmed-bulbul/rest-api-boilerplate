package com.restboilarplate.entity.system;


import com.restboilarplate.entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="SYSTEM_MENU_AUTHORIZATION")
public class SystemMenuAuthorization extends BaseEntity {

    String menuCode;
    String parentMenuCode;
    String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_menu_id", nullable = false)
    SystemMenu systemMenu;

    Boolean canSee;
    Integer sequence;
    Boolean createPermission;
    Boolean updatePermission;
    Boolean deletePermission;

    public SystemMenuAuthorization(String menuCode, String parentMenuCode, String username, SystemMenu systemMenu, Boolean canSee, Integer sequence, Boolean createPermission, Boolean updatePermission, Boolean deletePermission) {
        this.menuCode = menuCode;
        this.parentMenuCode = parentMenuCode;
        this.username = username;
        this.systemMenu = systemMenu;
        this.canSee = canSee;
        this.sequence = sequence;
        this.createPermission = createPermission;
        this.updatePermission = updatePermission;
        this.deletePermission = deletePermission;
    }
}
