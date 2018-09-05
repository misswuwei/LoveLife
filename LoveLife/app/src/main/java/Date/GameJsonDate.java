package Date;

import android.graphics.Bitmap;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Wuwei on 2018/2/5.
 */

public class GameJsonDate extends DateFather implements MultiItemEntity {

    //数据类型
    public int type;
    //游戏名称
    public String gameitem_name;
    //游戏图片
    public Bitmap gameitem_img;
    //游戏描述内容
    public String gameitem_content;
    //游戏标签
    public String game_flag1;
    public String game_flag2;
    public String game_flag3;
    //网页网址
    public String Uri;
    public String path;

    @Override
    public int getItemType() {
        return type;
    }
}
