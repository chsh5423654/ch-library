package cs.ch.chlibrary.log;

/**
 * @author Administrator
 * DATE on 2020/8/4
 * Describe:
 */
public class ChStackTraceUtil {


    public static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTrack, String ignorePackge, int maxDepth) {
        return cropStackTrace(getRealStackTrace(stackTrack, ignorePackge), maxDepth);
    }


    /**
     * 裁剪堆栈信息
     *
     * @param traceElements
     * @param maxDepth
     * @return
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] traceElements, int maxDepth) {
        int realDepth = traceElements.length;
        if (maxDepth > 0) {
            realDepth = Math.max(maxDepth, realDepth);
        }
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(traceElements, 0, realStack, 0, realDepth);

        return realStack;
    }


    /**
     * 获取除忽略包名之外的堆栈信息
     *
     * @param stackTrack
     * @param ignorePackage
     * @return
     */
    private static StackTraceElement[] getRealStackTrace(StackTraceElement[] stackTrack, String ignorePackage) {

        int ignoreDepth = 0;
        int allDepth = stackTrack.length;
        String className;
        for (int i = allDepth - 1; i >= 0; i--) {
            className = stackTrack[i].getClassName();
            if (ignorePackage != null && className.startsWith(ignorePackage)) {
                ignoreDepth = i + 1;
                break;
            }
        }
        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(stackTrack, ignoreDepth, realStack, 0, realDepth);
        return realStack;
    }

}
