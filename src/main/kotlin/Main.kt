fun main(args: Array<String>) {
    val result = payment(80000.0, 0.0, "MasterCard")
    when (result) {
        -1.0 -> println("Лимит превышен")
        else -> println("Ваша комиссия :" + result + " рублей ")
    }
}

fun payment(
    itemCount: Double, dayTransaction: Double, typeCard: String = "VK Pay",
    lastSumTransaction: Double = 0.0
): Double {
    when (typeCard) {
        "VK Pay" -> if (itemCount > 15000.0 || itemCount + lastSumTransaction > 40000.0) return -1.0
        else -> if ((dayTransaction + itemCount) > 150000 || itemCount + lastSumTransaction > 600000) return -1.0
    }
    return commission(typeCard, lastSumTransaction, itemCount)
}


fun commission(typeCard: String, lastSumTransaction: Double, itemCount: Double): Double = when (typeCard) {
    "MasterCard", "Maestro" -> if ((300 <= itemCount + lastSumTransaction) &&
        (lastSumTransaction + itemCount <= 75000)
    ) 0.0 else (itemCount * 0.006) + 20

    "Visa", "Мир" -> if ((itemCount * 0.0075) < 35.0) 35.0 else itemCount * 0.0075
    else -> 0.0
}


