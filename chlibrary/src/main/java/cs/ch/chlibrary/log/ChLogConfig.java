package cs.ch.chlibrary.log;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public abstract class ChLogConfig {

    static int MAX_LEN = 612;

    static ChTreadFormatter CH_TREAD_FORMATTER = new ChTreadFormatter();
    static ChStackTraceFormatter CH_STACK_TRACE_FORMATTER = new ChStackTraceFormatter();

    public String getGlobalTag() {
        return "ChLog";
    }

    public boolean enable() {
        return true;
    }

    public JsonParse injectJsonParse() {
        return null;
    }

    public interface JsonParse {
        String toJson(Object src);
    }


    public boolean includeTread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public ChLogPrinter[] printers() {
        return null;
    }

}
