package cs.ch.chlibrary.log;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public class ChTreadFormatter implements ChLogFormater<Thread> {

    @Override
    public String format(Thread data) {
        return "Thread" + data.getName();
    }
}
