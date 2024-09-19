package com.example.linkagelab.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.linkagelab.databinding.FragmentDatePickerBinding
import com.example.linkagelab.databinding.FragmentTimePickerBinding

class TimePickerFrag : Fragment() {

    private var _binding: FragmentTimePickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTimePickerBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}