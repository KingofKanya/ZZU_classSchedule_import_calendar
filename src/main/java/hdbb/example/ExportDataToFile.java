package hdbb.example;

import java.io.*;
import java.util.ArrayList;

public class ExportDataToFile {
    public static void exportDataToFile(ArrayList<Data> arr) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("D://k.ics"));
            out.println("BEGIN:VCALENDAR");

            out.println("PRODID:-//Google Inc//Google Calendar 70.9054//EN");
            out.println("VERSION:2.0");
            out.println("CALSCALE:GREGORIAN");
            out.println("METHOD:PUBLISH");
            out.println("X-WR-CALNAME:mrblack4751@gmail.com");
            //out.println("X-WR-TIMEZONE:UTC");
            out.println("TZID:Asia/Shanghai");

            //向文件中输出每节课的安排
            for (Data d : arr) {
                out.println("BEGIN:VEVENT");

                out.println("DTSTART:" + d.getDTSTART());
                out.println("DTEND:" + d.getDTEND());
                out.println("DTSTAMP:20230527T092825Z");//无关紧要的数据打印
                out.println("UID:" + d.getUID());
                out.println("CREATED:20230527T122516Z");//
                out.println("LAST-MODIFIED:20230527T122516Z");//
                out.println("SEQUENCE:0");//
                out.println("SUMMARY:" + d.getSUMMARY());
                out.println("DESCRIPTION:" + d.getDESCRIPTION());
                out.println("TRANSP:TRANSPARENT");//

                out.println("END:VEVENT");
            }

            out.println("END:VCALENDAR");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            // 打印异常堆栈跟踪信息
        }
    }
}
