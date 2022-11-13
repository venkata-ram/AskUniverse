package com.venkatapps.askuniverse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class OptionsRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<OptionsRecyclerViewAdapter.ViewHolder>() {

    private val options = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.options_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.optionTextView.text = options[position]
    }

    fun setData(options : ArrayList<String>){
        val diffCallback = OptionsDiffUtilsCallback(this.options,options)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.options.clear()
        this.options.addAll(options)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount(): Int {
        return options.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var optionTextView : TextView = itemView.findViewById(R.id.option_text)
    }

}

