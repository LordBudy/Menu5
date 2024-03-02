package com.example.menu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.example.menu.R
import com.example.menu.databinding.ActivityMainBinding
import com.example.menu.databinding.FragmentMenuMiniBinding


class Menu_mini : Fragment() {

    lateinit var binding: FragmentMenuMiniBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuMiniBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

// Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Выбранное блюдо")


    }

    companion object {
        @JvmStatic
        fun newInstance() = Menu_mini()
    }

}