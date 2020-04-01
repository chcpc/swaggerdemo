package com.chcpc.swaggerspringboot.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;

import java.util.ArrayList;

/**
 * @description: apiInfo配置类，集中配置于此
 * @author: 邱斌雨
 * @date: 2020/3/7
 */
public class ApiInfoConfig {
    // 配置作者信息，内容将会被填充到基本信息中
    private static Contact CONTACT = new Contact("邱斌雨", "http://www.baidu.com", "18689035598@qq.com");

    // 3.2 swagger页面的基本信息配置—apiInfo
    public ApiInfo apiInfoConfig() {
        // 这里也可以选择直接new一个ApiInfo
        return new ApiInfoBuilder()
                .title("分组1，通过ApiInfo去配置页面基本信息")
                .description("描述。该页面通过返回Docket的Bean实例，并配置其中的apiInfo属性，实现页面基本信息的配置")
                .version("版本号，例：1.0")
                .termsOfServiceUrl("服务协议的url")
                .license("许可，例：Apache 2.0")
                .licenseUrl("许可连接，例：http://www.baidu.com")
                // 作者信息
                .contact(CONTACT)
                .build();
    }

    public ApiInfo apiInfoWithApiSelectorConfig() {
        return new ApiInfo(
                "分组2，通过ApiSelector去选择要显示的api",
                "配置Docket中的ApiSelector属性，通过select()方法构造，实现页面基本信息的配置，当前页面只筛选出了controller包下的所有访问路径为/selected/**的接口",
                "1.0",
                "组织url",
                CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

    public ApiInfo enableEnvironmentConfig() {
        return new ApiInfo(
                "分组3，配置在不同环境中启用Swagger",
                "配置Docket中的enable属性，并使用Spring中的Environment和Profiles实现在不同环境中启用Swagger",
                "1.0",
                "组织url",
                CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

    public ApiInfo globalParameterConfig() {
        return new ApiInfo(
                "分组4，全局参数配置",
                "配置Docket中的全局参数，包括统一添加参数、返回参数配置、接口忽略参数。",
                "1.0",
                "组织url",
                CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

    public ApiInfo controllerConfig() {
        return new ApiInfo(
                "分组5，Controller类配置",
                "在实体类中添加Swagger注解。",
                "1.0",
                "组织url",
                CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

    public ApiInfo modelConfig() {
        return new ApiInfo(
                "分组6，Model类配置",
                "在实体类中添加Swagger注解。",
                "1.0",
                "组织url",
                CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

    public ApiInfo instance() {
        return new ApiInfo(
                "实例演示",
                "实例演示",
                "1.0",
                "组织url",
                CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }
}
