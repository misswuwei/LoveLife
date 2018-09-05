package lovelife.xiangmu.wuwei.lovelife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import Utils.grabDateUtils;

public class MainActivity extends Activity {
    protected static final int ENTER_HOME = 100;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ENTER_HOME:
                    enterHome();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化动画
        inView();

        setWindowmHeight();

        new Thread(new Runnable() {
            @Override
            public void run() {
                grabDateUtils.initDate();

            }
        }).start();
    }

    private void setWindowmHeight() {
        //获取窗体的高和宽
        WindowManager winManager=(WindowManager)getSystemService(WINDOW_SERVICE);
        grabDateUtils.windowsHeight = winManager.getDefaultDisplay().getHeight();
        grabDateUtils.windowsWeith = winManager.getDefaultDisplay().getWidth();
    }

    //初始化控件
    private void inView() {
        RelativeLayout rl_root = findViewById(R.id.rl_root);
        setAlphaAnimation(rl_root);
    }

    //设置动画
    private void setAlphaAnimation(View v){
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);

        alphaAnimation.setDuration(4000);

        v.startAnimation(alphaAnimation);

        //为保证读秒成功，延时发送消息
        mHandler.sendEmptyMessageDelayed(ENTER_HOME,4000);
    }

    private void enterHome(){
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }
}
