package Holder;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Date.DateFather;
import Date.NewsJsonDate;
import lovelife.xiangmu.wuwei.lovelife.R;
import OnClickListener.mRecycViewOnClickListener;

/**
 * Created by Wuwei on 2018/1/28.
 */

public class NewsTypeHolder extends absHolder implements View.OnClickListener,View.OnLongClickListener{
    private final TextView mTv_newstitle;
    private final ImageView mIv_newsimg;
    private final TextView mTv_newsother;

    private mRecycViewOnClickListener mListener;
    private RelativeLayout mLayout;
    private int mPosition;
    private View mView;

    //创建数据集合

    public NewsTypeHolder(View itemView,mRecycViewOnClickListener listener) {
        super(itemView);
        mTv_newstitle = itemView.findViewById(R.id.tv_newstitle);
        mIv_newsimg = itemView.findViewById(R.id.iv_newsimg);
        mTv_newsother = itemView.findViewById(R.id.tv_newsother);

        mListener = listener;
        mLayout = itemView.findViewById(R.id.rl_fragment_newsitem);
        mLayout.setOnClickListener(this);
        mLayout.setOnLongClickListener(this);
        mView = itemView;


    }

    @Override
    public void buileHoulder(DateFather date,int position) {
        mTv_newstitle.setText(((NewsJsonDate)date).newsText);
        mIv_newsimg.setImageBitmap(((NewsJsonDate)date).newsimage);
        mTv_newsother.setText(((NewsJsonDate)date).newSource);
        mPosition = position;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_fragment_newsitem:
                mListener.onRecycViewItemClickListener(mPosition);
                break;
        }

    }

    @Override
    public boolean onLongClick(View view) {
        mListener.onRecycViewLongClickListener(mPosition,mView,1);
        return false;
    }
}
