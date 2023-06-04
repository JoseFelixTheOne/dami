package com.example.dami.ui.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dami.databinding.ItemProductoBinding
import com.example.dami.entity.Producto

class ProductoViewHolder(private val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(producto: Producto){
        binding.lblNombreProd.text = producto.nom_prod

    }
}