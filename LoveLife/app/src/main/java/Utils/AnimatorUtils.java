package Utils;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Wuwei on 2018/3/16.
 */

public enum AnimatorUtils {
    INSTANCE;
   public void setMove(View view,int time,String propertyName){
       view.measure(0,0);
       ObjectAnimator translationY = ObjectAnimator.ofFloat(view, propertyName, -view.getHeight(),0);
       translationY.setDuration(time);
       translationY.start();
   }
}
