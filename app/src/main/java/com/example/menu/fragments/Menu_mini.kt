package com.example.menu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.menu.R
import com.example.menu.databinding.FragmentMenuMiniBinding


class Menu_mini : Fragment() {

lateinit var binding: FragmentMenuMiniBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuMiniBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}