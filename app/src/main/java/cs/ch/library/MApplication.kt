package cs.ch.library

import android.app.Application
import cs.ch.chlibrary.log.ChLogConfig
import cs.ch.chlibrary.log.ChLogManager

/**
 * @author  Administrator
 * DATE on 2020/8/3
 * Describe:
 */
class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ChLogManager.init(object : ChLogConfig(){
            override fun getGlobalTag(): String {
                return "MApplication"
            }

            override fun enable(): Boolean {
                return true
            }
        })
    }
}