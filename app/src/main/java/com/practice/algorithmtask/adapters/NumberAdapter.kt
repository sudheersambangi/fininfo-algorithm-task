package com.practice.algorithmtask.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.algorithmtask.data.NumberItem
import com.practice.algorithmtask.databinding.ItemCardBinding

class NumberAdapter(private var numberList : List<NumberItem>) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {


    class NumberViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NumberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return numberList.size
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val number = numberList[position]
        holder.binding.numberItem.text = number.number.toString()
        holder.binding.numberItem.setBackgroundColor(if(number.isHighlighted) Color.GREEN else Color.WHITE )
    }

    fun updateNumbers(newNumbers : List<NumberItem>){
        val diffCallback = NumberDiffCallback(numberList, newNumbers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        numberList = newNumbers
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }
}