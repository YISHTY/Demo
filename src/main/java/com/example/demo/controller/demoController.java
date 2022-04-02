package com.example.demo.controller;

import com.example.demo.entity.ProcessResult;
import com.example.demo.utils.RunCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping(value = "/code")
@Api(tags = "代码编译")
@Slf4j
public class demoController {

    @Autowired
    private RunCode runCode;

    @PostMapping(value = "/runCode")
    @ApiImplicitParam(name = "code",value = "代码",required = true)
    @ApiOperation(value = "测试接口")
    public String payType(@RequestParam(value = "code") String code) throws Exception {
        ProcessResult processResult = runCode.runCode("C", code);
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

}
