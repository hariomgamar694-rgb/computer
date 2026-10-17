# HR Computer Sikho (Zero to Computer Pro — आसान हिंदी में)

**HR Computer Sikho** is a complete, mobile-first Android learning application built with Kotlin and Jetpack Compose designed to teach complete beginners computer fundamentals up to practical office proficiency.

## Key Features & Screen Overview

1. **Home Dashboard**
   - Professional blue & white HR brand hero banner.
   - Live learning progress statistics (Completed lessons, Quiz average score, Saved bookmarks).
   - "Continue Learning" (सीखना जारी रखें) quick-resume card.
   - Quick-access tools: Keyboard Shortcuts Cheat-sheet, Practical Lab, Hardware & Software Troubleshooting.
   - Comprehensive course cards for all 10 courses with derived completion percentages.

2. **Course Library**
   - Filter courses by level (सभी, प्रारंभिक / Beginner, इंटरमीडिएट / Intermediate, एडवांस्ड / Advanced).
   - Real-time module counts, estimated duration, and status indicators.

3. **Course Detail Screen**
   - Course overview, objectives, and prerequisites.
   - Module accordion with lesson list, completion checks, duration, and one-tap resume.

4. **Detailed Lesson Reader**
   - Substantive, authentic Hindi explanations with standard English technical terms in parentheses.
   - Learning objectives and prerequisites.
   - Numbered step-by-step instructions with styled badges.
   - Real-world practical examples.
   - Specialized callout boxes:
     - 🛡️ सुरक्षा चेतावनी (Safety Notes)
     - ⚠️ सामान्य गलती और समाधान (Common Mistakes)
     - 💡 प्रो सुझाव (Pro Tips)
   - Media reference placeholders with explicit verification tracking.
   - Key summary (पाठ का मुख्य सारांश).
   - Practical tasks with clear distinction between in-app verified and self-reported practice.
   - Integrated Quiz CTA with previous score history.
   - Previous/Next navigation and instant "Mark as Completed" toggle.
   - Personal Lesson Notes creator and Bookmark toggle.
   - In-reader font size scaling controls (A- / A+).

5. **Practical Lab**
   - **Keyboard Shortcut Trainer**: Interactive key-combination selector (Ctrl, Alt, Shift, Win, C, V, X, D, etc.) with real-time technical validation.
   - **Typing Speed & Accuracy Tester**: Interactive Hindi and English typing drill with live WPM, accuracy (%), mistake counter, and timer.
   - **Windows File Explorer Simulator**: Interactive virtual folder system with Create Folder, Cut, Copy, Paste, Delete to Recycle Bin, and Restore.
   - **Excel Formula Playground**: Live spreadsheet sandbox for SUM, AVERAGE, MIN, MAX, COUNT, and IF logic with real-time output.

6. **Quiz Engine**
   - Interactive multiple-choice questions with Hindi explanations.
   - Immediate feedback showing whether an answer was correct or wrong.
   - Score calculation and passing threshold (70%).
   - Retry quiz and detailed Answer Review mode.
   - Saved persistently in the local Room database.

7. **Search Engine**
   - Instant search across Course Titles, Lesson Titles & Body Sections, Keyboard Shortcuts, and Troubleshooting Guides.
   - Quick filter chips (सभी, कोर्स, पाठ, शॉर्टकट्स, समस्या निवारण) and popular suggestion tags.

8. **My Learning (मेरी पढ़ाई)**
   - 4 Tabs: Completed Lessons, Bookmarks, Quiz Scores History, and My Notes.
   - Edit, view, and delete saved notes.

9. **Settings & Content Validator**
   - Font scale preferences (Normal, Large, Extra Large).
   - Theme toggle (System, Light, Dark).
   - Built-in Content Validation Utility that audits all courses, modules, lessons, quizzes, questions, and media references for duplicate IDs, broken references, and structural integrity.

---

## Complete Course Catalog (A to J)

- **A. Computer Fundamentals (कंप्यूटर के मूल सिद्धांत)**: IPO cycle, hardware vs software, CPU, RAM vs ROM, storage units (Bit, Byte, KB, MB, GB, TB).
- **B. Mouse, Keyboard and Typing (माउस, कीबोर्ड और टाइपिंग)**: Mouse grip, left/right/double click, drag & drop, special keys, home-row touch typing.
- **C. Windows and File Management (विंडोज और फाइल मैनेजमेंट)**: Desktop, Start menu, Taskbar, Windows 10 vs 11, Drives (C:, D:), folders, Cut/Copy/Paste, Recycle Bin.
- **D. Microsoft Word (माइक्रोसॉफ्ट वर्ड)**: Ribbons, text formatting, alignment (Left, Center, Right, Justify), tables, margins, portrait/landscape, PDF export.
- **E. Microsoft Excel (माइक्रोसॉफ्ट एक्सेल)**: Rows, columns, cell addresses, formulas (=SUM, =AVERAGE, =MIN, =MAX, =IF), marksheets.
- **F. Microsoft PowerPoint (माइक्रोसॉफ्ट पावरपॉइंट)**: Slides, layouts, themes, transitions, animations, full-screen slide show (F5).
- **G. Internet, Email and Digital Safety (इंटरनेट, ईमेल और डिजिटल सुरक्षा)**: URL structure, HTTPS padlock security, To/Cc/Bcc, attachments, strong passwords, 2FA, phishing fraud defense.
- **H. Troubleshooting and Maintenance (समस्या निवारण और रख-रखाव)**: Slow computer fixes, Task Manager (Ctrl+Shift+Esc), %temp% cleaning, disk cleanup, startup apps, Wi-Fi & audio checks.
- **I. PDF, Printing and Digital Office (पीडीएफ, प्रिंटिंग और डिजिटल ऑफिस)**: Print preview (Ctrl+P), A4 vs Legal size, print queue management, safe USB removal.
- **J. Networking, Advanced Skills and Career (नेटवर्किंग और करियर कौशल)**: LAN vs WAN, IP address basics, CMD commands (ipconfig, ping), data entry accuracy, computer operator roles.

---

## Technical Stack & Architecture

- **Language:** Kotlin 2.2
- **UI Framework:** Jetpack Compose (Material Design 3)
- **Local Persistence:** Room 2.7.0 with Kotlin Coroutines & Flow
- **Navigation:** Navigation Compose
- **Design System:** Accessible Blue & White theme (`#1565C0`)
- **Testing:** Local JVM JUnit 4 & Robolectric

---

## How to Build & Run Locally

1. Open the project in Android Studio (Ladybug or newer).
2. Sync the project with Gradle files.
3. Run the app on an Android emulator or connected device running Android 7.0+ (API 24+).
4. Run unit and Robolectric tests via Gradle:
   ```bash
   gradle :app:testDebugUnitTest
   ```
5. To generate an APK:
   ```bash
   gradle :app:assembleDebug
   ```
