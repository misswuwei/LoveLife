package Date;

import android.graphics.Bitmap;

/**
 * Created by Wuwei on 2018/6/27.
 */

public class meiHomeMarry {
    private String name;
    private Bitmap mBitmap;
    private String mHint;
    private String Uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public String getHint() {
        return mHint;
    }

    public void setHint(String hint) {
        mHint = hint;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }
}
