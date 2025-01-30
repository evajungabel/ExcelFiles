package com.excel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExcelFilesFilterRequestForm {

    private String fileName;

    // private SheetForm sheetForm;

    // private SheetColumnForm sheetColumnForm;

    // private SheetRowForm sheetRowForm;

}
