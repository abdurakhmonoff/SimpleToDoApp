package com.abdurakhmonoff.simpleto_doapp.utils

import android.content.Context
import com.abdurakhmonoff.simpleto_doapp.models.Todo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySharedPrefs(private val context: Context) {

    private val gson = Gson()
    private val sharedPreferences = context.getSharedPreferences("TODO_SHP", Context.MODE_PRIVATE)

    fun getTodo(): ArrayList<Todo> {
        val todosJson = sharedPreferences.getString("TODOS_JSON", "")
        return if (todosJson != "") {
            val type = object : TypeToken<ArrayList<Todo>>() {}.type
            gson.fromJson(todosJson, type)
        } else arrayListOf()
    }

    fun addTodo(todo: Todo) {
        val editor = sharedPreferences.edit()
        val todos = getTodo()
        todos.add(todo)
        val jsonTodos = gson.toJson(todos)
        editor.putString("TODOS_JSON", jsonTodos)
        editor.apply()
    }

    fun removeTodo(todoName: String) {
        val editor = sharedPreferences.edit()
        val todos = getTodo()
        val temp = arrayListOf<Todo>()
        for (i in todos) if (i.name==todoName) temp.add(i)
        todos.removeAll(temp)
        val todosJson = gson.toJson(todos)
        editor.remove("TODOS_JSON")
        editor.putString("TODOS_JSON", todosJson)
        editor.apply()
    }

}

/*

fun removeTodo(todoName: String) {
        val editor = sharedPreferences.edit()
        val todos = getTodo()
        for (i in todos) if (i.name == todoName) todos.remove(i)
        val todosJson = gson.toJson(todos)
        editor.remove("TODOS_JSON")
        editor.putString("TODOS_JSON", todosJson)
        editor.apply()
    }

 */