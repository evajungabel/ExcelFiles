package com.excel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.excel.domain.Excel;
import com.excel.dto.ExcelFilesFilterRequestForm;
import com.excel.dto.ExcelFilesFilterRequestInfo;
import com.excel.dto.SheetInfo;
import com.excel.exception.NoResourceFoundException;
import com.excel.repository.ExcelRepository;
import com.excel.specifications.ExcelSpecifications;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExcelSearchService {

    public ExcelRepository excelRepository;

    private ModelMapper modelMapper;

    @Autowired
    public ExcelSearchService(ExcelRepository excelRepository, ModelMapper modelMapper) {
        this.excelRepository = excelRepository;
        this.modelMapper = modelMapper;
    }

    public List<ExcelFilesFilterRequestInfo> getExcelFilesRequests(int page, int size, String sortDir, String sort,
            ExcelFilesFilterRequestForm excelFilesFilterRequestForm) {
        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Specification<Excel> spec = Specification.where(null);

        if (excelFilesFilterRequestForm.getFileName() != null) {
            spec = spec.and(ExcelSpecifications.hasFileName(excelFilesFilterRequestForm.getFileName()));
        }

        // if (excelFilesFilterRequestForm.getSheetForm() != null
        // && excelFilesFilterRequestForm.getSheetForm().getSheetName() != null) {
        // spec = spec.and(ExcelSpecifications
        // .hasSheetName(excelFilesFilterRequestForm.getSheetForm().getSheetName()));
        // }

        // if (excelFilesFilterRequestForm.getSheetColumnForm() != null
        // && excelFilesFilterRequestForm.getSheetColumnForm().getColumnName() != null)
        // {
        // spec = spec.and(ExcelSpecifications
        // .hasSheetColumnName(excelFilesFilterRequestForm.getSheetColumnForm().getColumnName()));
        // }

        Page<Excel> matchingExcels = excelRepository.findAll(spec, pageReq);
        if (page > matchingExcels.getTotalPages()) {
            throw new NoResourceFoundException(matchingExcels.getTotalPages());
        }

        return matchingExcels.getContent().stream()
                .map(excel -> {
                    ExcelFilesFilterRequestInfo excelFilesFilterRequestInfo = modelMapper.map(excel,
                            ExcelFilesFilterRequestInfo.class);
                    if (excel.getSheets() != null) {
                        SheetInfo sheetInfo = modelMapper.map(excel.getSheets(), SheetInfo.class);
                        excelFilesFilterRequestInfo.setSheetInfo(sheetInfo);
                        System.out.println(sheetInfo.toString());
                    }
                    return excelFilesFilterRequestInfo;
                })
                .collect(Collectors.toList());
    }

}