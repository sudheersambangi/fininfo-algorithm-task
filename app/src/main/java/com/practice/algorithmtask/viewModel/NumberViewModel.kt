package com.practice.algorithmtask.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.algorithmtask.data.NumberItem

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
            "Odd Numbers" -> highlightOddNumbers()
            "Even Numbers" -> highlightEvenNumbers()
            "Prime Numbers" -> highlightPrimeNumbers()
            "Fibonacci Numbers" -> highlightFibonacciNumber()
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
}

