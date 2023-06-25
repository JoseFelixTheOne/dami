package com.example.dami.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dami.R
import com.example.dami.databinding.FragmentDetCatBinding

class DetCatFragment : Fragment() {

    private var _binding: FragmentDetCatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetCatBinding.inflate(inflater, container, false)
        return binding.root
    }

}