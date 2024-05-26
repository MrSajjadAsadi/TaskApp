package ir.sajjadasadi.taskapp.mvp.presenter

import ir.sajjadasadi.taskapp.db.model.TaskEntity
import ir.sajjadasadi.taskapp.mvp.ext.BaseLifeCycle
import ir.sajjadasadi.taskapp.mvp.ext.OnBindData
import ir.sajjadasadi.taskapp.mvp.model.ModelMainActivity
import ir.sajjadasadi.taskapp.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : BaseLifeCycle {
    override fun onCreate() {
        setNewTask()
        setDataInitRecycler()
        dataHandler()
    }

    private fun setNewTask() {
        view.ShowDialog(
            object : OnBindData {
                override fun SaveData(taskEntity: TaskEntity) {
                    model.SetData(taskEntity)
                }
            }
        )
    }

    private fun setDataInitRecycler() {
        view.initRecycler(arrayListOf(), object : OnBindData {
            override fun EditData(taskEntity: TaskEntity) {
                model.EditData(taskEntity)
            }

            override fun DeleteData(taskEntity: TaskEntity) {
                model.DeleteData(taskEntity)
            }
        })


    }

    private fun dataHandler() {
        view.setData(
            object : OnBindData {
                override fun RequestData(state: Boolean) {
                    model.GetData(
                        state,
                        object : OnBindData {
                            override fun GetData(taskEntity: List<TaskEntity>) {
                                view.ShowTasks(taskEntity)
                            }
                        }
                    )
                }
            }
        )
    }

    override fun onDestroy() {
        model.CloseDatabase()
    }
}