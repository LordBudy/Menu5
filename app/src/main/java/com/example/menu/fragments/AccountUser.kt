package com.example.menu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.menu.databinding.FragmentAccountUserBinding
import com.example.menu.managers.FragmentManagerText


class AccountUser : Fragment() {


    lateinit var binding: FragmentAccountUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAccountUserBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Пользователь")


    }

    companion object {
        @JvmStatic
        fun newInstance() = AccountUser()
    }
}