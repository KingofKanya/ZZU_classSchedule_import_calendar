package org.example;

import java.io.*;
import java.util.ArrayList;

public class ExportDataToFile {
    public static void exportDataToFile(ArrayList arr) throws IOException {
        int l=arr.size();
        try {
            PrintWriter out = new PrintWriter(new FileWriter("D://kebiao.ics"));
            out.println("BEGIN:VCALENDAR");

            out.println("PRODID:-//Google Inc//Google Calendar 70.9054//EN");
            out.println("VERSION:2.0");
            out.println("CALSCALE:GREGORIAN");
            out.println("METHOD:PUBLISH");
            out.println("X-WR-CALNAME:mrblack4751@gmail.com");
            //out.println("X-WR-TIMEZONE:UTC");
            out.println("TZID:Asia/Shanghai");


            for (int i=0;i<l;i++){
                Data d=((Data)arr.get(i));
                out.println("BEGIN:VEVENT");

                out.println("DTSTART:"+d.getDTSTART());
                out.println("DTEND:"+d.getDTEND());
                out.println("DTSTAMP:20230527T092825Z");//
                out.println("UID:"+d.getUID());
                out.println("CREATED:20230527T122516Z");//
                out.println("LAST-MODIFIED:20230527T122516Z");//
                out.println("SEQUENCE:0");//
                out.println("SUMMARY:"+d.getSUMMARY());
                out.println("DESCRIPTION:"+d.getDESCRIPTION());
                //out.println("Location:");
                out.println("TRANSP:TRANSPARENT");//

                out.println("END:VEVENT");
            }

            out.println("END:VCALENDAR");
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        /*
        BEGIN:VEVENT
        DTSTART;VALUE=DATE:20240102
        DTEND;VALUE=DATE:20240103
        DTSTAMP:20230527T123630Z
        UID:0tqejmlhnmc67g227285gor8dj@google.com
        CREATED:20230527T122516Z
        LAST-MODIFIED:20230527T122516Z
        SEQUENCE:0
        STATUS:CONFIRMED
        SUMMARY:aaa
        TRANSP:TRANSPARENT
        END:VEVENT*/
        /*FileOutputStream fos=new FileOutputStream("D://kebiao.ics");
        fos.write("BEGIN:VCALENDAR".getBytes());

        fos.write("PRODID:-//Google Inc//Google Calendar 70.9054//EN".getBytes());
        fos.write("VERSION:2.0".getBytes());
        fos.write("CALSCALE:GREGORIAN".getBytes());
        fos.write("METHOD:PUBLISH".getBytes());
        fos.write("X-WR-CALNAME:mrblack4751@gmail.com".getBytes());
        fos.write("X-WR-TIMEZONE:UTC".getBytes());

        fos.write("END:VCALENDAR".getBytes());
        fos.close();*/
    }
}
