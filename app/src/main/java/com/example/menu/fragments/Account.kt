package com.example.menu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.menu.databinding.FragmentAccountBinding
import com.example.menu.db.MainDb
import com.example.menu.db.UserEntity
import com.example.menu.db.UserManager
import com.example.menu.managers.FragmentManager
import com.example.menu.managers.FragmentManagerText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Account :Fragment() {

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



binding.signUp.setOnClickListener{
//Применение .trim() гарантирует, что данные, полученные из полей ввода,
//не будут содержать нежелательных пробелов, которые могут повлиять на логику
   val name = binding.name.text.toString().trim()
   val password = binding.password.text.toString().trim()
   val email = binding.email.text.toString().trim()

    // Проверка на пустые поля
    if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
        // Вывод сообщения об ошибке
        Toast.makeText(requireContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
    }
    // Проверка валидности адреса электронной почты
    if (!isValidEmail(email)) {
        // Вывод сообщения об ошибке
        Toast.makeText(requireContext(), "Неверный адрес электронной почты", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
    }

    val users = UserEntity(
        id = null,
        name = name,
        password = password,
        email = email,
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
    // Сохраняем состояние успешной регистрации пользователя
    UserManager.setRegistered(true)
        // После выполнения операции открываем фрагмент
        FragmentManager.setFragment(
            AccountUser.newInstance(),
            requireActivity() as AppCompatActivity
        )
    }

}

     // Функция для проверки валидности адреса электронной почты
     private  fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Account()
    }

}