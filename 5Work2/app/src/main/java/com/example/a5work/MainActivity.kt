package com.example.a5work

import android.app.ActivityManager
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.a5work.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var handler: Handler
    private var isWorking = false
    private var progress = 0
    private var musicPlayer: MediaPlayer? = null
    private var workThread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        handler = Handler(Looper.getMainLooper()) { msg ->
            when (msg.what) {
                0 -> { // 更新進度
                    adapter.progressFragment.updateProgress(msg.arg1)
                }
                1 -> { // 結束
                    adapter.progressFragment.showFinished()
                    adapter.resultFragment.showFinished()
                    stopMusic()
                    isWorking = false
                }
            }
            true
        }
    }

    fun startWork() {
        if (isWorking) return
        isWorking = true
        progress = 0

        adapter.progressFragment.showPreparing()
        adapter.resultFragment.showWorking()
        playMusic()

        workThread = Thread {
            for (i in 1..100) {
                if (!isWorking) break
                Thread.sleep(50)
                progress = i
                handler.obtainMessage(0, i, 0).sendToTarget()
            }
            if (isWorking) handler.sendEmptyMessage(1)
        }
        workThread?.start()
    }

    fun cancelWork() {
        isWorking = false
        workThread?.interrupt()
        stopMusic()
        adapter.progressFragment.showFinished()
        adapter.resultFragment.showFinished()
    }

    private fun playMusic() {
        musicPlayer = MediaPlayer.create(this, R.raw.background_music)
        musicPlayer?.isLooping = true
        musicPlayer?.start()
    }

    private fun stopMusic() {
        musicPlayer?.stop()
        musicPlayer?.release()
        musicPlayer = null
    }
}