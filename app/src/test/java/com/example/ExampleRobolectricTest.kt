package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.content.CourseCatalog
import com.example.data.validator.ContentValidator
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context matches HR Computer Sikho`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("HR Computer Sikho", appName)
    }

    @Test
    fun `course catalog contains all 10 courses from A to J`() {
        val courses = CourseCatalog.getAllCourses()
        assertEquals(10, courses.size)
    }

    @Test
    fun `content validator confirms catalog integrity without errors`() {
        val report = ContentValidator.validateCatalog()
        assertTrue("Expected catalog to be valid without errors, but found: ${report.issues}", report.isValid)
        assertEquals(10, report.totalCourses)
        assertTrue(report.totalLessons > 0)
        assertTrue(report.totalQuizzes > 0)
    }
}
