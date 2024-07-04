package ynu.edu.controller;

import org.springframework.web.bind.annotation.*;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId){
        CommonResult<User> commonResult = new CommonResult<>();

        Integer code = 200;
        String message = "success(11001)";
        try {
            User user = new User(userId,"大明","123");
            commonResult.setResult(user);
        }
        catch (Exception e){
            code = 500;
            message = "error";
        }
        commonResult.setMessage(message);
        commonResult.setCode(code);
        System.out.println("-----------------11001");
        return commonResult;
    }

    // 新增POST方法 - 添加用户
    @PostMapping("/addUser")
    public CommonResult<User> addUser(@RequestBody User user) {
        // 实现逻辑，此处为简化示例，直接返回成功信息
        CommonResult<User> commonResult = new CommonResult<>();
        commonResult.setCode(200);
        commonResult.setMessage("User added successfully.");
        commonResult.setResult(user);
        return commonResult;
    }

    // 新增PUT方法 - 更新用户信息
    @PutMapping("/updateUser/{userId}")
    public CommonResult<User> updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        // 实现逻辑
        CommonResult<User> commonResult = new CommonResult<>();
        commonResult.setCode(200);
        commonResult.setMessage("User updated successfully.");
        commonResult.setResult(user);
        return commonResult;
    }

    // 新增DELETE方法 - 删除用户
    @DeleteMapping("/deleteUser/{userId}")
    public CommonResult<String> deleteUser(@PathVariable("userId") Integer userId) {
        // 实现逻辑
        CommonResult<String> commonResult = new CommonResult<>();
        commonResult.setCode(200);
        commonResult.setMessage("User deleted successfully.");
        commonResult.setResult("Deleted userId: " + userId);
        return commonResult;
    }
}
