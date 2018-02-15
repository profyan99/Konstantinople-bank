package com.konstantinoplebank.dao.mapper;

import com.konstantinoplebank.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

public interface RoleMapper {
    List<Role> findRoleByUserId(long id);

    void updateRole(@Param("roles") List<Role> roles, @Param("userid") long userid);

    void createRole(@Param("roles") List<Role> roles, @Param("userid") long userid);
}
