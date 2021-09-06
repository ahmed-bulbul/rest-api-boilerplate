package com.restboilarplate.controller.system;

import com.restboilarplate.controller.generic.ControllerGeneric;
import com.restboilarplate.dto.System.SystemMenuAdminCoreUiDTO;
import com.restboilarplate.entity.system.SystemMenu;

import java.util.List;

public interface SystemMenuController extends ControllerGeneric<SystemMenu> {

    public List<SystemMenuAdminCoreUiDTO> getSystemMenuAdminCoreUiResponse();
}
