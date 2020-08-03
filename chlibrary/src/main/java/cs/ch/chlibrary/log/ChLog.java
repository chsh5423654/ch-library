package cs.ch.chlibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public class ChLog {

    public static void v(Object... contents) {
        log(ChLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(ChLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(ChLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(ChLogType.D, tag, contents);
    }


    public static void i(Object... contents) {
        log(ChLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(ChLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(ChLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(ChLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(ChLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(ChLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(ChLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(ChLogType.A, tag, contents);
    }


    public static void log(@ChLogType.TYPE int type, Object... contents) {
        log(type, ChLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@ChLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(ChLogManager.getInstance().getConfig(), type, tag, contents);
    }


    public static void log(@NonNull ChLogConfig config, @ChLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        String body = parseBody(contents);
        sb.append(body);
        Log.println(type, tag, body);
    }

    private static String parseBody(@NonNull Object[] contents) {
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
