package com.quranacademy.atifquranapp.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 * Data class representing a Teacher
 */
data class Teacher(
    @DocumentId
    val id: String = "",

    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val profileImageUrl: String = "",
    val qualification: String = "",
    val specialization: List<String> = emptyList(),
    val experience: Int = 0, // Years of experience
    val bio: String = "",
    val isVerified: Boolean = false,
    val verificationStatus: VerificationStatus = VerificationStatus.PENDING,
    val rating: Float = 0f,
    val totalStudents: Int = 0,
    val coursesTaught: List<String> = emptyList(), // Course IDs

    // Verification documents (stored as URLs)
    val cnicUrl: String = "",
    val certificateUrls: List<String> = emptyList(),
    val introductionVideoUrl: String = "",

    @ServerTimestamp
    val createdAt: Timestamp? = null,

    @ServerTimestamp
    val updatedAt: Timestamp? = null
)

/**
 * Enum representing teacher verification status
 */
enum class VerificationStatus {
    PENDING,
    APPROVED,
    REJECTED
}
