package com.example.menu.interfaces



interface BasketListener {
    // Метод для обновления общей суммы
    fun updateTotalCost(newTotalCost: Double)

    // Интерфейс обратного вызова для обработки нажатия на кнопку "Minus"
    fun onDeleteClicked(position: Int)
}