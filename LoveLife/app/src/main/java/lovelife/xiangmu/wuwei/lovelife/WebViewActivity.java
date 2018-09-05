package lovelife.xiangmu.wuwei.lovelife;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Wuwei on 2018/2/12.
 */

public class WebViewActivity extends Activity {
    private WebView mWebView;
    private String mUri = "dd";
    private String mType = "0";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        //
        intiView();

        //
        initWebView();
    }

    private void initWebView() {

        Log.i("Uri =", mUri);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true); //设置可以支持缩放
        mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

        //*****自定义WebViewClient的原因，因为需要访问的网址的前缀是taptap，而webview能识别的是http等
        //所以需要添加自定义的前缀
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView wv, String url) {
                if(url == null) return false;

                try {
                    if(url.startsWith("weixin://") //微信
                            || url.startsWith("alipays://") //支付宝
                            || url.startsWith("mailto://") //邮件
                            || url.startsWith("tel://")//电话
                            || url.startsWith("taptap://")//大众点评
                            || url.startsWith("tel")
                        //其他自定义的scheme
                            ) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                    if (url.contains("dianying.baidu.com")){
                        wv.loadUrl(url+mUri.substring(26,mUri.length()));
                        return false;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }

                //处理http和https开头的url
                wv.loadUrl(url);
                return true;
            }
        };


        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(webViewClient);
        mWebView.loadUrl(mUri);
    }

    @SuppressLint("ResourceAsColor")
    private void intiView() {
        mUri = getIntent().getStringExtra("Uri");
        mType = getIntent().getStringExtra("Type");

        //初始化控件
        mWebView = findViewById(R.id.webview);
        RelativeLayout mRl = findViewById(R.id.rl_web);
        TextView mTv = findViewById(R.id.tv_web_toolbartitle);
        ImageView mIv = findViewById(R.id.iv_return);
        if (mType.equals("1")){
            mRl.setBackgroundColor(R.color.newsitem);
            mTv.setText("热点新闻");
        }else {
            mRl.setBackgroundColor(R.color.gameitem);
            mTv.setText("详情");
        }
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
