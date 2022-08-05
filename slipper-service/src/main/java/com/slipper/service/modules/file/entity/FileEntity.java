package com.slipper.service.modules.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件
 * 
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2022-06-21 05:29:36
 */
@Data
@TableName("file")
public class FileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 * 原始名称
	 */
	private String original;
	/**
	 * 实际名称
	 */
	private String actual;
	/**
	 * 扩展名称
	 */
	private String extension;
	/**
	 * 大小
	 */
	private Long size;
	/**
	 * 物理路径
	 */
	private String path;
	/**
	 * 虚拟路径
	 */
	private String url;
	/**
	 * 前缀
	 */
	private String prefix;
	/**
	 * 存储类型
	 */
	private Integer type;
	/**
	 * 上传用户ID
	 */
	private Long creator;
	/**
	 * 上传时间
	 */
	@JsonProperty("created_at")
	private Date createdAt;
	/**
	 * 是否删除：0-否 1-是
	 */
	@TableLogic
	private Integer deleted;
}
