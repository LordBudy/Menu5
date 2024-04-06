package com.example.menu.fragments

import android.view.Menu
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menu.R
import com.example.menu.databinding.FragmentBasketBinding
import com.example.menu.db.BasketAdapter
import com.example.menu.db.DishEntity
import com.example.menu.db.Dao
import com.example.menu.db.MainDb
import com.example.menu.interfaces.BasketListener
import com.example.menu.managers.FragmentManagerText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Suppress("DEPRECATION")
class Basket : Fragment(), BasketListener {

    lateinit var adapter: BasketAdapter
    lateinit var binding: FragmentBasketBinding
    lateinit var db: MainDb
    lateinit var dao: Dao
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
        adapter = BasketAdapter(this, dao,viewLifecycleOwner.lifecycleScope)
        // Инициализируем RecyclerView
        init()
        // Инициализируем loadDishesFromDb()
       loadDishesFromDb()
        //сообщаем системе, что у фрагмента есть свое собственное меню
        setHasOptionsMenu(true)

    }

    //--------------------------------------------------------------------------------------------------
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
       val dish = adapter.basketList[position]
        val dishPriceDish = dish.price_dish.toInt()
        //передаем в метод deleteDishFromDb удаляемую позицию
       deleteDishFromDb(dish,dishPriceDish)
    }

    //--------------------------------------------------------------------------------------------------
    // Метод для удаления объекта из базы данных и обновления адаптера
    private fun deleteDishFromDb(dish: DishEntity,priceDish: Int) {
       // Сохраняем текущее значение общей суммы перед удалением блюда
        adapter.previousTotalCost = totalCost
        // Запускаем корутину на главном потоке
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            // Удаляем блюдо из базы данных
            dao.deleteDish(dish)
            // Удаляем блюдо из списка в адаптере
            val isRemoved = adapter.basketList.remove(dish)
           if (isRemoved) {
               // Если блюдо было успешно удалено, обновляем общую стоимость и адаптер
               totalCost -= priceDish * dish.quantity // Вычитаем стоимость блюда, учитывая его количество
               // Пересчитываем countPrice для оставшихся блюд в корзине
               adapter.basketList.forEach { it.countPrice = it.price_dish.toInt() * it.quantity }
               // Обновляем адаптер
               adapter.notifyDataSetChanged()
               // Обновляем общую стоимость во фрагменте
               updateTotalCost(totalCost)
           }
        }
    }
    //--------------------------------------------------------------------------------------------------
    // Метод для загрузки блюд из базы данных в адаптер не забыть инициализировать
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
            updateTotalCost(BasketAdapter.calculateTotalCost(dataFromDb))
        }
    }

    //--------------------------------------------------------------------------------------------------
    // Метод для обновления общей суммы в корзине
    override fun updateTotalCost(newTotalCost: Double) {
        // Обновляем отображение общей стоимости в вашем фрагменте
        binding.itogo.text = "Сделать заказ: $newTotalCost руб."
        totalCost = newTotalCost // Обновляем значение totalCost
    }

    //--------------------------------------------------------------------------------------------------
    companion object {
        @JvmStatic
        fun newInstance() = Basket()
    }

}