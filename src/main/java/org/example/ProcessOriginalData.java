package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Main.arr;

public class ProcessOriginalData {
    //学期开始时间2023年2月13日   1676217600035  毫秒值
    static long semesterSatrtTime=1676217600035L;
    static long oneWeek=7L*24*3600*1000;
    static long oneDay=24L*3600*1000;
    static long oneHour=3600*1000L;
    static void analyseOriginalData(List<List<String>> originalData) {
        //System.out.println(originalData.size());--->11
        //读取List<List<String>>中的单个数据,并处理
        for (int classTime = 1; classTime < originalData.size(); classTime++) {//第一行没用
            List<String> classList = originalData.get(classTime);
            for (int weekTime = 1; weekTime < classList.size(); weekTime++) {//第一列无用
                String singleData = classList.get(weekTime);
                processSingleData(singleData, classTime, weekTime);

                /*//testcode
                System.out.println("classTime:"+classTime+"  weekTime:"+weekTime);*/

            }
        }
    }

    private static void processSingleData(String singleData, int classTime, int weekTime) {
        /*//对于军事理论这课,格式是军事理论(981001.01) (没有老师)
        //
        //
        //(1-8,北区9_305)
        //不能很好匹配,要在excel中手动加入*/

        //《“四史”教育专题》(397005.H6) (刘艳芳)
        //(5-8,北区9_105)
        //概率与数理统计(211007.20) (付士慧)
        //(13,北区9_105)
        //两个安排单独考虑

        //weekTime星期几    classTime一天中的第几节
        singleData.replaceAll("[\\n\\t]+", " ");//删除输入字符串中的所有换行符和制表符
        singleData="\""+singleData+"\"";

        Pattern pattern = Pattern.compile("\"([^\"]*?)\\(([^\\)]*?)\\) \\((.*?)\\)[\\s\\S]*?\\((.*?),(.*?)\\)");

        Matcher matcher = pattern.matcher(singleData);
        while (matcher.find()) {
            String courseName = matcher.group(1).trim();//trim去除空格和制表符
            String courseCode = matcher.group(2);
            String teacherName = matcher.group(3);
            String courseSchedule = matcher.group(4);
            String courseLocation = matcher.group(5);
            //处理courseSchedule
            String[][] processedCourseSchedule = process_courseSchedule(courseSchedule);
            //result[][]中分别放weekType,startWeek,endWeek
            new_Data(weekTime,classTime, processedCourseSchedule, courseName, courseCode, teacherName, courseLocation);

            //test code
            System.out.print("Course week: 周" + weekTime);
            System.out.println("第" + classTime + "节");
            System.out.println("Course name: " + courseName);
            System.out.println("Course code: " + courseCode);
            System.out.println("Teacher name: " + teacherName);
            System.out.println("Course schedule: " + courseSchedule);
            System.out.println("Course location: " + courseLocation);
            System.out.println("###########################################");
        }

    }

    private static void new_Data(int weekTime, int classTime, String[][] processedCourseSchedule, String courseName,
                                 String courseCode, String teacherName, String courseLocation) {
        //weekTime星期几    classTime一天中的第几节
        String summary = new String(courseName);
        String description = new String("老师:" + teacherName
                +"地点:" + courseLocation
                +"编码:" + courseCode);

        for (String[] eachWeekSchedule : processedCourseSchedule) {

            if (eachWeekSchedule[0]==null) {//就是  单
                if(eachWeekSchedule[2].equals("")){
                    String[] timeDuring = getTimeDuring(courseCode,Integer.parseInt(eachWeekSchedule[1]),weekTime,classTime);

                    Data data = new Data(timeDuring[0], timeDuring[1], description, summary);
                    arr.add(data);
                }else {
                    //对周循环,newData,i代表周数
                    for (int i = Integer.parseInt(eachWeekSchedule[1]); i <= Integer.parseInt(eachWeekSchedule[2]); i++) {
                        String[] timeDuring = getTimeDuring(courseCode,i,weekTime,classTime);

                        Data data = new Data(timeDuring[0], timeDuring[1], description, summary);
                        arr.add(data);
                    }
                }

            } else if (eachWeekSchedule[0].equals("单")) {
                if(eachWeekSchedule[2].equals("")){
                    String[] timeDuring = getTimeDuring(courseCode,Integer.parseInt(eachWeekSchedule[1]),weekTime,classTime);

                    Data data = new Data(timeDuring[0], timeDuring[1], description, summary);
                    arr.add(data);
                }else {
                    //对周循环,newData,i代表周数
                    for (int i = Integer.parseInt(eachWeekSchedule[1]); i <= Integer.parseInt(eachWeekSchedule[2]); i++) {
                        String[] timeDuring = getTimeDuring(courseCode,i,weekTime,classTime);

                        Data data = new Data(timeDuring[0], timeDuring[1], description, summary);
                        arr.add(data);
                    }
                }
            } else if(eachWeekSchedule[0].equals("双")){
                if(eachWeekSchedule[2].equals("")){
                    String[] timeDuring = getTimeDuring(courseCode,Integer.parseInt(eachWeekSchedule[1]),weekTime,classTime);

                    Data data = new Data(timeDuring[0], timeDuring[1], description, summary);
                    arr.add(data);
                }else {
                    //对周循环,newData,i代表周数
                    for (int i = Integer.parseInt(eachWeekSchedule[1]); i <= Integer.parseInt(eachWeekSchedule[2]); i+=2) {
                        String[] timeDuring = getTimeDuring(courseCode,i,weekTime,classTime);

                        Data data = new Data(timeDuring[0], timeDuring[1], description, summary);
                        arr.add(data);
                    }
                }
            }

        }
    }

