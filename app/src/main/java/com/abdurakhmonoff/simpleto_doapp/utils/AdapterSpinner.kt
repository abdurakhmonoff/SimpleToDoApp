package com.abdurakhmonoff.simpleto_doapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.abdurakhmonoff.simpleto_doapp.R
import com.abdurakhmonoff.simpleto_doapp.models.TodoDegree
import kotlinx.android.synthetic.main.child_item1.view.*

class AdapterSpinner(private val list: ArrayList<TodoDegree>) : BaseAdapter() {
    override fun getCount(): Int = list.size
    override fun getItem(position: Int): Any = list[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.child_item1, parent, false)
        itemView.degree_txt.text = list[position].name
        itemView.flag_ic.setImageResource(list[position].img)
        return itemView
    }
}