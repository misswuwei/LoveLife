package Utils

import android.util.Log
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS



/**
 * Created by Wuwei on 2018/8/2.
 */
object ThreadPoolUtil {

    val instance: ThreadPoolExecutor?
        get() = MyThreadPool.instance

    private object MyThreadPool {
        private var CORE_POOL_SIZE: Int = 0  //核心线程数
        private var MAXIMUM_POOL_SIZE: Int = 0   //最大线程数
        private val KEEP_ALIVE = 1
        var instance: ThreadPoolExecutor? = null

        init {
            //计算CUP数量
            val cpuNumber = Runtime.getRuntime().availableProcessors()
            CORE_POOL_SIZE = cpuNumber + 1
            MAXIMUM_POOL_SIZE = cpuNumber * 2 + 1
            instance = ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE.toLong(), TimeUnit.SECONDS, LinkedBlockingDeque<Runnable>(128))
            Log.i("ThreadPoolUtil", "static initializer: cpu数量：" + cpuNumber)
        }
    }
}