package com.slipper.service.modules.system.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.utils.Constant;
import com.slipper.service.modules.system.admin.dao.AdminDao;
import com.slipper.service.modules.system.admin.dao.AdminRoleDao;
import com.slipper.service.modules.system.admin.service.AdminRoleService;
import com.slipper.service.modules.system.admin.service.AdminService;
import com.slipper.service.modules.system.admin.vo.EditAdminVo;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Common;
import com.slipper.common.utils.Query;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.system.role.dto.RoleDto;
import com.slipper.service.modules.system.admin.dto.AdminDto;
import com.slipper.service.modules.system.admin.entity.AdminEntity;
import com.slipper.service.modules.system.admin.entity.AdminRoleEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminEntity> implements AdminService {

    @Resource
    private AdminRoleService adminRoleService;
    @Resource
    private AdminRoleDao adminRoleDao;

    @Override
    public RPage<AdminDto> queryPage(Map<String, Object> params) {
        Page<AdminDto> page = new Query<AdminDto>().getPage(params);
        Long adminId = (long)params.get("adminId");
        String username = (String)params.get("username");
        String nickname = (String)params.get("nickname");
        boolean isSuper = Common.isSuper(adminId);
        RPage<AdminDto> rPage;
        if (isSuper) {
            rPage = new RPage<>(baseMapper.queryAllPage(page, username, nickname));
        } else {
            rPage = new RPage<>(baseMapper.queryByCreatorPage(page, adminId, username, nickname));
        }
        return rPage;
    }

    @Override
    public AdminDto queryById(Long id) {
        return baseMapper.queryById(id);
    }

    @Override
    public AdminDto queryByUsername(String username) {
        return baseMapper.queryByUsername(username);
    }

    @Override
    @Transactional
    public void create(AdminEntity adminEntity, AdminDto adminDto) {
        validatedRole(adminEntity, adminDto);

        QueryWrapper<AdminEntity> wrapper = new QueryWrapper<AdminEntity>()
                .eq("username", adminEntity.getUsername());
        int count = this.count(wrapper);
        if (count != 0) {
            throw new RunException("数据库中已存在该记录!");
        }

        adminEntity.setCreatedAt(new Date());
        // 加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = new Sha256Hash(adminEntity.getPassword(), salt).toHex();
        adminEntity.setSalt(salt);
        adminEntity.setPassword(password);

        this.save(adminEntity);

//        List<AdminRoleEntity> adminRoleEntities = getAdminRoles(adminEntity.getId(), adminEntity.getRoleIds());
//        for (AdminRoleEntity adminRoleEntity : adminRoleEntities) {
//            adminRoleDao.insert(adminRoleEntity);
//        }
        adminRoleService.createOrUpdate(adminEntity.getId(), adminEntity.getRoleIds());
    }

    @Override
    @Transactional
    public void update(AdminEntity adminEntity, AdminDto adminDto) {
        validatedRole(adminEntity, adminDto);

        adminEntity.setUpdatedAt(new Date());
        // 密码为空则不修改密码
        if(StringUtils.isBlank(adminEntity.getPassword())) {
            adminEntity.setSalt(null);
            adminEntity.setPassword(null);
        } else {
            String salt = RandomStringUtils.randomAlphanumeric(20);
            String password = new Sha256Hash(adminEntity.getPassword(), salt).toHex();
            adminEntity.setSalt(salt);
            adminEntity.setPassword(password);
        }

        this.updateById(adminEntity);

        List<AdminRoleEntity> adminRoleEntities = getAdminRoles(adminEntity.getId(), adminEntity.getRoleIds());
        for (AdminRoleEntity adminRoleEntity : adminRoleEntities) {
            adminRoleDao.insert(adminRoleEntity);
        }
//        adminRoleService.createOrUpdate(adminEntity.getId(), adminEntity.getRoleIds());
    }

    @Override
    public int selfUpdate(EditAdminVo editAdminVo) {
        AdminEntity adminEntity = new AdminEntity();
        int type = 0;

        if (StringUtils.isNotBlank(editAdminVo.getNewPassword())) {
            if (StringUtils.isBlank(editAdminVo.getOldPassword())) {
                throw new RunException(Constant.WARNING_CODE, "旧密码不能为空");
            } else {
                AdminEntity admin = this.getById(editAdminVo.getId());
                String oldPassword = new Sha256Hash(editAdminVo.getOldPassword(), admin.getSalt()).toHex();
                if (!oldPassword.equals(admin.getPassword())) {
                    throw new RunException(Constant.WARNING_CODE, "旧密码不正确");
                }
                String newPassword = new Sha256Hash(editAdminVo.getNewPassword(), admin.getSalt()).toHex();
                adminEntity.setPassword(newPassword);
                type = 1;
            }
        }
        adminEntity.setId(editAdminVo.getId());
        adminEntity.setNickname(editAdminVo.getNickname());
        adminEntity.setMobile(editAdminVo.getMobile());
        adminEntity.setEmail(editAdminVo.getEmail());
        adminEntity.setAvatar(editAdminVo.getAvatar());
        adminEntity.setUpdatedAt(new Date());
        adminEntity.setUpdater(editAdminVo.getId());
        this.updateById(adminEntity);
        return type;
    }

    /**
     * 检查是否越权
     * @param adminEntity 用户实体
     * @param adminDto 当前用户
     */
    private void validatedRole(AdminEntity adminEntity, AdminDto adminDto) {
        // 判断是否是超级管理员
        if (Common.isSuper(adminDto.getId())) {
            return;
        }
        // 获取用户拥有的角色ID
        List<Long> roleIds = new ArrayList<>();
        for(RoleDto role : adminDto.getRoles()) {
            roleIds.add(role.getId());
        }
        // 判断是否越权
        if (!roleIds.containsAll(adminEntity.getRoleIds())) {
            throw new RunException(Constant.WARNING_CODE, "该用户权限已超出你的权限!");
        }

    }

    /**
     * 获取管理员角色关联列表
     * @param adminId
     * @param roleIds
     * @return
     */
    private List<AdminRoleEntity> getAdminRoles(Long adminId, List<Long> roleIds) {
        QueryWrapper<AdminRoleEntity> wrapper = new QueryWrapper<AdminRoleEntity>()
                .eq("admin_id", adminId);
        adminRoleDao.delete(wrapper);

        List<AdminRoleEntity> adminRoleEntities = new ArrayList<>();

        if (roleIds.size() == 0) {
            return adminRoleEntities;
        }

        for (Long roleId : roleIds) {
            AdminRoleEntity adminRole = new AdminRoleEntity();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoleEntities.add(adminRole);
        }

        return adminRoleEntities;
    }

}