package com.example.demo.utils;

import com.example.demo.config.Config;
import com.example.demo.entity.ProcessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class RunCode {
    private final Config config;

    private static String javaExec = null;


    private static AtomicLong nextLong = new AtomicLong(System.currentTimeMillis());

    @Autowired
    public RunCode(Config config) {
        this.config = config;
    }


    public ProcessResult runCode(String type, String code) throws IOException, InterruptedException {
        // 获取系统缓存文件的位置
//        String tmpDir = System.getProperty("java.io.tmpdir");
        String tmpDir = "temp";
        // 随机文件夹的名字
        File pwd = Paths.get(tmpDir, String.format("%016x", nextLong.incrementAndGet())).toFile();
        // 新建文件夹
        pwd.mkdirs();
        ProcessBuilder pb = null;
        switch (type) {
            case "C":
                try (Writer writer = new BufferedWriter(new FileWriter(new File(pwd, "Main.c"), Charset.defaultCharset()))) {
                    writer.write(code);
                }
                pb = new ProcessBuilder().command(config.getC()).directory(pwd);
                break;
            case "CPP":
                try (Writer writer = new BufferedWriter(new FileWriter(new File(pwd, "Main.cpp"), Charset.defaultCharset()))) {
                    writer.write(code);
                }
                pb = new ProcessBuilder().command(config.getCpp()).directory(pwd);
                break;
            case "JAVA":
                try (Writer writer = new BufferedWriter(new FileWriter(new File(pwd, "Main.java"), Charset.defaultCharset()))) {
                    writer.write(code);
                }
                String[] command = new String[]{getJavaExecutePath(), "-Dfile.encoding=" + Charset.defaultCharset(), "--source", "11", "--enable-preview", "Main.java"};
                pb = new ProcessBuilder().command(command).directory(pwd);
                break;
            case "PYTHON":
                try (Writer writer = new BufferedWriter(new FileWriter(new File(pwd, "Main.py"), Charset.defaultCharset()))) {
                    writer.write(code);
                }
                pb = new ProcessBuilder().command(config.getPython(), "Main.py").directory(pwd);
                break;
            default:
                break;
        }

        pb.redirectErrorStream(true);
        Process p = pb.start();
        System.out.println(p.getOutputStream());
        if (p.waitFor(5, TimeUnit.SECONDS)) {
            String result = null;
            try (InputStream input = p.getInputStream()) {
                result = readAsString(input, Charset.defaultCharset());
            }
            return new ProcessResult(p.exitValue(), result, pwd.getAbsolutePath());
        } else {
            System.err.println(String.format("Error: process %s timeout. destroy forcibly.", p.pid()));
            p.destroyForcibly();
            return new ProcessResult(p.exitValue(), "运行超时", null);
        }
    }

    private String getJavaExecutePath() {
        if (javaExec == null) {
            String javaHome = System.getProperty("java.home");
            String os = System.getProperty("os.name");
            boolean isWindows = os.toLowerCase().startsWith("windows");
            Path javaPath = Paths.get(javaHome, "bin", isWindows ? "java.exe" : "java");
            javaExec = javaPath.toString();
        }
        return javaExec;
    }

    public String readAsString(InputStream input, Charset charset) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[102400];
        for (; ; ) {
            int n = input.read(buffer);
            if (n == (-1)) {
                break;
            }
            output.write(buffer, 0, n);
        }
        return output.toString(charset);
    }
}
