package com.example.menu.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.menu.databinding.FragmentAccountUserBinding
import com.example.menu.db.BasketAdapter
import com.example.menu.db.Dao
import com.example.menu.db.MainDb
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

    }

    // Метод для загрузки блюд из базы данных в адаптер не забыть инициализировать
    fun loadUserFromDb() {
        // Запускаем корутину для выполнения операций с базой данных
        viewLifecycleOwner.lifecycleScope.launch {
            // Получаем данные из базы данных асинхронно
            val user = withContext(Dispatchers.IO) {
                dao.getUser()
            }
            // Обновляем представления макета с полученными данными о пользователе
            binding.apply {
                avatarDB.setImageDrawable(null) // Очистить изображение (если требуется)
                nameDB.text = user.name
                emailDB.text = user.email
                polDB.setText(user.polDB ?: "")
                ageDB.setText(user.ageDB?.toString() ?: "")
                sizeDB.setText(user.sizeDB?.toString() ?: "")
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
            // Обработайте выбранный URI изображения здесь
            // Например, вы можете отобразить его в ImageView.
            selectedImageUri?.let {
                val inputStream = requireActivity().contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.avatarDB.setImageBitmap(bitmap)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AccountUser()
    }
}