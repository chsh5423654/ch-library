package cs.ch.chlibrary.log;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public interface ChLogFormater<T> {
    String format(T data);
}
