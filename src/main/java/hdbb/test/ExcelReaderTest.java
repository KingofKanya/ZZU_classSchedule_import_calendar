package hdbb.test;

import hdbb.example.ExcelReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ExcelReaderTest {
    /**
     * 测试getOriginalData方法
     */
    @Test
    public void testAdd() throws IOException {
        List<List<String>> originalDataList = ExcelReader.getOriginalData();
        for (List<String> row : originalDataList) {
            System.out.println(row);
        }
    }
}
