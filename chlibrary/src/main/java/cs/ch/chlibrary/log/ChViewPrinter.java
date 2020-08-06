package cs.ch.chlibrary.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cs.ch.chlibrary.R;

/**
 * @author Administrator
 * DATE on 2020/8/4
 * Describe:
 */
public class ChViewPrinter implements ChLogPrinter {

    private RecyclerView recyclerView;
    private LogAdapter adapter;

    private ChViewPrinterProvider chViewPrinterProvider;

    @Override
    public void print(@NonNull ChLogConfig config, int level, String tag, @NonNull String printString) {
        adapter.addItem(new ChLogMo(System.currentTimeMillis(), level, tag, printString));
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    public ChViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        chViewPrinterProvider = new ChViewPrinterProvider(rootView, recyclerView);
    }


    /**
     * 获取ViewProvider ,通过 ViewProvider 可以控制log识图的展示和隐藏
     *
     * @return
     */
    @NonNull
    public ChViewPrinterProvider getViewProvider() {
        return chViewPrinterProvider;
    }


    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {

        private LayoutInflater inflater;
        private List<ChLogMo> logs = new ArrayList<>();

        public LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void addItem(ChLogMo mo) {
            logs.add(mo);
            notifyItemChanged(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = inflater.inflate(R.layout.chlog_item, parent, false);
            return new LogViewHolder(inflate);
        }


        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            ChLogMo chLogMo = logs.get(position);
            int color = getHighlightColor(chLogMo.level);
            holder.tv_tag.setTextColor(color);
            holder.tv_message.setTextColor(color);
            holder.tv_tag.setText(chLogMo.getFlatened());
            holder.tv_message.setText(chLogMo.log);
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }

        /**
         * 根据log 日志的级别获取不同的样色
         *
         * @param logLevel
         * @return
         */
        private int getHighlightColor(int logLevel) {
            int hightLight;
            switch (logLevel) {
                case ChLogType.V:
                    hightLight = 0xffbbbbbb;
                    break;
                case ChLogType.D:
                    hightLight = 0xffffffff;
                    break;
                case ChLogType.I:
                    hightLight = 0xff6a8759;
                    break;
                case ChLogType.W:
                    hightLight = 0xffbbb529;
                    break;
                case ChLogType.E:
                    hightLight = 0xffff6b68;
                    break;
                default:
                    hightLight = 0xffffff00;
                    break;
            }
            return hightLight;
        }

    }

    private static class LogViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tag;
        private TextView tv_message;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tag = itemView.findViewById(R.id.tv_tag);
            tv_message = itemView.findViewById(R.id.tv_message);
        }

    }

}
