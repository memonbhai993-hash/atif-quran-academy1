package com.quranacademy.atifquranapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quranacademy.atifquranapp.R
import com.quranacademy.atifquranapp.data.model.Course
import com.quranacademy.atifquranapp.data.model.CourseLevel
import com.quranacademy.atifquranapp.databinding.ItemCourseBinding

/**
 * Adapter for displaying courses in Home fragment
 */
class HomeCourseAdapter(
    private val onCourseClick: (Course) -> Unit
) : ListAdapter<Course, HomeCourseAdapter.CourseViewHolder>(CourseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position), onCourseClick)
    }

    class CourseViewHolder(
        private val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course, onCourseClick: (Course) -> Unit) {
            binding.txtCourseName.text = course.name
            binding.txtDuration.text = course.duration
            binding.txtFee.text = "$${course.feeMonthly.toInt()}/month"

            // Set level text and color
            binding.txtLevel.text = when (course.level) {
                CourseLevel.BEGINNER -> binding.root.context.getString(R.string.course_beginner)
                CourseLevel.INTERMEDIATE -> binding.root.context.getString(R.string.course_intermediate)
                CourseLevel.ADVANCED -> binding.root.context.getString(R.string.course_advanced)
            }

            binding.btnEnroll.setOnClickListener {
                onCourseClick(course)
            }
        }
    }

    private class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
}
