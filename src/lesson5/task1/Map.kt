@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1


// Урок 5: ассоциативные массивы и множества
// Максимальное количество баллов = 14
// Рекомендуемое количество баллов = 9
// Вместе с предыдущими уроками = 33/47

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая (2 балла)
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf(
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val newGrades = mutableMapOf<Int, List<String>>()
    for ((name, grade) in grades) {
        val list = newGrades.getOrDefault(grade, mutableListOf())
        newGrades[grade] = list + name
    }
    return newGrades
}

/**
 * Простая (2 балла)
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for (key in a.keys) {
        if (a[key] != b[key]) return false
    }
    return true
}

/**
 * Простая (2 балла)
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    val pairsToRemove = mutableListOf<String>()

    for ((name, key) in a) {
        if (b[name] == key) {
            pairsToRemove.add(name)
        }
    }

    for (name in pairsToRemove) {
        a.remove(name)
    }
}

/**
 * Простая (2 балла)
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val names = mutableListOf<String>()
    var flag = 0
    for (aNames in a) {
        for (bNames in b) {
            if (names.isEmpty() && aNames == bNames) flag = 1 else {
                for (element in names)
                    if (aNames == bNames && aNames != element && bNames != element) flag = 1
            }
        }
        if (flag == 1) names.add(aNames)
        flag = 0
    }

    return names
}

/**
 * Средняя (3 балла)
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val newMap = mutableMapOf<String, String>()
    for ((nameB, phoneB) in mapB)
        for ((nameA, phoneA) in mapA) {
            val listA = newMap.getOrDefault(nameA, phoneA)
            when {
                nameA == nameB && phoneA == phoneB -> newMap[nameA] = listA
                nameA !in mapB -> newMap[nameA] = listA
                nameB !in mapA -> newMap[nameB] = phoneB
                else -> newMap[nameA] = mapA[nameA] + ", " + phoneB
            }
        }
    return newMap
}

/**
 * Средняя (4 балла)
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val result = mutableMapOf<String, Double>()
    for ((stock1, grade1) in stockPrices) {
        var count = 0
        var gradeSum = 0.0
        result.getOrDefault(stock1, grade1)
        for ((stock2, grade2) in stockPrices)
            if (stock1 == stock2) {
                gradeSum += grade2
                count++
            }
        result[stock1] = gradeSum / count
    }
    return result
}

/**
 * Средняя (4 балла)
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var result = ""
    var min = 0.0
    var flag = 0
    for ((name, pairs) in stuff) {
        if (pairs.first == kind && (min == 0.0 || min > pairs.second)) {
            min = pairs.second
            result = name
            flag = 1
        }
    }
    return if (result == kind || flag == 1) result else null
}

/**
 * Средняя (3 балла)
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    var flag = 0
    if (chars == listOf<Char>() && word == "") return true
    if (chars.isEmpty() || word == "") return false
    for (element1 in word) {
        for (element2 in chars) if (element1 == element2) {
            flag = 0
            break
        } else flag = 1
        if (flag == 1) return false
    }
    return true
}

/**
 * Средняя (4 балла)
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    for (element1 in list) {
        var count = 0
        for (element2 in list)
            if (element1 == element2) count++
        if (count != 1) {
            result.getOrDefault(element1, count)
            result[element1] = count
        }
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Для заданного списка слов определить, содержит ли он анаграммы.
 * Два слова здесь считаются анаграммами, если они имеют одинаковую длину
 * и одно можно составить из второго перестановкой его букв.
 * Скажем, тор и рот или роза и азор это анаграммы,
 * а поле и полено -- нет.
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    if (words.isEmpty()) return false
    for (element1 in words) {
        for (element2 in words) {
            if (element1 == "" && element2 == "") return true
            if (element1 == element2) break
            if (element1.length == element2.length) {
                val map1 = mutableMapOf<Char, Int>()
                val map2 = mutableMapOf<Char, Int>()
                for (i in element1.indices) {
                    val count1 = 1
                    val count2 = 1
                    map1.getOrDefault(element1[i], count1)
                    map2.getOrDefault(element2[i], count2)
                    map1[element1[i]] = count1
                    map2[element2[i]] = count2
                    for ((key, value) in map1) if (element1[i] == key) {
                        map1[key] = value + 1
                    }
                    for ((key, value) in map2) if (element2[i] == key) {
                        map2[key] = value + 1
                    }
                }
                if (map1 == map2) return true
            }
        }
    }
    return false
}

/**
 * Сложная (5 баллов)
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 *
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Оставлять пустой список знакомых для людей, которые их не имеют (см. EvilGnome ниже),
 * в том числе для случая, когда данного человека нет в ключах, но он есть в значениях
 * (см. GoodGnome ниже).
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta"),
 *       "Friend" to setOf("GoodGnome"),
 *       "EvilGnome" to setOf()
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat"),
 *          "Friend" to setOf("GoodGnome"),
 *          "EvilGnome" to setOf(),
 *          "GoodGnome" to setOf()
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val result = mutableMapOf<String, Set<String>>()
    for ((friend1, friendsOfFriend1) in friends)
        for ((friend2, friendsOfFriend2) in friends) {
            val set = friendsOfFriend1.toMutableSet()
            result.getOrDefault(friend1, set)
            if (friend2 in set) {
                set.addAll(friendsOfFriend2)
                set.remove(friend1)
                result[friend1] = set
                break
            }
            result[friend1] = set
        }
    for (friendsOfFriend in friends.values)
        for (element in friendsOfFriend) {
            if (!friends.contains(element)) result[element] = emptySet()
        }
    return result
}

/**
 * Сложная (6 баллов)
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    if (list.isEmpty()) return Pair(-1, -1)
    val map = mutableMapOf<Int, Int>()
    for ((digit, count) in list.withIndex()) {
        map.getOrDefault(digit, count)
        map[count] = digit
    }
    for ((first, count1) in map)
        for ((second, count2) in map) {
            if (count1 == count2) break
            if (first + second == number)
                return if (first >= second) Pair(count2, count1)
                else Pair(count1, count2)
        }
    return Pair(-1, -1)
}

/**
 * Очень сложная (8 баллов)
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    if (treasures.isEmpty() || capacity == 0) return emptySet()
    val map = mutableMapOf<Set<String>, Int>()
    for ((treasure1, pair1) in treasures) {
        var height = capacity
        var cost = 0
        val set = mutableSetOf<String>()
        map.getOrDefault(set, cost)
        if (pair1.first <= height) {
            height -= pair1.first
            cost += pair1.first
            set.add(treasure1)
            map[set] = cost
        } else break
        for ((treasure2, pair2) in treasures) {
            if (treasure1 == treasure2) break
            if (pair2.first <= height) {
                height -= pair2.first
                cost += pair2.first
                set.add(treasure1)
                map[set] = cost
            }
        }
    }
    if (map.isEmpty()) return emptySet()
    var max = 0
    var result = setOf<String>()
    for ((set, cost) in map) {
        if (cost > max) {
            max = cost
            result = set
        }
    }
    return result
}
