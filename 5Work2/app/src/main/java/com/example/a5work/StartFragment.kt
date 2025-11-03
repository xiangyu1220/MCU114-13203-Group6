package com.example.a5work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a5work.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.btnStart.setOnClickListener {
            (activity as? MainActivity)?.startWork()
        }

        binding.btnCancel.setOnClickListener {
            (activity as? MainActivity)?.cancelWork()
        }

        return binding.root
    }
}