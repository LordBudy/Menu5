package com.example.menu.interfaces


// Интерфейс для слушателя корзины, который обновляет общую стоимость и
// обрабатывает удаление элементов

interface BasketListener {
    // Метод для обновления общей стоимости
    fun updateTotalCost(newTotalCost: Double)

    // Метод для обработки нажатия на кнопку "Minus" для удаления элемента из корзины
    fun onDeleteClicked(position: Int)
}