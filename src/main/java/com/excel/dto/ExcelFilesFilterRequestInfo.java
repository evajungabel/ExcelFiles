package com.excel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExcelFilesFilterRequestInfo {

    private String fileName;

    private SheetInfo sheetInfo;

    private SheetColumnInfo sheetColumnInfo;

    private SheetRowInfo sheetRowInfo;

}
