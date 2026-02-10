package com.quranacademy.atifquranapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quranacademy.atifquranapp.data.model.Teacher
import com.quranacademy.atifquranapp.databinding.ItemTeacherBinding

/**
 * Adapter for displaying teachers in Home fragment
 */
class HomeTeacherAdapter(
    private val onTeacherClick: (Teacher) -> Unit
) : ListAdapter<Teacher, HomeTeacherAdapter.TeacherViewHolder>(TeacherDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(getItem(position), onTeacherClick)
    }

    class TeacherViewHolder(
        private val binding: ItemTeacherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: Teacher, onTeacherClick: (Teacher) -> Unit) {
            binding.txtName.text = teacher.name
            binding.txtQualification.text = teacher.qualification
            binding.txtExperience.text = "${teacher.experience} years experience"
            binding.txtRating.text = String.format("%.1f", teacher.rating)
            binding.txtStudents.text = "${teacher.totalStudents} students"

            // Show/hide verified badge
            binding.imgVerified.visibility = if (teacher.isVerified) View.VISIBLE else View.GONE

            binding.btnBookClass.setOnClickListener {
                onTeacherClick(teacher)
            }
        }
    }

    private class TeacherDiffCallback : DiffUtil.ItemCallback<Teacher>() {
        override fun areItemsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
            return oldItem == newItem
        }
    }
}
