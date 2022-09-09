package org.khasanof.jpaspecification.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class BaseUtils {

    public static boolean isNumber(String var) {
        Assert.notNull(var);
        return (StringUtils.isNumeric(var));
    }

    public static String trimString(String var) {
        Assert.notNull(var);
        return var.substring(0, var.length() - 1);
    }

}
