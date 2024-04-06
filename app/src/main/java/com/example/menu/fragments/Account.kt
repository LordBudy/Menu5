package com.example.menu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.menu.databinding.FragmentAccountBinding
import com.example.menu.db.MainDb
import com.example.menu.db.UserEntity
import com.example.menu.managers.FragmentManager
import com.example.menu.managers.FragmentManagerText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Account :Fragment() {
var name:String? = null
var password:String? = null
var email:String? = null

lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAccountBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Аккаунт")

        name = binding.name.text.toString()
        password = binding.password.text.toString()
        email = binding.email.text.toString()

binding.signUp.setOnClickListener{
    val users = UserEntity(
        id = null,
        name = name.toString(),
        password = password.toString(),
        email = email.toString(),
        quantity = 0
    )
    // Получаем экземпляр базы данных
    val db = MainDb.getDb(requireContext())
    // Запускаем корутину для в базу данных
    lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.getDao().insertUser(users)
            }
        }
        // После выполнения операции открываем фрагмент
        FragmentManager.setFragment(
            AccountUser.newInstance(),
            requireActivity() as AppCompatActivity
        )
    }
}

    companion object {
        @JvmStatic
        fun newInstance() = Account()
    }

}