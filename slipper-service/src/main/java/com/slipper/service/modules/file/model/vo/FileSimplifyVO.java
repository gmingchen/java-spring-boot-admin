package com.slipper.service.modules.file.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件
 * 
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2022-06-21 05:29:36
 */
@Data
public class FileSimplifyVO implements Serializable {
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
	 * 虚拟路径
	 */
	private String url;
}
