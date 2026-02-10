package com.quranacademy.atifquranapp.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 * Data class representing an Online Class
 */
data class OnlineClass(
    @DocumentId
    val id: String = "",

    val title: String = "",
    val description: String = "",
    val courseId: String = "",
    val teacherId: String = "",
    val teacherName: String = "",
    val classType: ClassType = ClassType.LIVE,
    val meetLink: String = "",
    val scheduledDate: Timestamp? = null,
    val duration: Int = 0, // Duration in minutes
    val isLive: Boolean = false,
    val recordedVideoUrl: String = "",
    val thumbnailUrl: String = "",
    val maxParticipants: Int = 0,
    val currentParticipants: Int = 0,

    @ServerTimestamp
    val createdAt: Timestamp? = null,

    @ServerTimestamp
    val updatedAt: Timestamp? = null
)

/**
 * Enum representing class type
 */
enum class ClassType {
    LIVE,
    RECORDED
}
