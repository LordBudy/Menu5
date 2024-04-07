package com.example.menu.activitis

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.menu.R

import com.example.menu.databinding.ActivityMainBinding
import com.example.menu.db.MainDb
import com.example.menu.db.UserEntity
import com.example.menu.db.UserManager
import com.example.menu.fragments.Account
import com.example.menu.fragments.AccountUser
import com.example.menu.fragments.Basket
import com.example.menu.fragments.Home
import com.example.menu.fragments.Menu
import com.example.menu.fragments.Menu_mini
import com.example.menu.fragments.Search
import com.example.menu.interfaces.BasketImageClickListener
import com.example.menu.interfaces.FragmentTitleListener
import com.example.menu.interfaces.ImageClickListener
import com.example.menu.managers.FragmentManager
import com.example.menu.managers.FragmentManagerText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), FragmentTitleListener, ImageClickListener, BasketImageClickListener {
    // обьявляем переменную binding
    lateinit var binding: ActivityMainBinding
    // Объявляем переменную currentUser
    private var currentUser: UserEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //инициализируем эту переменную binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // указываем что он главный и получаем доступ ко всем id
        setContentView(binding.root)

        // Инициализируем UserManager с контекстом приложения
        UserManager.init(applicationContext)

        //инициализируем фрагмент менеджер для заголовков
        FragmentManagerText.registerFragmentTitleListener(this)

        // Запускаем корутину для получения имени пользователя из базы данных
        lifecycleScope.launch {
            val userName = getNameFromDb()
            setName(userName)
            if (UserManager.isRegistered()) {
                FragmentManager.setFragment(Home.newInstance(), this@MainActivity)
            } else {
                FragmentManager.setFragment(Account.newInstance(), this@MainActivity)
            }
        }

        // Установка слушателя для нижней навигации
        setBottomNavListener()
    }
    // Функция для получения имени пользователя из базы данных
    private suspend fun getNameFromDb(): String? {
        return withContext(Dispatchers.IO) {
            val user = MainDb.getDb(applicationContext).getDao().getUser()
            user?.name // Проверка на null перед вызовом метода getName()
        }
    }
    //--------------------------------------------------------------------------------------------------
    //обрабатываем нажатие кнопки backAtMenu переходиv на фрагмент Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.backAtMenu -> {
                // Возврат на фрагмент Menu при нажатии кнопки "назад"
                FragmentManager.setFragment(Menu.newInstance(), this)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    //--------------------------------------------------------------------------------------------------
    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.btn_home -> {
                    FragmentManager.setFragment(Home.newInstance(), this)

                }

                R.id.btn_search -> {
                    FragmentManager.setFragment(Search.newInstance(), this)
                }

                R.id.btn_basket -> {
                    FragmentManager.setFragment(Basket.newInstance(), this)
                }

                R.id.btn_account -> {
                    FragmentManager.setFragment(AccountUser.newInstance(), this)
                }
            }
            true
        }
    }

    //--------------------------------------------------------------------------------------------------
    // Переопределение метода для обработки нажатия кнопки "назад"
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    //названия фрагментов открытых на данный момент
    override fun onFragmentTitleChanged(title: String) {
        binding.InfoCategory.text = title
    }

    //--------------------------------------------------------------------------------------------------
    override fun onImageClicked(
        id_dish: Int,
        imageUrl: String,
        name: String,
        price: String,
        weight: String,
        description: String
    ) {
        // Показываем второй фрагмент с переданным URL
        val menuMini = Menu_mini()

        // Передаем URL второму фрагменту с использованием аргументов
        val args = Bundle().apply {
            putInt("id", id_dish)
            putString("url", imageUrl)
            putString("name", name)
            putString("price", price)
            putString("weight", weight)
            putString("description", description)
        }
        menuMini.arguments = args

        supportFragmentManager.beginTransaction()
            .add(R.id.Container_frag, menuMini)
            .addToBackStack(null)
            .commit()
    }

    //--------------------------------------------------------------------------------------------------
    override fun onImageAtBasketClicked(
        urlImage: String,
        nameDish: String,
        price: String,
        weight: String
    ) {
        // Показываем Basket фрагмент с переданными данными
        val basket = Basket()

        // Передаем значения Basket фрагменту с использованием аргументов
        val args = Bundle().apply {
            putString("urlBasket", urlImage)
            putString("nameBasket", nameDish)
            putString("priceBasket", price)
            putString("weightBasket", weight)
        }
        basket.arguments = args

        supportFragmentManager.beginTransaction()
            .replace(R.id.Container_frag, basket)
            .addToBackStack(null)
            .commit()
    }
//--------------------------------------------------------------------------------------------------
fun setAvatar(avatar: String?) {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
        // Если разрешение не предоставлено, запросить его
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE_READ_EXTERNAL_STORAGE
        )
    } else {
        // Разрешение уже предоставлено, выполнить необходимые действия
        loadAvatarFromUri(avatar)
    }
}
    //--------------------------------------------------------------------------------------------------
    // Метод для загрузки фотографии из URI и отображения в avatarMain
    private fun loadAvatarFromUri(avatar: String?) {
        avatar?.let { avatarUri ->
            val inputStream = this.contentResolver.openInputStream(Uri.parse(avatarUri))
            val bitmap = BitmapFactory.decodeStream(inputStream)
            binding.avatarMain.setImageBitmap(bitmap)
        }
    }

    //--------------------------------------------------------------------------------------------------

    fun setName(name: String?) {
        // Проверяем, что name не null и не пустое
        name?.let { userName ->
            // Устанавливаем имя пользователя в TextView
            binding.InfoMain.text = userName
        }
    }

    companion object {
        //можно использовать любое целое число здесь
        private const val REQUEST_CODE_READ_EXTERNAL_STORAGE = 123
    }
}
