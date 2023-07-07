package hdbb.example;

import java.util.UUID;

public class Data {
    /**
     * public String RANSP="OPAQUE";//Google日历特有的属性，表示事件是否透明
     * public String X_GOOGLE_CALENDAR_CONTENT_DISPLAY="CHIP";
     *     //Google日历特有的属性，表示事件在日历中的显示方式。
     * public String X_GOOGLE_CALENDAR_CONTENT_ICON="https://calendar.google.com/googlecalendar/images/cake.gif";
     *     //Google日历特有的属性，表示事件在日历中的图标
     * public String CLASS="PUBLIC";
     *     //事件可见性，公开
     */
    //记录每节课的课程数据

    //ICS文件中属性的排列顺序不影响ICS文件的解析，但是建议按照RFC5545规范的顺序排列

    public String DTSTART;//事件开始时间
    public String DTEND;//事件结束时间
    public String DTSTAMP = "20230527T092825Z";//事件创建时间，2023年5月27日9点28分25秒。
    public String UID = UUID.randomUUID().toString();//事件唯一标识符,不能重复
    public String CREATED = "20220526T080514Z";//事件创建时间
    public String DESCRIPTION;//事件描述
    public String LAST_MODIFIED = "20220526T080514Z";//事件最后修改时间
    public int SEQUENCE = 0;//Google日历特有的属性，表示事件序列号
    public String STATUS = "CONFIRMED";//事件状态，已确认。
    public String SUMMARY;//事件摘要   Title
    public String TRANSP = "TRANSPARENT";//表示事件是否透明，即是否显示在日历上

    public Data(String DTSTART, String DTEND, String DESCRIPTION, String SUMMARY) {
        this.DTSTART = DTSTART;
        this.DTEND = DTEND;
        this.DESCRIPTION = DESCRIPTION;
        this.SUMMARY = SUMMARY;
    }

    public void show() {
        System.out.println(DTSTART);
        System.out.println(DTEND);
        System.out.println(DESCRIPTION);
        System.out.println(SUMMARY);
    }

    public String getDTSTART() {
        return DTSTART;
    }

    public String getDTEND() {
        return DTEND;
    }

    public String getDTSTAMP() {
        return DTSTAMP;
    }

    public String getUID() {
        return UID;
    }

    public String getCREATED() {
        return CREATED;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getLAST_MODIFIED() {
        return LAST_MODIFIED;
    }

    public int getSEQUENCE() {
        return SEQUENCE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public String getSUMMARY() {
        return SUMMARY;
    }

    public String getTRANSP() {
        return TRANSP;
    }
}
