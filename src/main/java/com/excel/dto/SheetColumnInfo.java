package com.excel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SheetColumnInfo {

    private Integer orderNumber;

    private String columnName;

    private String columnType;

}
