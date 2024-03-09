package com.example.menu.fragments

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.menu.FragmentTitleListener
import com.example.menu.R

object FragmentManagerText: FragmentTitleListener {

    private var fragmentTitleListener: FragmentTitleListener? = null
    fun registerFragmentTitleListener(listener: FragmentTitleListener) {
        fragmentTitleListener = listener
    }
    override fun onFragmentTitleChanged(title: String) {
        fragmentTitleListener?.onFragmentTitleChanged(title)
    }
}