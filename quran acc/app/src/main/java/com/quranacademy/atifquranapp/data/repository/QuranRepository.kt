package com.quranacademy.atifquranapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.quranacademy.atifquranapp.data.model.*
import com.quranacademy.atifquranapp.util.Constants
import com.quranacademy.atifquranapp.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

/**
 * Repository for managing Quran Academy data from Firebase
 */
class QuranRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val coursesCollection = firestore.collection(Constants.COLLECTION_COURSES)
    private val teachersCollection = firestore.collection(Constants.COLLECTION_TEACHERS)
    private val studentsCollection = firestore.collection(Constants.COLLECTION_STUDENTS)
    private val classesCollection = firestore.collection(Constants.COLLECTION_CLASSES)
    private val paymentsCollection = firestore.collection(Constants.COLLECTION_PAYMENTS)

    // ==================== COURSES ====================

    /**
     * Get all courses
     */
    fun getCourses(): Flow<Resource<List<Course>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = coursesCollection
            .whereEqualTo("isActive", true)
            .orderBy("name")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch courses"))
                    return@addSnapshotListener
                }

                val courses = snapshot?.toObjects(Course::class.java) ?: emptyList()
                trySend(Resource.success(courses))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Get featured courses
     */
    fun getFeaturedCourses(): Flow<Resource<List<Course>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = coursesCollection
            .whereEqualTo("isActive", true)
            .whereEqualTo("isFeatured", true)
            .limit(5)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch featured courses"))
                    return@addSnapshotListener
                }

                val courses = snapshot?.toObjects(Course::class.java) ?: emptyList()
                trySend(Resource.success(courses))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Get course by ID
     */
    suspend fun getCourseById(courseId: String): Resource<Course> {
        return try {
            val document = coursesCollection.document(courseId).get().await()
            val course = document.toObject(Course::class.java)
            if (course != null) {
                Resource.success(course)
            } else {
                Resource.error("Course not found")
            }
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to fetch course")
        }
    }

    // ==================== TEACHERS ====================

    /**
     * Get all verified teachers
     */
    fun getVerifiedTeachers(): Flow<Resource<List<Teacher>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = teachersCollection
            .whereEqualTo("isVerified", true)
            .orderBy("name")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch teachers"))
                    return@addSnapshotListener
                }

                val teachers = snapshot?.toObjects(Teacher::class.java) ?: emptyList()
                trySend(Resource.success(teachers))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Get all teachers including unverified
     */
    fun getAllTeachers(): Flow<Resource<List<Teacher>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = teachersCollection
            .orderBy("name")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch teachers"))
                    return@addSnapshotListener
                }

                val teachers = snapshot?.toObjects(Teacher::class.java) ?: emptyList()
                trySend(Resource.success(teachers))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Get pending verification teachers (for admin)
     */
    fun getPendingVerificationTeachers(): Flow<Resource<List<Teacher>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = teachersCollection
            .whereEqualTo("verificationStatus", VerificationStatus.PENDING.name)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch pending teachers"))
                    return@addSnapshotListener
                }

                val teachers = snapshot?.toObjects(Teacher::class.java) ?: emptyList()
                trySend(Resource.success(teachers))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Get teacher by ID
     */
    suspend fun getTeacherById(teacherId: String): Resource<Teacher> {
        return try {
            val document = teachersCollection.document(teacherId).get().await()
            val teacher = document.toObject(Teacher::class.java)
            if (teacher != null) {
                Resource.success(teacher)
            } else {
                Resource.error("Teacher not found")
            }
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to fetch teacher")
        }
    }

    /**
     * Update teacher verification status
     */
    suspend fun updateTeacherVerification(
        teacherId: String,
        isVerified: Boolean,
        status: VerificationStatus
    ): Resource<Unit> {
        return try {
            teachersCollection.document(teacherId).update(
                mapOf(
                    "isVerified" to isVerified,
                    "verificationStatus" to status.name,
                    "updatedAt" to com.google.firebase.Timestamp.now()
                )
            ).await()
            Resource.success(Unit)
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to update teacher")
        }
    }

    // ==================== STUDENTS ====================

    /**
     * Get all students
     */
    fun getAllStudents(): Flow<Resource<List<Student>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = studentsCollection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch students"))
                    return@addSnapshotListener
                }

                val students = snapshot?.toObjects(Student::class.java) ?: emptyList()
                trySend(Resource.success(students))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Add new student
     */
    suspend fun addStudent(student: Student): Resource<Unit> {
        return try {
            studentsCollection.add(student).await()
            Resource.success(Unit)
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to add student")
        }
    }

    /**
     * Update student
     */
    suspend fun updateStudent(student: Student): Resource<Unit> {
        return try {
            studentsCollection.document(student.id).set(student).await()
            Resource.success(Unit)
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to update student")
        }
    }

    /**
     * Delete student
     */
    suspend fun deleteStudent(studentId: String): Resource<Unit> {
        return try {
            studentsCollection.document(studentId).delete().await()
            Resource.success(Unit)
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to delete student")
        }
    }

    // ==================== CLASSES ====================

    /**
     * Get all online classes
     */
    fun getOnlineClasses(): Flow<Resource<List<OnlineClass>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = classesCollection
            .orderBy("scheduledDate", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch classes"))
                    return@addSnapshotListener
                }

                val classes = snapshot?.toObjects(OnlineClass::class.java) ?: emptyList()
                trySend(Resource.success(classes))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Get live classes
     */
    fun getLiveClasses(): Flow<Resource<List<OnlineClass>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = classesCollection
            .whereEqualTo("isLive", true)
            .orderBy("scheduledDate", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch live classes"))
                    return@addSnapshotListener
                }

                val classes = snapshot?.toObjects(OnlineClass::class.java) ?: emptyList()
                trySend(Resource.success(classes))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Get recorded classes
     */
    fun getRecordedClasses(): Flow<Resource<List<OnlineClass>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = classesCollection
            .whereEqualTo("classType", ClassType.RECORDED.name)
            .orderBy("scheduledDate", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch recorded classes"))
                    return@addSnapshotListener
                }

                val classes = snapshot?.toObjects(OnlineClass::class.java) ?: emptyList()
                trySend(Resource.success(classes))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Add new class
     */
    suspend fun addOnlineClass(onlineClass: OnlineClass): Resource<Unit> {
        return try {
            classesCollection.add(onlineClass).await()
            Resource.success(Unit)
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to add class")
        }
    }

    // ==================== PAYMENTS ====================

    /**
     * Get all payments
     */
    fun getAllPayments(): Flow<Resource<List<Payment>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = paymentsCollection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch payments"))
                    return@addSnapshotListener
                }

                val payments = snapshot?.toObjects(Payment::class.java) ?: emptyList()
                trySend(Resource.success(payments))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Get pending payments
     */
    fun getPendingPayments(): Flow<Resource<List<Payment>>> = callbackFlow {
        trySend(Resource.loading())

        val listener = paymentsCollection
            .whereEqualTo("paymentStatus", PaymentVerificationStatus.PENDING.name)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.error(error.message ?: "Failed to fetch pending payments"))
                    return@addSnapshotListener
                }

                val payments = snapshot?.toObjects(Payment::class.java) ?: emptyList()
                trySend(Resource.success(payments))
            }

        awaitClose { listener.remove() }
    }

    /**
     * Add new payment
     */
    suspend fun addPayment(payment: Payment): Resource<Unit> {
        return try {
            paymentsCollection.add(payment).await()
            Resource.success(Unit)
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to add payment")
        }
    }

    /**
     * Update payment status
     */
    suspend fun updatePaymentStatus(
        paymentId: String,
        status: PaymentVerificationStatus
    ): Resource<Unit> {
        return try {
            paymentsCollection.document(paymentId).update(
                mapOf(
                    "paymentStatus" to status.name,
                    "updatedAt" to com.google.firebase.Timestamp.now()
                )
            ).await()
            Resource.success(Unit)
        } catch (e: Exception) {
            Resource.error(e.message ?: "Failed to update payment status")
        }
    }

    companion object {
        @Volatile
        private var instance: QuranRepository? = null

        fun getInstance(): QuranRepository {
            return instance ?: synchronized(this) {
                instance ?: QuranRepository().also { instance = it }
            }
        }
    }
}
