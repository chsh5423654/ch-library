package cs.ch.chlibrary.log;

import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Administrator
 * DATE on 2020/8/4
 * Describe:
 */
public class ChViewPrinterProvider {

    private FrameLayout rootView;
    private View floatingView;
    private boolean isOpen;
    private FrameLayout logView;
    private RecyclerView recyclerView;

    public ChViewPrinterProvider(FrameLayout rootView, View floatingView, boolean isOpen, FrameLayout logView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.floatingView = floatingView;
        this.isOpen = isOpen;
        this.logView = logView;
        this.recyclerView = recyclerView;
    }





}
