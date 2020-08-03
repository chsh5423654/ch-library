package cs.ch.chlibrary.log;

import androidx.annotation.NonNull;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public interface ChLogPrinter {

    void print(@NonNull ChLogConfig config,int level,String tag,@NonNull String printString);
}
