package com.slipper.service.modules.file.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
public class FileVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
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
	 * 上传时间
	 */
	@JsonProperty("created_at")
	private Date createdAt;
}
