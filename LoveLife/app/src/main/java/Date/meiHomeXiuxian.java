package Date;

import android.graphics.Bitmap;

/**
 * 百度糯米首页休闲数据
 * Created by Wuwei on 2018/6/27.
 */

public class meiHomeXiuxian {
    private String name;
    private Bitmap mBitmap;
    private String Url;
    private String comment;
    private String sale;

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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
