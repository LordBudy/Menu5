package com.example.menu

interface BasketImageClickListener {
    fun onImageAtBasketClicked(urlImage: String,
                       nameDish:String,
                       price:String,
                       weight:String)
}