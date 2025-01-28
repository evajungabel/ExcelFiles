package com.excel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/index")
    public String handleIndex() {
        return "index";
    }

    @GetMapping("/upload-excel-file")
    public String handleExcelUpload() {
        return "upload-excel-file";
    }

    @GetMapping("/search-excel-file")
    public String handleSearchExcel() {
        return "search-excel-file";
    }

}
