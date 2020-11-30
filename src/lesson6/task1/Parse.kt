@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.NumberFormatException


// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    if (parts.size != 3) return ""
    val firstPart: Int
    val secondPart: Int
    val thirdPart: Int
    try {
        thirdPart = if (parts[2].toInt() > 0) parts[2].toInt() else return ""
        secondPart = when (parts[1]) {
            "января" -> 1
            "февраля" -> 2
            "марта" -> 3
            "апреля" -> 4
            "мая" -> 5
            "июня" -> 6
            "июля" -> 7
            "августа" -> 8
            "сентября" -> 9
            "октября" -> 10
            "ноября" -> 11
            "декабря" -> 12
            else -> return ""
        }
        val flag = if (thirdPart % 400 == 0 || thirdPart % 4 == 0
            && thirdPart % 100 != 0
        ) 1 else 0
        firstPart = when {
            (secondPart == 1 || secondPart == 3 || secondPart == 5 || secondPart == 7
                    || secondPart == 8 || secondPart == 10 || secondPart == 12)
                    && parts[0].toInt() in 1..31 -> parts[0].toInt()
            (secondPart == 4 || secondPart == 6 || secondPart == 9 || secondPart == 11)
                    && parts[0].toInt() in 1..30 -> parts[0].toInt()
            secondPart == 2 && (flag == 1 && parts[0].toInt() in 1..29
                    || flag == 0 && parts[0].toInt() in 1..28) -> parts[0].toInt()
            else -> return ""
        }
    } catch (e: NumberFormatException) {
        return ""
    }
    return String.format("%02d.%02d.%d", firstPart, secondPart, thirdPart)
}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    if (parts.size != 3) return ""
    var firstPart: String
    val secondPart: String
    val thirdPart: String
    try {
        thirdPart = if (parts[2].toInt() > 0) parts[2] else return ""
        secondPart = when (parts[1].toInt()) {
            1 -> "января"
            2 -> "февраля"
            3 -> "марта"
            4 -> "апреля"
            5 -> "мая"
            6 -> "июня"
            7 -> "июля"
            8 -> "августа"
            9 -> "сентября"
            10 -> "октября"
            11 -> "ноября"
            12 -> "декабря"
            else -> return ""
        }
        val flag = if (parts[2].toInt() % 400 == 0 || parts[2].toInt() % 4 == 0
            && parts[2].toInt() % 100 != 0
        ) 1 else 0
        firstPart = when {
            (secondPart == "января" || secondPart == "марта" || secondPart == "мая" || secondPart == "июля"
                    || secondPart == "августа" || secondPart == "октября" || secondPart == "декабря")
                    && parts[0].toInt() in 1..31 -> parts[0]
            (secondPart == "апреля" || secondPart == "июня" || secondPart == "сентября" || secondPart == "ноября")
                    && parts[0].toInt() in 1..30 -> parts[0]
            secondPart == "февраля" && (flag == 1 && parts[0].toInt() in 1..29
                    || flag == 0 && parts[0].toInt() in 1..28) -> parts[0]
            else -> return ""
        }
        if (firstPart.toInt() < 10) firstPart = firstPart.drop(1)
    } catch (e: NumberFormatException) {
        return ""
    }
    return String.format("%s %s %s", firstPart, secondPart, thirdPart)
}

