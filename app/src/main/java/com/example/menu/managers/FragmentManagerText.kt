package com.example.menu.managers

import com.example.menu.interfaces.FragmentTitleListener

object FragmentManagerText: FragmentTitleListener {

    private var fragmentTitleListener: FragmentTitleListener? = null
    fun registerFragmentTitleListener(listener: FragmentTitleListener) {
        fragmentTitleListener = listener
    }
    override fun onFragmentTitleChanged(title: String) {
        fragmentTitleListener?.onFragmentTitleChanged(title)
    }
}