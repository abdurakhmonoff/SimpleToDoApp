package com.abdurakhmonoff.simpleto_doapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import com.abdurakhmonoff.simpleto_doapp.models.Todo
import com.abdurakhmonoff.simpleto_doapp.models.TodoDegree
import com.abdurakhmonoff.simpleto_doapp.utils.AdapterSpinner
import com.abdurakhmonoff.simpleto_doapp.utils.MySharedPrefs
import kotlinx.android.synthetic.main.activity_add_todo.*

class AddTodo : AppCompatActivity() {
    private lateinit var degreeList:ArrayList<TodoDegree>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        degreeList = arrayListOf(
            TodoDegree("Urgent",R.drawable.ic_flag_red),
            TodoDegree("High",R.drawable.ic_flag_yellow),
            TodoDegree("Normal",R.drawable.ic_flag_blue),
            TodoDegree("Low",R.drawable.ic_flag_gray)
        )

        spinnerDegree.adapter = AdapterSpinner(degreeList)

        save_todo.setOnClickListener {
            val name = todo_name_input.text.toString()
            val desc = todo_desc_input.text.toString()
            val degree = degreeList[spinnerDegree.selectedItemPosition]
            val createDate = create_date_input.text.toString()
            val deadline = deadline_input.text.toString()
            val allTodo = MySharedPrefs(this).getTodo()
            if (name!="" && desc!="" && createDate!="" && deadline!=""){
                val nodeTxt = allTodo.find { it.name == name }
                if (nodeTxt == null){
                    val todo = Todo(name,desc,degree,createDate,deadline)
                    MySharedPrefs(this).addTodo(todo)
                    finish()
                }else{
                    Toast.makeText(this, "Please enter other name! It's already exists!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please, fill all blanks!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}