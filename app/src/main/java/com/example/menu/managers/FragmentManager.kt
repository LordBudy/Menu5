package com.example.menu.managers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.menu.R


object FragmentManager {
    //переменная в которой хранится состояние последнего загруженного фрагмента
    lateinit var currentFrag: Fragment

    //с помощью этой функци переключаем фрагменты
    fun setFragment(newFrag: Fragment, activity: AppCompatActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        //с помощью replace заменяем содержимое Container_frag на newFrag
        transaction.replace(R.id.Container_frag, newFrag)

            // Добавляем фрагмент в стек, чтобы можно было использовать кнопку "назад"
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

        transaction.commit()
        currentFrag = newFrag
    }
}