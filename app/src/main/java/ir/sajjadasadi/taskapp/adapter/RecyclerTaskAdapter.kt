package ir.sajjadasadi.taskapp.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.sajjadasadi.taskapp.R
import ir.sajjadasadi.taskapp.databinding.RecyclerItemBinding
import ir.sajjadasadi.taskapp.db.model.TaskEntity
import ir.sajjadasadi.taskapp.mvp.ext.OnBindData

class RecyclerTaskAdapter(
    private val task: ArrayList<TaskEntity>, private val onBindData: OnBindData
) : RecyclerView.Adapter<RecyclerTaskAdapter.TaskViewHolder>() {
    inner class TaskViewHolder(private val binding: RecyclerItemBinding) :
        ViewHolder(binding.root) {
        fun SetData(data: TaskEntity) {
            binding.txtTitle.text = data.title
            binding.checkBox.isChecked = data.state
            binding.checkBox.setOnClickListener {
                if (data.state) {
                    onBindData.EditData(
                        TaskEntity(data.id, data.title, false)
                    )
                } else {
                    onBindData.EditData(
                        TaskEntity(data.id, data.title, true)
                    )
                }
            }
            binding.imgDelete.setOnClickListener {

                val inflater = LayoutInflater.from(itemView.context)
                val view = inflater.inflate(R.layout.layout_cmt_dialog, null)

                val alertTitle = view.findViewById<TextView>(R.id.alertTitle)
                val alertMessage = view.findViewById<TextView>(R.id.alertMessage)

                alertTitle.text = "حذف وظیفه"
                alertMessage.text = "آیا مطمئن هستید؟"

                val builder =
                    AlertDialog.Builder(itemView.context).setView(view)
                        .setPositiveButton("بله") { dialog, _ ->
                            onBindData.DeleteData(data)
                            Toast.makeText(itemView.context,"با موفقیت حذف شد",Toast.LENGTH_SHORT).show()

                        }.setNegativeButton("خیر") { dialog, _ ->
                            dialog.cancel()
                        }.create()

                builder.show()









            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskViewHolder(
        RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    )

    override fun getItemCount() = task.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.SetData(task[position])
    }

    fun dataUpdate(newlist: ArrayList<TaskEntity>) {
        val diffCallBack = RecyclerDiffUtils(task, newlist)
        val diffResault = DiffUtil.calculateDiff(diffCallBack)
        task.clear()
        task.addAll(newlist)

        diffResault.dispatchUpdatesTo(this)
    }
}