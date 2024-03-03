package com.example.menu.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.menu.BasketImageClickListener
import com.example.menu.ImageClickListener
import com.example.menu.R
import com.example.menu.databinding.FragmentMenuMiniBinding
import com.squareup.picasso.Picasso


class Menu_mini : Fragment() {

    lateinit var binding: FragmentMenuMiniBinding
    lateinit var navController: NavController
private lateinit var basketImageClickListener: BasketImageClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BasketImageClickListener) {
            basketImageClickListener = context
        } else {
            throw RuntimeException("$context должен реализовать BasketImageClickListener")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuMiniBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Выбранное блюдо")
//принимаем переданное изображение по Url и прочие данные
        var urlIm = arguments?.getString("url")
        var name = arguments?.getString("name")
        var price = arguments?.getString("price")
        var weight = arguments?.getString("weight")
        var description = arguments?.getString("description")

//Загружаем изображение в фрагменте Menu_mini, используя библиотеку загрузки изображений Picasso
        Picasso.get().load(urlIm).into(binding.im)
        binding.nameDish.text = name
        binding.priceDish.text = price
        binding.weight.text = weight
        binding.Description.text = description

        binding.atBascket.setOnClickListener {
basketImageClickListener.onImageAtBasketClicked(urlIm.toString(),name.toString(),price.toString(),weight.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Menu_mini()
    }

}