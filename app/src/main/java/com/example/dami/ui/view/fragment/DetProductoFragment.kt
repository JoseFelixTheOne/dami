package com.example.dami.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dami.R
import com.example.dami.databinding.FragmentDetProductoBinding


class DetProductoFragment : Fragment() {

    private var _binding : FragmentDetProductoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetProductoBinding.inflate(inflater, container, false)
        return binding.root
    }

}