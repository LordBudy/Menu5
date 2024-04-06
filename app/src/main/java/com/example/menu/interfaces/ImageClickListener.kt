package com.example.menu.interfaces

interface ImageClickListener {
    fun onImageClicked(
        id_dish: Int,
        imageUrl: String,
        name: String,
        price: String,
        weight: String,
        description: String
    )
}