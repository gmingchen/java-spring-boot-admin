package com.slipper.service.modules.token.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.utils.TokenUtils;
import com.slipper.service.modules.role.dao.RoleDao;
import com.slipper.service.modules.role.entity.RoleEntity;
import com.slipper.service.modules.role.service.RoleService;
import com.slipper.service.modules.token.dao.TokenDao;
import com.slipper.service.modules.token.entity.TokenEntity;
import com.slipper.service.modules.token.service.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户凭证
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {

    @Resource
    private TokenUtils tokenUtils;

    @Override
    public TokenEntity createToken(Long id, Integer mode) {
        // 生成token
        String token = TokenUtils.generate();
        // 获取当前时间
        Date now = new Date();
        // 计算过期时间
        Date expire = new Date(now.getTime() + tokenUtils.getExpire() * 1000);
        // 设置token信息
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setAdministratorId(id);
        tokenEntity.setToken(token);
        tokenEntity.setExpiredAt(expire);
        tokenEntity.setCreatedAt(now);

        switch (mode) {
            case 1:
                if (this.getById(id) == null) {
                    this.save(tokenEntity);
                } else {
                    this.updateById(tokenEntity);
                }
                break;
            default:
        }

        return tokenEntity;
    }

    @Override
    public void destroyToken(Long id, Integer mode) {
        switch (mode) {
            case 1:
                this.removeById(id);
            default:
        }
    }
}
