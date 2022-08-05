package com.slipper.service.modules.token.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户凭证
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@TableName("token")
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("administrator_id")
    @TableId(value = "administrator_id", type = IdType.INPUT)
    private Long administratorId;
    /**
     * 凭证
     */
    private String token;
    /**
     * 到期时间
     */
    @JsonProperty("expired_at")
    private Date expiredAt;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;
}
