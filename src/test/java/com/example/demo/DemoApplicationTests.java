package com.example.demo;

import com.example.demo.utils.RunCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

}


@SpringBootTest
class RunApplicationTests {

    @Autowired
    private RunCode runCode;

    @Test
    void contextLoads() throws Exception {
        String code =
                "#include <stdio.h>\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "   printf(\"Hello, World! \\n\");\n" +
                "   \n" +
                "   return 0;\n" +
                "}";
        System.out.println(runCode.runCode("C", code).getOutput());
    }

}