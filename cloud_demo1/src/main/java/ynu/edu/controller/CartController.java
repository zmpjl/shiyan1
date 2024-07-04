package ynu.edu.controller;

import org.springframework.web.client.RestTemplate;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/getCartById/{userId}")
    public CommonResult<User> getCartById(@PathVariable("userId") Integer userId){

        //通过服务提供者名称来获取Eureka server上的服务列表
        CommonResult<User> result = restTemplate.getForObject(
                "http://provide-serve/user/getUserById/"+userId.toString(),
                CommonResult.class);
        return result;
    }
    // 新增POST方法调用
    @PostMapping("/createCartForUser")
    public CommonResult<User> createCartForUser(@RequestBody User user) {
        CommonResult<User> result = restTemplate.postForObject(
                "http://provide-serve/user/addUser",
                user,
                CommonResult.class);
        return result;
    }

    // 新增PUT方法调用
    @PutMapping("/updateCart/{userId}")
    public CommonResult<User> updateCart(@PathVariable("userId") Integer userId, @RequestBody User user) {
        String url = "http://provide-serve/user/updateUser/{userId}";

        // Create the request entity with the user object and headers if needed
        HttpEntity<User> requestEntity = new HttpEntity<>(user);

        // Send the PUT request
        ResponseEntity<CommonResult> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                CommonResult.class,
                userId);

        // Extract and return the response body
        return responseEntity.getBody();
    }

    // 新增DELETE方法调用
    @DeleteMapping("/removeCart/{userId}")
    public CommonResult<String> removeCart(@PathVariable("userId") Integer userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CommonResult<String>> response = restTemplate.exchange(
                "http://provide-serve/user/deleteUser/{userId}",
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<CommonResult<String>>() {},
                userId
        );

        return response.getBody();
    }

}
