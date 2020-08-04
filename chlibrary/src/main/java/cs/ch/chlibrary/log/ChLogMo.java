package cs.ch.chlibrary.log;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author Administrator
 * DATE on 2020/8/4
 * Describe:
 */
public class ChLogMo {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
    public long timeMillis;
    public int level;
    public String tag;
    public String log;

    public ChLogMo(long timeMillis, int level, String tag, String log) {
        this.timeMillis = timeMillis;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String flattenedLog() {
        return getFlatened() + "\n" + log;
    }

    public String getFlatened() {
        return format(timeMillis) + '|' + level + '|' + tag + "|∶";
    }

    public String format(long timeMillis) {
        return sdf.format(timeMillis);
    }

}
