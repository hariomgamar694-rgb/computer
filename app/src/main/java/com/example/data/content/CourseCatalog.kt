package com.example.data.content

import com.example.data.model.Course
import com.example.data.model.Lesson

object CourseCatalog {

    val courses: List<Course> by lazy {
        listOf(
            CourseCatalogA_B.courseA,
            CourseCatalogA_B.courseB,
            CourseCatalogC_D.courseC,
            CourseCatalogC_D.courseD,
            CourseCatalogE_F.courseE,
            CourseCatalogE_F.courseF,
            CourseCatalogG_H.courseG,
            CourseCatalogG_H.courseH,
            CourseCatalogI_J.courseI,
            CourseCatalogI_J.courseJ
        )
    }

    private val lessonList: List<Lesson> by lazy {
        courses.flatMap { course ->
            course.modules.flatMap { module ->
                module.lessons
            }
        }
    }

    private val lessonMap: Map<String, Lesson> by lazy {
        lessonList.associateBy { it.id }
    }

    fun getAllCourses(): List<Course> = courses

    fun getCourseById(courseId: String): Course? {
        return courses.find { it.id == courseId }
    }

    fun getAllLessons(): List<Lesson> = lessonList

    fun getLessonById(lessonId: String): Lesson? {
        return lessonMap[lessonId]
    }

    fun getLessonsForCourse(courseId: String): List<Lesson> {
        val course = getCourseById(courseId) ?: return emptyList()
        return course.modules.flatMap { it.lessons }
    }

    fun getNextLesson(currentLessonId: String): Lesson? {
        val index = lessonList.indexOfFirst { it.id == currentLessonId }
        return if (index >= 0 && index < lessonList.size - 1) lessonList[index + 1] else null
    }

    fun getPreviousLesson(currentLessonId: String): Lesson? {
        val index = lessonList.indexOfFirst { it.id == currentLessonId }
        return if (index > 0) lessonList[index - 1] else null
    }
}
