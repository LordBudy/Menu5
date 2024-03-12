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
class Basket : Fragment() {
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
        adapter = Adapter()
        // Инициализируем RecyclerView
        init()
//--------------------------------------------------------------------------------------------------
//при попытки доступа к базе данных на главном потоке,
//может заблокировать пользовательский интерфейс на длительный период времени
//Чтобы избежать это, используем корутины для
// выполнения асинхронных операций базы данных в фоновом режиме.
//--------------------------------------------------------------------------------------------------
        // Запускаем корутину для выполнения операций с базой данных
        viewLifecycleOwner.lifecycleScope.launch {
            // Получаем данные из базы данных асинхронно
            val dataFromDb = withContext(Dispatchers.IO) {
                dao.getAllDish()
            }
            // Передайте полученные данные в адаптер с помощью метода setData()
            adapter.setData(dataFromDb)
        }

    }

    private fun init() {
        binding.apply {
            rvBasket.layoutManager = LinearLayoutManager(requireContext())
            rvBasket.adapter = adapter
        }
    }
    // Метод для удаления объекта из базы данных
    private fun deleteDishFromDb(dish: DishEntity) {
        // Запускаем корутину для выполнения операции удаления асинхронно
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                // Вызываем метод удаления из DAO
                dao.deleteDish(dish)
            }
            // После удаления, обновляем список данных в адаптере
            // Для этого, вам может потребоваться вызвать запрос на получение всех данных
            // и передать их в setData метод адаптера
            // Например:
            val newData = withContext(Dispatchers.IO) {
                dao.getAllDish()
            }
            adapter.setData(newData)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = Basket()
    }
}