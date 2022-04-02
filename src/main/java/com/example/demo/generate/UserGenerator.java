package com.example.demo.generate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://124.223.197.249:3306/test?characterEncoding=utf-8&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8";
        List<String> tables = new ArrayList<>();
        tables.add("demo");

        FastAutoGenerator.create(url, "root", "root")
                .globalConfig(builder -> {
                    builder.author("yzk")               //作者
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();            //开启覆盖之前生成的文件

                })
                .packageConfig(builder -> builder.parent("com.example.demo")
                        .moduleName("practice")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("serviceImpl")
                        .controller("controller")
                        .mapper("mapper")
                        .xml("mapper")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper")))
                .strategyConfig(builder -> builder.addInclude(tables)
                        .addTablePrefix("p_")
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .entityBuilder()
                        .enableLombok()
                        .logicDeleteColumnName("deleted")
                        .enableTableFieldAnnotation()
                        .controllerBuilder()
                        .formatFileName("%sController")
                        .enableRestStyle()
                        .mapperBuilder()
                        .enableBaseResultMap()  //生成通用的resultMap
                        .superClass(BaseMapper.class)
                        .formatMapperFileName("%sMapper")
                        .enableMapperAnnotation()
                        .formatXmlFileName("%sMapper"))
                .templateEngine(new FreemarkerTemplateEngine())   // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

