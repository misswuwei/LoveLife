package Date;


import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wuwei on 2018/1/19.
 */

public class NewsJsonDate extends DateFather{
    //子类可以使用父类的对象
    //数据类型

    //数据类型
    public int type;
    //新闻内容
    public String newsText;
    //附带图片
    public Bitmap newsimage;
    //新闻标题
    public String newTitle;
    //新闻来源
    public String newSource;
    //网页链接
    public String Uri;

    //网页链接
    public String path;

}
