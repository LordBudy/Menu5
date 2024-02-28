package com.example.menu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.menu.R
import com.example.menu.databinding.FragmentMenuBinding


class Menu : Fragment() {
lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = Menu()
    }
}