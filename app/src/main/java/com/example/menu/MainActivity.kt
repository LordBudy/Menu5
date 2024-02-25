package com.example.menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.menu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //сперва обьявляем переменную
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //теперь инициализируем эту переменную
        binding = ActivityMainBinding.inflate(layoutInflater)
        //и тут указываем что он главный и получаем доступ ко всем id
        setContentView(binding.root)

        MAIN = this
    navController = Navigation.findNavController(this,R.id.Container_frag)

    }

}