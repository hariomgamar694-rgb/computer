package com.example.data.content

import com.example.data.model.*

object CourseCatalogC_D {

    val courseC = Course(
        id = "course_windows",
        title = "C. विंडोज और फाइल मैनेजमेंट (Windows & File Management)",
        englishTitle = "Windows & File Management",
        hindiTitle = "विंडोज और फाइल मैनेजमेंट",
        description = "डेस्कटॉप, टास्कबार, फाइल एक्सप्लोरर, ड्राइव्स (C:, D:), फोल्डर बनाना, कट-कॉपी-पेस्ट, रीसायकल बिन, फाइल एक्सटेंशन और विंडोज 10/11 के अंतर।",
        level = CourseLevel.BEGINNER,
        category = "ऑपरेटिंग सिस्टम (OS)",
        estimatedHours = "4 घंटे",
        prerequisites = listOf("माउस और कीबोर्ड का बुनियादी संचालन"),
        iconKey = "window",
        modules = listOf(
            Module(
                id = "mod_win_1",
                courseId = "course_windows",
                title = "मॉड्यूल 1: विंडोज इंटरफेस और डेस्कटॉप",
                description = "डेस्कटॉप, स्टार्ट मेनू, टास्कबार और क्विक सेटिंग्स।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_win_desktop",
                        courseId = "course_windows",
                        moduleId = "mod_win_1",
                        title = "1.1 डेस्कटॉप, टास्कबार और स्टार्ट मेनू",
                        englishTitle = "Desktop, Taskbar & Start Menu",
                        durationMinutes = 20,
                        learningObjectives = listOf(
                            "कंप्यूटर शुरू होने के बाद डेस्कटॉप स्क्रीन के अवयवों को पहचानना",
                            "टास्कबार पर पिन किए गए ऐप्स और सिस्टम ट्रे (घड़ी, वाई-फाई, वॉल्यूम) का उपयोग",
                            "विंडोज 10 और विंडोज 11 में स्टार्ट बटन के स्थान का अंतर जानना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "डेस्कटॉप क्या है? (What is Desktop?)",
                                content = "कंप्यूटर बूट (चालू) होने के बाद जो पहली मुख्य स्क्रीन दिखाई देती है, उसे डेस्कटॉप कहते हैं। इस पर आइकन (Icons), शॉर्टकट और वॉलपेपर होते हैं।",
                                steps = listOf(
                                    "1. Icons: छोटे चित्र जो फाइलों, फोल्डरों या सॉफ्टवेयरों का प्रतिनिधित्व करते हैं।",
                                    "2. Wallpaper: डेस्कटॉप की बैकग्राउंड इमेज जिसे आप सेटिंग्स से बदल सकते हैं।",
                                    "3. This PC (या My Computer): कंप्यूटर के सभी डिस्क ड्राइव्स देखने का मुख्य द्वार।"
                                )
                            ),
                            LessonSection(
                                heading = "टास्कबार और स्टार्ट मेनू (Taskbar & Start Menu)",
                                content = "स्क्रीन के सबसे नीचे स्थित पट्टी को टास्कबार कहते हैं:",
                                steps = listOf(
                                    "Start Button (विंडोज लोगो): इस पर क्लिक करने से सभी इंस्टॉल किए गए सॉफ्टवेयरों की लिस्ट खुलती है। (Win Key दबाने पर भी खुलता है)।",
                                    "Search Bar: किसी भी फाइल या ऐप को तुरंत ढूंढने के लिए।",
                                    "System Tray (दाहिनी तरफ): समय, तारीख, वॉल्यूम, वाई-फाई नेटवर्क और बैटरी स्टेटस दिखाता है।",
                                    "Windows 10 vs 11 अंतर: Windows 10 में स्टार्ट बटन बाईं ओर (Left) होता है, जबकि Windows 11 में डिफ़ॉल्ट रूप से बीच में (Center) स्थित होता है।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "कंप्यूटर को बंद करने के लिए हमेशा: Start -> Power Button -> Shut Down चुनें। पावर केबल न खींचें।"
                        ),
                        summary = listOf(
                            "टास्कबार पर चल रहे प्रोग्राम दिखाई देते हैं।",
                            "शॉर्टकट की Win + D दबाने से तुरंत सारे खुले ऐप्स मिनिमाइज होकर डेस्कटॉप दिखने लगता है।"
                        ),
                        practicalTasks = listOf(
                            PracticalTask(
                                id = "task_win_show_desktop",
                                title = "डेस्कटॉप टॉगल करें",
                                instructions = listOf(
                                    "कीबोर्ड पर Windows Key + D दबाकर डेस्कटॉप पर जाएं और फिर दोबारा दबाकर वापस आएं।"
                                ),
                                isTechnicallyVerifiable = true,
                                verificationType = VerificationType.SHORTCUT_TESTER,
                                targetExpectedInput = "Win+D"
                            )
                        ),
                        quiz = Quiz(
                            id = "quiz_win_desktop",
                            lessonId = "les_win_desktop",
                            title = "विंडोज डेस्कटॉप टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_win_1",
                                    question = "सभी खुले हुए विंडोज को तुरंत मिनिमाइज करके डेस्कटॉप देखने का शॉर्टकट क्या है?",
                                    options = listOf("Ctrl + C", "Windows Key + D", "Alt + F4", "Ctrl + Alt + Del"),
                                    correctAnswerIndex = 1,
                                    explanation = "Windows Key + D तुरंत डेस्कटॉप दिखाता है (Desktop Toggle)।"
                                ),
                                Question(
                                    id = "q_win_2",
                                    question = "विंडोज 11 में डिफ़ॉल्ट रूप से टास्कबार आइकन किस स्थान पर होते हैं?",
                                    options = listOf("बाएं कोने में (Left)", "स्क्रीन के शीर्ष पर (Top)", "बीच में (Center)", "दाएं कोने में (Right)"),
                                    correctAnswerIndex = 2,
                                    explanation = "विंडोज 11 में टास्कबार और स्टार्ट मेनू डिफ़ॉल्ट रूप से स्क्रीन के केंद्र (Center) में अलाइन होते हैं।"
                                )
                            )
                        )
                    )
                )
            ),
            Module(
                id = "mod_win_2",
                courseId = "course_windows",
                title = "मॉड्यूल 2: फाइल एक्सप्लोरर और फोल्डर प्रबंधन",
                description = "नया फोल्डर बनाना, कट, कॉपी, पेस्ट, रीनेम, डिलीट और रीसायकल बिन।",
                order = 2,
                lessons = listOf(
                    Lesson(
                        id = "les_win_file_management",
                        courseId = "course_windows",
                        moduleId = "mod_win_2",
                        title = "2.1 फाइल्स, फोल्डर्स और कट-कॉपी-पेस्ट",
                        englishTitle = "Files, Folders & Cut-Copy-Paste",
                        durationMinutes = 25,
                        learningObjectives = listOf(
                            "फाइल और फोल्डर में अंतर समझना",
                            "ड्राइव (C:, D:) का वर्गीकरण और C: ड्राइव की सुरक्षा",
                            "नया फोल्डर बनाना (Ctrl + Shift + N)",
                            "कट (Ctrl+X), कॉपी (Ctrl+C) और पेस्ट (Ctrl+V) में अंतर"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "फाइल और फोल्डर में अंतर",
                                content = "फाइल (File) में वास्तविक डेटा सुरक्षित होता है (जैसे गाना, फोटो, वर्ड डॉक्यूमेंट), जबकि फोल्डर (Folder) एक डिजिटल दराज (Drawer/Bag) की तरह होता है जिसके अंदर कई फाइलों और सब-फोल्डरों को व्यवस्थित रखा जाता है।",
                                steps = listOf(
                                    "फाइल एक्सटेंशन: फाइल के नाम के बाद डॉट (.) के बाद का भाग यह बताता है कि फाइल किस प्रकार की है। जैसे document.docx (वर्ड फाइल), picture.jpg (फोटो), song.mp3 (ऑडियो)।"
                                )
                            ),
                            LessonSection(
                                heading = "फोल्डर कैसे बनाएं और फाइल ट्रांसफर कैसे करें?",
                                content = "कदम-दर-कदम प्रक्रिया:",
                                steps = listOf(
                                    "1. नया फोल्डर बनाना: खाली जगह पर राइट क्लिक करें -> New -> Folder चुनें (या शॉर्टकट: Ctrl + Shift + N)।",
                                    "2. नाम बदलना (Rename): फोल्डर पर क्लिक करें और F2 दबाएं या राइट क्लिक करके Rename चुनें।",
                                    "3. Copy (कॉपी - Ctrl+C): फाइल मूल जगह पर भी रहती है और उसकी एक प्रति दूसरी जगह बन जाती है।",
                                    "4. Cut (कट - Ctrl+X): फाइल मूल जगह से हटकर नई जगह पहुंच जाती है (Move)।",
                                    "5. Paste (पेस्ट - Ctrl+V): कॉपी या कट की गई फाइल को नई जगह रखने के लिए।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "Local Disk (C:) में विंडोज ऑपरेटिंग सिस्टम की फाइलें होती हैं। C: ड्राइव की किसी भी सिस्टम फाइल को बिना जानकारी के डिलीट न करें।"
                        ),
                        commonMistakes = listOf(
                            "साधारण Delete और Permanent Delete में अंतर न समझना। Shift + Delete दबाने पर फाइल रीसायकल बिन में नहीं जाती, सीधे हमेशा के लिए डिलीट हो जाती है।"
                        ),
                        summary = listOf(
                            "Ctrl + C = कॉपी (Copy), Ctrl + X = कट (Cut), Ctrl + V = पेस्ट (Paste)।",
                            "डिलीट की गई फाइलें रीसायकल बिन (Recycle Bin) में सुरक्षित रहती हैं, जिन्हें 'Restore' किया जा सकता है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_win_files",
                            lessonId = "les_win_file_management",
                            title = "फाइल मैनेजमेंट टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_file_1",
                                    question = "विंडोज में नया फोल्डर बनाने का कीबोर्ड शॉर्टकट क्या है?",
                                    options = listOf("Ctrl + N", "Ctrl + Shift + N", "Alt + N", "Shift + F"),
                                    correctAnswerIndex = 1,
                                    explanation = "Ctrl + Shift + N दबाने से तुरंत नया फोल्डर बन जाता है।"
                                ),
                                Question(
                                    id = "q_file_2",
                                    question = "किसी फाइल को रीसायकल बिन में भेजे बिना स्थायी (Permanently) डिलीट करने का शॉर्टकट क्या है?",
                                    options = listOf("Ctrl + Delete", "Shift + Delete", "Alt + Delete", "F8 + Delete"),
                                    correctAnswerIndex = 1,
                                    explanation = "Shift + Delete दबाने से फाइल स्थायी रूप से हट जाती है और रीसायकल बिन में नहीं जाती।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    val courseD = Course(
        id = "course_word",
        title = "D. माइक्रोसॉफ्ट वर्ड (Microsoft Word)",
        englishTitle = "Microsoft Word",
        hindiTitle = "माइक्रोसॉफ्ट वर्ड",
        description = "ऑफिस लेटर, रिज्यूमे, बायोडाटा, एप्लीकेशन, पैराग्राफ फॉर्मेटिंग, टेबल, हेडर-फूटर, पेज नंबर और पीडीएफ एक्सपोर्ट में संपूर्ण दक्षता।",
        level = CourseLevel.INTERMEDIATE,
        category = "ऑफिस टूल्स (Office Tools)",
        estimatedHours = "5 घंटे",
        prerequisites = listOf("कीबोर्ड टाइपिंग और फाइल सेव करने की जानकारी"),
        iconKey = "description",
        modules = listOf(
            Module(
                id = "mod_word_1",
                courseId = "course_word",
                title = "मॉड्यूल 1: डॉक्यूमेंट निर्माण और टेक्स्ट फॉर्मेटिंग",
                description = "फॉन्ट, साइज, बोल्ड, इटैलिक, अंडरलाइन, अलाइनमेंट और रंग।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_word_formatting",
                        courseId = "course_word",
                        moduleId = "mod_word_1",
                        title = "1.1 टेक्स्ट फॉर्मेटिंग और पैराग्राफ सेटिंग्स",
                        englishTitle = "Text Formatting & Paragraph Alignment",
                        durationMinutes = 25,
                        learningObjectives = listOf(
                            "MS Word का इंटरफेस (Ribbon, Tabs, Ruler, Status Bar) समझना",
                            "फॉन्ट बदलना, साइज बढ़ाना/घटाना और कलर देना",
                            "बोल्ड (Ctrl+B), इटैलिक (Ctrl+I), अंडरलाइन (Ctrl+U) का प्रयोग",
                            "4 प्रकार के अलाइनमेंट: Left, Center, Right, Justify"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "टेक्स्ट स्टाइलिंग (Text Styling)",
                                content = "डॉक्यूमेंट को सुंदर और आकर्षक बनाने के लिए Home टैब के Font ग्रुप का उपयोग किया जाता है:",
                                steps = listOf(
                                    "Bold (Ctrl + B): अक्षरों को गहरा और मोटा करने के लिए। (शीर्षकों के लिए)",
                                    "Italic (Ctrl + I): अक्षरों को थोड़ा तिरछा करने के लिए। (उद्धरण या परिभाषाओं के लिए)",
                                    "Underline (Ctrl + U): शब्दों के नीचे रेखा खींचने के लिए।",
                                    "Font Family: फॉन्ट का प्रकार चुनना (जैसे Kruti Dev, Mangal, Arial, Times New Roman)।",
                                    "Font Size: अक्षरों का आकार बढ़ाना या घटाना। सामान्य डॉक्यूमेंट बॉडी के लिए 12pt आदर्श होता है।"
                                )
                            ),
                            LessonSection(
                                heading = "पैराग्राफ अलाइनमेंट (Alignment)",
                                content = "अलाइनमेंट बताता है कि पेज के दोनों किनारों के बीच टेक्स्ट कैसे व्यवस्थित रहेगा:",
                                steps = listOf(
                                    "1. Align Left (Ctrl + L): टेक्स्ट बाईं ओर बराबर रहता है (अंग्रेजी और सामान्य ड्राफ्टिंग)।",
                                    "2. Center (Ctrl + E): टेक्स्ट पेज के ठीक बीच में आ जाता है (मुख्य हेडिंग्स के लिए)।",
                                    "3. Align Right (Ctrl + R): टेक्स्ट दाईं ओर बराबर होता है (तारीख या प्रेषक का पता)।",
                                    "4. Justify (Ctrl + J): टेक्स्ट दोनों किनारों (Left & Right) पर बिल्कुल बराबर और सुव्यवस्थित रहता है (किताबों और सरकारी पत्रों के लिए अनिवार्य)।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "काम करते समय हर 5 मिनट में Ctrl + S दबाकर डॉक्यूमेंट सेव करते रहें ताकि अचानक बिजली बंद होने पर आपका काम न खोए।"
                        ),
                        summary = listOf(
                            "Justify (Ctrl + J) डॉक्यूमेंट को सबसे प्रोफेशनल लुक देता है।",
                            "Ctrl + Z से गलती होने पर तुरंत Undo (पिछली स्थिति) किया जा सकता है।"
                        ),
                        practicalTasks = listOf(
                            PracticalTask(
                                id = "task_word_save_shortcut",
                                title = "सेव और अनडू शॉर्टकट टेस्ट",
                                instructions = listOf(
                                    "कीबोर्ड पर Ctrl + S दबाएं (Save) और फिर Ctrl + Z दबाएं (Undo)।"
                                ),
                                isTechnicallyVerifiable = true,
                                verificationType = VerificationType.SHORTCUT_TESTER,
                                targetExpectedInput = "Ctrl+S"
                            )
                        ),
                        quiz = Quiz(
                            id = "quiz_word_fmt",
                            lessonId = "les_word_formatting",
                            title = "वर्ड फॉर्मेटिंग क्विज",
                            questions = listOf(
                                Question(
                                    id = "q_wd_1",
                                    question = "डॉक्यूमेंट में टेक्स्ट को दोनों तरफ से बराबर (Justify) करने का शॉर्टकट क्या है?",
                                    options = listOf("Ctrl + J", "Ctrl + E", "Ctrl + B", "Ctrl + R"),
                                    correctAnswerIndex = 0,
                                    explanation = "Ctrl + J से टेक्स्ट दोनों सिरों पर बराबर संरेखित (Justify) हो जाता है।"
                                ),
                                Question(
                                    id = "q_wd_2",
                                    question = "टेक्स्ट को सेंटर (बीच में) करने का शॉर्टकट क्या है?",
                                    options = listOf("Ctrl + C", "Ctrl + E", "Ctrl + M", "Alt + C"),
                                    correctAnswerIndex = 1,
                                    explanation = "Ctrl + E सेंटर अलाइनमेंट के लिए होता है (Ctrl + C कॉपी के लिए होता है)।"
                                )
                            )
                        )
                    )
                )
            ),
            Module(
                id = "mod_word_2",
                courseId = "course_word",
                title = "मॉड्यूल 2: टेबल, पेज लेआउट और पीडीएफ एक्सपोर्ट",
                description = "तालिका बनाना, मार्जिन सेट करना, हेडर-फूटर और पीडीएफ के रूप में सहेजना।",
                order = 2,
                lessons = listOf(
                    Lesson(
                        id = "les_word_tables_pdf",
                        courseId = "course_word",
                        moduleId = "mod_word_2",
                        title = "2.1 टेबल निर्माण, मार्जिन और PDF एक्सपोर्ट",
                        englishTitle = "Tables, Margins & PDF Export",
                        durationMinutes = 25,
                        learningObjectives = listOf(
                            "Insert Tab से Table (पंक्तियां और कॉलम) बनाना",
                            "Page Layout से Margins (Normal, Narrow) और Orientation (Portrait, Landscape) सेट करना",
                            "डॉक्यूमेंट को बिना फॉर्मेट बिगड़े PDF फाइल में एक्सपोर्ट करना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "टेबल कैसे बनाएं? (Inserting Tables)",
                                content = "डेटा को सुव्यवस्थित करने के लिए टेबल का उपयोग करें:",
                                steps = listOf(
                                    "1. ऊपर दिए गए मेनू में Insert टैब पर क्लिक करें।",
                                    "2. Table पर क्लिक करें और जितने Rows (पंक्तियां) और Columns (कॉलम) चाहिए उन्हें चुनें।",
                                    "3. एक सेल से अगले सेल में जाने के लिए Tab की दबाएं।"
                                )
                            ),
                            LessonSection(
                                heading = "पेज लेआउट और PDF में सेव करना",
                                content = "प्रिंट या शेयर करने से पहले लेआउट जांचना अनिवार्य है:",
                                steps = listOf(
                                    "Orientation: Portrait (लंबाई में) या Landscape (चौड़ाई में)। सामान्यतः लेटर या रिज्यूमे Portrait में होते हैं।",
                                    "PDF Export: File मेनू में जाएं -> Export -> Create PDF/XPS पर क्लिक करें (या Save As में जाकर File Type: PDF चुनें)।",
                                    "फायदा: PDF फाइल को किसी भी फोन या कंप्यूटर पर खोलने पर उसका फॉन्ट और लेआउट कभी नहीं बिगड़ता।"
                                )
                            )
                        ),
                        proTips = listOf(
                            "सरकारी या प्राइवेट ऑफिस में बायोडाटा या पत्र भेजते समय हमेशा .docx की जगह .pdf भेजें।"
                        ),
                        summary = listOf(
                            "टेबल डेटा को स्पष्ट दर्शाती है।",
                            "PDF (Portable Document Format) डिजिटल डॉक्यूमेंट शेयरिंग का सबसे सुरक्षित और मानक प्रारूप है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_word_table_pdf",
                            lessonId = "les_word_tables_pdf",
                            title = "वर्ड टेबल व लेआउट टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_wt_1",
                                    question = "डॉक्यूमेंट को चौड़ाई में (Horizontal) प्रिंट करने के लिए कौन सा ओरिएंटेशन चुना जाता है?",
                                    options = listOf("Portrait", "Landscape", "Vertical", "Square"),
                                    correctAnswerIndex = 1,
                                    explanation = "Landscape ओरिएंटेशन चौड़ा होता है, जबकि Portrait सीधा लंबा होता है।"
                                ),
                                Question(
                                    id = "q_wt_2",
                                    question = "PDF का पूरा नाम क्या है?",
                                    options = listOf("Personal Document File", "Portable Document Format", "Printed Data Form", "Public Document Folder"),
                                    correctAnswerIndex = 1,
                                    explanation = "PDF = Portable Document Format।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )
}
