package com.sapphire.service.user.impl;

import com.sapphire.constant.RoleNameConstant;
import com.sapphire.domain.Role;
import com.sapphire.repository.RoleRepository;
import com.sapphire.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/18<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
   @Autowired
   private RoleRepository roleRepository;

   public Role getUserRole() {
      return roleRepository.getRoleByName(RoleNameConstant.USER_ROLE_NAME);
   }

   public Role getAdminRole() {
      return roleRepository.getRoleByName(RoleNameConstant.ADMIN_ROLE_NAME);
   }

   public Role getGuestRole() {
      return roleRepository.getRoleByName(RoleNameConstant.GUEST_ROLE_NAME);
   }
}
