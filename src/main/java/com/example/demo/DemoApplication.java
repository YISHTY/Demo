package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @author  yzk
 * @since   2022/4/2 16:11
 * @version 1.0
**/

@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@Slf4j
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext application = new SpringApplicationBuilder().sources(DemoApplication.class)
                // SpringMvc环境上下文使用(NONE:非web环境,上下文使用;REACTIVE:SpringMebFlux环境,上下文使用)
                .web(WebApplicationType.SERVLET).headless(false).run(args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        log.info("\n\033[31m--------------------------------------------------------------------\n\t" +
                "Knife4j: \thttp://" + ip + ":" + port + contextPath + "doc.html" + "\n\t" +
                "Project Started Up Successfully" + "\n" +
                "--------------------------------------------------------------------");
    }

}
