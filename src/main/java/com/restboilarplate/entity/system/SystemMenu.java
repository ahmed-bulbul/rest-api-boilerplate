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
    String code;
    String description;

    String name;
    String url;
    String requestUrl;
    Integer sequence;
    String icon;
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

//    @OneToMany(mappedBy ="parentMenu")
//    public List<SystemMenu> children = new ArrayList<>();

    String parentMenuCode;

    Boolean superAdminAccessOnly;
    Boolean adminAccessOnly;

    public SystemMenu(String code, String description, String openUrl, String iconHtml, Boolean isActive, Integer sequence) {
        this.code = code;
        this.description = description;
        this.url = openUrl;
        this.icon = iconHtml;
        this.isActive = isActive;
        this.sequence = sequence;
//        this.parentMenu = parentMenu;
    }
}