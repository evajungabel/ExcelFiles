package com.excel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excel.dto.ExcelFilesFilterRequestForm;
import com.excel.dto.ExcelFilesFilterRequestInfo;
import com.excel.service.ExcelSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/excelsearch")
@Slf4j
public class ExcelSearchController {

    private ExcelSearchService excelSearchService;

    @Autowired
    public ExcelSearchController(ExcelSearchService excelSearchService) {
        this.excelSearchService = excelSearchService;
    }

    @PostMapping()
    @Operation(summary = "Getting filtered, paginated and sorted list of excel files by anybody")
    @ApiResponse(responseCode = "200", description = "Paginated, sorted and filtered list of excel files is got by anybody.")
    public ResponseEntity<List<ExcelFilesFilterRequestInfo>> findByRequestedValues(
            @RequestParam("sortDir") String sortDir,
            @RequestParam("sort") String sort,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @Valid @RequestBody ExcelFilesFilterRequestForm excelFilesFilterRequestForm) {
        log.info("Http request, POST /api/excelsearch, with variables: " + page + size + sort + sortDir
                + excelFilesFilterRequestForm);
        List<ExcelFilesFilterRequestInfo> excelFilesFilterRequestInfos = excelSearchService.getExcelFilesRequests(page,
                size,
                sortDir, sort, excelFilesFilterRequestForm);
        log.info("POST data from repository from /api/excelsearch, with variable: " + page + size + sort
                + sortDir + excelFilesFilterRequestForm);
        return new ResponseEntity<>(excelFilesFilterRequestInfos, HttpStatus.OK);
    }
}
