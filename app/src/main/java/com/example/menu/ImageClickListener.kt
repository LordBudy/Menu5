package com.example.menu

interface ImageClickListener {
    fun onImageClicked(urlImage: String,
                       nameDish:String,
                       price:String,
                       weight:String,
                       description:String)
}