package don.juan.matus.android.vktabata

import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.media.SoundPool
import android.media.ToneGenerator
import androidx.annotation.ColorInt
import kotlin.concurrent.thread

object RushThread {

    @ColorInt
    val RED = 0x3FFF0000 //-0x10000      //0xFFFF0000 0x3FFF0000

    @ColorInt
    val GREEN = 0x3F00FF00 // -0xff0100   //0xFF00FF00  0x3F00FF00

    var applicationContext: Context? = null
        set(value) {
            namPora = sp.load(value, R.raw.nam_pora, 0)
            tolkoSpokoistvie = sp.load(value, R.raw.hello, 0)
        }
    var sp: SoundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    var namPora: Int? = null
    var tolkoSpokoistvie: Int? = null
    var ra: RushActivity? = null
    val tg = ToneGenerator(AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME)
    var prepareInfo: Int = 10
    var workInfo: Int = 20
    var restInfo: Int = 10
    var repeatInfo: Int = 8
    var bkGround: Int = GREEN
    private val lock = Object()
    val thread: Thread = thread(start = true) {
        startWorkRun()
    }

    init {
    }

    fun resume() {
        synchronized(lock) {
            lock.notifyAll()
        }
    }

    fun startWorkRun() {
        while (true) {
            synchronized(lock) {
                lock.wait()
            }
            nam_pora()
            bkGround = GREEN
            ra?.printBgGround()
            ra?.printSecInfo(prepareInfo)
            ra?.printWorkInfo(repeatInfo)
            for (i in prepareInfo - 1 downTo 0) {
                Thread.sleep(1000)
                if (i <= 4) {
                    playSoundByNum(1, 200)
                }
                bkGround = GREEN
                ra?.printBgGround()
                ra?.printSecInfo(i)
                ra?.printWorkInfo(repeatInfo)
            }
            for (i in repeatInfo - 1 downTo 0) {
                bkGround = RED
                ra?.printBgGround()
                ra?.printSecInfo(workInfo)
                ra?.printWorkInfo(i + 1)
                playSoundByNum(0, 1000)
                for (j in workInfo - 1 downTo 0) {
                    Thread.sleep(1000)
                    if (j <= 4) {
                        playSoundByNum(1, 200)
                    }
                    bkGround = RED
                    ra?.printBgGround()
                    ra?.printSecInfo(j)
                    ra?.printWorkInfo(i + 1)
                }
                if (i != 0) {
                    bkGround = GREEN
                    ra?.printBgGround()
                    ra?.printSecInfo(restInfo)
                    ra?.printWorkInfo(i + 1)
                    playSoundByNum(0, 1000)
                    for (j in restInfo - 1 downTo 0) {
                        Thread.sleep(1000)
                        if (j <= 4) {
                            playSoundByNum(1, 200)
                        }
                        bkGround = GREEN
                        ra?.printBgGround()
                        ra?.printSecInfo(j)
                        ra?.printWorkInfo(i + 1)
                    }
                }
            }
            bkGround = GREEN
            ra?.printBgGround()
            ra?.printSecInfo(0)
            ra?.printWorkInfo(0)
            playSoundByNum(29, 2000)
            Thread.sleep(3000)
            spokoistvie()
        }
    }

    fun playSoundByNum(s: Int, durationMi: Int) {
        thread(start = true) {
            tg.startTone(s, durationMi)
            Thread.sleep(durationMi.toLong())
        }
    }

    fun nam_pora() {
        thread(start = true) {
            namPora?.let { sp.play(it, 1f, 1f, 0, 0, 1f) }
        }
    }

    fun spokoistvie() {
        thread(start = true) {
            tolkoSpokoistvie?.let { sp.play(it, 1f, 1f, 0, 0, 1f) }
        }
    }

}
