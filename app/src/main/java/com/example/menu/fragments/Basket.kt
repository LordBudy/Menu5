package com.example.menu.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.menu.R
import com.example.menu.databinding.FragmentBasketBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class Basket : Fragment() {

lateinit var binding: FragmentBasketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Корзина")

        //принимаем переданное изображение по Url и прочие данные
        val urlIm = arguments?.getString("urlBasket")
        val name = arguments?.getString("nameBasket")
        val price = arguments?.getString("priceBasket")
        val weight = arguments?.getString("weightBasket")


//Загружаем изображение в фрагменте Menu_mini, используя библиотеку загрузки изображений Picasso
        Picasso.get()
            .load(urlIm)
            .into(binding.basketImage1, object : Callback {
                override fun onSuccess() {
//          Изображение успешно загружено ,устанавливаем значение макета linearLayout видимый
                    binding.layout1.visibility = View.VISIBLE
                }
                override fun onError(e: Exception?) {
//          Изображение не удалось установить ,устанавливаем значение невидимый
                    binding.layout1.visibility = View.GONE
                }
            })
        binding.nameDish1.text = name
        binding.priceDish1.text = price
        binding.weight1.text = weight
    }
    companion object {
        @JvmStatic
        fun newInstance() = Basket()
    }
}