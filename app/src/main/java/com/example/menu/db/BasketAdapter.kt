package com.example.menu.db

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.databinding.ItemDishBinding
import com.example.menu.interfaces.BasketListener
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Адаптер для RecyclerView

class BasketAdapter(
    private val listener: BasketListener,
    private val dao: Dao,
    private val lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    // Список элементов
    val basketList = mutableListOf<DishEntity>()
    // для хранения предыдущей общей суммы
    var previousTotalCost = 0.0


    // ViewHolder для элементов списка
    inner class BasketHolder(private val binding: ItemDishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Метод для связывания данных элемента списка с его представлением
        fun bind(dish: DishEntity) {
            with(binding) {
                Picasso.get()
                    .load(dish.url_dish)
                    .into(basketIm1)

                // название блюда
                nameDish.text = dish.name_dish
                // фиксированная цена блюда
                priceDish.text = dish.price_dish
                //вес блюда
                weight.text = dish.weight_dish
                      //количество одгого вида бюда
                Count.text = dish.quantity.toString()
// Установка значения countPrice где будет сумироваться цена одного и того же блюда
                val countPriceValue = dish.price_dish.toInt() * dish.quantity
                CountPrice.text = countPriceValue.toString()
//--------------------------------------------------------------------------------------------------
                // Обработчик нажатия на кнопку "Plus"
                plusBtn.setOnClickListener {
                    // Получаем текущее количество из TextView Count
                    var current = Count.text.toString().toInt()
                    // Увеличиваем количество на 1
                    current++
                    // Обновляем текстовое представление Count
                    Count.text = current.toString()
                    val price = dish.price_dish.toInt()
                    //умножаем цену на количество блюд при каждом нажатии на плюс
                    val newPrice = price * current.toInt()
                    //обновляем текущую цену с учетом количества блюд
                    CountPrice.text = newPrice.toString()
                    // Обновляем количество блюда в DishEntity
                    dish.quantity = current
                    // Обновляем countPrice в объекте DishEntity
                    dish.countPrice = newPrice
                    // Сохраняем обновленное количество блюда в базе данных
                    updateDishInDb(dish)
                    // Прибавляем стоимость блюда к общей сумме
                    totalCost += price
                    // Обновляем общую стоимость в фрагменте Basket
                    updateTotalCost()
                }

//--------------------------------------------------------------------------------------------------
                // Обработчик нажатия на кнопку "Minus"
                minusBtn.setOnClickListener {
                    var current = Count.text.toString().toInt()
                    if (current > 1) {
                        current--
                        Count.text = current.toString()
                        val price = dish.price_dish.toInt()
                        val newPrice = price * current.toInt()
                        CountPrice.text = newPrice.toString()
                        // Обновляем количество блюда в объекте DishEntity
                        dish.quantity = current
                        // Обновляем countPrice в объекте DishEntity
                        dish.countPrice = newPrice
                        // Сохраняем обновленное количество блюда в базе данных
                        updateDishInDb(dish)
                        // Отнимаем стоимость блюда от общей суммы
                        totalCost -= price
                        // Обновляем общую стоимость в фрагменте
                        updateTotalCost()
                    } else {
                        // Передаем позицию удаляемого блюда в метод onDeleteClicked
                        val position = adapterPosition
                        listener.onDeleteClicked(position)
                    }
                }
            }
        }
    }

    //--------------------------------------------------------------------------------------------------
    // Создаем ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val binding = ItemDishBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BasketHolder(binding)
    }

    // Привязываем данные к ViewHolder
    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        holder.bind(basketList[position])
    }

    // Возвращаем количество элементов списка
    override fun getItemCount(): Int {
        return basketList.size
    }
//--------------------------------------------------------------------------------------------------
    // Метод для обновления данных в списке и уведомления адаптера об изменениях
fun setData(data: List<DishEntity>) {
    // Очищаем корзину перед добавлением новых данных
    basketList.clear()
    // Добавляем новые данные в корзину
    basketList.addAll(data)

    // Вычисляем общую стоимость с учётом новых данных
    totalCost = calculateTotalCost(basketList)
    // Уведомляем адаптер о изменениях
    notifyDataSetChanged()
    // Обновляем общую стоимость во фрагменте
    updateTotalCost()
}
    private fun updateTotalCost() {
        totalCost = calculateTotalCost(basketList)
        listener.updateTotalCost(totalCost)
    }
    //обновляем данные бд
    private fun updateDishInDb(dish: DishEntity) {
        lifecycleScope.launch {
            // Выполните обновление в фоновом потоке
            withContext(Dispatchers.IO) {
                dao.updateDish(dish)
            }
            // После обновления данных в базе, пересчитываем значение CountPrice
            dish.countPrice = dish.price_dish.toInt() * dish.quantity
            // После обновления данных в базе, обновляем общую стоимость
            updateTotalCost()
            // Обновляем отображение CountPrice в пользовательском интерфейсе
            notifyDataSetChanged()
        }
    }

    companion object {
        // Общая стоимость всех блюд
        var totalCost = 0.0
        // Статический метод для вычисления общей стоимости всех блюд
        fun calculateTotalCost(basketList: List<DishEntity>): Double {
            var sum = 0.0
            for (dish in basketList) {
                sum += dish.price_dish.toDouble() * dish.quantity
            }
            return sum
        }
    }
}