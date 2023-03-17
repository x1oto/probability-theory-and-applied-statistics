import kotlin.random.Random

// масив кількості граней у кожного з дайсів
private val diceArray = intArrayOf(4, 6, 8, 10, 12, 20)

// масив початкових імовірностей для кожного дайсу
private var startChanceArray = DoubleArray(diceArray.size) { 1.0 / 6.0 }

fun main(args: Array<String>) {
    // масив ймовірностей випадання числа на кожному з дайсів
    val dropChanceArray = DoubleArray(diceArray.size)

    // масив добутків початкових імовірностей і ймовірностей випадання числа на кожному з дайсів
    val multipleArrays = DoubleArray(diceArray.size)

    // масив кінцевих імовірностей для кожного дайсу
    val endChanceArray = DoubleArray(diceArray.size)

    // випадково вибираємо дайс
    val chosenDice = diceArray.random()
    println("вибраний дайс: $chosenDice")

    // змінна для збереження суми добутків початкових імовірностей і ймовірностей випадання числа
    var sum = 0.0

    // повторюємо експеримент 10 разів
    repeat(10) {
        isExist(chosenDice, dropChanceArray) // генеруємо число на вибраному дайсі та обчислюємо ймовірності випадання на кожному дайсі
        sum = multipleAndSumArray(multipleArrays, dropChanceArray) // обчислюємо добутки початкових імовірностей і ймовірностей випадання числа на кожному дайсі та їх суму
        divideArrays(endChanceArray, multipleArrays, sum) // обчислюємо кінцеві імовірності для кожного дайсу
        for (i in diceArray.indices) {
            // виводимо інформацію про кожний дайс: кількість граней, початкову імовірність, ймовірність випадання числа, добуток, кінцеву імовірність
            println("Дайс: ${diceArray[i]}      Початкова імов.: ${startChanceArray[i]}     Ймовірність випадання: ${dropChanceArray[i]}    Добуток: ${multipleArrays[i]}      Кін. імов.: ${endChanceArray[i]}")
            // виведення загальної суми ймовірностей після кидка останнього кубика
            if(i == 5){
                println("Сума: $sum")
            }
        }
        // викликаємо функцію для заміни даних
        changeOldData(endChanceArray)
    }
}

// функція, яка копіює дані з endChanceArray, і підставляє в startChanceArray
private fun changeOldData(endChanceArray: DoubleArray) {
    startChanceArray = endChanceArray.copyOf()
}

// функція, для знаходження кінцевого результату, діленням multipleArrays[i] на sum
private fun divideArrays(endChanceArray: DoubleArray, multipleArrays: DoubleArray, sum: Double) {
    for (i in diceArray.indices) {
        endChanceArray[i] = multipleArrays[i] / sum
    }
}

// функція, в якій ми перемножуємо масиви, і записуємо в суму результат їх множення
private fun multipleAndSumArray(multipleArrays: DoubleArray, dropChanceArray: DoubleArray): Double {
    var sum = 0.0
    for (i in diceArray.indices) {
        multipleArrays[i] = startChanceArray[i] * dropChanceArray[i]
        sum += multipleArrays[i]
    }
    return sum
}

// функція для первірки чи існує ймовірність, тобто, якщо випавша грань > розмірність кубика, то ймовірність випадання такої грані на цьому кубику = 0
// в іншому випадку 1.0 / diceArray[i]
private fun isExist(chosenDice: Int, dropChanceArray: DoubleArray) {
    val number = Random.nextInt(1, chosenDice + 1)
    println("вибрана грань: $number \n")
    for (i in diceArray.indices) {
        dropChanceArray[i] = if (number <= diceArray[i]) 1.0 / diceArray[i] else 0.0
    }
}
