package com.example.to_dolistpro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolistpro.databinding.ItemTaskBinding

class TaskAdapter(
    private val list: List<TaskEntity>
) : RecyclerView.Adapter<TaskAdapter.VH>(){

    inner class VH(val binding: ItemTaskBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {
        val task = list[position]
        holder.binding.tvTitle.text = task.title
        holder.binding.tvPriority.text =
            if (task.priority == 3) "Alta"
            else if (task.priority == 2) "Media"
            else "Baixa"
    }

    override fun getItemCount() = list.size

}