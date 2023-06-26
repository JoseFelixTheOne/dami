package com.example.dami.ui.view.adapter


import androidx.recyclerview.widget.RecyclerView
import com.example.dami.databinding.ItemPersonaBinding
import com.example.dami.entity.Persona

class PersoViewHolder (private val binding: ItemPersonaBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(persona: Persona){

        binding.lblIdPersona.text = persona.id_per.toString()
        binding.lblNombrePersona.text = persona.nom_per
        binding.lblApaternoPerosna.text = persona.apePat_per


    }

}