package com.quranacademy.atifquranapp.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 class representing a Quran * Data Course
 */
data class Course(
    @DocumentId
    val id: String = "",

    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val duration: String = "",
    val feeMonthly: Double = 0.0,
    val level: CourseLevel = CourseLevel.BEGINNER,
    val features: List<String> = emptyList(),
    val isFeatured: Boolean = false,
    val isActive: Boolean = true,
    val teacherId: String = "",
    val maxStudents: Int = 0,
    val enrolledStudents: Int = 0,

    @ServerTimestamp
    val createdAt: Timestamp? = null,

    @ServerTimestamp
    val updatedAt: Timestamp? = null
)

/**
 * Enum representing course difficulty levels
 */
enum class CourseLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
}
