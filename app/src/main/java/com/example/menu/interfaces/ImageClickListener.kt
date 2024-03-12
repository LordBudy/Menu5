package com.example.menu.interfaces

interface ImageClickListener {
    fun onImageClicked(urlImage: String,
                       nameDish:String,
                       price:String,
                       weight:String,
                       description:String)
}