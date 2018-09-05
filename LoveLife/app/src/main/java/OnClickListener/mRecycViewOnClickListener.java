package OnClickListener;

import android.view.View;

/**
 * Created by Wuwei on 2018/2/12.
 */

public interface mRecycViewOnClickListener {
    public void onRecycViewItemClickListener(int position);
    public void onRecycViewDownloadItemClickListener(int position);
    public void onRecycViewLongClickListener(int position,View view,int type);
}
