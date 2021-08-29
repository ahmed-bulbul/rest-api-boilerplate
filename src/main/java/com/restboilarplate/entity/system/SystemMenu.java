package com.restboilarplate.entity.system;

import com.restboilarplate.entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="SYSTEM_MENU")
public class SystemMenu  extends BaseEntity {


    @Column(unique = true)
    String code; // menuCode is unique for all, if needed then user will add menuCode+orgCode
    String description;

    String entityName;
    String openUrl;
    Integer sequence;
    String iconHtml;
    Boolean hasChild;
    Boolean visibleToAll;
    String chkAuthorization;
    String chkAuthorizationChar;
    Boolean leftSideMenu;
    Boolean dashboardMenu;
    Boolean mainHeaderMenu;

    Boolean isChild;
    Boolean isOpenNewTab;
    Boolean isActive;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_menu_id", referencedColumnName = "id")
    SystemMenu parentMenu;

    @OneToMany(mappedBy ="parentMenu")
    public List<SystemMenu> children = new ArrayList<>();

    String parentMenuCode;

    Boolean superAdminAccessOnly;
    Boolean adminAccessOnly;
}