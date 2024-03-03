package com.example.menu

import android.media.Image
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.menu.databinding.ActivityMainBinding
import com.example.menu.fragments.Account
import com.example.menu.fragments.Basket
import com.example.menu.fragments.FragmentManager
import com.example.menu.fragments.FragmentManagerText
import com.example.menu.fragments.Home
import com.example.menu.fragments.Menu_mini
import com.example.menu.fragments.Search

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), FragmentTitleListener,ImageClickListener,BasketImageClickListener {
    //сперва обьявляем переменную
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //теперь инициализируем эту переменную
        binding = ActivityMainBinding.inflate(layoutInflater)
        // указываем что он главный и получаем доступ ко всем id
        setContentView(binding.root)
//инициализируем фрагмент менеджер для заголовков
        FragmentManagerText.registerFragmentTitleListener(this)

        // Проверяем, что savedInstanceState равно null, чтобы избежать пересоздания фрагмента при повороте экрана
        if (savedInstanceState == null) {
           supportFragmentManager.beginTransaction()
                .replace(R.id.Container_frag, Home())
                .commit()
        }
        setBottomNavListener()

    }

    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.btn_home -> {
                    FragmentManager.setFragment(Home.newInstance(), this)

                }

                R.id.btn_search -> {
                    FragmentManager.setFragment(Search.newInstance(), this)
                }

                R.id.btn_basket -> {
                    FragmentManager.setFragment(Basket.newInstance(), this)
                }

                R.id.btn_account -> {
                    FragmentManager.setFragment(Account.newInstance(), this)
                }
            }
            true
        }
    }

    // Переопределение метода для обработки нажатия кнопки "назад"
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onFragmentTitleChanged(title: String) {
        binding.catInfo.text = title
    }

    override fun onImageClicked(
        urlImage: String,
        nameDish: String,
        price: String,
        weight: String,
        description: String
    ) {
        // Показываем второй фрагмент с переданным URL
        val menuMini = Menu_mini()

        // Передаем URL второму фрагменту с использованием аргументов
        val args = Bundle().apply {
            putString("url", urlImage)
            putString("name", nameDish)
            putString("price", price)
            putString("weight", weight)
            putString("description", description)
        }
        menuMini.arguments = args

        supportFragmentManager.beginTransaction()
            .add(R.id.Container_frag, menuMini)
            .addToBackStack(null)
            .commit()
    }

    override fun onImageAtBasketClicked(
        urlImage: String,
        nameDish: String,
        price: String,
        weight: String
    ) {
        // Показываем Basket фрагмент с переданными данными
        val basket = Basket()

        // Передаем значения Basket фрагменту с использованием аргументов
        val args = Bundle().apply {
            putString("urlBasket", urlImage)
            putString("nameBasket", nameDish)
            putString("priceBasket", price)
            putString("weightBasket", weight)
        }
        basket.arguments = args

        supportFragmentManager.beginTransaction()
            .replace(R.id.Container_frag, basket)
            .addToBackStack(null)
            .commit()
    }

}