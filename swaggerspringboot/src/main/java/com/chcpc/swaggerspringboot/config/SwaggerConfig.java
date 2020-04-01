package com.chcpc.swaggerspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Swagger配置类，只需要创建配置类并加上@Configuration和@EnableSwagger即可启用。
 * @author: 邱斌雨
 * @date: 2020/3/7
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 1. Swagger本身无需任何配置就可以使用
    // 默认访问页面为：${baseUrl}/swagger-ui.html
    // 默认json数据访问路径为：${baseUrl}/v2/api-docs

    // 2. 可以通过注册一个Bean实例Docket来配置Swagger的@Bean
    // 返回一个默认页面，直接返回一个Docket即可。
    // Docket类中，属性groupName(群组名)默认为"default"。属性ApiInfo也为常量DEFAULT，即页面默认内容。
    @Bean
    public Docket defaultDocket(){
        // 创建Docket时，必须要有DocumentationType(文档类型)，可选SWAGGER_2、SWAGGER_12、SPRING_WEB，默认为SWAGGER_2
        return new Docket(DocumentationType.SWAGGER_2);
    }


    // 3. 配置Swagger的页面基本信息—ApiInfo
    // 3.1 在Docket里配置apiInfo
    @Bean
    public Docket apiInfoConfig(){
        // 如果存在多个Docket实例，则每个实例都必须具有此方法提供的唯一groupName。默认为“默认”。
        return new Docket(DocumentationType.SWAGGER_2).groupName("1.Docket配置类—1）配置页面基本信息")
                // 配置api页面基本信息
                .apiInfo(new ApiInfoConfig().apiInfoConfig());
    }

    // 4. 配置Swagger的api选择器—apiSelector
    // 4.1 在Docket里配置ApiSelector(通过select()方法配置)，对要显示的api进行选择。
    // 注：可以注入多个Docket实例，注意方法名称需不同，并配置groupName("组名")进行分组
    @Bean
    public Docket apiSelectorConfig(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("1.Docket配置类—2）配置api选择")
                .apiInfo(new ApiInfoConfig().apiInfoWithApiSelectorConfig())
                // 同通过select方法构造ApiSelector实例 — 用于筛选要显示到页面的api
                // select方法有.path(路径选择)和.apis(路径过滤)两种方法进行选择
                .select()
                    // 4.1.1 apis()，RequestHandlerSelectors配置要扫描接口的方式，5种
                    .apis(RequestHandlerSelectors.basePackage("com.example.swaggerdemo.controller"))
                        // ① basePackage，指定扫描包，即basePackage("com.example.swaggerdemo.controller")
                        // ② any，扫描全部
                        // ③ none，不扫描
                        // ④ withClassAnnotation()，扫描类上的注解，参数是注解的反射对象，例如@RestController
                        // ⑤ withMethodAnnotation()，扫描方法上的注解，例如@GetMapping
                    // 4.1.2 paths()方法，PathSelectors的regex或者ant方法来过滤
                    .paths(PathSelectors.ant("/test/selected/**"))
                        // ① regex，正则表达式匹配
                        // ② ant，通配符匹配
                    // 4.1.3 最后通过build()方法构造出ApiSelector实例
                    .build();
    }

    // 5. 配置Swagger的在不同环境启用—enable
    // 只希望Swagger在生产环境中使用，在发布时不使用。
    // 添加入参Environment，获取spring中的环境
    @Bean
    public Docket enableEnvironmentConfig(Environment environment){

        // 5.1 设置要显示的Swagger环境，可以是字符串数组
        Profiles profiles = Profiles.of("dev","test");

        // 5.2 acceptsProfiles()获取项目环境，可以在docket里添加参数Environment，放入配置文件
        // 也可以是其他方法如getActiveProfiles()，getDefaultProfiles()
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2).groupName("1.Docket配置类—3）配置在不同环境中启用Swagger")
                .apiInfo(new ApiInfoConfig().enableEnvironmentConfig())
                // enable为是否打开swagger，默认为true，如果为false，则swagger不能在浏览器中访问
                .enable(flag);
    }


    // 6. 配置Swagger全局参数配置—统一添加参数、返回参数配置、接口忽略参数。
    @Bean
    public Docket globalParameterConfig(){
        // 6.1 我们可以对指定的api统一添加参数
        Parameter parameter = new ParameterBuilder()
                .name("X-Auth-Token")
                .description("用户登陆令牌")
                // 添加的参数类型，可以是header, cookie, body, query
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                // 是否强制要求
                .required(true)
                .build();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameter);

        // 6.2 我们可以统一指定返回结果时的消息内容
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到资源").build());
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("参数错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("没有认证").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("没有没有访问权限").build());
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("请求成功").build());

        return new Docket(DocumentationType.SWAGGER_2).groupName("1.Docket配置类—4）全局参数配置")
                .apiInfo(new ApiInfoConfig().globalParameterConfig())
                .select().paths(PathSelectors.ant("/test/globalParameterConfig")).build()
                // 设置api要添加的参数
                .globalOperationParameters(parameters)
                // 配置多种请求头的返回参数
                .globalResponseMessage(RequestMethod.GET,responseMessageList)
                .globalResponseMessage(RequestMethod.POST,responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE,responseMessageList)
                .globalResponseMessage(RequestMethod.PUT,responseMessageList)
                // 6.3 配置接口忽略参数
                // 即接口入参中哪些类型的参数不会显示到页面当中
                .ignoredParameterTypes(String.class)
                ;
    }

    // 7. 模型类配置—对实体类使用Swagger的注解
    @Bean
    public Docket controllerConfig(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("2.Controller类配置")
                .apiInfo(new ApiInfoConfig().controllerConfig())
                .select().paths(PathSelectors.ant("/test/controller/**")).build();
    }

    // 8. 模型类配置—对实体类使用Swagger的注解
    @Bean
    public Docket modelConfig(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("3.Model类配置")
                .apiInfo(new ApiInfoConfig().modelConfig())
                .select().paths(PathSelectors.ant("/test/apiModel")).build();
    }


    // 9. Swagger的Ui页面配置类—UiConfiguration
    @Bean
    public UiConfiguration uiConfiguration(){
        return UiConfigurationBuilder.builder()
                // 如果设置为true，则启用标记和操作的深度链接。
                .deepLinking(false)
                // 设置操作列表中operationId的显示。默认为false。
                .displayOperationId(false)
                // Models的默认展开深度(设置为-1完全隐藏模型)，默认为1。
                .defaultModelsExpandDepth(1)
                // Model的默认展开深度，默认为1。
                .defaultModelExpandDepth(1)
                // 在首次呈现API时如何显示模型，默认为EXAMPLE。
                // (用户总是可以通过点击“model”和“Example Value”链接来切换给定模型的渲染效果。)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                // 是否显示Try-It-Out请求的请求持续时间(以毫秒为单位)，默认不显示。
                .displayRequestDuration(true)
                // 控制操作和标记的显示方式，可以是“list”(仅展开标记)、“full”(展开标记和操作)或“none”(不展开任何内容)，默认不展开。
                .docExpansion(DocExpansion.LIST)
                // 如果设置，则启用筛选。顶部栏将显示一个编辑框，您可以使用它来过滤所显示的标记操作。可以是布尔值来启用或禁用，也可以是字符串，在这种情况下，将使用该字符串作为筛选器表达式启用筛选。筛选是区分大小写的，它与标记内任何地方的筛选器表达式相匹配。
                .filter(true)
                // 如果设置，则将显示的标记操作的数量限制为最多这么多。默认是显示所有操作。
                .maxDisplayedTags(10)
                // 对每个API的操作列表应用排序。它可以是'alpha'(按路径字母数字排序)、'method'(按HTTP方法排序)或一个函数(参见Array.prototype.sort()以了解排序函数的工作方式)。默认是服务器返回的订单没有改变。
                .operationsSorter(OperationsSorter.ALPHA)
                // 控制操作、参数和模式的供应商扩展(x-)字段和值的显示。
                .showExtensions(true)
                // 对每个API的标记列表应用排序。它可以是'alpha'(按路径按字母数字排序)，也可以是一个函数(参见Array.prototype.sort()以了解如何编写排序函数)。对于每一次传递，都将两个标记名称字符串传递给排序器。默认是Swagger-UI确定的顺序。
                .tagsSorter(TagsSorter.ALPHA)
                // 启用了Try it out特性的HTTP方法列表。空数组禁止对所有操作进行尝试。这并不会过滤显示中的操作。
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                // 启用了Try it out特性的HTTP方法列表。空数组禁止对所有操作进行尝试。在默认情况下，swagger - ui会尝试针对swagger验证配置。io的在线验证器。您可以使用此参数设置不同的验证器URL，例如用于本地部署的验证器(验证器徽章)。将其设置为null将禁用验证。此参数仅与Swagger 2.0规范相关。
                .validatorUrl(null)
                .build();
    }

    // 10. 实例演示
    @Bean
    public Docket instance(Environment environment){

        boolean flag = environment.acceptsProfiles(Profiles.of("dev","test"));

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("X-Auth-Token").description("用户登陆令牌").parameterType("header")
                .modelRef(new ModelRef("String")).required(true).build());

                List<ResponseMessage> responseMessageList = new ArrayList<>();
                responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到目标资源").build());
                responseMessageList.add(new ResponseMessageBuilder().code(400).message("参数错误").build());
                responseMessageList.add(new ResponseMessageBuilder().code(401).message("没有认证").build());
                responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
                responseMessageList.add(new ResponseMessageBuilder().code(403).message("没有没有访问权限").build());
                responseMessageList.add(new ResponseMessageBuilder().code(200).message("请求成功").build());

        return new Docket(DocumentationType.SWAGGER_2).groupName("实例演示")
                .apiInfo(new ApiInfoConfig().instance())
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.swaggerdemo.controller"))
                    .paths(PathSelectors.ant("/instance/**"))
                    .build()
                .enable(flag)
                .globalOperationParameters(parameters)

                .globalResponseMessage(RequestMethod.GET,responseMessageList)
                .globalResponseMessage(RequestMethod.POST,responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE,responseMessageList)
                .globalResponseMessage(RequestMethod.PUT,responseMessageList)

                .ignoredParameterTypes(HttpServletRequest.class)
                ;
    }

}