package com.example.menu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menu.databinding.FragmentBasketBinding
import com.example.menu.db.Adapter
import com.example.menu.db.DishEntity
import com.example.menu.db.ImageDao
import com.example.menu.db.MainDb
import com.example.menu.managers.FragmentManagerText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Suppress("DEPRECATION")
class Basket : Fragment(), Adapter.OnDeleteClickListener {
    lateinit var adapter: Adapter
    lateinit var binding: FragmentBasketBinding
    lateinit var db: MainDb
    lateinit var dao: ImageDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Получаем экземпляр базы данных
        db = MainDb.getDb(requireContext())
        // Получаем DAO для выполнения запросов к базе данных
        dao = db.getDao()
        // Устанавливаем название фрагмента с использованием менеджера
        FragmentManagerText.onFragmentTitleChanged("Корзина")
        // Инициализируем адаптер
        // Передаем this в конструктор адаптера для установки слушателя
        adapter = Adapter(this, dao)
        // Инициализируем RecyclerView
        init()
        loadDishesFromDb()
    }

//--------------------------------------------------------------------------------------------------
// при попытки доступа к базе данных на главном потоке,
//может заблокировать пользовательский интерфейс на длительный период времени
//Чтобы избежать это, используем корутины для
// выполнения асинхронных операций базы данных в фоновом режиме.
//--------------------------------------------------------------------------------------------------
    fun loadDishesFromDb() {
        // Запускаем корутину для выполнения операций с базой данных
        viewLifecycleOwner.lifecycleScope.launch {
            // Получаем данные из базы данных асинхронно
            val dataFromDb = withContext(Dispatchers.IO) {
                dao.getAllDish()
            }
            // Передаем полученные данные в адаптер с помощью метода setData()
            adapter.setData(dataFromDb)
        }
    }
    //настраиваем RecyclerView
    private fun init() {
        binding.apply {
            rvBasket.layoutManager = LinearLayoutManager(requireContext())
            rvBasket.adapter = adapter
        }
    }

    // Метод обратного вызова для обработки нажатия на кнопку "Minus" в адаптере
    override fun onDeleteClicked(position: Int) {
        // Получаем удаляемый объект из списка и вызываем метод удаления из базы данных
        val deletedDish = adapter.basketList[position]
        deleteDishFromDb(deletedDish)
    }

    // Метод для удаления объекта из базы данных и обновления адаптера
    private fun deleteDishFromDb(dish: DishEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            dao.deleteDish(dish)
            // После удаления, обновляем список данных в адаптере
            loadDishesFromDb()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = Basket()
    }

}