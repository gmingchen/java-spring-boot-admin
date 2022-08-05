package com.slipper.service.modules.file.model.form;

import lombok.Data;

import java.io.Serializable;
import com.slipper.service.model.PageDateModel;

/**
 * 文件
 * 
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2022-06-21 05:29:36
 */
@Data
public class FilePageDateForm extends PageDateModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 存储类型
	 */
	private Integer type;
}
