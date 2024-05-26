package ir.sajjadasadi.taskapp.mvp.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.sajjadasadi.taskapp.adapter.RecyclerTaskAdapter
import ir.sajjadasadi.taskapp.databinding.ActivityMainBinding
import ir.sajjadasadi.taskapp.databinding.CustomDialogBinding
import ir.sajjadasadi.taskapp.db.model.TaskEntity
import ir.sajjadasadi.taskapp.mvp.ext.OnBindData

class ViewMainActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))
    private lateinit var adapter: RecyclerTaskAdapter
    fun initRecycler(task: ArrayList<TaskEntity>, onBindData: OnBindData) {
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL,
            false
        )
        adapter = RecyclerTaskAdapter(task, onBindData)
        binding.recyclerView.adapter = adapter
    }

    fun ShowTasks(tasks: List<TaskEntity>) {

        val data = arrayListOf<TaskEntity>()
        tasks.forEach { data.add(it) }
        adapter.dataUpdate(data)
    }

    fun setData(onBindData: OnBindData) {
        onBindData.RequestData(false)
        binding.rdbTrue.setOnClickListener{
            onBindData.RequestData(true)
            binding.txtState.text = "کارهای انجام شده"
        }
        binding.rdbFalse.setOnClickListener{
            onBindData.RequestData(false)
            binding.txtState.text = "کارهای انجام نشده"
        }
    }

    fun ShowDialog(onBindData: OnBindData) {
        binding.fab.setOnClickListener {
            val view = CustomDialogBinding.inflate(LayoutInflater.from(context))
            val dialog = Dialog(context)
            dialog.setContentView(view.root)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            view.btnCancel.setOnClickListener { dialog.dismiss() }

            view.btnSave.setOnClickListener {
                val text = view.edtTask.text.toString()
                if (text.isNotEmpty()) {
                    onBindData.SaveData(TaskEntity(title = text, state = false))
                    Toast.makeText(context, "وظیفه شما با موفقیت ثبت شد", Toast.LENGTH_SHORT)
                        .show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(context, "لطفا متن وظیفه را وارد کنید", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    fun InitRecycler(onBindData: OnBindData) {
        val adapter = RecyclerTaskAdapter(arrayListOf<TaskEntity>(), onBindData)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
    }
}