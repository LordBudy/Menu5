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

class Adapter(private val listener: BasketListener,
              private val dao: ImageDao,
              private val lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<Adapter.BasketHolder>() {

    // Список элементов
    val basketList = ArrayList<DishEntity>()
    // для хранения предыдущей общей суммы
    var previousTotalCost = 0.0
    // Общая стоимость всех блюд
    var totalCost = 0.0

    // ViewHolder для элементов списка
    inner class BasketHolder(private val binding: ItemDishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Метод для связывания данных элемента списка с его представлением
        fun bind(dish: DishEntity, countPrice: Double) {
            with(binding) {
                Picasso.get()
                    .load(dish.url_dish)
                    .into(basketIm1)
                nameDish.text = dish.name_dish
                priceDish.text = dish.price_dish
                weight.text = dish.weight_dish
                Count.text = dish.quantity.toString()
                // Установка значения countPrice
                CountPrice.text = countPrice.toString()
//--------------------------------------------------------------------------------------------------
                // Обработчик нажатия на кнопку "Plus"
                plusBtn.setOnClickListener {
                    // Получаем текущее количество из TextView Count
                    var current = Count.text.toString().toInt()
                    // Увеличиваем количество на 1
                    current++
                    // Обновляем текстовое представление Count
                    Count.text = current.toString()
                    val price = dish.price_dish.toDouble()
                    val newPrice = price * current
                    CountPrice.text = newPrice.toString()
                    // Обновляем количество блюда в объекте DishEntity
                    dish.quantity = current
                    // Обновляем countPrice в объекте DishEntity
                    dish.countPrice = newPrice
                    // Сохраняем обновленное количество блюда в базе данных
                    updateDishInDb(dish)
                    // Прибавляем стоимость блюда к общей сумме
                    totalCost += price
                    // Обновляем общую стоимость в фрагменте
                    listener.updateTotalCost(totalCost)
                }

//--------------------------------------------------------------------------------------------------
                // Обработчик нажатия на кнопку "Minus"
                minusBtn.setOnClickListener {
                    var current = Count.text.toString().toInt()
                    if (current > 1) {
                        current--
                        Count.text = current.toString()
                        val price = dish.price_dish.toDouble()
                        val newPrice = price * current
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
                        listener.updateTotalCost(totalCost)
                    } else {
                        // Передаем стоимость удаляемого блюда в метод onDeleteClicked
                        val adapterPosition = adapterPosition
                        listener.onDeleteClicked(adapterPosition)
                    }
                }
            }
        }
    }

    // Создаем ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val binding = ItemDishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketHolder(binding)
    }

    // Привязываем данные к ViewHolder
    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val dish = basketList[position]
        // Получаем значение countPrice из объекта DishEntity
        val countPrice = dish.countPrice
        holder.bind(dish, countPrice)
    }

    // Возвращаем количество элементов списка
    override fun getItemCount(): Int {
        return basketList.size
    }

    // Метод для обновления данных в списке и уведомления адаптера об изменениях
    fun setData(data: List<DishEntity>) {
        basketList.clear()
        basketList.addAll(data)
        // Вычисляем общую стоимость после обновления списка
        totalCost = calculateTotalCost(basketList)
        notifyDataSetChanged()
        // Обновляем общую стоимость во фрагменте
        listener.updateTotalCost(totalCost)
    }

    fun updateDishQuantity(deletedDish: DishEntity, deletedQuantity: Int) {
        val index = basketList.indexOfFirst { it.id == deletedDish.id }
        if (index != -1) {
            val updatedDish = basketList[index]
            updatedDish.quantity -= deletedQuantity
            if (updatedDish.quantity <= 0) {
                // Удаляем блюдо из списка
                basketList.removeAt(index)
                notifyItemRemoved(index)
            } else {
                // Уведомить адаптер об измененном элементе
                notifyItemChanged(index)
            }
        }
    }
    //обновляем данные бд
    private fun updateDishInDb(dish: DishEntity) {
        lifecycleScope.launch {
            // Выполните обновление в фоновом потоке
            withContext(Dispatchers.IO) {
                dao.updateDish(dish)
            }
        }
    }

    companion object {
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