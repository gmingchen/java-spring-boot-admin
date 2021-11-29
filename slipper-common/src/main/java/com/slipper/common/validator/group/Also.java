package com.slipper.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 校验顺序 并且关系
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@GroupSequence({Create.class, Update.class})
public interface Also {
}
