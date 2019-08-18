package us.luckylu.dev.client.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.luckylu.dev.client.service.UserService;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;
import us.luckylu.dev.common.util.ResponseUtil;

import javax.annotation.Resource;

/**
 * @author lu
 * @create 2019-03-18 11:43
 */
@Api(tags = "sample", description = "提供样本")
@Validated
@RestController
@RequestMapping("sample")
public class SampleController {

    @Resource
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Hello SpringBoot!--!";
    }

    @GetMapping("/lombok")
    public ResponseDto<String> lombok(){
        return ResponseUtil.getSuccessRsp("abc");
    }
}
