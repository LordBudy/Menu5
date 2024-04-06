package com.example.menu.activitis

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.example.menu.R
import com.example.menu.databinding.ActivityMainBinding
import com.example.menu.db.UserManager
import com.example.menu.fragments.Account
import com.example.menu.fragments.AccountUser
import com.example.menu.fragments.Basket
import com.example.menu.fragments.Home
import com.example.menu.fragments.Menu_mini
import com.example.menu.fragments.Search
import com.example.menu.interfaces.BasketImageClickListener
import com.example.menu.interfaces.FragmentTitleListener
import com.example.menu.interfaces.ImageClickListener
import com.example.menu.managers.FragmentManager
import com.example.menu.managers.FragmentManagerText

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), FragmentTitleListener, ImageClickListener, BasketImageClickListener {
    // обьявляем переменную binding
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //инициализируем эту переменную binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // указываем что он главный и получаем доступ ко всем id
        setContentView(binding.root)

        // Инициализируем UserManager с контекстом приложения
        UserManager.init(applicationContext)

        //инициализируем фрагмент менеджер для заголовков
        FragmentManagerText.registerFragmentTitleListener(this)

        // Проверяем, был ли пользователь уже зарегистрирован
        if (UserManager.isRegistered()) {
            // Если пользователь уже зарегистрирован, открываем главную страницу
            supportFragmentManager.beginTransaction()
                .replace(R.id.Container_frag, Home.newInstance())
                .commit()
        } else {
            // Если пользователь не зарегистрирован, открываем страницу регистрации
            supportFragmentManager.beginTransaction()
                .replace(R.id.Container_frag, Account.newInstance())
                .commit()
        }

        setBottomNavListener()
    }

    //--------------------------------------------------------------------------------------------------
    //обрабатываем нажатие кнопки backAtMenu переходиv на фрагмент Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.backAtMenu -> {
                // Возврат на фрагмент Menu при нажатии кнопки "назад"
                FragmentManager.setFragment(com.example.menu.fragments.Menu.newInstance(), this)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    //--------------------------------------------------------------------------------------------------
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
                    FragmentManager.setFragment(AccountUser.newInstance(), this)
                }
            }
            true
        }
    }

    //--------------------------------------------------------------------------------------------------
    // Переопределение метода для обработки нажатия кнопки "назад"
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    //названия фрагментов открытых на данный момент
    override fun onFragmentTitleChanged(title: String) {
        binding.catInfo.text = title
    }

    //--------------------------------------------------------------------------------------------------
    override fun onImageClicked(
        id_dish: Int,
        imageUrl: String,
        name: String,
        price: String,
        weight: String,
        description: String
    ) {
        // Показываем второй фрагмент с переданным URL
        val menuMini = Menu_mini()

        // Передаем URL второму фрагменту с использованием аргументов
        val args = Bundle().apply {
            putInt("id", id_dish)
            putString("url", imageUrl)
            putString("name", name)
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

    //--------------------------------------------------------------------------------------------------
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
//--------------------------------------------------------------------------------------------------

}