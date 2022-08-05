package com.slipper.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author gumingchen
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class AuthToken implements AuthenticationToken {
    private String token;

    public AuthToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
