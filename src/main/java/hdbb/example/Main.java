package hdbb.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    //此数据所有类共享
    public static ArrayList<Data> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        List<List<String>> originalDataList = ExcelReader.getOriginalData();
        HashMap<String,String> curriculum_schedule = ExcelReader.getCurriculumSchedule();

        ProcessOriginalData.analyseOriginalData(originalDataList);


        /*//Test code
        for (Data object : arr) {
            object.show();
            System.out.println("####################");
        }*/

        ExportDataToFile.exportDataToFile(arr);
    }
}