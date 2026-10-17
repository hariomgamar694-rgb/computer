package com.example.data.content

import com.example.data.model.*

object CourseCatalogI_J {

    val courseI = Course(
        id = "course_office_printing",
        title = "I. पीडीएफ, प्रिंटिंग और डिजिटल ऑफिस (PDF, Printing & Office)",
        englishTitle = "PDF, Printing & Digital Office",
        hindiTitle = "पीडीएफ, प्रिंटिंग और डिजिटल ऑफिस",
        description = "प्रिंटर सेटअप, प्रिंट प्रीव्यू (Ctrl+P), पेपर साइज (A4, Legal), प्रिंट क्यू क्लियर करना, स्कैनर का उपयोग, पेन ड्राइव सुरक्षा और ZIP आर्काइव बनाना।",
        level = CourseLevel.INTERMEDIATE,
        category = "ऑफिस टूल्स (Office Tools)",
        estimatedHours = "3.5 घंटे",
        prerequisites = listOf("वर्ड और विंडोज फाइल मैनेजमेंट का ज्ञान"),
        iconKey = "print",
        modules = listOf(
            Module(
                id = "mod_print_1",
                courseId = "course_office_printing",
                title = "मॉड्यूल 1: प्रिंटिंग, पेपर साइज और प्रिंटर प्रबंधन",
                description = "Ctrl + P, A4 vs Legal साइज, पेज रेंज, डुप्लेक्स प्रिंटिंग और प्रिंट क्यू।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_print_mastery",
                        courseId = "course_office_printing",
                        moduleId = "mod_print_1",
                        title = "1.1 प्रिंटिंग तकनीक: A4 vs Legal और प्रिंट सेटिंग्स",
                        englishTitle = "Printing Mastery, Paper Sizes & Duplex",
                        durationMinutes = 20,
                        learningObjectives = listOf(
                            "प्रिंट कमांड (Ctrl + P) और प्रिंट प्रीव्यू की महत्ता समझना",
                            "A4 साइज (सामान्य पत्र) और Legal साइज (स्टांप पेपर/कोर्ट दस्तावेज) में अंतर",
                            "पेज रेंज (Page Range - जैसे 1-3, 5, 8) चुनना",
                            "पेज के दोनों तरफ प्रिंट करना (Duplex / Two-Sided Printing)"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "प्रिंट प्रीव्यू क्यों जरूरी है? (Print Preview)",
                                content = "कागज और स्याही बचाने के लिए प्रिंट करने से पहले प्रीव्यू देखना अनिवार्य है:",
                                steps = listOf(
                                    "1. Ctrl + P दबाने पर प्रिंट विंडो और साथ में प्रीव्यू दिखता है।",
                                    "2. जांचें कि क्या कोई टेबल या टेक्स्ट किनारे से कट तो नहीं रहा है।",
                                    "3. पेपर साइज: अधिकांश आधिकारिक फाइलों के लिए 'A4' (210 x 297 mm) चुनें। अदालती या कानूनी दस्तावेजों के लिए 'Legal' (लंबा पेपर) चुनें।",
                                    "4. Copies: आपको इस डॉक्यूमेंट की कितनी प्रतियां चाहिए (जैसे 2 या 3)।"
                                )
                            ),
                            LessonSection(
                                heading = "प्रिंट क्यू और अटका हुआ प्रिंटर (Print Queue)",
                                content = "जब प्रिंटर कमांड लेने के बाद भी नहीं चलता:",
                                steps = listOf(
                                    "कारण: प्रिंटर में कागज खत्म होना, केबल ढीली होना या प्रिंट क्यू में कोई खराब जॉब अटक जाना।",
                                    "समाधान: टास्कबार के पास प्रिंटर आइकन पर डबल क्लिक करें -> प्रिंट जॉब पर राइट क्लिक करके 'Cancel All Documents' करें और प्रिंटर को रीस्टार्ट करें।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "पेन ड्राइव निकालते समय हमेशा टास्कबार से 'Safely Remove Hardware and Eject Media' पर क्लिक करें, सीधे न खींचें।"
                        ),
                        summary = listOf(
                            "भारत में 95% सामान्य प्रिंटिंग A4 साइज पेपर पर होती है।",
                            "Ctrl + P यूनिवर्सल प्रिंटिंग शॉर्टकट है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_print_office",
                            lessonId = "les_print_mastery",
                            title = "प्रिंटिंग कौशल टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_pr_1",
                                    question = "कार्यालयों में सामान्य पत्राचार के लिए सबसे अधिक उपयोग होने वाला मानक पेपर साइज कौन सा है?",
                                    options = listOf("Letter", "A4", "A3", "B5"),
                                    correctAnswerIndex = 1,
                                    explanation = "A4 (210 x 297 mm) भारतीय कार्यालयों और स्कूलों का मानक प्रिंटिंग पेपर साइज है।"
                                ),
                                Question(
                                    id = "q_pr_2",
                                    question = "डॉक्यूमेंट को प्रिंट करने का सार्वभौमिक (Universal) शॉर्टकट क्या है?",
                                    options = listOf("Ctrl + Shift + P", "Ctrl + P", "Alt + P", "Win + P"),
                                    correctAnswerIndex = 1,
                                    explanation = "Ctrl + P दबाकर किसी भी सॉफ्टवेयर में प्रिंट डायलॉग खोला जा सकता है।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    val courseJ = Course(
        id = "course_networking_career",
        title = "J. नेटवर्किंग और करियर कौशल (Networking & Career Skills)",
        englishTitle = "Networking & Career Skills",
        hindiTitle = "नेटवर्किंग और करियर कौशल",
        description = "LAN, Wi-Fi, IP एड्रेस की बुनियादी समझ, कमांड प्रॉम्प्ट (CMD), डेटा एंट्री की गति, ऑफिस वर्कफ़्लो और कंप्यूटर ऑपरेटर बनने की संपूर्ण मार्गदर्शिका।",
        level = CourseLevel.ADVANCED,
        category = "करियर और कौशल (Career & Skills)",
        estimatedHours = "5 घंटे",
        prerequisites = listOf("वर्ड, एक्सेल और इंटरनेट का संपूर्ण ज्ञान"),
        iconKey = "work",
        modules = listOf(
            Module(
                id = "mod_net_career_1",
                courseId = "course_networking_career",
                title = "मॉड्यूल 1: नेटवर्किंग की मूल बातें और CMD",
                description = "LAN, Router, Wi-Fi, IP एड्रेस (IPv4), पिंग (ping) और कमांड प्रॉम्प्ट।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_net_lan_cmd",
                        courseId = "course_networking_career",
                        moduleId = "mod_net_career_1",
                        title = "1.1 LAN, Wi-Fi, IP एड्रेस और CMD कमांड्स",
                        englishTitle = "LAN, Wi-Fi, IP Addressing & Command Prompt",
                        durationMinutes = 25,
                        learningObjectives = listOf(
                            "LAN (Local Area Network) और WAN (Wide Area Network) में अंतर",
                            "IP एड्रेस (जैसे 192.168.1.1) क्या है और यह कैसे काम करता है",
                            "Command Prompt (CMD) खोलना और बुनियादी कमांड्स (ipconfig, ping) चलाना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "नेटवर्किंग के मूल घटक (Network Components)",
                                content = "कंप्यूटरों को आपस में जोड़कर डेटा और प्रिंटर शेयर करने की प्रणाली को नेटवर्क कहते हैं:",
                                steps = listOf(
                                    "LAN (Local Area Network): एक कमरे, स्कूल या ऑफिस बिल्डिंग के कंप्यूटरों का स्थानीय नेटवर्क।",
                                    "WAN (Wide Area Network): पूरे शहर या दुनिया भर में फैला नेटवर्क (जैसे इंटरनेट)।",
                                    "Router: आपके घर/ऑफिस के सभी डिवाइसेस को इंटरनेट से जोड़ने और वाई-फाई प्रदान करने वाला मुख्य उपकरण।",
                                    "IP Address: नेटवर्क पर जुड़े प्रत्येक डिवाइस का विशिष्ट डिजिटल पता (जैसे 192.168.1.10)।"
                                )
                            ),
                            LessonSection(
                                heading = "कमांड प्रॉम्प्ट (CMD) की उपयोगी कमांड्स",
                                content = "विंडोज में टेक्स्ट आधारित कमांड चलाकर नेटवर्क जांचना:",
                                steps = listOf(
                                    "CMD खोलना: Win + R दबाएं, cmd टाइप करें और Enter दबाएं।",
                                    "ipconfig: अपने कंप्यूटर का IP एड्रेस और डिफॉल्ट गेटवे देखने के लिए।",
                                    "ping google.com: यह जांचने के लिए कि आपका इंटरनेट चल रहा है या कनेक्शन में रुकावट है।"
                                )
                            )
                        ),
                        summary = listOf(
                            "LAN स्थानीय नेटवर्क है और इंटरनेट सबसे बड़ा WAN है।",
                            "CMD में ipconfig से IP जांचा जाता है और ping से कनेक्टिविटी जांची जाती है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_net_cmd",
                            lessonId = "les_net_lan_cmd",
                            title = "नेटवर्किंग और CMD टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_cmd_1",
                                    question = "विंडोज कमांड प्रॉम्प्ट में अपने कंप्यूटर का IP एड्रेस देखने के लिए कौन सी कमांड टाइप करते हैं?",
                                    options = listOf("getip", "ipconfig", "show_network", "ping ip"),
                                    correctAnswerIndex = 1,
                                    explanation = "'ipconfig' कमांड नेटवर्क एडॉप्टर का IP एड्रेस, सबनेट मास्क और गेटवे दिखाती है।"
                                ),
                                Question(
                                    id = "q_cmd_2",
                                    question = "इंटरनेट कनेक्शन की सक्रियता और प्रतिक्रिया गति (Latency) जांचने के लिए कौन सी कमांड प्रयोग होती है?",
                                    options = listOf("ping", "speedtest", "connect", "check"),
                                    correctAnswerIndex = 0,
                                    explanation = "'ping' कमांड सर्वर पर डेटा पैकेट भेजकर प्रतिक्रिया की पुष्टि करती है।"
                                )
                            )
                        )
                    )
                )
            ),
            Module(
                id = "mod_net_career_2",
                courseId = "course_networking_career",
                title = "मॉड्यूल 2: ऑफिस जॉब, डेटा एंट्री और अंतिम परीक्षा",
                description = "कंप्यूटर ऑपरेटर जिम्मेदारियां, रिज्यूमे प्रिपरेशन और फाइनल असेसमेंट।",
                order = 2,
                lessons = listOf(
                    Lesson(
                        id = "les_career_data_entry",
                        courseId = "course_networking_career",
                        moduleId = "mod_net_career_2",
                        title = "2.1 डेटा एंट्री कौशल और कंप्यूटर ऑपरेटर कैरियर",
                        englishTitle = "Data Entry Skills & Computer Operator Career",
                        durationMinutes = 30,
                        learningObjectives = listOf(
                            "एक सफल कंप्यूटर ऑपरेटर के मुख्य कर्तव्य समझना",
                            "डेटा एंट्री में गति के साथ 99%+ एक्यूरेसी बनाए रखने के नियम",
                            "दैनिक ऑफिस रूटीन: ईमेल, डॉक्यूमेंट ड्राफ्टिंग, फाइलिंग और बैकअप"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "कंप्यूटर ऑपरेटर की मुख्य भूमिकाएं (Job Roles)",
                                content = "सरकारी और निजी कार्यालयों में कंप्यूटर ऑपरेटर के दैनिक कार्य:",
                                steps = listOf(
                                    "1. MS Word में आधिकारिक पत्र, मेमो और रिपोर्ट ड्राफ्ट करना।",
                                    "2. MS Excel में दैनिक बिक्री, उपस्थिति, बजट या बिलिंग का डेटा दर्ज करना और फॉर्मूले लगाना।",
                                    "3. दस्तावेजों को स्कैन करके सुरक्षित फोल्डर संरचना में सहेजना और जरूरत पड़ने पर प्रिंट करना।",
                                    "4. आधिकारिक ईमेल का तुरंत और विनम्रता से उत्तर देना और फाइल्स अटैच करना।",
                                    "5. दिन के अंत में सभी महत्वपूर्ण डेटा का पेन ड्राइव या गूगल ड्राइव में बैकअप लेना।"
                                )
                            )
                        ),
                        summary = listOf(
                            "डेटा एंट्री में स्पीड से अधिक महत्वपूर्ण शुद्धता (Accuracy) है।",
                            "HR Computer Sikho कोर्स पूरा करके आप किसी भी ऑफिस में आत्मविश्वास के साथ कार्य कर सकते हैं।"
                        ),
                        quiz = Quiz(
                            id = "quiz_career_final",
                            lessonId = "les_career_data_entry",
                            title = "फाइनल कैरियर असेसमेंट",
                            questions = listOf(
                                Question(
                                    id = "q_car_1",
                                    question = "डेटा एंट्री कार्य में सबसे महत्वपूर्ण गुण क्या माना जाता है?",
                                    options = listOf("केवल तेज टाइपिंग गति", "उच्च शुद्धता (Accuracy) और त्रुटिरहित डेटा", "सजावटी फॉन्ट्स का प्रयोग", "केवल माउस का उपयोग"),
                                    correctAnswerIndex = 1,
                                    explanation = "डेटा एंट्री में शुद्धता (Accuracy) सर्वोच्च प्राथमिकता होती है, क्योंकि एक गलत अंक पूरे डेटा को खराब कर सकता है।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )
}
