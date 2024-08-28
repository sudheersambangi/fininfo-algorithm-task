package com.practice.algorithmtask.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.practice.algorithmtask.R
import com.practice.algorithmtask.adapters.NumberAdapter
import com.practice.algorithmtask.databinding.ActivityMainBinding
import com.practice.algorithmtask.utils.Constants.Companion.RULE_EVEN_NUMBERS
import com.practice.algorithmtask.utils.Constants.Companion.RULE_FIBONACCI_SEQUENCE
import com.practice.algorithmtask.utils.Constants.Companion.RULE_ODD_NUMBERS
import com.practice.algorithmtask.utils.Constants.Companion.RULE_PRIME_NUMBERS
import com.practice.algorithmtask.viewModel.NumberViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var numberAdapter: NumberAdapter
    private val numberViewModel: NumberViewModel by viewModels()
    private val list: List<Int> = (1..100).toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        numberAdapter = NumberAdapter(emptyList())

        binding.numberRecyclerView.layoutManager = GridLayoutManager(this, 5)
        binding.numberRecyclerView.adapter = numberAdapter

        val rulesList = listOf(RULE_ODD_NUMBERS, RULE_EVEN_NUMBERS, RULE_PRIME_NUMBERS, RULE_FIBONACCI_SEQUENCE)
        val adapter = ArrayAdapter(this, R.layout.spinner_drop_down, rulesList)

        binding.rulesSpinner.adapter = adapter

        binding.rulesSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    numberViewModel.applyRule(rulesList[position])
                    Toast.makeText(this@MainActivity, rulesList[position], Toast.LENGTH_SHORT).show()
                }
            }

        numberViewModel.liveDataNumber.observe(this, Observer { numberList ->
            numberAdapter.updateNumbers(numberList)
        })
    }
}