package hdbb.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static ArrayList<Data> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<List<String>> originalDataList = ExcelReader.getOriginalData();
        ProcessOriginalData.analyseOriginalData(originalDataList);

        //Test code
        for (Data object : arr) {
            object.show();
            System.out.println("####################");
        }

        ExportDataToFile.exportDataToFile(arr);
    }
}