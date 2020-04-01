package com.chcpc.swaggerspringboot.controller;

import com.chcpc.swaggerspringboot.model.DemoModel;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Api：修饰整个类，描述Controller的作用
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 * @ApiImplicitParams：多个请求参数
 * @ApiImplicitParam：一个请求参数
 * @ApiResponses：HTTP响应整体描述
 * @ApiResponse：HTTP响应其中1个描述
 * @ApiModel：用对象来接收参数
 * @ApiModelProperty：用对象接收参数时，描述对象的一个字段
 * @ApiIgnore：使用该注解忽略这个API
 *
 * @author: 邱斌雨
 * @date: 2020/3/8
 */

/*
 1. @Api用于类，标识这个类是swagger的资源
    1）tags：标签名称，可以有多个值，可生成多个副本。
    2）value：该controller简短的标题，当没有tags时启用
    3）produce：说明该controller是使用什么显示层格式的
    4）protocols：使用什么协议
 */
@Api(tags = {"测试Controller"}, value = "曾承载资源的API声明，现已不使用转而使用tags")
@RestController
@RequestMapping("/test")
public class TestController {
    @ApiOperation(value = "配置类—2.被选中的api")
    @GetMapping("/selected/first")
    public String first() {
        return "first";
    }

    @ApiOperation(value = "配置类—2.被选中的api")
    @GetMapping("/selected/second")
    public String second() {
        return "second";
    }

    @ApiOperation(value = "配置类—1.全局参数配置样例",
            notes = " 1）该接口需要header中有token\n 2）该类下的返回消息以统一配置\n 3）入参中的String类参数被设置忽略")
    @GetMapping("/globalParameterConfig")
    public String globalParameterConfig(String str) {
        return "返回的String内容："+str;
    }

    /*---------------------------------------------------------------------------*/

    /* 2. @ApiOperation注解的使用：贴在方法上，主要用下面的这几个属性
        1）value：该方法简短的介绍
        2）note：详细介绍
        3）code：正常情况下返回的状态码是多少
        4）httpMethod：使用什么http方法
        5）response：响应什么对象，这里写的是响应的对象的字节码
        6）consumes：传来的数据应该是什么格式，例如"application/json"或"application/xml"
        7）produce：输出的数据应该是什么格式，例如"application/json"或"application/xml"
        8）tags：类似@Api中的tags，可用于重新分组。
     */
    @ApiOperation(value = "接口—2.@ApiOperation注解的使用，贴在方法上。",
            notes = " 1）value：该方法简短的介绍\n 2）note：详细介绍\n 3）code：正常情况下返回的状态码是多少\n 4）httpMethod：使用什么http方法\n 5）response：响应什么对象，这里写的是响应的对象的字节码\n 6）consumes：传来的数据应该是什么格式，例如application/json或application/xml\n 7）produce：输出的数据应该是什么格式，例如application/json或application/xml\n 8）tags：类似@Api中的tags，可用于重新分组。",
            code = 200,
            consumes = "application/json",
            produces = "application/json"
    )
    @PostMapping(value = "/controller/apiOperation")
    public String apiOperation(@RequestParam String str) {
        return str;
    }

    /* 3. 参数的描述
       3.1 @ApiImplicitParams 多个请求参数
       3.1 @ApiImplicitParam 一个请求参数
       3.1 @ApiParam 单个参数描述
       1）name:参数名
       2）value:参数名对应的值
       3）dataType：参数的类型
       4）require：该参数是否必填
       5）paramType：该参数的来源类型，它的值有如下：
        query：该参数从地址栏问号后面的参数获取
        form：该参数从form表单中获取
        path：该参数从URL地址上获取
        body：该参数从请求体中获取
        header：该参数从请求头获取
     */
    @ApiOperation("接口—3.api参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str1", value = "字段说明", dataType = "String", paramType = "query", defaultValue = "默认值1", example = "样例值1")})
    @GetMapping(value = "/controller/apiParam")
    public String apiParam(
            String str1,
            @ApiParam(name = "可修改显示名称", value = "字段说明", defaultValue = "默认值2", example = "样例值2",required = true)
            @RequestParam("可修改显示名称")
                    String str2) {
        return str1+str2;
    }

    /* 4. 响应的描述:
       @ApiResponses：HTTP响应整体的描述
       @ApiResponse：HTTP响应中某一种响应的描述
       用在@ApiResponses中，一般用于表达一个错误的响应信息(200相应不写在这里面)
       code：数字，例如400
       message：信息，例如"请求参数没填好"
       response：抛出的异常类
     */
    @ApiOperation("接口—4.api响应配置")
    @ApiResponses({
            @ApiResponse(code = 500, message = "编辑后的500响应的内容描述")})
    @GetMapping(value = "/controller/apiResponse")
    public void apiResponse(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }
    // 5. 忽略接口API: @ApiIgnore
    @ApiIgnore
    @GetMapping(value = "/controller/apiIgnore")
    public void apiIgnore() {}

    /*---------------------------------------------------------------------------*/

    /* 6. 对象模型描述:
       @ApiModel/@ApiModelProperty
       @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
       @ApiModelProperty：描述一个model的属性
     */
    @ApiOperation(value = "模型—1.api模型配置",notes = "@ApiModel/@ApiModelProperty\n" +
            "       @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）\n" +
            "       @ApiModelProperty：描述一个model的属性")
    @PostMapping(value = "/apiModel")
    public DemoModel apiModel(@RequestBody DemoModel demoModel) {
        return demoModel;
    }
}
