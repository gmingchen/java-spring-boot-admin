package com.slipper.service.modules.system.token.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.utils.TokenUtils;
import com.slipper.service.modules.system.token.dao.TokenDao;
import com.slipper.service.modules.system.token.entity.TokenEntity;
import com.slipper.service.modules.system.token.service.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {

    @Resource
    private TokenUtils tokenUtils;

    @Override
    public TokenEntity createToken(Long adminId) {
        // 生成token
        String token = TokenUtils.generate();
        // 获取当前时间
        Date now = new Date();
        // 计算过期时间
        Date expireTime = new Date(now.getTime() + tokenUtils.getExpire() * 1000);
        // 判断是否有token记录
        TokenEntity tokenObj = this.getById(adminId);
        // 设置token信息
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setAdminId(adminId);
        tokenEntity.setToken(token);
        tokenEntity.setUpdatedAt(now);
        tokenEntity.setExpiredAt(expireTime);
        if (tokenObj == null) { // 新增token记录
            this.save(tokenEntity);
        } else { // 更新token记录
            this.updateById(tokenEntity);
        }
        return tokenEntity;
    }

    @Override
    public void destroyToken(Long adminId) {
        this.removeById(adminId);
    }

    @Override
    public TokenEntity queryByToken(String token) {
        return baseMapper.queryByToken(token);
    }

    @Override
    public TokenEntity queryByAdminId(Long adminId) {
        return baseMapper.queryByAdminId(adminId);
    }
}