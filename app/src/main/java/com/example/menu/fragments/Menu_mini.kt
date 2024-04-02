package com.example.menu.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.menu.interfaces.BasketImageClickListener
import com.example.menu.databinding.FragmentMenuMiniBinding
import com.example.menu.db.DishEntity
import com.example.menu.db.MainDb
import com.example.menu.managers.FragmentManager
import com.example.menu.managers.FragmentManagerText
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Suppress("DEPRECATION")
class Menu_mini : Fragment() {
    var id: Int? = 0
    var urlIm: String? = null
    var name: String? = null
    var price: String? = null
    var weight: String? = null
    var description: String? = null
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
//--------------------------------------------------------------------------------------------------
//принимаем переданное изображение по Url и прочие данные
        id = arguments?.getInt("id")
        urlIm = arguments?.getString("url")
        name = arguments?.getString("name")
        price = arguments?.getString("price")
        weight = arguments?.getString("weight")
        description = arguments?.getString("description")

//Загружаем изображение в фрагменте Menu_mini, используя библиотеку загрузки изображений Picasso
        Picasso.get().load(urlIm).into(binding.im)
        binding.nameDish.text = name
        binding.priceDish.text = price
        binding.weight.text = weight
        binding.Description.text = description


        binding.atBascket.setOnClickListener {
            //передаем в базу данных необходимые поля
            val dish = DishEntity(
                idAvto = null,
                id_dish = id,
                url_dish = urlIm.toString(),
                name_dish = name.toString(),
                price_dish = price.toString(),
                countPrice = price.toString().toInt(),
                weight_dish = weight.toString(),
                quantity = 1
            )
            // Получаем экземпляр базы данных
            val db = MainDb.getDb(requireContext())
            // Запускаем корутину для выполнения операции поиска в базе данных
            lifecycleScope.launch {
                // Выполняем поиск блюда в фоновом режиме
                val existingDish = withContext(Dispatchers.IO) {
                    // Используем метод getDishById для поиска блюда по идентификатору
                    db.getDao().getDishById(id)
                }
                if (existingDish != null) {
                    // Если блюдо существует, увеличиваем его количество на 1
                    existingDish.quantity++
                    // Обновляем countPrice, умножив цену блюда на новое количество
                    existingDish.countPrice = existingDish.price_dish.toInt() * existingDish.quantity
                    // Выполняем обновление блюда в базе данных
                    withContext(Dispatchers.IO) {
                        db.getDao().updateDish(existingDish)
                    }
                } else {
                    // Если блюдо не существует, вставляем новое блюдо в базу данных
                    withContext(Dispatchers.IO) {
                        db.getDao().insertDish(dish)
                    }
                }
                // После выполнения операции открываем фрагмент Basket
                FragmentManager.setFragment(
                    Basket.newInstance(),
                    requireActivity() as AppCompatActivity
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Menu_mini()
    }

}