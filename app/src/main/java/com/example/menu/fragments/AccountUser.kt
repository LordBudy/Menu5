package com.example.menu.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.menu.R
import com.example.menu.activitis.MainActivity
import com.example.menu.databinding.FragmentAccountUserBinding
import com.example.menu.db.Dao
import com.example.menu.db.MainDb
import com.example.menu.db.UserEntity
import com.example.menu.db.UserManager
import com.example.menu.managers.FragmentManagerText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Suppress("DEPRECATION")
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
        FragmentManagerText.onFragmentTitleChanged("настройки пользователя")
// Получаем экземпляр базы данных
        db = MainDb.getDb(requireContext())
        // Получаем DAO для выполнения запросов к базе данных
        dao = db.getDao()
        // прослушиватель кликов для изображения аватара
        binding.avatarDB.setOnClickListener {
            openStorageForImage()
        }

        binding.deleteDB.setOnClickListener {
            currentUser?.let { deleteUserFromDb(it) }
        }
        checkStoragePermission()
// Загружаем данные пользователя после создания представления
        loadUserFromDb()

        // Установка слушателя на кнопку сохранения
        binding.saveDB.setOnClickListener {
            val pol = binding.polDBedit.text.toString()
            val age = binding.ageDBedit.text.toString()
            val size = binding.sizeDBedit.text.toString()

            // Проверяем, есть ли хотя бы одно заполненное поле
            if (pol.isNotEmpty() || age.isNotEmpty() || size.isNotEmpty() || currentUser?.avatar != null) {
                // Обновляем данные текущего пользователя
                currentUser?.apply {
                    polDB = pol
                    ageDB = if (age.isNotEmpty()) age.toInt() else null
                    sizeDB = if (size.isNotEmpty()) size.toInt() else null
                    // Обновляем данные пользователя в базе данных
                    updateUserInDb()
                }

                // Обновляем представления текстовых полей с новыми данными
                binding.apply {
                    polDB.text = pol
                    ageDB.text = age
                    sizeDB.text = size
                }

                // Передаем изображение и имя пользователя в MainActivity
                (requireActivity() as MainActivity).apply {
                    setAvatar(currentUser?.avatar)
                    setName(currentUser?.name)
                }
            } else {
                // Если нет ни одного заполненного поля или загруженного фото
            // что нужно заполнить хотя бы одно поле или загрузить фото
                Toast.makeText(
                    requireContext(),
                    "Пожалуйста, заполнить хотя бы одно поле или загрузить фото",
                    Toast.LENGTH_SHORT
                )
                    .show()
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
        private fun openStorageForImage() {
            // Создаем намерение для выбора изображения из любого доступного источника
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*" // Указываем тип файлов, которые можно выбрать (в данном случае, изображения)
            }
            // Запускаем активность для выбора изображения из любого доступного источника
            resultLauncher.launch(intent)
        }

    // Обработка результата выбора изображения
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { uri ->
                // Запуск активности обрезки изображения с использованием Android Image Cropper
               // startCropActivity(uri)
            }
        }
    }
    // Метод для запуска активности обрезки изображения
//    private fun startCropActivity(uri: Uri) {
//        CropImage.activity(uri)
//            .start(requireContext(), this)
//    }
//    // Обработка результата обрезки изображения
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            val result = CropImage.getActivityResult(data)
//            if (resultCode == Activity.RESULT_OK) {
//                val resultUri: Uri? = result.uri
//                // Здесь вы можете обработать обрезанное изображение
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                val error = result.error
//                // Обработка ошибки при обрезке изображения
//            }
//        }
//    }

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
                        polDB.text = user.polDB
                        ageDB.text = user.ageDB.toString()
                        sizeDB.text = user.sizeDB.toString()
                        // Если у пользователя есть URI изображения, отобразить его
                        user.avatar?.let { avatarUri ->
                            val inputStream = requireActivity().contentResolver.openInputStream(
                                Uri.parse(avatarUri)
                            )
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

        private fun checkStoragePermission() {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Разрешение не предоставлено, запросить его
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_STORAGE_PERMISSION
                )
            } else {
                // Разрешение уже предоставлено, выполнить необходимые действия
                //openGalleryForImage()
            }
        }

        @Deprecated("Deprecated in Java")
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Разрешение предоставлено, открываем галерею

                } else {
                    // Разрешение не предоставлено, вы можете выполнить дополнительные действия здесь, если необходимо
                    // Например, вы можете показать сообщение об ошибке или попытаться запросить разрешение снова
                }
            }
        }

        companion object {
            @JvmStatic
            fun newInstance() = AccountUser()
            private const val REQUEST_CODE_STORAGE_PERMISSION = 100
        }
    }