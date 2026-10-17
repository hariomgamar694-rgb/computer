package com.example.data.content

import com.example.data.model.*

object CourseCatalogA_B {

    val courseA = Course(
        id = "course_fundamentals",
        title = "A. कंप्यूटर के मूल सिद्धांत (Computer Fundamentals)",
        englishTitle = "Computer Fundamentals",
        hindiTitle = "कंप्यूटर के मूल सिद्धांत",
        description = "कंप्यूटर की बुनियादी संरचना, हार्डवेयर, सॉफ्टवेयर, मेमोरी और काम करने के तरीके को बिल्कुल जीरो लेवल से समझें।",
        level = CourseLevel.BEGINNER,
        category = "बुनियादी ज्ञान (Basics)",
        estimatedHours = "4 घंटे",
        prerequisites = listOf("कोई पूर्व ज्ञान आवश्यक नहीं — बिल्कुल शुरुआती शिक्षार्थियों के लिए"),
        iconKey = "desktop_windows",
        modules = listOf(
            Module(
                id = "mod_fund_1",
                courseId = "course_fundamentals",
                title = "मॉड्यूल 1: कंप्यूटर क्या है और कैसे काम करता है?",
                description = "कंप्यूटर का परिचय, IPO चक्र और इतिहास।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_fund_intro",
                        courseId = "course_fundamentals",
                        moduleId = "mod_fund_1",
                        title = "1.1 कंप्यूटर का परिचय और IPO चक्र",
                        englishTitle = "Introduction to Computer & IPO Cycle",
                        durationMinutes = 15,
                        learningObjectives = listOf(
                            "कंप्यूटर की परिभाषा और मूल उद्देश्य समझना",
                            "IPO (Input -> Process -> Output) चक्र को उदाहरण सहित जानना",
                            "कंप्यूटर के मुख्य अंगों की पहचान करना"
                        ),
                        prerequisites = emptyList(),
                        sections = listOf(
                            LessonSection(
                                heading = "कंप्यूटर क्या है? (What is Computer?)",
                                content = "कंप्यूटर एक इलेक्ट्रॉनिक उपकरण (Electronic Device) है जो यूजर से डेटा और निर्देश (Data & Instructions) लेता है, उस पर प्रक्रिया (Process) करता है और अर्थपूर्ण परिणाम यानी आउटपुट (Output/Information) प्रदान करता है।",
                                examples = listOf(
                                    "उदाहरण: जब आप कैलकुलेटर में 5 + 5 टाइप करते हैं, तो 5 और + इनपुट हैं, गणना होना प्रोसेस है, और स्क्रीन पर 10 दिखना आउटपुट है।"
                                ),
                                callout = "कंप्यूटर स्वयं कोई निर्णय नहीं ले सकता; यह आपके द्वारा दिए गए निर्देशों (Programs) के अनुसार ही कार्य करता है।"
                            ),
                            LessonSection(
                                heading = "IPO साइकिल (Input - Process - Output Cycle)",
                                content = "दुनिया का प्रत्येक कंप्यूटर मुख्य रूप से तीन चरणों में कार्य करता है:",
                                steps = listOf(
                                    "1. Input (इनपुट): कीबोर्ड, माउस या माइक द्वारा डेटा या कमांड देना।",
                                    "2. Process (प्रोसेस): CPU (Central Processing Unit) द्वारा उस डेटा पर गणना या विश्लेषण करना।",
                                    "3. Output (आउटपुट): मॉनिटर स्क्रीन पर डिस्प्ले होना या प्रिंटर द्वारा प्रिंट निकलना।",
                                    "4. Storage (स्टोरेज): भविष्य में उपयोग के लिए हार्ड डिस्क या SSD में डेटा सुरक्षित रखना।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "कंप्यूटर को हमेशा उचित तरीके से शट डाउन (Shut Down) करें, कभी भी मेन स्विच सीधे बंद न करें।"
                        ),
                        commonMistakes = listOf(
                            "डेटा (Data) और सूचना (Information) को एक ही समझना। कच्चा तथ्य (जैसे संख्याएं) डेटा है, जबकि प्रोसेस होने के बाद निकला परिणाम सूचना है।"
                        ),
                        proTips = listOf(
                            "कंप्यूटर को धूल-मिट्टी और पानी से दूर रखें ताकि आंतरिक घटक सुरक्षित रहें।"
                        ),
                        summary = listOf(
                            "कंप्यूटर एक तेज और सटीक इलेक्ट्रॉनिक मशीन है।",
                            "यह IPO (Input-Process-Output) सिद्धांत पर कार्य करता है।",
                            "कंप्यूटर के दो मुख्य पहलू हैं: हार्डवेयर (जिसे छू सकते हैं) और सॉफ्टवेयर (जो निर्देश देता है)।"
                        ),
                        practicalTasks = listOf(
                            PracticalTask(
                                id = "task_fund_identify_ipo",
                                title = "IPO चक्र की पहचान करें",
                                instructions = listOf(
                                    "अपने आसपास के 3 कार्यों (जैसे एटीएम से पैसे निकालना) में इनपुट, प्रोसेस और आउटपुट को पहचानें।"
                                ),
                                isTechnicallyVerifiable = false,
                                verificationType = VerificationType.SELF_REPORTED
                            )
                        ),
                        quiz = Quiz(
                            id = "quiz_fund_intro",
                            lessonId = "les_fund_intro",
                            title = "कंप्यूटर परिचय क्विज",
                            passingScorePercentage = 70,
                            questions = listOf(
                                Question(
                                    id = "q_fund_1",
                                    question = "कंप्यूटर मुख्य रूप से किस चक्र (Cycle) पर काम करता है?",
                                    options = listOf("IPO (Input-Process-Output)", "OPI (Output-Process-Input)", "PIO (Process-Input-Output)", "Direct Action"),
                                    correctAnswerIndex = 0,
                                    explanation = "कंप्यूटर हमेशा पहले इनपुट लेता है, फिर प्रोसेस करता है, और अंत में आउटपुट देता है (Input -> Process -> Output)।"
                                ),
                                Question(
                                    id = "q_fund_2",
                                    question = "कंप्यूटर का मस्तिष्क (Brain of Computer) किसे कहा जाता है?",
                                    options = listOf("मॉनिटर (Monitor)", "CPU (Central Processing Unit)", "कीबोर्ड (Keyboard)", "हार्ड डिस्क (Hard Disk)"),
                                    correctAnswerIndex = 1,
                                    explanation = "CPU कंप्यूटर के सभी निर्देशों को प्रोसेस और नियंत्रित करता है, इसलिए इसे कंप्यूटर का मस्तिष्क कहा जाता है।"
                                )
                            )
                        ),
                        relatedLessonIds = listOf("les_fund_hw_sw", "les_fund_memory")
                    ),
                    Lesson(
                        id = "les_fund_hw_sw",
                        courseId = "course_fundamentals",
                        moduleId = "mod_fund_1",
                        title = "1.2 हार्डवेयर और सॉफ्टवेयर में अंतर",
                        englishTitle = "Hardware vs Software",
                        durationMinutes = 20,
                        learningObjectives = listOf(
                            "हार्डवेयर (Hardware) और सॉफ्टवेयर (Software) की स्पष्ट परिभाषा जानना",
                            "सिस्टम सॉफ्टवेयर और एप्लीकेशन सॉफ्टवेयर में अंतर समझना",
                            "मुख्य इनपुट व आउटपुट डिवाइसेस की सूची याद रखना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "हार्डवेयर (Hardware)",
                                content = "कंप्यूटर के वे सभी भौतिक भाग (Physical Parts) जिन्हें हम आंखों से देख सकते हैं और हाथों से छू सकते हैं, हार्डवेयर कहलाते हैं।",
                                examples = listOf(
                                    "इनपुट डिवाइस: कीबोर्ड, माउस, स्कैनर, माइक्रोफोन, वेबकैम।",
                                    "आउटपुट डिवाइस: मॉनिटर, प्रिंटर, स्पीकर, हेडफोन, प्रोजेक्टर।",
                                    "स्टोरेज डिवाइस: हार्ड डिस्क (HDD), SSD, पेन ड्राइव, मेमोरी कार्ड।"
                                )
                            ),
                            LessonSection(
                                heading = "सॉफ्टवेयर (Software)",
                                content = "सॉफ्टवेयर निर्देशों और प्रोग्रामों का एक समूह (Set of Instructions) है जो हार्डवेयर को बताता है कि क्या और कैसे करना है। सॉफ्टवेयर को छुआ नहीं जा सकता।",
                                steps = listOf(
                                    "1. सिस्टम सॉफ्टवेयर (System Software): जैसे Windows 10/11, Android, Linux। यह पूरे कंप्यूटर को संचालित करता है।",
                                    "2. एप्लीकेशन सॉफ्टवेयर (Application Software): जैसे MS Word, Excel, Chrome Browser। यह यूजर के विशिष्ट कार्यों के लिए होता है।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "बिना एंटीवायरस या अज्ञात वेबसाइटों से कभी अनधिकृत सॉफ्टवेयर डाउनलोड न करें।"
                        ),
                        commonMistakes = listOf(
                            "पेन ड्राइव को सॉफ्टवेयर समझना। पेन ड्राइव एक हार्डवेयर स्टोरेज डिवाइस है, जबकि उसमें रखी फाइलें सॉफ्टवेयर/डेटा हैं।"
                        ),
                        summary = listOf(
                            "हार्डवेयर कंप्यूटर का शरीर है और सॉफ्टवेयर उसकी आत्मा।",
                            "दोनों एक-दूसरे के बिना अधूरे हैं।"
                        ),
                        quiz = Quiz(
                            id = "quiz_fund_hw_sw",
                            lessonId = "les_fund_hw_sw",
                            title = "हार्डवेयर और सॉफ्टवेयर टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_hw_1",
                                    question = "इनमें से कौन सा इनपुट डिवाइस (Input Device) है?",
                                    options = listOf("प्रिंटर", "मॉनिटर", "माउस", "स्पीकर"),
                                    correctAnswerIndex = 2,
                                    explanation = "माउस यूजर से क्लिक और मूवमेंट इनपुट लेता है, जबकि प्रिंटर और मॉनिटर आउटपुट देते हैं।"
                                ),
                                Question(
                                    id = "q_hw_2",
                                    question = "Windows 11 किस प्रकार का सॉफ्टवेयर है?",
                                    options = listOf("सिस्टम सॉफ्टवेयर (ऑपरेटिंग सिस्टम)", "एप्लीकेशन सॉफ्टवेयर", "हार्डवेयर घटक", "एंटीवायरस"),
                                    correctAnswerIndex = 0,
                                    explanation = "Windows एक ऑपरेटिंग सिस्टम यानी सिस्टम सॉफ्टवेयर है जो पूरे कंप्यूटर को चलाने का आधार प्रदान करता है।"
                                )
                            )
                        )
                    )
                )
            ),
            Module(
                id = "mod_fund_2",
                courseId = "course_fundamentals",
                title = "मॉड्यूल 2: मेमोरी और स्टोरेज इकाइयां",
                description = "RAM, ROM, SSD, HDD और डेटा की इकाइयां (Bit, Byte, GB)।",
                order = 2,
                lessons = listOf(
                    Lesson(
                        id = "les_fund_memory",
                        courseId = "course_fundamentals",
                        moduleId = "mod_fund_2",
                        title = "2.1 RAM, ROM और स्टोरेज इकाइयां (Bit, Byte, GB)",
                        englishTitle = "Memory Types & Storage Units",
                        durationMinutes = 20,
                        learningObjectives = listOf(
                            "RAM और ROM के बीच का अंतर समझना",
                            "अस्थिर (Volatile) और स्थायी (Non-volatile) मेमोरी को जानना",
                            "डेटा माप की इकाइयां (KB, MB, GB, TB) सीखना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "RAM vs ROM",
                                content = "कंप्यूटर में दो मुख्य प्रकार की प्राथमिक मेमोरी (Primary Memory) होती है:",
                                steps = listOf(
                                    "RAM (Random Access Memory): यह एक अस्थायी (Volatile) मेमोरी है। जब तक कंप्यूटर चालू रहता है, प्रोग्राम इसमें लोड रहते हैं। बिजली जाने पर इसका डेटा मिट जाता है।",
                                    "ROM (Read Only Memory): यह स्थायी (Non-Volatile) होती है। इसमें कंप्यूटर को स्टार्ट करने वाले निर्देश (BIOS/UEFI) सुरक्षित रहते हैं।"
                                )
                            ),
                            LessonSection(
                                heading = "डेटा मापन की इकाइयां (Memory Measurement Units)",
                                content = "कंप्यूटर बाइनरी भाषा (0 और 1) समझता है। इसे मापने का पैमाना इस प्रकार है:",
                                steps = listOf(
                                    "1 Bit = 0 या 1 (सबसे छोटी इकाई)",
                                    "8 Bits = 1 Byte (एक अक्षर या संख्या)",
                                    "1024 Bytes = 1 Kilobyte (KB)",
                                    "1024 KB = 1 Megabyte (MB)",
                                    "1024 MB = 1 Gigabyte (GB)",
                                    "1024 GB = 1 Terabyte (TB)"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "अपने महत्वपूर्ण डेटा का हमेशा बैकअप किसी बाहरी ड्राइव या क्लाउड पर रखें।"
                        ),
                        summary = listOf(
                            "अधिक RAM होने से कंप्यूटर एक साथ कई प्रोग्राम (Multitasking) आसानी से चला सकता है।",
                            "SSD पारंपरिक HDD की तुलना में 5 से 10 गुना तेज होती है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_fund_memory",
                            lessonId = "les_fund_memory",
                            title = "मेमोरी और यूनिट्स टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_mem_1",
                                    question = "1 Gigabyte (GB) में कितने Megabyte (MB) होते हैं?",
                                    options = listOf("100 MB", "1000 MB", "1024 MB", "512 MB"),
                                    correctAnswerIndex = 2,
                                    explanation = "कंप्यूटर बाइनरी सिस्टम में 1 GB = 1024 MB होता है।"
                                ),
                                Question(
                                    id = "q_mem_2",
                                    question = "कंप्यूटर बंद होने पर किस मेमोरी का डेटा नष्ट हो जाता है?",
                                    options = listOf("ROM", "हार्ड डिस्क", "RAM", "पेन ड्राइव"),
                                    correctAnswerIndex = 2,
                                    explanation = "RAM एक अस्थायी (Volatile) मेमोरी है, जो बिजली कटते ही खाली हो जाती है।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    val courseB = Course(
        id = "course_typing",
        title = "B. माउस, कीबोर्ड और टाइपिंग (Mouse, Keyboard & Typing)",
        englishTitle = "Mouse, Keyboard & Typing",
        hindiTitle = "माउस, कीबोर्ड और टाइपिंग",
        description = "माउस को सही पकड़ना, लेफ्ट/राइट क्लिक, ड्रैग-ड्रॉप, कीबोर्ड की सभी कुंजियों का उपयोग और हिंदी/अंग्रेजी टाइपिंग का बुनियादी अभ्यास।",
        level = CourseLevel.BEGINNER,
        category = "बुनियादी ज्ञान (Basics)",
        estimatedHours = "3 घंटे",
        prerequisites = listOf("कंप्यूटर का सामान्य परिचय"),
        iconKey = "keyboard",
        modules = listOf(
            Module(
                id = "mod_typing_1",
                courseId = "course_typing",
                title = "मॉड्यूल 1: माउस संचालन और तकनीक",
                description = "लेफ्ट क्लिक, राइट क्लिक, डबल क्लिक, स्क्रॉल और ड्रैग-एंड-ड्रॉप।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_mouse_basics",
                        courseId = "course_typing",
                        moduleId = "mod_typing_1",
                        title = "1.1 माउस का सही उपयोग और क्रियाएं",
                        englishTitle = "Mouse Operations & Proper Grip",
                        durationMinutes = 15,
                        learningObjectives = listOf(
                            "माउस को सही मुद्रा (Ergonomic Grip) में पकड़ना",
                            "लेफ्ट क्लिक, राइट क्लिक और डबल क्लिक में अंतर",
                            "ड्रैग और ड्रॉप (Drag and Drop) तकनीक में महारत"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "माउस के मुख्य 5 एक्शन (5 Core Mouse Actions)",
                                content = "माउस को हमेशा सपाट माउस पैड पर रखें और कलाई को आराम की स्थिति में रखें:",
                                steps = listOf(
                                    "1. Left Click (लेफ्ट क्लिक): किसी फाइल, फोल्डर या बटन को चुनने (Select) के लिए।",
                                    "2. Double Click (डबल क्लिक): किसी फाइल, फोल्डर या प्रोग्राम को खोलने (Open) के लिए तुरंत दो बार लेफ्ट क्लिक करना।",
                                    "3. Right Click (राइट क्लिक): संदर्भ मेनू (Context Menu/Options) खोलने के लिए, जिसमें कट, कॉपी, डिलीट, प्रॉपर्टीज आदि विकल्प मिलते हैं।",
                                    "4. Drag and Drop (ड्रैग और ड्रॉप): लेफ्ट बटन को दबाए रखकर माउस को हिलाना और दूसरी जगह ले जाकर छोड़ना।",
                                    "5. Scroll Wheel (स्क्रॉल व्हील): लंबे वेब पेज या डॉक्यूमेंट को ऊपर-नीचे सरकाने के लिए बीच के पहिये को घुमाना।"
                                )
                            )
                        ),
                        proTips = listOf(
                            "डबल क्लिक करते समय माउस को हिलाएं नहीं, अन्यथा कंप्यूटर उसे ड्रैग समझ सकता है।"
                        ),
                        summary = listOf(
                            "लेफ्ट क्लिक = चुनना, डबल क्लिक = खोलना, राइट क्लिक = ऑप्शंस देखना।"
                        ),
                        practicalTasks = listOf(
                            PracticalTask(
                                id = "task_mouse_drag",
                                title = "डेस्कटॉप पर आइकन ड्रैग और ड्रॉप करें",
                                instructions = listOf(
                                    "डेस्कटॉप पर किसी एक आइकन पर लेफ्ट क्लिक दबाए रखें।",
                                    "माउस को स्क्रीन के दूसरी ओर ले जाएं और बटन छोड़ें।"
                                ),
                                isTechnicallyVerifiable = false,
                                verificationType = VerificationType.SELF_REPORTED
                            )
                        ),
                        quiz = Quiz(
                            id = "quiz_mouse_ops",
                            lessonId = "les_mouse_basics",
                            title = "माउस ऑपरेशंस क्विज",
                            questions = listOf(
                                Question(
                                    id = "q_mouse_1",
                                    question = "किसी फाइल को खोलने (Open) के लिए कौन सा माउस एक्शन किया जाता है?",
                                    options = listOf("राइट क्लिक", "डबल लेफ्ट क्लिक", "स्क्रॉल", "माउस को हिलाना"),
                                    correctAnswerIndex = 1,
                                    explanation = "डबल लेफ्ट क्लिक करने से फाइल या प्रोग्राम ओपन हो जाता है।"
                                ),
                                Question(
                                    id = "q_mouse_2",
                                    question = "किसी फाइल के ऑप्शंस (जैसे Copy, Delete, Rename) देखने के लिए क्या करेंगे?",
                                    options = listOf("राइट क्लिक (Right Click)", "लेफ्ट क्लिक", "डबल क्लिक", "ड्रैग"),
                                    correctAnswerIndex = 0,
                                    explanation = "राइट क्लिक करने से उस आइटम का कॉन्टेक्स्ट मेनू (Options) खुलता है।"
                                )
                            )
                        )
                    )
                )
            ),
            Module(
                id = "mod_typing_2",
                courseId = "course_typing",
                title = "मॉड्यूल 2: कीबोर्ड की कुंजियां और टाइपिंग स्पीड",
                description = "अल्फाबेट, नंबर, फंक्शन, नेविगेशन, स्पेशल कीज और होम रो तकनीक।",
                order = 2,
                lessons = listOf(
                    Lesson(
                        id = "les_keyboard_keys",
                        courseId = "course_typing",
                        moduleId = "mod_typing_2",
                        title = "2.1 कीबोर्ड लेआउट और मुख्य कुंजियां (Keys)",
                        englishTitle = "Keyboard Layout & Special Keys",
                        durationMinutes = 25,
                        learningObjectives = listOf(
                            "कीबोर्ड के विभिन्न वर्गों (Alphanumeric, Function, Navigation, Modifiers) को जानना",
                            "Shift, Caps Lock, Ctrl, Alt, Enter और Backspace का सटीक उपयोग",
                            "टच टाइपिंग (Touch Typing) की होम रो (ASDF - JKL;) स्थिति सीखना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "कीबोर्ड की प्रमुख कुंजियां (Key Categories)",
                                content = "एक मानक कीबोर्ड में सामान्यतः 104 कुंजियां होती हैं:",
                                steps = listOf(
                                    "Enter (एंटर): नई लाइन शुरू करने या किसी कमांड को सबमिट करने के लिए।",
                                    "Backspace (बैकस्पेस): कर्सर के बाईं (Left) ओर का अक्षर मिटाने के लिए।",
                                    "Delete (डिलीट): कर्सर के दाईं (Right) ओर का अक्षर मिटाने के लिए।",
                                    "Caps Lock: अंग्रेजी के सभी अक्षरों को CAPITAL (बड़ा) करने के लिए।",
                                    "Shift: किसी एक अक्षर को बड़ा करने या किसी बटन के ऊपर छपे चिन्ह (जैसे @, #, $) को टाइप करने के लिए।",
                                    "Spacebar: दो शब्दों के बीच खाली जगह (Space) देने के लिए।"
                                )
                            ),
                            LessonSection(
                                heading = "होम रो तकनीक (Home Row Typing Technique)",
                                content = "बिना देखे टाइपिंग करने के लिए उंगलियों को हमेशा होम रो पर टिका कर रखें:",
                                steps = listOf(
                                    "बाएं हाथ की उंगलियां: A, S, D, F पर रखें। (F पर एक छोटा उभार होता है)।",
                                    "दाएं हाथ की उंगलियां: J, K, L, ; पर रखें। (J पर भी उभार होता है)।",
                                    "दोनों अंगूठे (Thumbs): स्पेसबार पर रखें।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "टाइपिंग के दौरान कलाई को टेबल पर बहुत ज्यादा न दबाएं; एर्गोनोमिक स्थिति में रखें ताकि उंगलियों में खिंचाव न आए।"
                        ),
                        commonMistakes = listOf(
                            "Backspace और Delete में भ्रमित होना। Backspace बाईं ओर मिटाता है और Delete दाईं ओर मिटाता है।"
                        ),
                        summary = listOf(
                            "होम रो (ASDF - JKL;) सही टाइपिंग की नींव है।",
                            "F और J कीज़ पर उभरे निशान हाथ की स्थिति बिना देखे सही रखने के लिए होते हैं।"
                        ),
                        practicalTasks = listOf(
                            PracticalTask(
                                id = "task_keyboard_homerow",
                                title = "होम रो टाइपिंग अभ्यास",
                                instructions = listOf(
                                    "नोटपैड खोलें और बिना कीबोर्ड देखे 'asdf jkl; asdf jkl;' 10 बार टाइप करें।"
                                ),
                                isTechnicallyVerifiable = true,
                                verificationType = VerificationType.SHORTCUT_TESTER,
                                targetExpectedInput = "asdf jkl;"
                            )
                        ),
                        quiz = Quiz(
                            id = "quiz_keyboard_keys",
                            lessonId = "les_keyboard_keys",
                            title = "कीबोर्ड क्विज",
                            questions = listOf(
                                Question(
                                    id = "q_kb_1",
                                    question = "कर्सर के बाईं (Left) तरफ के अक्षर को मिटाने के लिए किस कुंजी का उपयोग होता है?",
                                    options = listOf("Delete", "Backspace", "Spacebar", "Escape"),
                                    correctAnswerIndex = 1,
                                    explanation = "Backspace कर्सर के बाईं ओर का अक्षर मिटाता है, जबकि Delete दाईं ओर का।"
                                ),
                                Question(
                                    id = "q_kb_2",
                                    question = "कीबोर्ड पर होम रो (Home Row) में किन दो अक्षरों पर उभार (Bump) होता है?",
                                    options = listOf("A और L", "F और J", "G और H", "Q और P"),
                                    correctAnswerIndex = 1,
                                    explanation = "F और J कुंजियों पर हल्का सा उभार होता है ताकि बिना देखे उंगलियों की स्थिति सही पहचानी जा सके।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )
}
