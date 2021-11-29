package com.slipper.service.modules.captcha.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("captcha")
public class CaptchaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * UUID
	 */
	@TableId(type = IdType.INPUT)
	private String uuid;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 到期时间
	 */
	@JsonProperty("expired_at")
	private Date expiredAt;

}
