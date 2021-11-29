package com.slipper.service.modules.system.token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.system.token.entity.TokenEntity;

/**
 * Token
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface TokenService extends IService<TokenEntity> {

    TokenEntity createToken (Long adminId);

    void destroyToken (Long adminId);

    TokenEntity queryByToken (String token);

    TokenEntity queryByAdminId (Long adminId);



}

