package cs.ch.chlibrary.log;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public class ChStackTraceFormatter implements ChLogFormater<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] data) {
        if (data == null || data.length == 0) {
            return null;
        } else if (data.length == 1) {
            return "\t-" + data[0].toString();
        } else {
            StringBuilder sb = new StringBuilder(128);
            for (int i = 0; i < data.length; i++) {
                if (i == 0) {
                    sb.append("stacktrace: \n");
                }
                if (i != data.length - 1) {
                    sb.append("\t├");
                    sb.append(data[i].toString());
                    sb.append("\n");
                } else {
                    sb.append("\t└");
                    sb.append(data[i].toString());
                }
            }
            return sb.toString();
        }
    }
}
