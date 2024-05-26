package ir.sajjadasadi.taskapp.mvp.model

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ir.sajjadasadi.taskapp.db.DBHandler
import ir.sajjadasadi.taskapp.db.model.TaskEntity
import ir.sajjadasadi.taskapp.mvp.ext.OnBindData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelMainActivity(private val activity: AppCompatActivity) {
    private val db = DBHandler.getDatabase(activity)
    fun SetData(taskEntity: TaskEntity) {
        activity.lifecycleScope.launch {
            withContext(
                Dispatchers.IO
            ) {
                db.taskDao().insertTask(taskEntity)
            }
        }
    }
    fun EditData(taskEntity: TaskEntity) {
        activity.lifecycleScope.launch {
            withContext(
                Dispatchers.IO
            ) {
                db.taskDao().updateTasks(taskEntity)
            }
        }
    }
    fun DeleteData(taskEntity: TaskEntity) {
        activity.lifecycleScope.launch {
            withContext(
                Dispatchers.IO
            ) {
                db.taskDao().deleteTasks(taskEntity)
            }
        }
    }
    fun GetData(state: Boolean,onBindData: OnBindData)
    {
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val tasks=db.taskDao().getTasksByColumn(state)
                withContext(Dispatchers.Main)
                {
                    tasks.collect{
                        onBindData.GetData(it)
                    }
                }
            }
        }
    }

    fun getTasks(state: Boolean, onBindData: OnBindData) {
        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO )
            {
                val tasks=db.taskDao().getTasksByColumn(state)
                withContext(Dispatchers.Main)
                {
                    tasks.collect{
                        onBindData.GetData(it)
                    }
                }
            }
        }
    }

    fun CloseDatabase() {
        db.close()
    }
}