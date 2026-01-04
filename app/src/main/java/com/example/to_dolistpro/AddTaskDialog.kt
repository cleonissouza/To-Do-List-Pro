package com.example.to_dolistpro

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.to_dolistpro.databinding.DialogAddTaskBinding

class AddTaskDialog(
    private val onSaved: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogAddTaskBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setTitle("Nova tarefa")
            .setView(binding.root)
            .setPositiveButton("Salvar") { _, _ ->
                val title = binding.edtTitle.text.toString()

                val priority = when (binding.radioGroup.checkedRadioButtonId){
                    binding.rbHigh.id -> 3
                    binding.rbMedium.id -> 2
                    else -> 1
                }

                if (title.isNotBlank()){
                    ToDoListProDatabase.get(requireContext())
                        .taskDao()
                        .insert(TaskEntity(title = title, priority = priority))

                    onSaved
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }
}