    private static String[] getTimeDuring(String courseCode, int i, int weekTime, int classTime) {
        //weekTime星期几    classTime一天中的第几节   //对周循环,newData,i代表周数
        String[] str=new String[2];
        long courseLastingTime=getCourseTime(courseCode);
        long courseStartTimeInDay=getCourseStartTimeInDay(classTime);
        long start=semesterSatrtTime+(i-1)*oneWeek+(weekTime-1)*oneDay+courseStartTimeInDay;
        long end=start+courseLastingTime;
        String[] s=transferLongToString(start,end);
        return s;
    }

    private static String[] transferLongToString(long start, long end) {
        String[] s=new String[2];
        Date d=new Date(start);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        s[0]= sdf.format(d);
        Date d2=new Date(end);
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        s[1]= sdf2.format(d2);
        return s;
    }

    private static long getCourseStartTimeInDay(int classTime) {
        long t=switch(classTime){
            case 1->8*oneHour;
            case 2->8*oneHour;//
            case 3->10*oneHour+10*60*1000L;
            case 4->8*oneHour;//
            case 5->14*oneHour;
            case 6->8*oneHour;//
            case 7->16*oneHour;
            case 8->8*oneHour;//
            case 9->19*oneHour;
            case 10->8*oneHour;//
            default ->0L;
        };
        return t;
    }

    private static long getCourseTime(String courseCode) {//根据课程id获得一节课的长度
        long t=switch (courseCode){
            case "211007.20","211017.25","221001.02","321002.J2"
                    ,"371015.D9","397005.H6","491002.21","772031.01"
                    ,"772032.01","772158.01","981001.01"
                    ->100*60*1000L;
            case "371022.H9"->45*60*1000L;

            default -> 0L;
        };
        return t;
    }

    /*private static String getOriginalDataByPath() throws IOException {
        FileInputStream fis = new FileInputStream
                ("C:\\Users\\ASUS\\Downloads\\t.txt");
        byte[] bytes = new byte[60000];
        int length = fis.read(bytes);
        return new String(bytes, 0, length);
        //从课程表数据看
    }*/

    private static String[][] process_courseSchedule(String courseSchedule) {
        String[] parts = courseSchedule.split("\\s+");//匹配任意空白字符
        //将剩余的字符串按照空白字符分割，提取每个  周   段

        String[][] result = new String[parts.length][3];
        //result[][]中分别放weekType,startWeek,endWeek
        for (int i = 0; i < result.length; i++) {
            if (parts[i].startsWith("单") || parts[i].startsWith("双")) {
                result[i][0] = parts[i].substring(0, 1);
                parts[i] = parts[i].substring(1).trim();
            }
            String[] weeks = parts[i].split("-");
            result[i][1] = weeks.length > 0 ? weeks[0] : "";
            result[i][2] = weeks.length > 1 ? weeks[1] : "";

            /*//test code
            System.out.println(courseSchedule);
            System.out.println("第"+result[i][1]+"到第"+result[i][2]+"周"+"   "+result[i][0]);*/
        }
        return result;
    }
}
