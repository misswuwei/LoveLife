package Utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Wuwei on 2018/2/13.
 */

public class SetAnimation {
    public static void setAnimation(View view){
        ObjectAnimator anmal1 = ObjectAnimator.ofFloat(view, "translationX", 500, -20, 0).setDuration(1200);
        ObjectAnimator anmal2 = ObjectAnimator.ofFloat(view, "alpha", 0, 1f).setDuration(1200);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(anmal1,anmal2);
    }
}
