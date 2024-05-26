package ir.sajjadasadi.taskapp.adapter

import androidx.recyclerview.widget.DiffUtil

class RecyclerDiffUtils(
    private val oldlist: ArrayList<*>,
    private val newlist: ArrayList<*>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldlist.size

    override fun getNewListSize() = newlist.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldlist[oldItemPosition] == newlist[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldlist == newlist

}