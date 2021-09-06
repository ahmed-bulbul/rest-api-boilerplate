package com.restboilarplate.serviceImpl.system;

import com.restboilarplate.dto.System.SystemMenuAdminCoreUiDTO;
import com.restboilarplate.entity.system.SystemMenu;
import com.restboilarplate.repository.system.SystemMenuRepository;
import com.restboilarplate.service.generic.impl.ServiceGenericImpl;
import com.restboilarplate.service.system.SystemMenuService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemMenuServiceImpl extends ServiceGenericImpl<SystemMenu> implements SystemMenuService {

    @Autowired
    private SystemMenuRepository systemMenuRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SystemMenuAdminCoreUiDTO> getAdminCoreUiResponse() {
        return ((List<SystemMenu>) systemMenuRepository.findAll()).stream().map(this::convertToSystemMenuAdminCoreUiDto)
                .collect(Collectors.toList());
    }

    private SystemMenuAdminCoreUiDTO convertToSystemMenuAdminCoreUiDto(SystemMenu systemMenu) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        SystemMenuAdminCoreUiDTO systemMenuAdminCoreUiDTO = modelMapper.map(systemMenu, SystemMenuAdminCoreUiDTO.class);
        return systemMenuAdminCoreUiDTO;
    }

}
