package com.example.menu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.menu.MainActivity
import com.example.menu.R


object FragmentManager  {
    //переменная в которой хранится состояние последнего загруженного фрагмента
    private var currentFrag: BaseFragment? = null

//функция с помощью нее переключаем фрагменты
    fun setFragment(newFrag: BaseFragment,activity: AppCompatActivity){
        val transaction = activity.supportFragmentManager.beginTransaction()
    //с помощью replace заменяем содержимое Container_frag на newFrag
        transaction.replace(R.id.Container_frag, newFrag)
        transaction.commit()
        currentFrag = newFrag
    }
}