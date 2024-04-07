package com.example.menu.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.menu.R
import com.example.menu.activitis.MainActivity
import com.example.menu.databinding.FragmentAccountUserBinding
import com.example.menu.db.BasketAdapter
import com.example.menu.db.Dao
import com.example.menu.db.MainDb
import com.example.menu.db.UserEntity
import com.example.menu.db.UserManager
import com.example.menu.managers.FragmentManagerText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AccountUser : Fragment() {
    lateinit var db: MainDb
    lateinit var dao: Dao

    lateinit var binding: FragmentAccountUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Пользователь")
// Получаем экземпляр базы данных
        db = MainDb.getDb(requireContext())
        // Получаем DAO для выполнения запросов к базе данных
        dao = db.getDao()
        // Настройка прослушивателя кликов для просмотра изображения аватара
        binding.avatarDB.setOnClickListener {
            openGalleryForImage()
        }

        binding.deleteDB.setOnClickListener {
            currentUser?.let { deleteUserFromDb(it) }
        }
// Загружаем данные пользователя после создания представления
        loadUserFromDb()

        // Установка слушателя на кнопку сохранения
        binding.saveDB.setOnClickListener {
            // Обновляем данные пользователя в базе данных
            updateUserInDb()
            // Передаем изображение и имя пользователя в MainActivity
            (requireActivity() as MainActivity).apply {
                setAvatar(currentUser?.avatar)
                setName(currentUser?.name)
            }
        }
    }
    // Объявляем переменную для текущего пользователя
    private var currentUser: UserEntity? = null

   
    //метод для обновления данных пользователя в базе данных
    private fun updateUserInDb() {
        currentUser?.let { user ->
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    dao.updateUser(user)
                }
            }
        }
    }
    // Способ открытия фотогалереи для выбора изображения
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    // Средство запуска результатов действий для обработки выбранного изображения
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImageUri = data?.data
            selectedImageUri?.let {
                val inputStream = requireActivity().contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.avatarDB.setImageBitmap(bitmap)

                // Сохранение URI изображения в базу данных
                currentUser?.avatar = selectedImageUri.toString()
                updateUserInDb()
            }
        }
    }
    // Обновление информации о пользователе в методе loadUserFromDb():
    fun loadUserFromDb() {
        // Запускаем корутину для выполнения операций с базой данных
        viewLifecycleOwner.lifecycleScope.launch {
            // Получаем данные из базы данных асинхронно
            currentUser = withContext(Dispatchers.IO) {
                MainDb.getDb(requireContext()).getDao().getUser()
            }
            // Проверяем, что пользователь был загружен
            currentUser?.let { user ->
                // Обновляем представления макета с полученными данными о пользователе
                binding.apply {
                    avatarDB.setImageDrawable(null)
                    nameDB.text = user.name
                    emailDB.text = user.email
                    polDB.setText(user.polDB ?: "")
                    ageDB.setText(user.ageDB?.toString() ?: "")
                    sizeDB.setText(user.sizeDB?.toString() ?: "")

                    // Если у пользователя есть URI изображения, отобразите его
                    user.avatar?.let { avatarUri ->
                        val inputStream = requireActivity().contentResolver.openInputStream(Uri.parse(avatarUri))
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        avatarDB.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
    private fun deleteUserFromDb(currentUser: UserEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                // Удаление пользователя из базы данных
                MainDb.getDb(requireContext()).getDao().deleteUser(currentUser)
                // Очистка информации о пользователе из менеджера пользователя
                UserManager.clearUser()
            }

            // Переход на экран регистрации после удаления пользователя
            (requireActivity() as MainActivity).apply {
                setAvatar(null) // Очистка аватара
                setName(null) // Очистка имени пользователя
                // Переход на экран регистрации
                supportFragmentManager.beginTransaction()
                    .replace(R.id.Container_frag, Account.newInstance())
                    .commit()
            }
            // Обновляем поле InfoMain на пустую строку
            (requireActivity() as MainActivity).setName("Info")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AccountUser()
    }
}