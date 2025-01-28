package com.excel.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.excel.domain.Excel;
import com.excel.domain.Sheet;
import com.excel.domain.SheetColumn;
import com.excel.domain.SheetRow;
import com.excel.repository.ExcelRepository;
import com.excel.repository.SheetColumnRepository;
import com.excel.repository.SheetRepository;
import com.excel.repository.SheetRowRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExcelUploadService {

    @Autowired
    private ExcelRepository excelRepository;

    @Autowired
    private SheetRepository sheetRepository;

    @Autowired
    private SheetColumnRepository sheetColumnRepository;

    @Autowired
    private SheetRowRepository sheetRowRepository;

    public void uploadExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);

            Excel excel = new Excel();
            excel.setFileName(file.getOriginalFilename());
            excel = excelRepository.save(excel);

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = new Sheet();
                org.apache.poi.ss.usermodel.Sheet poiSheet = workbook.getSheetAt(i);
                sheet.setSheetName(poiSheet.getSheetName());
                sheet.setExcel(excel);
                sheet = sheetRepository.save(sheet);

                Row headerRow = poiSheet.getRow(0);
                if (headerRow != null) {
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        Cell cell = headerRow.getCell(j);

                        SheetColumn sheetColumn = new SheetColumn();
                        sheetColumn.setOrderNumber(j + 1);
                        sheetColumn.setColumnName(cell.getStringCellValue());
                        sheetColumn.setColumnType("String");
                        sheetColumn.setSheet(sheet);
                        sheetColumn = sheetColumnRepository.save(sheetColumn);
                        
                        for (int k = 1; k <= poiSheet.getLastRowNum(); k++) {
                            Row dataRow = poiSheet.getRow(k);
                            if (dataRow != null) {
                                Cell dataCell = dataRow.getCell(j);
                                SheetRow sheetRow = new SheetRow();
                                sheetRow.setRowOrderNumber(k);
                                sheetRow.setSheetColumn(sheetColumn);
                                sheetRow.setValue(dataCell != null ? dataCell.toString() : null);
                                sheetRowRepository.save(sheetRow);
                            }
                        }
                    }
                }
            }

            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to process the uploaded Excel file", e);
        }
    }
}
