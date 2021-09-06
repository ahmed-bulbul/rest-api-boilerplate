package com.restboilarplate.service.system;

import com.restboilarplate.dto.System.SystemMenuAdminCoreUiDTO;
import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.service.generic.ServiceGeneric;

import java.util.List;

public interface SystemMenuService extends ServiceGeneric<SystemMenu> {


    List<SystemMenuAdminCoreUiDTO> getAdminCoreUiResponse();
}
