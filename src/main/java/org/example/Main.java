package org.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;



public class Main {

    public static ArrayList arr = new ArrayList<Data>();

    public static void main(String[] args) throws IOException {
        List<List<String>> originalDataList = ExcelReader.getOriginalData();
        ProcessOriginalData.analyseOriginalData(originalDataList);
        int l=arr.size();

        //testcode
        for (int i=0;i<l;i++){
            ((Data)arr.get(i)).show();
            System.out.println("####################");
        }
        ExportDataToFile.exportDataToFile(arr);
    }

}