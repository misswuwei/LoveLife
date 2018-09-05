package lovelife.xiangmu.wuwei.lovelife;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nineoldandroids.animation.ObjectAnimator;

import org.jetbrains.annotations.NotNull;

import Fragment.HomeFragment;
import Fragment.NewsFragment;
import Fragment.UserFragment;
import Fragment.FoodFragment;
import Fragment.GameHomeFragment;
import Fragment.GameFragment;
import Utils.AppUtil;

/**
 * Created by Wuwei on 2018/1/18.
 */

public class HomeActivity extends Activity implements GameHomeFragment.show {
    FragmentManager fragmentManager;
    RelativeLayout rl_page;
    private RelativeLayout mRl_page;
    private int mId = R.id.ll_home;

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    Float startY, moveY;
    float move;
    private boolean finish = false;
    private LinearLayout mLl_page;
    private Fragment gameFragment = new GameHomeFragment();
    private Fragment homeFragment = new HomeFragment();
    private Fragment newsFragment = new NewsFragment();
    private Fragment baiduaFragment = new FoodFragment();
    private Fragment meFragment = new UserFragment();
    private FragmentTransaction mTransaction;
    private Fragment mFragment = new UserFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        intiUI();

        showFragment(homeFragment,"home");

        showUpdate();

        setListener();
        AppUtil.setDatabase(HomeActivity.this);
    }

    private void setListener() {

    }


    @SuppressLint("ResourceType")
    private void setPagecolor(int id) {
        if (mId != id) {
            if (mId != R.id.iv_movie){
                findViewById(mId).setBackgroundResource(R.drawable.bg_rl_oval);
            }
            if (id != R.id.iv_movie){
                findViewById(id).setBackgroundResource(R.color.home_item_background);
            }
        }
        mId = id;
    }


    //弹出更新框
    private void showUpdate() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        View view = View.inflate(this, R.layout.dialog_update, null);

        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        Button bt_yes = (Button) view.findViewById(R.id.bt_yes);
        Button bt_no = (Button) view.findViewById(R.id.bt_no);

        bt_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        bt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "正在下载......", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void intiUI() {
        mRl_page = findViewById(R.id.rl_page);
        mLl_page = findViewById(R.id.ll_page);
        LinearLayout ll_news = findViewById(R.id.ll_news);
        LinearLayout ll_game = findViewById(R.id.ll_game);
        ImageView iv_movie = findViewById(R.id.iv_movie);
        LinearLayout ll_me = findViewById(R.id.ll_me);
        LinearLayout home = findViewById(R.id.ll_home);

        home.setBackgroundResource(R.color.home_item_background);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(homeFragment,"home");
                setPagecolor(R.id.ll_home);
            }
        });


        ll_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(gameFragment,"game");
                setPagecolor(R.id.ll_game);
            }
        });
        ll_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(newsFragment,"news");
                setPagecolor(R.id.ll_news);
            }
        });
        iv_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(baiduaFragment,"baidu");
                setPagecolor(R.id.iv_movie);
            }
        });
        ll_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(meFragment,"user");
                setPagecolor(R.id.ll_me);
            }
        });
        move = mLl_page.getHeight();
    }

    @Override
    public void showFragment(@NotNull Fragment fragment, @NotNull String tag) {
        if (mFragment != fragment){
            fragmentManager = getFragmentManager();
            mTransaction = fragmentManager.beginTransaction();
            if(fragmentManager.findFragmentByTag(tag) == null){
                mTransaction.add(R.id.rl_page, fragment,tag);
                mTransaction.hide(mFragment);
                mTransaction.show(fragment);
                mFragment = fragment;
            }else {
                mTransaction.hide(mFragment);
                mTransaction.show(fragment);
                mFragment = fragment;
            }
            mTransaction.commit();
        }
    }

    //滑动隐藏底部栏
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = ev.getY();
                if ((moveY - startY) >= 50) {
                    if (!finish) {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mLl_page, "translationY", 0, 0);
                        animator.setDuration(500);
                        animator.start();
                        finish = true;

                    }
                } else {
                    if (finish) {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mLl_page, "translationY", 0, mLl_page.getHeight());
                        animator.setDuration(500);
                        animator.start();
                        finish = false;
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }



}
