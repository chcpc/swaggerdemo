# Swagger2

学习目标：

- 了解Swagger的作用和概念
- 在SpringBoot中集成Swagger

## 前言

接口文档，对于前后端开发人员都是十分重要，尤其是这几年流行前后端分离，需要更加准确实时的接口文档。但接口文档辨编写和维护费时费力，经常来不及更新，，前端人员和后端人员无法做到“及时协商，尽早解决”，最终导致问题集中爆发。因此迫切需要一个获取准确实时的接口文档解决方案！

## 一、Swagger简介

**简介**

Swagger是一个规范和完整的框架，用于生成、描述、调用和可视化Restful风格的Web服务。总体目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。

**特点**

- 号称世界上最流行的Api框架
- Restful Api文档在线自动生成工具=>Api文档与API定义同步更新
- 直接允许，可以在线测试API接口；
- 支持多种语言：（Java，Php...）

**作用**

- 接口文档在线自动生成

- 功能测试

**组成**

- Swagger-tools：提供各种与Swagger进行集成与交互的工具。例如模式校验、Swagger1.2文档转换为Swagger2.0文档等功能。

- Swagger-editor : 一个用于编辑API文档的工具

- Swagger-core：用户java/Scala的Swagger实现。与JAX-RS(Jersey、Resteasy、CXF...)、Servlets和Play框架进行集成

- Swagger-js：用于javaScript和Swagger实现。

- Swagger-node-express：Swagger模块，用于node.js的Express web应用框架。

- Swagger-ui：一个无依赖的html、js、css集合，可以为Swagger兼容API动态生成优雅文档

- Swagger-codegen：一个模板引擎，通过分析用户Swagger资源声明以各种语言生产客户端代码

## 二、在SpringBoot中集成Swagger2

在SpringBoot中集成Swagger2较为方便，只需要添加依赖，并添加配置类即可启用Swagger

### 1、添加依赖

- springfox-swagger2
- springfox-swagger-ui

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

### 2、添加配置类

定义一个SwaggerConfig类，只需要添加配置类注解@Configuration，以及Swagger的@EnableSwagger2即可启用。

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
}
```

### 3、添加一些控制器类

### 4、测试运行

- 打开http://localhost:8080/swagger-ui.html 查看api文档界面
- 打开http://localhost:8080/v2/api-docs 查看json数据

## 三、配置Swagger

Swagger可配置以下四个部分内容

### 1、Docket类配置

在SwaggerConfig类中进行配置，需要配置Swagger的bean实例Docket，以下为常用配置。

- DocumentationType，文档类型，默认SWAGGER_2

- groupName，分组名，默认default
- apiInfo，配置api页面信息
- select，选择器，用于筛选api
- enable，是否启用swagger
- globalOperationParameters，全局接口参数配置
- globalResponseMessage，全局响应message配置
- ignoredParameterTypes，要忽略的参数类型

### 2、Controller类配置

- @Api：用于类，标识这个类是swagger的资源
  - tags：标签名称，可以有多个值，可生成多个副本。
  - value：该controller简短的标题，当没有tags时启用
  - produce：说明该controller是使用什么显示层格式的
  - protocols：使用什么协议
- @ApiOperation：写在方法上，对方法进行配置
  - value：该方法简短的介绍
  - note：详细介绍
  - code：正常情况下返回的状态码是多少
  - httpMethod：使用什么http方法
  - consumes：传来的数据应该是什么格式，例如"application/json"或"application/xml"
  - produce：输出的数据应该是什么格式，例如"application/json"或"application/xml"
  - tags：类似@Api中的tags，可用于重新分组。
-  @ApiImplicitParams：多个请求参数
-  @ApiImplicitParam：一个请求参数
- @ApiParam 单个参数描述
  - name：参数名
  - value：参数名对应的值
  - dataType：参数的类型
  - required：该参数是否必填
  - paramType：该参数的来源类型，它的值有如下：
    - query：该参数从地址栏问号后面的参数获取
    - form：该参数从form表单中获取
    - path：该参数从URL地址上获取
    - body：该参数从请求体中获取
    - header：该参数从请求头获取
- @ApiResponses：HTTP响应整体的描述
- @ApiResponse：HTTP响应中某一种响应的描述
  - code：数字，例如400
  - message：信息，例如"请求参数没填好"
  - response：抛出的异常类
- @ApiIgnore：用于方法或参数上，表示这个对象被忽略

### 3、模型类配置

Swagger可以使用注解来配置实体类

- @ApiModel：描述一个Model的信息，特别是作为Post请求参数，无法使用@ApiImplicitParam注解进行描述的时候
  - value：类名称
  - description：描述
- @ApiModelProperty
  - value：属性名称
  - name：重写属性名称，这会改变实际前后端交互时的名称
  - dataType：重写属性类型，同样会改变实际交互时的熟悉，使用类全名
  - required：是否必填
  - example：举例
  - hidden：是否隐藏

### 4、Ui配置

同样是个Bean，需要注入到spring容器中

- deepLinking：启用标记和操作的深度链接
- displayOperationId：设置操作列表中operationId的显示。默认为false。
- defaultModelsExpandDepth：Models的默认展开深度(设置为-1完全隐藏模型)，默认为1
- defaultModelExpandDepth：Model的默认展开深度，默认为1。
- defaultModelRendering：在首次呈现API时如何显示模型，默认为EXAMPLE。
- displayRequestDuration：是否显示Try-It-Out请求的请求持续时间(以毫秒为单位)
- docExpansion：控制操作和标记的显示方式，可以是“list”(仅展开标记)、“full”(展开标记和操作)或“none”(不展开任何内容)，默认不展开。
- filter：启用筛选，顶部栏将显示一个编辑框，您可以使用它来过滤所显示的标记操作。
- maxDisplayedTags：显示的标记操作的数量限制为最多这么多
- operationsSorter：对每个API的操作列表应用排序方式
- showExtensions：控制操作、参数和模式的供应商扩展(x-)字段和值的显示。
- tagsSorter：对每个API的标记列表应用排序方式
- supportedSubmitMethods：启用了Try it out特性的HTTP方法列表，这并不会过滤显示中的操作。
- validatorUrl：启用了Try it out特性的HTTP方法列表，在默认情况下，swagger - ui会尝试针对swagger验证配置

## 四、整合Spring Security

## 五、整合Validator

## 六、在SpringCloud中整合Swagger2

## 七、可搭配使用的其他开源项目

## 总结

**Swagger的用途**

1. 我们可以通过Swagger给一些难理解的熟悉或者接口，增加注释信息
2. 接口文档实时更新
3. 可以在线测试

**优点**：简单 , 实时 , 可测试 , 容易管理
**缺点**：代码侵入性太强, 影响正常代码阅读