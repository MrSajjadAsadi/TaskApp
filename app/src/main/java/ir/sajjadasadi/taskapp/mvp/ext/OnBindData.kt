package ir.sajjadasadi.taskapp.mvp.ext

import ir.sajjadasadi.taskapp.db.model.TaskEntity

interface OnBindData {
    fun SaveData(taskEntity: TaskEntity){}
    fun EditData(taskEntity: TaskEntity){}
    fun GetData(taskEntity: List<TaskEntity>){}
    fun RequestData(state:Boolean){}
    fun DeleteData(taskEntity: TaskEntity){}
}