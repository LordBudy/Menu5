package com.example.menu.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.room.Room
import com.example.menu.interfaces.BasketImageClickListener
import com.example.menu.databinding.FragmentMenuMiniBinding
import com.example.menu.db.Adapter
import com.example.menu.db.DishEntity
import com.example.menu.db.MainDb
import com.example.menu.managers.FragmentManager
import com.example.menu.managers.FragmentManagerText
import com.squareup.picasso.Picasso


@Suppress("DEPRECATION")
class Menu_mini : Fragment() {
    var urlIm: String? = null
    var name: String? = null
    var price: String? = null
    var weight: String? = null
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
        urlIm = arguments?.getString("url")
        name = arguments?.getString("name")
        price = arguments?.getString("price")
        weight = arguments?.getString("weight")
        var description = arguments?.getString("description")

//Загружаем изображение в фрагменте Menu_mini, используя библиотеку загрузки изображений Picasso
        Picasso.get().load(urlIm).into(binding.im)
        binding.nameDish.text = name
        binding.priceDish.text = price
        binding.weight.text = weight
        binding.Description.text = description



        //создаем переменную и вызываем базу данных
        val db = MainDb.getDb(requireContext())

        binding.atBascket.setOnClickListener {
            //передаем в базу данных необходимые поля
            val dish = DishEntity(
                null,
                url_dish = urlIm.toString(),
                name_dish = name.toString(),
                price_dish = price.toString(),
                weight_dish = weight.toString(),
                quantity = 1
            )
            // Вставьте данные в базу данных
            //здесь вызываем из базы данных функцию getDao а она подтягивает интерфейс ImageDao с его функциями
            // и заключаем в Thread{}.start() для запуска на второстепенном потоке а не на основном
            Thread {
                db.getDao().insertDish(dish)
            }.start()

            //открываем фрагмент Basket
            FragmentManager.setFragment(
                Basket.newInstance(),
                requireActivity() as AppCompatActivity
            )
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = Menu_mini()
    }

}