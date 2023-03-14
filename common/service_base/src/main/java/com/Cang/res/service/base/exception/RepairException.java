package com.Cang.res.service.base.exception;

import com.Cang.res.common.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * @Date:2023/3/14 12:43
 * @Author:Cang
 */
@Data
public class RepairException extends RuntimeException{

    // 状态码
    private Integer code;

    /**
     * 接收状态码和消息
     * @param message
     * @param code
     */
    public RepairException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型
     * @param resultCodeEnum
     */
    public RepairException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "RepairException{" +
                "code=" + code +
                ",message=" + this.getMessage() +
                '}';
    }
}
