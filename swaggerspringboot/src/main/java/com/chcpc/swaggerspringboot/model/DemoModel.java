package com.chcpc.swaggerspringboot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 对象模型描述:
 *  @ApiModel/@ApiModelProperty
 *  @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 *  value：名称
 *  description：描述
 *  @ApiModelProperty：描述一个model的属性
 *  value：名称
 *  name：重写属性名称
 *  dataType：重写属性类型
 *  required：是否必填
 *  example：举例
 *  hidden：是否隐藏
 */
@ApiModel(value = "样例实体类", description = "样例实体类描述")
@Data
public class DemoModel {
    @ApiModelProperty(name = "名称(接口参数中使用name)", value = "名称(Models中使用value)", example = "模型1", notes = "说明")
    private String name;

    @ApiModelProperty(name = "描述(接口参数中使用name)", value = "描述(Models中使用value)", example = "用于演示", notes = "说明", required = true)
    private String description;
}