/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    var result = ""
    var flagOpen = 0
    var count = 0
    for (i in phone.indices) {
        when (phone[i]) {
            '+' -> {
                if (result.isEmpty()) result += phone[i] else return ""
            }
            '(' -> flagOpen = 1
            ')' -> if (flagOpen == 0 || count == 0) return "" else continue
            in "0123456789" -> {
                if (flagOpen == 1) count++
                result += phone[i]
            }
            in "- " -> continue
            else -> return ""
        }
    }
    return result
}

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (expression.isEmpty() || expression[0] == ' ') throw IllegalArgumentException()
    val setOfDigits = expression.split(' ')
    var result = 0
    var digit: Int
    var flag = 1
    for (i in setOfDigits.indices) {
        when {
            i % 2 == 0 -> {
                digit = try {
                    if (setOfDigits[i][0] == '+' || setOfDigits[i][0] == '-') throw IllegalArgumentException()
                    setOfDigits[i].toInt()
                } catch (e: NumberFormatException) {
                    throw IllegalArgumentException()
                }
                if (flag == 1) result += digit else result -= digit
            }
            i % 2 == 1 -> if (setOfDigits[i] == "+" || setOfDigits[i] == "-")
                flag = if (setOfDigits[i] == "+") 1 else 0
            else throw IllegalArgumentException()

        }
    }
    return result
}

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var index = 0
    val setOfWords = str.split(' ')
    for (i in 0 until setOfWords.size - 1) {
        if (setOfWords[i].equals(setOfWords[i + 1], true)) return index
        index += setOfWords[i].length + 1
    }

    return -1
}

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String = TODO()

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    var result = 0
    var secondDigit = 0
    val romanToDigit = mapOf('M' to 1000, 'D' to 500, 'C' to 100, 'L' to 50, 'X' to 10, 'V' to 5, 'I' to 1)
    for (i in roman.indices) {
        val firstDigit = if (romanToDigit.contains(roman[i])) romanToDigit.getValue(roman[i]) else return -1
        if (i == 0) {
            if (roman.length == 1) return firstDigit
            else secondDigit = firstDigit
        } else {
            when {
                roman[i - 1] == roman[i] -> result += firstDigit
                romanToDigit.getValue(roman[i - 1]) > firstDigit -> {
                    result += secondDigit
                    secondDigit = firstDigit
                }
                else -> result = result + firstDigit - 2 * secondDigit
            }
        }
        if (i == roman.length - 1) {
            result += secondDigit
            return result

        }
    }
    return -1
}

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    if (commands.length == 1) if (commands[0] !in " -+><") throw IllegalArgumentException()
    val result = mutableListOf<Int>()
    var currentCell = 0
    while (currentCell <= cells - 1) {
        result.add(0)
        currentCell++
    }
    if (commands.isEmpty()) return result

    var currentClose = 0
    var currentOpen = 0
    var flag = 0
    val mapOfSquareBracket = mutableMapOf<Int, Int>()
    for (i in 0 until commands.length - 1) {
        var repetitive = 0
        if (commands[i] in " -+><[]") {
            if (commands[i] == '[') {
                flag = 1
                for (j in i + 1 until commands.length)
                    when (commands[j]) {
                        '[' -> repetitive++
                        ']' -> if (repetitive == 0) {
                            mapOfSquareBracket[i] = j
                            mapOfSquareBracket[j] = i
                            flag = 0
                            break
                        } else repetitive--
                    }
            }
        } else throw IllegalArgumentException()
        currentOpen += if (commands[i] == '[') 1 else 0
        currentClose += if (commands[i] == ']') 1 else 0
        if (flag == 1) throw IllegalArgumentException()
    }
    if (commands.last() !in " +-><[]") throw IllegalArgumentException()
    if (commands.last() == '[') currentOpen++ else if (commands.last() == ']') currentClose++
    if (currentClose != currentOpen) throw IllegalArgumentException()

    currentCell = cells / 2
    var numberOfActions = 0
    var index = 0
    println(currentCell)
    while (index in commands.indices) {
        if (numberOfActions == limit) return result
        when (commands[index]) {
            '>' -> {
                currentCell++
                if (currentCell >= cells) throw IllegalStateException()
            }
            '<' -> {
                currentCell--
                if (currentCell < 0) throw IllegalStateException()
            }
            '+' -> result[currentCell]++
            '-' -> result[currentCell]--
            '[' -> if (result[currentCell] == 0) {
                index = mapOfSquareBracket[index]!!
            }
            ']' -> if (result[currentCell] != 0) index = mapOfSquareBracket.getValue(index)
        }
        numberOfActions++
        index++
    }
    println(currentCell)
    return result
}