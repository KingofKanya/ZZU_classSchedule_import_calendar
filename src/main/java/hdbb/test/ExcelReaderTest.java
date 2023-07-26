package hdbb.test;

import hdbb.example.ExcelReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ExcelReaderTest {
    /**
     * 测试getOriginalData方法
     */
    @Test
    public void testGetOriginalData() throws IOException {
        List<List<String>> originalDataList = ExcelReader.getOriginalData();
        for (List<String> row : originalDataList) {
            System.out.println(row);
        }
    }
    /**
     * 测试 getCurriculumSchedule 方法
     */
    @Test
    public void testGetCurriculumSchedule() throws IOException {
        HashMap<String,String> curriculum_schedule = ExcelReader.getCurriculumSchedule();

        curriculum_schedule.forEach((courseTitle,lessonNumber)->{
            System.out.println("课程名称:"+courseTitle+"  课程序号:"+lessonNumber);
        });
    }
}
