package cs.ch.chlibrary.log;

import androidx.annotation.NonNull;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public class ChLogManager {

    private ChLogConfig config;

    private static ChLogManager instance;

    private ChLogManager(ChLogConfig chLogConfig) {
        this.config = chLogConfig;
    }

    public static ChLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull ChLogConfig config) {
        instance = new ChLogManager(config);
    }

    public ChLogConfig getConfig() {
        return config;
    }

}
