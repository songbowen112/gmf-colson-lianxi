package com.colson.intergration.common.biz.pkg;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class BaseDTO implements Serializable {
    private static final long serialVersionUID = 752625707505718769L;

    public BaseDTO() {
    }

    public String toString() {
        try {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        } catch (Exception var2) {
            var2.printStackTrace();
            return super.toString();
        }
    }
}
