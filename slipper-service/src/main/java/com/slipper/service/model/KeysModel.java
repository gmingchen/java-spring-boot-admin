package com.slipper.service.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 键值 数组
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class KeysModel<K> {
    /**
     * 键值
     */
    @NotNull(message = "Key不能为空")
    @Size(min = 1, message = "至少有一个Key")
    private List<K> keys;
}
