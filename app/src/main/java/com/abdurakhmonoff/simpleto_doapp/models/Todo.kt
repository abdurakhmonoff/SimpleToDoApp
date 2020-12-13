package com.abdurakhmonoff.simpleto_doapp.models

import java.io.Serializable

data class Todo(val name:String, val description:String, val degree:TodoDegree, val crateDate:String, val deadLine:String, var category:String = "Open"):Serializable