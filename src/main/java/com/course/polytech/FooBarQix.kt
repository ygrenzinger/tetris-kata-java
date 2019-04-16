package com.course.polytech

import java.util.HashMap
import java.util.stream.Collectors
import java.util.stream.IntStream

object FooBarQix {

    private val NUMBER_2_STRING = HashMap<Int, String>()

    init {
        NUMBER_2_STRING[3] = "Foo"
        NUMBER_2_STRING[5] = "Bar"
        NUMBER_2_STRING[7] = "Qix"
    }

    fun convert(number: Int): String {
        val result = NUMBER_2_STRING.keys
                .stream()
                .map { k -> convert(number, k) }
                .collect<String, *>(Collectors.joining())
        return if (result.isEmpty()) number.toString() else result
    }

    private fun convert(number: Int, key: Int?): String {
        return manageDivisibility(number, key) + manageContaining(number, key)
    }

    private fun manageContaining(number: Int, value: Int?): String {
        val charForNumber = Character.forDigit(value!!, 10)
        return number.toString()
                .chars()
                .filter { x -> charForNumber.toInt() == x }
                .mapToObj<String> { c -> NUMBER_2_STRING[value] }
                .collect<String, *>(Collectors.joining())
    }

    private fun manageDivisibility(number: Int, value: Int?): String {
        return if (number % value!! == 0) {
            NUMBER_2_STRING[value]
        } else ""
    }

    @JvmStatic
    fun main(args: Array<String>) {
        IntStream.range(1, 100).forEach { number -> println(convert(number)) }
    }

}
