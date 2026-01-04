package com.example.to_dolistpro

import android.R.attr.priority
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_dolistpro.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment(R.layout.fragment_task_list) {

    private lateinit var binding: FragmentTaskListBinding
    private lateinit var db: ToDoListProDatabase
    private lateinit var adapter: TaskAdapter

    private var tasks = mutableListOf<TaskEntity>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTaskListBinding.bind(view)

        db = ToDoListProDatabase.get(requireContext())

        adapter = TaskAdapter(tasks)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        loadTasks()

        binding.fabAdd.setOnClickListener {
            AddTaskDialog{
                loadTasks()
            }.show(parentFragmentManager, "add")
        }

        binding.btnHigh.setOnClickListener { filter(3) }
        binding.btnMedium.setOnClickListener { filter(2) }
        binding.btnLow.setOnClickListener { filter(1) }
        binding.btnAll.setOnClickListener { loadTasks() }

    }

    private fun loadTasks() {
        tasks.clear()
        tasks.addAll(db.taskDao().getAll())
        adapter.notifyDataSetChanged()
    }

    private fun filter(priority: Int) {
        tasks.clear()
        tasks.addAll(db.taskDao().getAll().filter { it.priority == priority })
        adapter.notifyDataSetChanged()
    }

}