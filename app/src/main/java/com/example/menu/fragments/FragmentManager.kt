package com.example.menu.fragments

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.menu.R


object FragmentManager  {
    //переменная в которой хранится состояние последнего загруженного фрагмента
    lateinit var currentFrag: Fragment

//функция с помощью нее переключаем фрагменты
    fun setFragment(newFrag: Fragment, activity: AppCompatActivity){
        val transaction = activity.supportFragmentManager.beginTransaction()
    //с помощью replace заменяем содержимое Container_frag на newFrag
        transaction.replace(R.id.Container_frag, newFrag)
        transaction.commit()
        currentFrag = newFrag
    }
}