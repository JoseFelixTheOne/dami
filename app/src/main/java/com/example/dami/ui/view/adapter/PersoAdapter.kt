package com.example.dami.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dami.databinding.ItemCategoriaBinding
import com.example.dami.databinding.ItemPersonaBinding

import com.example.dami.entity.Persona

class PersoAdapter(val lista : List<Persona>, val onclick: (Persona) -> Unit) : RecyclerView.Adapter<PersoViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersoViewHolder {
        val binding = ItemPersonaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: PersoViewHolder, position: Int) {
        val persona = lista[position]
        holder.bind(persona)
        holder.itemView.setOnClickListener {
            onclick(persona)
        }
    }

}