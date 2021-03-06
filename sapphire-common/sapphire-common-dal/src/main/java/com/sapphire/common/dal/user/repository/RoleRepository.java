package com.sapphire.common.dal.user.repository;

import com.sapphire.common.dal.user.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/18<br/>
 * Email: byp5303628@hotmail.com
 */
public interface RoleRepository extends Repository<Role, Long> {
    @Query("select r from Role as r where r.roleName = :name")
    Role getRoleByName(@Param("name") String name);
}
