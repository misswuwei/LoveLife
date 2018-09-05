package Date;

import android.graphics.Bitmap;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 美团首页电影栏
 * Created by Wuwei on 2018/6/26.
 */

public class meiHomeMovie implements MultiItemEntity {
    private Bitmap mBitmap;//图片
    private String name;//电影名称
    private String Url;//链接

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type = 1;

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
