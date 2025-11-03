package com.example.a5work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a5work.databinding.FragmentProgressBinding

class ProgressFragment : Fragment() {

    private lateinit var binding: FragmentProgressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun showPreparing() {
        binding.tvStatus.text = "準備中..."
    }

    fun updateProgress(percent: Int) {
        binding.tvStatus.text = "完成百分比: $percent%"
        binding.progressBar.progress = percent
    }

    fun showFinished() {
        binding.tvStatus.text = "背景工作結束"
    }
}