package com.example.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.menu.databinding.ActivityMainBinding
import com.example.menu.fragments.Account
import com.example.menu.fragments.Basket
import com.example.menu.fragments.FragmentManager
import com.example.menu.fragments.Home
import com.example.menu.fragments.Search

class MainActivity : AppCompatActivity() {
    //сперва обьявляем переменную
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //теперь инициализируем эту переменную
        binding = ActivityMainBinding.inflate(layoutInflater)
        // указываем что он главный и получаем доступ ко всем id
        setContentView(binding.root)

        // Проверяем, что savedInstanceState равно null, чтобы избежать пересоздания фрагмента при повороте экрана
        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction
                .replace(R.id.Container_frag, Home())
            fragmentTransaction
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


}