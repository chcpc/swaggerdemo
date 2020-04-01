package com.chcpc.swaggerspringboot.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Restful响应体", description = "Restful风格的响应内容，用于封装响应内容")
@Data
public class RestResult<T> {
    @ApiModelProperty(value = "响应码", example = "200", notes = "多种响应码")
    private Integer code;
    @ApiModelProperty(value = "响应消息", example = "success", notes = "可返回响应消息")
    private String message;
    @ApiModelProperty(value = "响应内容", example = "data", notes = "实际要响应的内容")
    private T data;

    public RestResult(){
        this.setCode(200);
        this.setMessage("success");
    }
}
