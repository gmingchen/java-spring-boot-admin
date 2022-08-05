package com.slipper.service.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 键-值 对象
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class KeyValueModel<K, V> {
    /**
     * 键值
     */
    @NotNull(message = "Key不能为空")
    private K key;
    /**
     * 值
     */
    @NotNull(message = "Value不能为空")
    private V value;
}
