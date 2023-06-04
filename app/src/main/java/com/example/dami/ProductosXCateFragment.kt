package com.example.dami

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dami.databinding.FragmentProductosXCateBinding
import com.example.dami.entity.Resultado
import com.example.dami.ui.view.adapter.CateAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductosXCateFragment : Fragment() {

    private var _binding : FragmentProductosXCateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductosXCateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}