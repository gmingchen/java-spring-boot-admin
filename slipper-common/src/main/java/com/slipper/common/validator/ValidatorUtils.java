package com.slipper.common.validator;

import com.slipper.common.config.ValidatorConfig;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = ValidatorConfig.getValidator();
    }

    /**
     * 功能描述:校验注解参数
     */
    public static <T> void validated(T object, Class<?>... groups) throws RunException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder message = new StringBuilder();
            message.append(Constant.VERIFICATION_ERROR_MESSAGE);
            String comma = "";
            for (ConstraintViolation<Object> constraint:  constraintViolations) {
                message.append(comma).append(constraint.getPropertyPath()+ "-" + constraint.getMessage());
                comma = ",";
            }
            throw new RunException(Constant.VERIFICATION_ERROR_CODE, message.toString());
        }
    }
}
