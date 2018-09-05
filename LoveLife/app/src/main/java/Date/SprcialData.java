package Date;

import android.graphics.Bitmap;

/**
 * 存储专题数据的javabean
 * Created by Wuwei on 2018/7/4.
 */

public class SprcialData {
    String Url;
    Bitmap mBitmap;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}
