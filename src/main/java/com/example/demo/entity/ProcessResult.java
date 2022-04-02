package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * @author  yzk
 * @since   2022/4/2 16:11
 * @version 1.0
**/

@Getter
@Setter
public class ProcessResult {
    private int exitCode;

    private String output;

    private String filePath;

    public ProcessResult(int exitCode, String output, String filePath) {
        this.exitCode = exitCode;
        this.output = output;
        this.filePath = filePath;
    }
}

