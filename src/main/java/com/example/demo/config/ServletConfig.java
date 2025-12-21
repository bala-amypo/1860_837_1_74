package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan(basePackages = "com.example.demo.servlet")
public class ServletConfig {
}
