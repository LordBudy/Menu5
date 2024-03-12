package com.example.menu.interfaces

interface BasketImageClickListener {
    fun onImageAtBasketClicked(urlImage: String,
                       nameDish:String,
                       price:String,
                       weight:String)
}