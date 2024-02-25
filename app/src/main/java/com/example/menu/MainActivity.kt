package com.example.menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.menu.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //сперва обьявляем переменную
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //теперь инициализируем эту переменную
        binding = ActivityMainBinding.inflate(layoutInflater)
        //и тут указываем что он главный и получаем доступ ко всем id
        setContentView(binding.root)
        MAIN = this

        navController = findNavController(R.id.Container_frag)

    }
    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.btn_home ->{

                }
                R.id.btn_search ->{

                }
                R.id.btn_basket ->{

                }
                R.id.btn_account ->{

                }
            }
            true
        }
    }
}