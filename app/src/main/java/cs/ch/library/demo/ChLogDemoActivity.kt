package cs.ch.library.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cs.ch.chlibrary.log.ChLog
import cs.ch.library.R

/**
 * @author  Administrator
 * DATE on 2020/8/3
 * Describe:
 */
class ChLogDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch_log_demo)
        findViewById<View>(R.id.btn_CLog).setOnClickListener {
            printLog()
        }
    }

    private fun printLog() {
        ChLog.a("9900")
    }


}