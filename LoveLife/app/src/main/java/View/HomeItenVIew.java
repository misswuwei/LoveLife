package View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lovelife.xiangmu.wuwei.lovelife.R;

/**
 * Created by Wuwei on 2018/1/20.
 */

public class HomeItenVIew extends RelativeLayout {
    private String mItemText;
    private String mItemOther;
    private int mItemImg;
    private TextView tv_newstitle;
    private TextView tv_newsother;
    private ImageView iv_newsimg;

    public HomeItenVIew(Context context) {
        this(context,null);
    }

    public HomeItenVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeItenVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context,R.layout.item_news,this);

        tv_newstitle = (TextView) findViewById(R.id.tv_newstitle);
        iv_newsimg = (ImageView) findViewById(R.id.iv_newsimg);
        tv_newsother = (TextView) findViewById(R.id.tv_newsother);

    }

    public void setTitle(String s){
        tv_newstitle.setText(s);
    }

    public void setcontent(String s){
        tv_newsother.setText(s);
    }
    public void setImg(int bipmip){
        iv_newsimg.setBackgroundResource(bipmip);
    }
}
