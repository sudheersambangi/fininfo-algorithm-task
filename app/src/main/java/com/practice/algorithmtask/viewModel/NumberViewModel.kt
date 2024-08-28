package com.practice.algorithmtask.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.algorithmtask.data.NumberItem
import com.practice.algorithmtask.utils.Constants.Companion.RULE_EVEN_NUMBERS
import com.practice.algorithmtask.utils.Constants.Companion.RULE_FIBONACCI_SEQUENCE
import com.practice.algorithmtask.utils.Constants.Companion.RULE_ODD_NUMBERS
import com.practice.algorithmtask.utils.Constants.Companion.RULE_PRIME_NUMBERS

class NumberViewModel : ViewModel() {

    private var _numberData = MutableLiveData<List<NumberItem>>()

    val liveDataNumber: LiveData<List<NumberItem>> = _numberData

    private val numbersList = mutableListOf<NumberItem>()

    init {
        for (number in 1..100) {
            numbersList.add(NumberItem(number, false))
        }
        _numberData.value = numbersList
    }

    fun applyRule(rule: String) {
        numbersList.forEach { it.isHighlighted = false }
        when (rule) {
            RULE_ODD_NUMBERS -> highlightOddNumbers()
            RULE_EVEN_NUMBERS -> highlightEvenNumbers()
            RULE_PRIME_NUMBERS -> highlightPrimeNumbers()
            RULE_FIBONACCI_SEQUENCE -> highlightFibonacciNumber()
        }
        _numberData.value = numbersList
    }

    private fun highlightOddNumbers() {
        numbersList.forEach { if (it.number % 2 != 0) it.isHighlighted = true }
        Log.i("rules",numbersList.toList().toString())
    }

    private fun highlightEvenNumbers() {
        numbersList.forEach { if (it.number % 2 == 0) it.isHighlighted = true }
        Log.i("rules",numbersList.toList().toString())
    }

    private fun highlightPrimeNumbers(){
    numbersList.forEach { if (isPrime(it.number)) it.isHighlighted = true }
    }

    private fun highlightFibonacciNumber(){
        val fibonacciList = generateFibonacciSeries(100)
        numbersList.forEach { if (fibonacciList.contains(it.number)) it.isHighlighted = true }
    }

    private fun isPrime(number: Int) : Boolean {
        if (number < 2) return false
        for (i in 2 until number){
            if (number % i == 0 ){
                return false
            }
        }
        return true
    }

    private fun generateFibonacciSeries(number: Int): List<Int> {
        if (number <= 0) return emptyList()

        val fibonacciList = mutableListOf(0, 1)
        while (fibonacciList.size < number) {
            val nextValue = fibonacciList[fibonacciList.size - 1] + fibonacciList[fibonacciList.size - 2]
            fibonacciList.add(nextValue)
        }
        return fibonacciList
    }


}

