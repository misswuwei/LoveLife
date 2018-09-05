package Date;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Wuwei on 2018/6/27.
 */

public class TypeData implements MultiItemEntity {
    public int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
