package com.example.to_dolistpro

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.to_dolistpro.databinding.DialogAddTaskBinding

class AddEditTaskDialog(
    private val task: TaskEntity?,
    private val onSaved: () -> Unit
) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogAddTaskBinding.inflate(LayoutInflater.from(requireContext()))
        val db = ToDoListProDatabase.get(requireContext())

        //se for editar, preencha os campos
        if (task != null) {
            binding.edtTitle.setText(task.title)
            when (task.priority) {
                3 -> binding.rbHigh.isChecked = true
                2 -> binding.rbMedium.isChecked = true
                else -> binding.rblow.isChecked = true
            }
        }else{
            binding.rblow.isChecked = true
        }
        val titleDialog = if (task == null) "Nova tarefa"
        else "Editar tarefa"
        val textPositive = if (task == null) "Salvar"
        else "Atualizar"

        return AlertDialog.Builder(requireContext())
            .setTitle(titleDialog)
            .setView(binding.root)
            .setPositiveButton(textPositive) { _, _ ->
                val title = binding.edtTitle.text.toString().trim()
                if (title.isBlank()) return@setPositiveButton

                val priority = when (binding.radioGroup.checkedRadioButtonId){
                    binding.rbHigh.id -> 3
                    binding.rbMedium.id -> 2
                    else -> 1
                }

                if (task == null){
                    //Add
                    db.taskDao().insert(TaskEntity(title = title, priority = priority))
                }else{
                    //Edit (mantem o mesmo id)
                    db.taskDao().update(TaskEntity(id = task.id, title = title, priority = priority))
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }
}