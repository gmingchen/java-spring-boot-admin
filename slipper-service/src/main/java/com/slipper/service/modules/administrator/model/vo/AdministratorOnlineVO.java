package com.slipper.service.modules.administrator.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.service.modules.role.model.dto.RoleSimplifyDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 管理员 在线 信息
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class AdministratorOnlineVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别：0-女 1-男 2-未知
     */
    private Integer sex;
    /**
     * 是否是超管：0-否 1-是
     */
    private Integer supervisor;
    /**
     * 企业ID
     */
    @JsonProperty("enterprise_id")
    private Long enterpriseId;
    /**
     * 企业名称
     */
    @JsonProperty("enterprise_name")
    private String enterpriseName;
    /**
     * 登录时间
     */
    @JsonProperty("login_at")
    private Date loginAt;

}
