package cs.ch.chlibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public class ChLog {

    private static final String CH_LOG_PACKAGE;

    static {
        String className = ChLog.class.getName();
        CH_LOG_PACKAGE = className.substring(0, className.lastIndexOf(".") + 1);
    }

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

        if (config.includeTread()) {
            String threadInfo = ChLogConfig.CH_TREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (config.stackTraceDepth() > 0) {
            String stackTrace = ChLogConfig.CH_STACK_TRACE_FORMATTER.format(ChStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), CH_LOG_PACKAGE,config.stackTraceDepth()));
            sb.append(stackTrace).append("\n");
        }

        String body = parseBody(contents, config);
        sb.append(body);

        List<ChLogPrinter> printers = config.printers() != null ? Arrays.asList(config.printers()) : ChLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        //打印Log
        for (ChLogPrinter chLogPrinter : printers) {
            chLogPrinter.print(config, type, tag, sb.toString());
        }
    }

    private static String parseBody(@NonNull Object[] contents, ChLogConfig config) {
        if (config.injectJsonParse() != null) {
            return config.injectJsonParse().toJson(contents);
        }
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
