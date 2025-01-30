package com.excel.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.excel.domain.Excel;
import com.excel.domain.Sheet;
import com.excel.domain.SheetColumn;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public final class ExcelSpecifications {

    public static Specification<Excel> hasFileName(String fileName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fileName"), fileName);
    }

    // public static Specification<Excel> hasSheetName(String sheetName) {
    // return (root, query, criteriaBuilder) -> {
    // Join<Excel, Sheet> sheetJoin = root.join("sheet", JoinType.INNER);
    // return criteriaBuilder.equal(sheetJoin.get("sheetName"), sheetName);
    // };
    // }

    // public static Specification<Excel> hasSheetColumnName(String sheetColumnName)
    // {
    // return (root, query, criteriaBuilder) -> {
    // Join<Excel, SheetColumn> sheetColumnNameJoin = root.join("cloumnName",
    // JoinType.INNER);
    // return criteriaBuilder.equal(sheetColumnNameJoin.get("columnName"),
    // sheetColumnName);
    // };
    // }

}
