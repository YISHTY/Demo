package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

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

