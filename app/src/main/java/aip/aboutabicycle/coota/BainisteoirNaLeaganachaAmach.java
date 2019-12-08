package aip.aboutabicycle.coota;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class BainisteoirNaLeaganachaAmach extends LinearLayoutManager {

    int SMOOTH_VALUE = 10;

    BainisteoirNaLeaganachaAmach(Context context) {
        super(context);
        setSmoothScrollbarEnabled(true);
    }

    /*@Override
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        final int count = getChildCount();
        if (count > 0) {
            return SMOOTH_VALUE * 3;
        }
        return 0;
    }

    @Override
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return Math.max((getItemCount() - 1) * SMOOTH_VALUE, 0);
    }

    @Override
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        final int count = getChildCount();
        if (count <= 0) {
            return 0;
        }
        int heightOfScreen;
        int firstPos = findFirstVisibleItemPosition();
        if (firstPos == RecyclerView.NO_POSITION) {
            return 0;
        }
        View view = findViewByPosition(firstPos);
        if (view == null) {
            return 0;
        }

        // Top of the view in pixels
        final int top = getDecoratedTop(view);
        int height = getDecoratedMeasuredHeight(view);
        if (height <= 0) {
            heightOfScreen = 0;
        } else {
            heightOfScreen = Math.abs(SMOOTH_VALUE * top / height);
        }
        if (heightOfScreen == 0 && firstPos > 0) {
            return SMOOTH_VALUE * firstPos - 1;
        }
        return (SMOOTH_VALUE * firstPos) + heightOfScreen;
    }*/
}
