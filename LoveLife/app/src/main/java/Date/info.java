package Date;

import android.graphics.Bitmap;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Wuwei on 2018/5/12.
 */
@Entity
public class info {
    @Id(autoincrement = true)
    public Long Id;
    public String Uri;
    public String title;
    public String path;
    @Generated(hash = 859419291)
    public info(Long Id, String Uri, String title, String path) {
        this.Id = Id;
        this.Uri = Uri;
        this.title = title;
        this.path = path;
    }
    @Generated(hash = 468757636)
    public info() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getUri() {
        return this.Uri;
    }
    public void setUri(String Uri) {
        this.Uri = Uri;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }

}
