package hdbb.example;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelReader {
    public static String fileLocation = "D:\\教务处导出已修改.xlsx";

    /**
     *
     * @return 返回课程列表,使用Hashmap,key为课程序号,Value为课程名称
     */
    public static HashMap<String,String> getCurriculumSchedule() throws IOException {
        HashMap<String,String> curriculum_schedule = new HashMap<>();
        FileInputStream file = new FileInputStream(fileLocation);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (int rowIndex = 22; ; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row==null){
                break;
            }
            if(row.getCell(4).getCellType() == CellType.NUMERIC) {
                curriculum_schedule.put(String.valueOf(row.getCell(4).getNumericCellValue()), row.getCell(2).getStringCellValue());
            } else {
                curriculum_schedule.put(row.getCell(4).getStringCellValue(), row.getCell(2).getStringCellValue());
            }
        }
        return curriculum_schedule;
    }

    /**
     *
     * @return 获取课程表单元格的原始数据
     * @throws IOException
     */
    public static List<List<String>> getOriginalData() throws IOException {
        FileInputStream file = new FileInputStream(fileLocation);

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        List<List<String>> data = new ArrayList<>();

        for (int rowIndex = 8; rowIndex <= 18; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            List<String> rowData = new ArrayList<>();
            for (int colIndex = 0; colIndex <= 7; colIndex++) {
                Cell cell = row.getCell(colIndex);
                rowData.add(cell.getStringCellValue());
            }
            data.add(rowData);
        }
        return data;
    }
}
