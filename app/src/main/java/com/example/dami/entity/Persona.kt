package com.example.dami.entity
import java.io.Serializable
class Persona(val id_per: Int,
              val nom_per: String,
              val apePat_per: String,
              val apeMat_per: String,
              val correo_per: String,
              val dir_per: String,
              val flagCliente_per: String): Serializable {

    override fun toString(): String {
        return "Persona(id_per=$id_per, nom_per='$nom_per', apePat_per='$apePat_per', apeMat_per='$apeMat_per', correo_per='$correo_per', dir_per='$dir_per', flagCliente_per='$flagCliente_per')"
    }
}


/*
    "id_per": 1,
    "nom_per": "sample string 2",
    "apePat_per": "sample string 3",
    "apeMat_per": "sample string 4",
    "correo_per": "sample string 5",
    "dir_per": "sample string 6",
    "activo_per": "sample string 7",
    "flagCliente_per": "sample string 8"*/