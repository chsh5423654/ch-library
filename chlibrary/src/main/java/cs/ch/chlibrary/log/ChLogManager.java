package cs.ch.chlibrary.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * DATE on 2020/8/3
 * Describe:
 */
public class ChLogManager {

    private ChLogConfig config;

    private static ChLogManager instance;
    private List<ChLogPrinter> printers = new ArrayList<>();

    private ChLogManager(ChLogConfig chLogConfig, ChLogPrinter[] printers) {
        this.config = chLogConfig;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static ChLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull ChLogConfig config, ChLogPrinter... printers) {
        instance = new ChLogManager(config, printers);
    }

    public ChLogConfig getConfig() {
        return config;
    }

    public List<ChLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(ChLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(ChLogPrinter printer) {
        if (printer != null) {
            printers.remove(printer);
        }
    }

}
