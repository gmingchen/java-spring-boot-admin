package com.slipper.service.modules.token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.role.entity.RoleEntity;
import com.slipper.service.modules.token.entity.TokenEntity;

/**
 * 用户凭证
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface TokenService extends IService<TokenEntity> {

    /**
     * 创建token
     * @param id 管理员ID
     * @param mode 存储位置：1-数据库 2-Redis
     * @return
     */
    TokenEntity createToken (Long id, Integer mode);

    /**
     * 销毁token
     * @param id 管理员ID
     * @param mode 存储位置：1-数据库 2-Redis
     * @return
     */
    void destroyToken (Long id, Integer mode);
}
