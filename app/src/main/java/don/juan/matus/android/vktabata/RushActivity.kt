package don.juan.matus.android.vktabata

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_rush.*

class RushActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RushThread.ra = this
        setContentView(R.layout.activity_rush)
        setSupportActionBar(toolbar)
        if (savedInstanceState != null) {
            RushThread.prepareInfo = savedInstanceState.getInt("prepareInfo", 10)
            RushThread.workInfo = savedInstanceState.getInt("workInfo", 20)
            RushThread.restInfo = savedInstanceState.getInt("restInfo", 10)
            RushThread.repeatInfo = savedInstanceState.getInt("repeatInfo", 8)
            RushThread.bkGround = savedInstanceState.getInt("bkGround", 8)
        } else {
            RushThread.prepareInfo = intent.getIntExtra("prepareInfo", 10)
            RushThread.workInfo = intent.getIntExtra("workInfo", 20)
            RushThread.restInfo = intent.getIntExtra("restInfo", 10)
            RushThread.repeatInfo = intent.getIntExtra("repeatInfo", 8)
            RushThread.resume()
        }
        printBgGround()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("prepareInfo", RushThread.prepareInfo)
        savedInstanceState.putInt("workInfo", RushThread.workInfo)
        savedInstanceState.putInt("restInfo", RushThread.restInfo)
        savedInstanceState.putInt("repeatInfo", RushThread.repeatInfo)
        savedInstanceState.putInt("bkGround", RushThread.bkGround)
    }

    fun printBgGround() {
        runOnUiThread {
            val textSec = findViewById(R.id.textSec) as TextView
            val textWorkInfo = findViewById(R.id.textWorkInfo) as TextView
            textSec.setBackgroundColor(RushThread.bkGround)
            textWorkInfo.setBackgroundColor(RushThread.bkGround)
        }
    }

    fun printSecInfo(infoCurrent: Int) {
        runOnUiThread {
            val textSec = findViewById(R.id.textSec) as TextView
            textSec.text = infoCurrent.toString()
        }
    }

    fun printWorkInfo(infoCurrent: Int) {
        runOnUiThread {
            val textWorkInfo = findViewById(R.id.textWorkInfo) as TextView
            textWorkInfo.text = infoCurrent.toString()
        }
    }

}
