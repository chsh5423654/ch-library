package cs.ch.chui.tab.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * @author Administrator
 * DATE on 2020/8/7
 * Describe:
 */
public interface IChTablayout<Tab extends ViewGroup,D> {

    Tab findTab(@NonNull D data);




}
