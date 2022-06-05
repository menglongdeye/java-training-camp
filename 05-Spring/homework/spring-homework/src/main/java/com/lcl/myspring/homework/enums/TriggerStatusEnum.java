package com.lcl.myspring.homework.enums;

import com.lcl.myspring.homework.utils.ValueNameEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TriggerStatusEnum implements ValueNameEnum {
    SOP(0, "停止"),
    RUNNING(1, "运行");

    private int code;
    private String desc;


    @Override
    public int getValue() {
        return code;
    }
}
