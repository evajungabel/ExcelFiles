package com.excel.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.excel.service.ExcelUploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/excelupload")
@Slf4j
public class ExcelUploadController {

    private ExcelUploadService excelUploadService;

    @Autowired
    public ExcelUploadController(ExcelUploadService excelUploadService) {
        this.excelUploadService = excelUploadService;
    }

    @PostMapping()
    @Operation(summary = "Saving excel files by anybody")
    @ApiResponse(responseCode = "200", description = "Saved excel files are given by anybody.")
    public ResponseEntity<Map<String, String>> uploadExcel(@RequestParam("file") MultipartFile file) {
        log.info("Http request, POST /api/excelupload, with variables: " + file);
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "File is empty"));
        }

        try {
            excelUploadService.uploadExcel(file);
            log.info("POST data from repository from /api/excelupload, with variable: " + file);
            return ResponseEntity.ok(Collections.singletonMap("message", "File uploaded successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Failed to upload file: " + e.getMessage()));
        }
    }
}
