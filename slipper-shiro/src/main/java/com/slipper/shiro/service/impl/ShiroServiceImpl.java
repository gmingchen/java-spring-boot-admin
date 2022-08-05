package com.slipper.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.slipper.service.modules.administrator.dao.AdministratorDao;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.enterprise.dao.EnterpriseMenuDao;
import com.slipper.service.modules.token.dao.TokenDao;
import com.slipper.service.modules.token.entity.TokenEntity;
import com.slipper.shiro.service.ShiroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * shiro
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

    @Resource
    TokenDao tokenDao;
    @Resource
    AdministratorDao administratorDao;
    @Resource
    EnterpriseMenuDao enterpriseMenuDao;

    @Override
    public TokenEntity queryTokenByToken(String token) {
        LambdaQueryWrapper<TokenEntity> wrapper = new LambdaQueryWrapper<TokenEntity>()
                .eq(TokenEntity::getToken, token);
        return tokenDao.selectOne(wrapper);
    }

    @Override
    public AdministratorRoleDTO queryAdministratorById(Long id) {
        return administratorDao.queryById(id);
    }

    @Override
    public Set<String> queryPermissionByAdministrator(AdministratorRoleDTO administratorRoleDto) {
        List<String> list;
        if (administratorRoleDto.getSupervisor() == 1) {
            list = enterpriseMenuDao.queryPermissionByEnterpriseId(administratorRoleDto.getEnterpriseId());
        } else {
            list = enterpriseMenuDao.queryPermissionByAdministratorId(administratorRoleDto.getId());
        }
        return getPermissionSet(list);
    }

    /**
     * 权限list 转 set
     * @param permissionList
     * @return
     */
    private Set<String> getPermissionSet(List<String> permissionList) {
        Set<String> permissions = new HashSet<>();
        for (String p : permissionList) {
            if (StringUtils.isBlank(p)) {
                continue;
            }
            permissions.addAll(Arrays.asList(p.trim().split(";")));
        }
        return permissions;
    }
}