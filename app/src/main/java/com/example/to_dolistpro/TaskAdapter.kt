package com.example.to_dolistpro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolistpro.databinding.ItemTaskBinding

class TaskAdapter(
    private val list: List<TaskEntity>,
    private val onClick: (TaskEntity) -> Unit,
    private val onLongClick: (TaskEntity) -> Unit,
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    inner class TaskViewHolder(val binding: ItemTaskBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val task = list[position]
        holder.binding.tvTitle.text = task.title
        holder.binding.tvPriority.text =
            when (task.priority) {
                3 -> "Alta"
                2 -> "Media"
                else -> "Baixa"
            }
        holder.binding.root.setOnClickListener { onClick(task) }
        holder.binding.root.setOnLongClickListener {
            onLongClick(task)
            true
        }
    }

    override fun getItemCount() = list.size

}