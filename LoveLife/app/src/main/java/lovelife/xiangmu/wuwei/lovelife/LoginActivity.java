package lovelife.xiangmu.wuwei.lovelife;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import Utils.AnimatorUtils;
import Utils.SPUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import netUtil.BaseObserver;
import netUtil.RetrofitFactory;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Wuwei on 2018/3/15.
 */

public class LoginActivity extends Activity {

    @BindView(R.id.iv_user_icon)
    ImageView mIvUserIcon;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    private EditText mEt_username;
    private EditText mEt_password;
    private Button mLoginbutton;
    private FrameLayout mFrame_error;
    private TextView mError_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ButterKnife.bind(this);

        if (!SPUtil.getString(LoginActivity.this,"access_token","").equals("")) startActivity(new Intent(LoginActivity.this,FindActivity.class));
        //初始化布局
        intiView();
    }

    private void intiView() {
        TextView loginName = findViewById(R.id.tv_user_name);
        ImageView loginIcon = findViewById(R.id.iv_user_icon);
        mEt_username = findViewById(R.id.et_username);
        mEt_password = findViewById(R.id.et_password);
        mLoginbutton = findViewById(R.id.loginbutton);
        mFrame_error = findViewById(R.id.frame_error);
        mError_text = findViewById(R.id.error_text);
        mLoginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });

        AnimatorUtils.INSTANCE.setMove(loginIcon, 500, "translationY");
        initAnimation();
    }

    /**
     * 初始化条目动画
     */
    private void initAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mIvUserIcon, "translationX", -500, 0);
        objectAnimator.setDuration(800);
        objectAnimator.start();

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mTvUserName, "translationX", 500, 0);
        objectAnimator1.setDuration(800);
        objectAnimator1.start();

        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mLoginbutton, "translationY", 500, 0);
        objectAnimator2.setDuration(800);
        objectAnimator2.start();

        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mEt_username, "translationX", -1080, 0);
        objectAnimator3.setDuration(800);
        objectAnimator3.start();

        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mEt_password, "translationX", 1080, 0);
        objectAnimator4.setDuration(800);
        objectAnimator4.start();

    }

    /**
     * 检查格式
     */
    private void check() {
//        if (mEt_username.getText().toString().equals("123") && mEt_password.getText().toString().equals("123")) {
//            //验证成功
////            SPUtil.putBoolean(LoginActivity.this, "UserLogin", true);
////            startActivity(new Intent(this,TextActivity.class));
//        } else {
//            if (!mEt_username.getText().toString().equals("123")) {
//                //用户名有误
//                showErrorMassage("用户有误");
//            }
//            if (!mEt_password.getText().toString().equals("123")) {
//                //密码错误
//                showErrorMassage("密码有误");
//            }
//        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("account_type", "phone");
        jsonObject.addProperty("phone", "86"+mEt_username.getText().toString());
        jsonObject.addProperty("password", mEt_password.getText().toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());

        RetrofitFactory.getRetrofitService().login(requestBody).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(@NotNull JsonObject requeas) {
                        try {
                            JSONObject data = new JSONObject(requeas.get("data").toString());
                            Log.i("", "onSuccess: "+data.get("access_token"));
                            SPUtil.putString(LoginActivity.this,"access_token",data.get("access_token").toString());
                            startActivity(new Intent(LoginActivity.this,FindActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFail(@NotNull JsonObject requeas) {
                        Log.i("", "onFail: "+requeas.toString());
                        showErrorMassage("登录失败");
                    }
                });
    }

    /**
     * 展示错误信息
     */
    public void showErrorMassage(String text) {
        mError_text.setText(text);
        mFrame_error.setVisibility(View.VISIBLE);
        Animator animator = AnimatorInflater.loadAnimator(LoginActivity.this, R.animator.error_show);
        animator.setTarget(mFrame_error);
        animator.start();
    }
}
