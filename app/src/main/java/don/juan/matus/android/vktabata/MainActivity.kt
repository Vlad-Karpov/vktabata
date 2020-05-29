package don.juan.matus.android.vktabata

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var prepareInfo: Int = 10
    private var workInfo: Int = 20
    private var restInfo: Int = 10
    private var repeatInfo: Int = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RushThread.applicationContext = this.applicationContext
        if (savedInstanceState != null) {
            prepareInfo = savedInstanceState?.getInt("prepareInfo") ?: 10
            workInfo = savedInstanceState?.getInt("workInfo") ?: 20
            restInfo = savedInstanceState?.getInt("restInfo") ?: 10
            repeatInfo = savedInstanceState?.getInt("repeatInfo") ?: 8
        }
        printAllInfo()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("prepareInfo", prepareInfo);
        savedInstanceState.putInt("workInfo", workInfo);
        savedInstanceState.putInt("restInfo", restInfo);
        savedInstanceState.putInt("repeatInfo", repeatInfo);
    }

    fun onClickStartWork(view: View) {
        val intent = Intent(this@MainActivity, RushActivity::class.java)
        intent.putExtra("prepareInfo", prepareInfo)
        intent.putExtra("workInfo", workInfo)
        intent.putExtra("restInfo", restInfo)
        intent.putExtra("repeatInfo", repeatInfo)
        startActivity(intent)
    }

    fun onClickRepeatInfoPlus(view: View) {
        repeatInfo++
        printRepeatInfo()
    }

    fun onClickRepeatInfoMinus(view: View) {
        if (repeatInfo > 2) {
            repeatInfo--
            printRepeatInfo()
        }
    }

    fun onClickRestInfoPlus(view: View) {
        restInfo++
        printRestInfo()
    }

    fun onClickRestInfoMinus(view: View) {
        if (restInfo > 5) {
            restInfo--
            printRestInfo()
        }
    }

    fun onClickWorkInfoPlus(view: View) {
        workInfo++
        printWorkInfo()
    }

    fun onClickWorkInfoMinus(view: View) {
        if (workInfo > 5) {
            workInfo--
            printWorkInfo()
        }
    }

    fun onClickPrepareInfoPlus(view: View) {
        prepareInfo++
        printPrepareInfo()
    }

    fun onClickPrepareInfoMinus(view: View) {
        if (prepareInfo > 5) {
            prepareInfo--
            printPrepareInfo()
        }
    }

    private fun printPrepareInfo() {
        val textViewPrepareInfo = findViewById(R.id.textViewPrepareInfo) as TextView
        textViewPrepareInfo.text = prepareInfo.toString()
    }

    private fun printWorkInfo() {
        val textViewWorkInfo = findViewById(R.id.textViewWorkInfo) as TextView
        textViewWorkInfo.text = workInfo.toString()
    }

    private fun printRestInfo() {
        val textViewRestInfo = findViewById(R.id.textViewRestInfo) as TextView
        textViewRestInfo.text = restInfo.toString()
    }

    private fun printRepeatInfo() {
        val textViewRepeatInfo = findViewById(R.id.textViewRepeatInfo) as TextView
        textViewRepeatInfo.text = repeatInfo.toString()
    }

    private fun printAllInfo() {
        runOnUiThread {
            printPrepareInfo()
            printWorkInfo()
            printRestInfo()
            printRepeatInfo()
        }
    }

}
