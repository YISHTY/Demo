package com.example.demo.controller;

import com.example.demo.entity.ProcessResult;
import com.example.demo.service.RunCodeService;
import com.example.demo.utils.RunCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author  yzk
 * @since 2022/4/2 16:10
 * @version 1.0
 **/

@RestController
@RequestMapping(value = "/code")
@Api(tags = "代码编译")
@Slf4j
public class DemoController {

    @Resource
    private RunCodeService runCodeService;

    @Autowired
    private RunCode runCode;

    @CrossOrigin
    @PostMapping(value = "/runCode")
    @ApiImplicitParam(name = "code", value = "代码", required = true)
    @ApiOperation(value = "代码运行")
    public String runCode(@RequestParam(value = "code") String code, @RequestParam(value = "type") String type) throws Exception {
        ProcessResult processResult = runCode.runCode(type, code);
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(processResult.getFilePath() + "\\a.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String result;
        String output = null;
        while ((result = br.readLine()) != null) {
            output = result + "\n";
        }
        br.close();
        return output + "\n" + p.exitValue();
    }

    @CrossOrigin
    @PostMapping(value = "/test")
    @ApiImplicitParam(name = "code", value = "代码", required = true)
    @ApiOperation(value = "测试接口")
    public String test(@RequestParam(value = "code") String code) {
        runCodeService.runCLangCode(code);
        return code;
    }
}
