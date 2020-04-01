package com.chcpc.swaggerspringboot.controller;

import com.chcpc.swaggerspringboot.model.User;
import com.chcpc.swaggerspringboot.model.vo.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(tags = "实例演示类")
@RestController
@RequestMapping("/instance")
public class InstanceController {

    private static List<User> userList = new ArrayList<>();
    static {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        user.setUsername("邱斌雨");
        user.setPassword("96632");
        user.setDescription("初始user");
        userList.add(user);
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表，无需传入参数")
    @GetMapping("/users")
    public RestResult getUserList(){
        RestResult result = new RestResult();
        result.setData(userList);
        return result;
    }

    @ApiOperation(value = "获取单个用户", notes = "获取单个用户，必需传入uuid")
    @GetMapping("/users/{uuid}")
    public RestResult getUser(
            @PathVariable("uuid") @ApiParam(value = "UUID", required = true)
                    String uuid){
        RestResult result = new RestResult();
        for(User user : userList){
            if(user.getUuid().equals(uuid)) {
                result.setData(user);
            }
        }
        return result;
    }

    @ApiOperation(value = "创建单个用户", notes = "创建单个用户，必需传入信息")
    @PostMapping("/users")
    public RestResult createUser(@RequestBody User user){
        RestResult result = new RestResult();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setUuid(uuid);
        userList.add(user);
        result.setData(user);
        return result;
    }

    @ApiOperation(value = "修改单个用户", notes = "修改单个用户，必需传入uuid等信息")
    @PutMapping("/users")
    public RestResult updateUser(@RequestBody User user){
        RestResult result = new RestResult();
        String uuid = user.getUuid();
        for(User u : userList){
            if(u.getUuid().equals(uuid)) {
                userList.remove(u);
                userList.add(user);
            }
        }
        return result;
    }

    @ApiOperation(value = "删除单个用户", notes = "获取单个用户，必需传入uuid")
    @DeleteMapping("/users/{uuid}")
    public RestResult deleteUser(
            @PathVariable("uuid") @ApiParam(value = "UUID", required = true)
                    String uuid){
        for(User user : userList){
            if(user.getUuid().equals(uuid)) {
                userList.remove(user);
                break;
            }
        }
        RestResult result = new RestResult();
        return result;
    }
}
