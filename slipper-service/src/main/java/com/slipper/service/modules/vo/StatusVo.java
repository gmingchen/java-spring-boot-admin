package com.slipper.service.modules.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusVo<K, V> {
    @NotNull(message = "Key不能为空")
    private K key;
    @NotNull(message = "Value不能为空")
    private V value;
}
