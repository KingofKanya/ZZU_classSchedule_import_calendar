package org.example;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static List<List<String>> getOriginalData() throws IOException {
        String fileLocation = "D:\\教务处导出已修改.xlsx";
        FileInputStream file = new FileInputStream(fileLocation);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<List<String>> data = new ArrayList<>();
        for (int rowIndex = 8; rowIndex <= 18; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            List<String> rowData = new ArrayList<>();
            for (int colIndex = 0; colIndex <= 7; colIndex++) {
                Cell cell = row.getCell(colIndex);
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    case FORMULA:
                        rowData.add(cell.getCellFormula());
                        break;
                    default:
                        rowData.add("");
                }
            }
            data.add(rowData);
        }

        /*// 打印数据
        for (List<String> row : data) {
            System.out.println(row);
        }*/
        return data;
    }
}
