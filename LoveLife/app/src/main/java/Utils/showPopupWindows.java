package Utils;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Date.NewsJsonDate;
import Utils.AppUtil;
import Utils.grabDateUtils;
import Date.info;

import Date.DateFather;
import Date.GameJsonDate;
import lovelife.xiangmu.wuwei.lovelife.R;

/**
 * Created by Wuwei on 2018/2/16.
 */

public class showPopupWindows {
    public static PopupWindow mPopupWindow;
    public static int mHeight;
    private static TextView Filename;
    private static TextView filesize;
    private static Button dwmload;


    public static void ShowPopupWindow(View view, int Id, final Context context, final int position, final int type) {
        //添加展示布局
        View popupwinView = View.inflate(context, Id, null);

        //为popupwindow窗体的弹出设置相应的动画
        //从不透明到透明的动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);

        //圆心扩散的动画0-1从没有到填充满整个控件
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0, 1,
                0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        /**Animation.RELATIVE_TO_SELF:依赖于控件本身
         * 0.5f：控件x的一半
         * */

        //设置一个动画集合将两个动画添加到集合中
        AnimationSet animationSet = new AnimationSet(true);//两个动画执行(共享)一个数学函数
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);

        mPopupWindow = new PopupWindow(popupwinView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        TextView poptext = popupwinView.findViewById(R.id.tv_pop);
        poptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 1){
                    grabDateUtils.dateList.add(grabDateUtils.NewsDateList.get(position));
                    info info = new info(null,((NewsJsonDate)grabDateUtils.NewsDateList.get(position)).Uri,((NewsJsonDate)grabDateUtils.NewsDateList.get(position)).newsText
                            ,((NewsJsonDate)grabDateUtils.NewsDateList.get(position)).path);
                    AppUtil.getInfoDao().insert(info);
                }else if (type == 3){
                    grabDateUtils.dateList.add(grabDateUtils.GameDateList.get(position));
                    info info = new info(null,((GameJsonDate)grabDateUtils.GameDateList.get(position)).Uri,((GameJsonDate)grabDateUtils.GameDateList.get(position)).gameitem_name
                            ,((GameJsonDate)grabDateUtils.GameDateList.get(position)).path);
                    AppUtil.getInfoDao().insert(info);
                }
//              l

                Toast.makeText(context,"已收藏", Toast.LENGTH_LONG).show();
            }
        });


        //要设置一个背景才能让回退按钮生效，所以创建一个透明的背景(new ColorDrawable())
//        mPopupWindow.setBackgroundDrawable(new ColorDrawabl());

//        LinearLayout ll_gameitem = view.findViewById(R.id.ll_home_game_item);
//        ll_gameitem.measure(0,0);
        view.measure(0,0);

        if (type == 6){
            mPopupWindow.showAsDropDown(view, 680, view.getHeight()-(view.getMeasuredHeight()/2));
            Log.i("run", "ShowDownloadPopupWindow: 执行了");
        }else {
            //指定窗体的位置showAsDropDown（在指定view的下面在通过偏移x和y的坐标调整）
            mPopupWindow.showAsDropDown(view, 680, view.getHeight()-(view.getMeasuredHeight()*2));
            Log.i("www", "ShowDownloadPopupWindow: 执行了");
        }


        //通过展示的view执行动画集合
        popupwinView.startAnimation(animationSet);
    }

    /**
     * 展示删除的popup
     * @param view
     * @param Id
     * @param context
     * @param position
     * @param type
     */
    public static void ShowDelectPopupWindow(View view, int Id, final Context context, final int position) {
        //添加展示布局
        View popupwinView = View.inflate(context, Id, null);

        //为popupwindow窗体的弹出设置相应的动画
        //从不透明到透明的动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);

        //圆心扩散的动画0-1从没有到填充满整个控件
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0, 1,
                0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        /**Animation.RELATIVE_TO_SELF:依赖于控件本身
         * 0.5f：控件x的一半
         * */

        //设置一个动画集合将两个动画添加到集合中
        AnimationSet animationSet = new AnimationSet(true);//两个动画执行(共享)一个数学函数
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);

        mPopupWindow = new PopupWindow(popupwinView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        TextView poptext = popupwinView.findViewById(R.id.tv_delete);
        poptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<info> infos = AppUtil.getInfoDao().loadAll();
                AppUtil.getInfoDao().delete(infos.get(position));
                Toast.makeText(context,"已删除", Toast.LENGTH_SHORT).show();
            }
        });


        //要设置一个背景才能让回退按钮生效，所以创建一个透明的背景(new ColorDrawable())
//        mPopupWindow.setBackgroundDrawable(new ColorDrawabl());

//        LinearLayout ll_gameitem = view.findViewById(R.id.ll_home_game_item);
//        ll_gameitem.measure(0,0);
        view.measure(0,0);
        mPopupWindow.showAsDropDown(view, 680, view.getHeight()-(view.getMeasuredHeight()*2));
        Log.i("", "onLongClick: 执行了");
        //通过展示的view执行动画集合
        popupwinView.startAnimation(animationSet);
    }

    public static void ShowDownloadPopupWindow(View view, int Id, final Context context, final int position, final int type) {
        //添加展示布局
        final View popupwinView = View.inflate(context, Id, null);

        Filename = popupwinView.findViewById(R.id.tv_downpop_filename);
        filesize = popupwinView.findViewById(R.id.tv_downpop_size);
        dwmload = popupwinView.findViewById(R.id.bt_downpop_dowmload);

        dwmload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                Toast.makeText(context,((GameJsonDate)grabDateUtils.GameDateList.get(position)).gameitem_name+"正在下载",Toast.LENGTH_LONG).show();
            }
        });

        //为popupwindow窗体的弹出设置相应的动画
        //从不透明到透明的动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);

        //拿到控件的高
        LinearLayout ll = popupwinView.findViewById(R.id.windowll);

        ll.measure(0,0);

        //位移动画
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,ll.getMeasuredHeight(),0);
        translateAnimation.setDuration(300);
        translateAnimation.setFillAfter(true);
        /**Animation.RELATIVE_TO_SELF:依赖于控件本身
         * 0.5f：控件x的一半
         * */

        //设置一个动画集合将两个动画添加到集合中
        AnimationSet animationSet = new AnimationSet(true);//两个动画执行(共享)一个数学函数
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);

        mPopupWindow = new PopupWindow(popupwinView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);

        //要设置一个背景才能让回退按钮生效，所以创建一个透明的背景(new ColorDrawable())
//        mPopupWindow.setBackgroundDrawable(new ColorDrawabl());

        //指定窗体的位置showAsDropDown（在指定view的下面在通过偏移x和y的坐标调整）
        mPopupWindow.showAsDropDown(view, 0, grabDateUtils.windowsHeight-ll.getMeasuredHeight());


        //通过展示的view执行动画集合
        popupwinView.startAnimation(animationSet);
    }
}
