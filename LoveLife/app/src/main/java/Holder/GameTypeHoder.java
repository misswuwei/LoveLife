package Holder;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import Date.DateFather;
import Date.GameJsonDate;
import Utils.SetAnimation;
import lovelife.xiangmu.wuwei.lovelife.R;
import OnClickListener.mRecycViewOnClickListener;

/**
 * Created by Wuwei on 2018/2/5.
 */

public class GameTypeHoder extends absHolder implements View.OnClickListener,View.OnLongClickListener {
    public TextView gameitem_number;
    public TextView gameitem_name;
    //游戏图片
    public ImageView gameitem_img;
    //游戏描述内容
    public TextView gameitem_content;
    //游戏标签
    public Button gameitem_download;
    public Button gameitemFlagButton1;
    public Button gameitemFlagButton2;
    public Button gameitemFlagButton3;

    public LinearLayout mItemlayout;
    private mRecycViewOnClickListener mListener;

    private int itemposition;
    private View mView;

    public GameTypeHoder(View itemView,mRecycViewOnClickListener listener) {
        super(itemView);
        gameitem_name = itemView.findViewById(R.id.tv_gameitem_name);
        gameitem_img = itemView.findViewById(R.id.iv_gameitem_img);
        gameitem_content = itemView.findViewById(R.id.tv_gameitem_content);
        gameitem_download = itemView.findViewById(R.id.bt_gameitem_download);
        gameitem_number = itemView.findViewById(R.id.gameitem_number);
        gameitemFlagButton1 = itemView.findViewById(R.id.bt_gameitem_flag1);
        gameitemFlagButton2 = itemView.findViewById(R.id.bt_gameitem_flag2);
        gameitemFlagButton3 = itemView.findViewById(R.id.bt_gameitem_flag3);
        mItemlayout = itemView.findViewById(R.id.ll_fragment_gameitem);

        mListener = listener;
        mView = itemView;


        gameitem_download.setOnClickListener(this);
        mItemlayout.setOnClickListener(this);
        mItemlayout.setOnLongClickListener(this);

    }


    @Override
    public void buileHoulder(DateFather date,int position) {
        gameitem_name.setText(((GameJsonDate)date).gameitem_name);
        gameitem_img.setImageBitmap(((GameJsonDate)date).gameitem_img);
        gameitem_content.setText(((GameJsonDate)date).gameitem_content);
        gameitem_number.setText(""+(position+1));

        gameitemFlagButton1.setText(((GameJsonDate)date).game_flag1);
        gameitemFlagButton2.setText(((GameJsonDate)date).game_flag2);
        gameitemFlagButton3.setText(((GameJsonDate)date).game_flag3);
        Log.i("xx", "buileHoulder: "+position+((GameJsonDate)date).gameitem_name);

        itemposition = position;
    }

    @Override
    public void onClick(View view) {
        //这是View.OnClickListener的父类方法
        switch (view.getId()){
            case R.id.ll_fragment_gameitem:
                mListener.onRecycViewItemClickListener(itemposition);
                break;
            case R.id.bt_gameitem_download:
                mListener.onRecycViewDownloadItemClickListener(itemposition);
                break;
        }

    }

    @Override
    public boolean onLongClick(View view) {
        mListener.onRecycViewLongClickListener(itemposition,mView,3);
        return false;
    }
}
