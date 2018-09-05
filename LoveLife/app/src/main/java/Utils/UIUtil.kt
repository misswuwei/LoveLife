package Utils


/**
 * Created by Wuwei on 2018/9/5.
 */
object UIUtil{

    val instance: UIUtil?
        get() = Myinstance.instance
    private object Myinstance{
        var instance: UIUtil? =null
        init {
            instance = UIUtil
        }
    }
    /**
     * dp 转 px
     *
     * @param dpValue
     * @return
     */
    fun dp2px(dpValue: Float): Int {
        val scale = AppUtil.getAppC().getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px 转 dp
     *
     * @param pxValue
     * @return
     */
    fun px2dp(pxValue: Float): Int {
        val scale = AppUtil.getAppC().getResources().getDisplayMetrics().density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * sp 转 px
     *
     * @param spValue
     * @return
     */
    fun sp2px(spValue: Float): Int {
        val fontScale = AppUtil.getAppC().getResources().getDisplayMetrics().scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * px 转 sp
     *
     * @param pxValue
     * @return
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = AppUtil.getAppC().getResources().getDisplayMetrics().scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(): Int {
        val dm = AppUtil.getAppC().getResources().getDisplayMetrics()
        return dm.heightPixels
    }

    /**
     * 获取屏幕宽
     */
    fun getScreenWidth(): Int {
        val dm = AppUtil.getAppC().getResources().getDisplayMetrics()
        return dm.widthPixels
    }

    /**
     * 返回屏幕高度的 1/5
     * @return
     */
    fun getScreenHeightOneFifth(): Int {
        return getScreenHeight() / 5
    }
}