package cs.ch.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cs.ch.library.demo.ChLogDemoActivity

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.tv_h ->{
                startActivity(Intent(this,ChLogDemoActivity::class.java))
            }
        }
    }
}
