package cs.ch.chlibrary.log;

import androidx.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Administrator
 * DATE on 2020/8/6
 * Describe:
 * 1.BlockingQueue 的使用，防止频繁的创建线程
 * 2，线程同步
 * 3，文件操作，BufferdWriter的应用
 */
public class ChFilePrinter implements ChLogPrinter {

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    private final String logPath;
    private final long retentionTime;
    private LogWriter writer;
    private volatile PinterWorker worker;
    private volatile static ChFilePrinter instance;


    public static ChFilePrinter getInstance(String logPath, long retentionTime) {
        if (instance == null) {
            synchronized (ChFilePrinter.class) {
                if (instance == null) {
                    instance = new ChFilePrinter(logPath, retentionTime);
                }
            }
        }
        return instance;
    }


    public ChFilePrinter(String logPath, long retentionTime) {
        this.logPath = logPath;
        this.retentionTime = retentionTime;

    }

    @Override
    public void print(@NonNull ChLogConfig config, int level, String tag, @NonNull String printString) {

        long currentTimeMillis = System.currentTimeMillis();
        if (!worker.isRunning()) {
            worker.start();
        }
        worker.put(new ChLogMo(currentTimeMillis, level, tag, printString));

    }

    private void cleanExpiredLog() {
        if (retentionTime <= 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        File logDir = new File(logPath);
        File[] files = logDir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (currentTimeMillis - file.lastModified() > retentionTime) {
                file.delete();
            }
        }

    }


    private class PinterWorker implements Runnable {

        private BlockingDeque<ChLogMo> logMos = new LinkedBlockingDeque<>();
        private volatile boolean running;

        void put(ChLogMo log) {
            try {
                logMos.put(log);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 判断工作线程是否在运行中
         */
        boolean isRunning() {
            synchronized (this) {
                return running;
            }
        }

        void start() {
            synchronized (this) {
                EXECUTOR.execute(this);
                running = true;
            }
        }

        @Override
        public void run() {
            ChLogMo log;
            while (true) {
                try {
                    log = logMos.take();
                    doPrint(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    synchronized (this) {
                        running = false;
                    }
                }
            }

        }
    }

    private void doPrint(ChLogMo log) {
        String preFileName = writer.getPreFileName();
        if (preFileName == null) {
            String newFileName = genFileName();
            if (writer.isReady()) {
                writer.close();
            }
            if (!writer.ready(newFileName)) {
                return;
            }
        }
        writer.append(log.flattenedLog());
    }

    private String genFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(System.currentTimeMillis()));
    }


    /**
     * 基于 BufferedWriter 将日志写入文件
     */
    private class LogWriter {

        private String prFileName;
        private File logFile;
        private BufferedWriter bufferedWriter;

        boolean isReady() {
            return bufferedWriter != null;
        }

        String getPreFileName() {
            return prFileName;
        }

        /**
         * log 写入前的准备操作
         *
         * @param newFileName 要保存 log 的文件名
         * @return true 表示准备就绪
         */
        boolean ready(String newFileName) {
            prFileName = newFileName;
            logFile = new File(logPath, newFileName);

            if (!logFile.exists()) {
                try {
                    File parent = logFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdir();
                    }
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    prFileName = null;
                    logFile = null;
                    return false;
                }
            }

            try {
                bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            } catch (Exception e) {
                e.printStackTrace();
                prFileName = null;
                logFile = null;
                return false;
            }
            return true;

        }

        /**
         * 关闭 BufferedWriter
         *
         * @return
         */
        boolean close() {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    bufferedWriter = null;
                    prFileName = null;
                    logFile = null;
                }
            }
            return true;
        }


        /**
         * 将log写入文件
         *
         * @param flattenedLog
         */
        void append(String flattenedLog) {
            try {
                bufferedWriter.write(flattenedLog);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
