package com.chcpc.swaggerspringboot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户实体类")
@Data
public class User {
    @ApiModelProperty(value = "uuid", example = "e4cd4a26570b4bbcbdc3b0e8a1e78bfc", notes = "uuid", required = true)
    private String uuid;

    @ApiModelProperty(value = "用户名(Models中使用value)", example = "邱斌雨", notes = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", example = "95533", notes = "密码内容", required = true)
    private String password;

    @ApiModelProperty(value = "描述", example = "这是描述", notes = "描述内容")
    private String description;
}
