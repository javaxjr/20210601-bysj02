package com.tjetc.controller;

import org.springframework.stereotype.Controller;

@Controller
public class UploadConfig {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
