package com.abdurakhmonoff.simpleto_doapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.abdurakhmonoff.simpleto_doapp.R
import com.abdurakhmonoff.simpleto_doapp.models.Todo
import kotlinx.android.synthetic.main.child_item2.view.*
import kotlinx.android.synthetic.main.group_item2.view.*

class AdapterELV(
    private val titleList: ArrayList<String>,
    private val map: HashMap<String, List<Todo>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int = titleList.size

    override fun getChildrenCount(groupPosition: Int): Int = map[titleList[groupPosition]]!!.size

    override fun getGroup(groupPosition: Int): Any = titleList[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        map[titleList[groupPosition]]!![childPosition].name

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val itemView = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.group_item2,parent,false)
        itemView.group2_txt.text = titleList[groupPosition]
        return itemView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val itemView = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.child_item2,parent,false)
        itemView.child2_txt.text = map[titleList[groupPosition]]!![childPosition].name
        return itemView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}