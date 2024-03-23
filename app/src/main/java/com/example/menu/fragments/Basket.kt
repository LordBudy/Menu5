package com.example.menu.fragments

import android.view.Menu
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menu.R
import com.example.menu.databinding.FragmentBasketBinding
import com.example.menu.db.Adapter
import com.example.menu.db.DishEntity
import com.example.menu.db.ImageDao
import com.example.menu.db.MainDb
import com.example.menu.interfaces.BasketListener
import com.example.menu.managers.FragmentManagerText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Suppress("DEPRECATION")
class Basket : Fragment(), BasketListener {
    lateinit var adapter: Adapter
    lateinit var binding: FragmentBasketBinding
    lateinit var db: MainDb
    lateinit var dao: ImageDao
    private var totalCost = 0.0 // Общая стоимость всех блюд
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
//сообщаем системе, что у фрагмента есть свое собственное меню
        setHasOptionsMenu(true)

    }

    //надуваем кнопку меню в экшен баре для этого фрагмента
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
//--------------------------------------------------------------------------------------------------

    //настраиваем RecyclerView
    private fun init() {
        binding.apply {
            rvBasket.layoutManager = LinearLayoutManager(requireContext())
            rvBasket.adapter = adapter
        }
    }
    //--------------------------------------------------------------------------------------------------
// при попытки доступа к базе данных на главном потоке,
//может заблокировать пользовательский интерфейс на длительный период времени
//Чтобы избежать это, используем корутины для
// выполнения асинхронных операций базы данных в фоновом режиме.

    // Метод обратного вызова для обработки нажатия на кнопку "Minus" в адаптере
    override fun onDeleteClicked(position: Int) {
        val deletedDish = adapter.basketList[position]
        val deletedPrice = deletedDish.price_dish.toDouble() // Стоимость удаляемого блюда
        // Удаляем блюдо из базы данных
        deleteDishFromDb(deletedDish, deletedPrice)
    }

    // Метод для удаления объекта из базы данных и обновления адаптера
    private fun deleteDishFromDb(dish: DishEntity, deletedPrice: Double) {
        viewLifecycleOwner.lifecycleScope.launch {
            dao.deleteDish(dish)
            // Ожидаем завершения удаления из базы данных
            withContext(Dispatchers.Main) {
                // Первым делом загружаем данные из базы данных в адаптер
                loadDishesFromDb()
                // После этого обновляем общую стоимость корзины
                updateTotalCost(totalCost - deletedPrice) // Вычитаем стоимость удаленного блюда
            }
        }
    }

    // Метод для пересчета общей стоимости всех блюд в корзине
    private fun calculateTotalCost(): Double {
        var sum = 0.0
        for (dish in adapter.basketList) {
            sum += dish.price_dish.toDouble()
        }
        return sum
    }
    // Метод для загрузки блюд из базы данных в адаптер
    private fun loadDishesFromDb() {
        // Запускаем корутину для выполнения операций с базой данных
        viewLifecycleOwner.lifecycleScope.launch {
            // Получаем данные из базы данных асинхронно
            val dataFromDb = withContext(Dispatchers.IO) {
                dao.getAllDish()
            }
            // Передаем полученные данные в адаптер с помощью метода setData()
            adapter.setData(dataFromDb)
            // Пересчитываем общую стоимость корзины после обновления данных в адаптере
            updateTotalCost(calculateTotalCost())
        }
    }

    // Метод для обновления общей суммы
    override fun updateTotalCost(newTotalCost: Double) {
        // Обновляем отображение общей стоимости в вашем фрагменте
        binding.itogo.text = "К оплате: $newTotalCost руб."
        totalCost = newTotalCost // Обновляем значение totalCost
    }

    companion object {
        @JvmStatic
        fun newInstance() = Basket()
    }

}