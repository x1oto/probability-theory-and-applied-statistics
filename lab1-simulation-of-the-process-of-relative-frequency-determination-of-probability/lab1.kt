// імпорт бібліотеки для вводу чисел та класу для генерації рандомних чисел
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

// головна функція
fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // введення кількості кидків
    print("\nВведіть кількість кидків: ")
    val numberOfThrows = scanner.nextInt()

    // масив для збереження кількості кожного числа
    val totalOfNumbers = IntArray(4)

    // масив, який містить рандомні числа
    val generalArray = IntArray(numberOfThrows)

    // масив для збереження суми елементів
    val sumArray = DoubleArray(generalArray.size)

    // генерація рандомних чисел та підрахунок кількості кожного числа
    for (i in generalArray.indices){
        generalArray[i] = createRandomNumber()
        totalOfNumbers[generalArray[i] - 1]++
    }

    // виведення результатів
    printAllResults(generalArray, totalOfNumbers, sumArray)
}

// функція для виведення всіх результатів
private fun printAllResults(
    generalArray: IntArray, // масив, який містить рандомні числа
    totalOfNumbers: IntArray, // масив для збереження кількості кожного числа
    sumArray: DoubleArray // масив для збереження суми елементів
) {
    // виведення масиву рандомних чисел
    println(generalArray.joinToString(separator = ", "))

    // виведення кількості кожного числа
    println("\nКількість одиниць: ${totalOfNumbers[0]}\nКількість двійок: ${totalOfNumbers[1]}\nКількість трійок: ${totalOfNumbers[2]}\nКількість четвірок: ${totalOfNumbers[3]}\n")

    // виведення вибіркового матиматичного сподівання
    println("Вибіркове матиматичне сподівання: " + findSelectiveMathExpectation(generalArray))

    // виведення вибіркової дисперсії
    println("Вибіркова дисперсія: " + findSampleVariance(generalArray, sumArray))

    // виведення вибіркового середньоквадратичного відхилення
    println("Вибіркове середньоквадратичне відхилення: " + findSampleMeanSquareDeviation(generalArray, sumArray))
}


// функція для знаходження вибіркового математичного сподівання
private fun findSelectiveMathExpectation(generalArray: IntArray): Double = generalArray.average()

// функція для знаходження вибіркової дисперсії
private fun findSampleVariance(generalArray: IntArray, sumArray: DoubleArray): Double{
    for (i in generalArray.indices){
        sumArray[i] += (generalArray[i] - generalArray.average()).pow(2)
    }
    return sumArray.average()
}
// знаходимо відхилення від середнього для кожного елементу масиву
private fun findSampleMeanSquareDeviation(generalArray: IntArray, sumArray: DoubleArray): Double = sqrt(findSampleVariance(generalArray, sumArray))

//функція, яке генерує рандомне число, використовуючи клас Random, і повертає його в Int, діапазон [1..5)
private fun createRandomNumber(): Int = Random.nextInt(1, 5)
