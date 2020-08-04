package cs.ch.library

import android.app.Application
import com.google.gson.Gson
import cs.ch.chlibrary.log.ChConsolePrinter
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
        ChLogManager.init(object : ChLogConfig() {

            override fun injectJsonParse(): JsonParse {
                return JsonParse { src -> Gson().toJson(src) }
            }

            override fun getGlobalTag(): String {
                return "MApplication"
            }

            override fun enable(): Boolean {
                return true
            }
        }, ChConsolePrinter())
    }
}