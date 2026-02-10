package com.quranacademy.atifquranapp.util

/**
 * Constants used throughout the application
 */
object Constants {

    // Firebase Collection Names
    const val COLLECTION_COURSES = "courses"
    const val COLLECTION_TEACHERS = "teachers"
    const val COLLECTION_STUDENTS = "students"
    const val COLLECTION_CLASSES = "online_classes"
    const val COLLECTION_PAYMENTS = "payments"
    const val COLLECTION_ADMINS = "admins"

    // Firebase Storage Paths
    const val STORAGE_PROFILE_IMAGES = "profile_images"
    const val STORAGE_CERTIFICATES = "certificates"
    const val STORAGE_PAYMENT_PROOFS = "payment_proofs"
    const val STORAGE_CLASS_VIDEOS = "class_videos"

    // SharedPreferences Keys
    const val PREFS_NAME = "quran_academy_prefs"
    const val PREF_IS_ADMIN = "is_admin"
    const val PREF_ADMIN_USERNAME = "admin_username"
    const val PREF_USER_ID = "user_id"
    const val PREF_USER_NAME = "user_name"
    const val PREF_IS_FIRST_LAUNCH = "is_first_launch"

    // Admin Credentials (These should be stored securely in production)
    const val ADMIN_USERNAME = "admin"
    const val ADMIN_PASSWORD = "atif123"
    const val ADMIN_ID = "admin_001"

    // Google Forms URLs
    const val STUDENT_REGISTRATION_FORM_URL = "https://forms.gle/YOUR_STUDENT_FORM_ID"
    const val TEACHER_REGISTRATION_FORM_URL = "https://forms.gle/YOUR_TEACHER_FORM_ID"
    const val PAYMENT_UPLOAD_FORM_URL = "https://forms.gle/YOUR_PAYMENT_FORM_ID"

    // Google Sheets URLs (for viewing data)
    const val STUDENTS_SHEET_URL = "https://docs.google.com/spreadsheets/d/YOUR_STUDENTS_SHEET_ID"
    const val TEACHERS_SHEET_URL = "https://docs.google.com/spreadsheets/d/YOUR_TEACHERS_SHEET_ID"
    const val PAYMENTS_SHEET_URL = "https://docs.google.com/spreadsheets/d/YOUR_PAYMENTS_SHEET_ID"

    // Contact Information
    const val CONTACT_WHATSAPP = "https://wa.me/YOUR_WHATSAPP_NUMBER"
    const val CONTACT_EMAIL = "info@atifquranacademy.com"
    const val CONTACT_PHONE = "+1234567890"
    const val YOUTUBE_CHANNEL_URL = "https://www.youtube.com/YOUR_CHANNEL"

    // Timeouts
    const val SPLASH_DELAY = 2000L // 2 seconds
    const val NETWORK_TIMEOUT = 30L // 30 seconds

    // Pagination
    const val PAGE_SIZE = 20

    // Validation
    const val MIN_PASSWORD_LENGTH = 6
    const val MIN_AGE = 5
    const val MAX_AGE = 100
}
