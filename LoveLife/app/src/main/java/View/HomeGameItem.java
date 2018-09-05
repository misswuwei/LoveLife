package View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import lovelife.xiangmu.wuwei.lovelife.R;

/**
 * Created by Wuwei on 2018/1/21.
 */

public class HomeGameItem extends LinearLayout {
    public HomeGameItem(Context context) {
        this(context,null);
    }

    public HomeGameItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeGameItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.item_home_game,this);
    }
}
