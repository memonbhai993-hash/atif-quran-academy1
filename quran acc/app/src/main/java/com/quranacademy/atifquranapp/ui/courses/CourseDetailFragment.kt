package com.quranacademy.atifquranapp.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quranacademy.atifquranapp.databinding.FragmentCourseDetailBinding

/**
 * Course Detail Fragment - Shows detailed information about a course
 */
class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseId = arguments?.getString("courseId")
        loadCourseDetails(courseId)
    }

    private fun loadCourseDetails(courseId: String?) {
        // TODO: Load course details from repository
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
