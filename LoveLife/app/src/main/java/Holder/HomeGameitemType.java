package Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import Date.DateFather;
import Date.GameJsonDate;
import lovelife.xiangmu.wuwei.lovelife.R;
import OnClickListener.mRecycViewOnClickListener;

/**
 * Created by Wuwei on 2018/3/5.
 */

public class HomeGameitemType extends absHolder implements View.OnClickListener,View.OnLongClickListener{

    private final TextView mGamename;
    private final ImageView mGamesrc;
    private final TextView mGameother;
    private final LinearLayout mLinearLayout;
    private mRecycViewOnClickListener mListener;
    private int mPosition;
    private View mView;

    public HomeGameitemType(View itemView,mRecycViewOnClickListener listener) {
        super(itemView);
        mGamename = itemView.findViewById(R.id.tv_gameitem_ming);
        mGamesrc = itemView.findViewById(R.id.iv_gameitem_src);
        mGameother = itemView.findViewById(R.id.tv_gameitem_other);
        mLinearLayout = itemView.findViewById(R.id.ll_home_game_item);

        mLinearLayout.setOnClickListener(this);
        mLinearLayout.setOnLongClickListener(this);
        mListener = listener;
        mView = itemView;

    }

    @Override
    public void buileHoulder(DateFather date, int position) {
        mGamename.setText(((GameJsonDate)date).gameitem_name);
        mGamesrc.setImageBitmap(((GameJsonDate)date).gameitem_img);
        mGameother.setText(((GameJsonDate)date).gameitem_content);

        mPosition = position;
    }

    @Override
    public void onClick(View view) {
        mListener.onRecycViewItemClickListener(mPosition);
    }

    @Override
    public boolean onLongClick(View view) {
        mListener.onRecycViewLongClickListener(mPosition,mView,6);
        return false;
    }
}
