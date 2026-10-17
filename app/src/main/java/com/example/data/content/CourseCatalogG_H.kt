package com.example.data.content

import com.example.data.model.*

object CourseCatalogG_H {

    val courseG = Course(
        id = "course_internet",
        title = "G. इंटरनेट, ईमेल और डिजिटल सुरक्षा (Internet & Digital Safety)",
        englishTitle = "Internet, Email & Digital Safety",
        hindiTitle = "इंटरनेट, ईमेल और डिजिटल सुरक्षा",
        description = "वेब ब्राउज़र, गूगल सर्च ट्रिक्स, URL संरचना (HTTP vs HTTPS), ईमेल ड्राफ्टिंग, अटैचमेंट, क्लाउड स्टोरेज, मजबूत पासवर्ड, फिशिंग फ्रॉड और साइबर सुरक्षा।",
        level = CourseLevel.BEGINNER,
        category = "डिजिटल सुरक्षा (Safety & Web)",
        estimatedHours = "4 घंटे",
        prerequisites = listOf("कंप्यूटर संचालन की सामान्य जानकारी"),
        iconKey = "language",
        modules = listOf(
            Module(
                id = "mod_net_1",
                courseId = "course_internet",
                title = "मॉड्यूल 1: ब्राउज़र, सर्च और ईमेल शिष्टाचार",
                description = "Google Chrome, URL संरचना, ईमेल (To, Cc, Bcc, Subject) और अटैचमेंट भेजना।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_net_email_basics",
                        courseId = "course_internet",
                        moduleId = "mod_net_1",
                        title = "1.1 वेब ब्राउजिंग और प्रोफेशनल ईमेल भेजना",
                        englishTitle = "Web Browsing & Professional Emailing",
                        durationMinutes = 25,
                        learningObjectives = listOf(
                            "URL के भागों को पहचानना (https://, डोमेन नाम, .com/.org/.gov.in)",
                            "सुरक्षित वेबसाइट की पहचान (HTTPS और पैडलॉक ताला चिन्ह)",
                            "ईमेल के मुख्य घटक: To, Cc (Carbon Copy), Bcc (Blind Carbon Copy) और Subject",
                            "फाइल अटैच (Attachment) करने की सीमाएं और सावधानियां"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "URL और HTTPS सुरक्षा (Web Security)",
                                content = "इंटरनेट पर किसी भी वेबसाइट का पता URL कहलाता है:",
                                steps = listOf(
                                    "HTTP vs HTTPS: 'S' का अर्थ है 'Secure' (सुरक्षित)। हमेशा ध्यान दें कि जिस वेबसाइट पर आप पासवर्ड या बैंक कार्ड डालते हैं, वहां https:// और हरा/ग्रे ताला चिन्ह (Padlock) अवश्य हो।",
                                    "डोमेन एक्सटेंशन: .gov.in (भारतीय सरकारी संस्थाएं), .edu (शैक्षणिक संस्थान), .org (गैर-सरकारी संगठन), .com (व्यावसायिक)।"
                                )
                            ),
                            LessonSection(
                                heading = "ईमेल लिखना और भेजना (Email Composition)",
                                content = "Gmail या Outlook में नया ईमेल (Compose) करते समय इन बातों का ध्यान रखें:",
                                steps = listOf(
                                    "To (प्रति): मुख्य प्राप्तकर्ता का ईमेल पता जिसे उत्तर देना आवश्यक है।",
                                    "Cc (Carbon Copy): उन लोगों के ईमेल जिन्हें केवल सूचना (FYI) के लिए लूप में रखना है। इनके नाम सबको दिखते हैं।",
                                    "Bcc (Blind Carbon Copy): गुप्त प्रतिलिपि। इसमें जोड़े गए पते अन्य प्राप्तकर्ताओं को दिखाई नहीं देते।",
                                    "Subject (विषय): 4-8 शब्दों में स्पष्ट विषय लिखें (जैसे: 'Leave Application - Rahul Sharma')। कभी भी विषय खाली न छोड़ें।",
                                    "Attachment (पेपरक्लिप आइकन): रिज्यूमे या फोटो जोड़ने के लिए। ध्यान रहे: ईमेल में आम तौर पर अधिकतम 25 MB तक की फाइल भेजी जा सकती है।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "अज्ञात प्रेषक से आए ईमेल में दिए गए लिंक पर कभी क्लिक न करें और न ही .exe या .zip अटैचमेंट डाउनलोड करें।"
                        ),
                        summary = listOf(
                            "To = मुख्य रिसीवर, Cc = जानकारी के लिए, Bcc = गुप्त कॉपी।",
                            "HTTPS सुनिश्चित करता है कि आपका डेटा एन्क्रिप्टेड है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_net_email",
                            lessonId = "les_net_email_basics",
                            title = "इंटरनेट और ईमेल टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_net_1",
                                    question = "ईमेल में Bcc का पूरा नाम क्या है?",
                                    options = listOf("Blind Carbon Copy", "Basic Communication Channel", "Binary Computer Code", "Backup Contact Copy"),
                                    correctAnswerIndex = 0,
                                    explanation = "Bcc = Blind Carbon Copy। इसमें डाले गए ईमेल पते अन्य प्राप्तकर्ताओं से छिपे रहते हैं।"
                                ),
                                Question(
                                    id = "q_net_2",
                                    question = "किसी सुरक्षित वेबसाइट के URL में 'HTTPS' में 'S' का क्या अर्थ है?",
                                    options = listOf("Standard", "Secure", "Server", "Speed"),
                                    correctAnswerIndex = 1,
                                    explanation = "S का अर्थ Secure है, जो दर्शाता है कि संचार एन्क्रिप्टेड है।"
                                )
                            )
                        )
                    )
                )
            ),
            Module(
                id = "mod_net_2",
                courseId = "course_internet",
                title = "मॉड्यूल 2: साइबर सुरक्षा, पासवर्ड और फ्रॉड से बचाव",
                description = "मजबूत पासवर्ड बनाना, 2FA, फिशिंग कॉल्स/मैसेज और पब्लिक वाई-फाई के खतरे।",
                order = 2,
                lessons = listOf(
                    Lesson(
                        id = "les_net_cyber_safety",
                        courseId = "course_internet",
                        moduleId = "mod_net_2",
                        title = "2.1 मजबूत पासवर्ड, 2FA और फिशिंग स्कैम से सुरक्षा",
                        englishTitle = "Strong Passwords, 2FA & Phishing Defense",
                        durationMinutes = 20,
                        learningObjectives = listOf(
                            "मजबूत पासवर्ड (Strong Password) के 4 नियम जानना",
                            "टू-फैक्टर ऑथेंटिकेशन (2FA / OTP) का महत्व समझना",
                            "फिशिंग (Phishing) और फर्जी लॉटरी/बैंक फ्रॉड की पहचान करना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "मजबूत पासवर्ड के 4 सुनहरे नियम",
                                content = "आसान पासवर्ड जैसे '123456' या 'Rahul@123' हैकर्स सेकंडों में क्रैक कर लेते हैं:",
                                steps = listOf(
                                    "1. कम से कम 12 अक्षरों की लंबाई रखें।",
                                    "2. कैपिटल लेटर (A-Z), स्मॉल लेटर (a-z), नंबर (0-9) और स्पेशल सिंबल (@, #, $, %) का मिश्रण बनाएं।",
                                    "3. कभी भी अपना नाम, जन्मतिथि या मोबाइल नंबर पासवर्ड में न रखें।",
                                    "4. 2FA (Two-Factor Authentication) अवश्य चालू रखें जिससे पासवर्ड पता चलने पर भी बिना फोन OTP के कोई लॉगिन न कर सके।"
                                )
                            ),
                            LessonSection(
                                heading = "फिशिंग (Phishing) की पहचान कैसे करें?",
                                content = "धोखेबाज फर्जी ईमेल या मैसेज भेजकर असली जैसी दिखने वाली फर्जी वेबसाइट पर पासवर्ड मांगते हैं:",
                                steps = listOf(
                                    "चेतावनी संकेत: 'आपका बैंक खाता बंद हो जाएगा, तुरंत इस लिंक पर क्लिक करें' जैसे डराने वाले संदेश।",
                                    "स्पेलिंग त्रुटियां: असली sbi.co.in की जगह sbi-update-kyc.xyz जैसा संदिग्ध डोमेन होना।",
                                    "बैंक या सरकारी अधिकारी कभी भी आपसे कॉल या मैसेज पर पासवर्ड, कार्ड पिन या OTP नहीं मांगते।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "साइबर फ्रॉड होने पर तुरंत 1930 राष्ट्रीय साइबर हेल्पलाइन पर कॉल करें या cybercrime.gov.in पर रिपोर्ट दर्ज करें।"
                        ),
                        summary = listOf(
                            "OTP कभी किसी के साथ शेयर न करें।",
                            "मजबूत पासवर्ड + 2FA = आपकी सर्वश्रेष्ठ डिजिटल ढाल।"
                        ),
                        quiz = Quiz(
                            id = "quiz_net_safety",
                            lessonId = "les_net_cyber_safety",
                            title = "डिजिटल सुरक्षा टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_sec_1",
                                    question = "इनमें से कौन सा सबसे मजबूत पासवर्ड का उदाहरण है?",
                                    options = listOf("Password123", "Rahul@1998", "K9#m@L2!vP9q", "12345678"),
                                    correctAnswerIndex = 2,
                                    explanation = "'K9#m@L2!vP9q' में बड़े, छोटे अक्षर, अंक और विशेष चिन्हों का लंबा व यादृच्छिक मिश्रण है जो सबसे सुरक्षित है।"
                                ),
                                Question(
                                    id = "q_sec_2",
                                    question = "धोखे से यूजर का यूजरनेम और पासवर्ड चुराने के लिए बनाई गई फर्जी वेबसाइट की तकनीक क्या कहलाती है?",
                                    options = listOf("फायरवॉल", "फिशिंग (Phishing)", "एन्क्रिप्शन", "सर्फिंग"),
                                    correctAnswerIndex = 1,
                                    explanation = "फिशिंग (Phishing) एक ऐसा हमला है जिसमें असली जैसा दिखने वाला जाल बनाकर व्यक्तिगत क्रेडेंशियल चुराए जाते हैं।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    val courseH = Course(
        id = "course_troubleshooting",
        title = "H. समस्या निवारण और रख-रखाव (Troubleshooting & Maintenance)",
        englishTitle = "Troubleshooting & Maintenance",
        hindiTitle = "समस्या निवारण और रख-रखाव",
        description = "धीमे कंप्यूटर को तेज करना, डिस्क क्लीनअप, स्टार्टअप ऐप्स हटाना, टास्क मैनेजर (Ctrl+Shift+Esc), हैंग होना, वाई-फाई व साउंड समस्या निवारण और सुरक्षित बैकअप।",
        level = CourseLevel.INTERMEDIATE,
        category = "रखरखाव (Maintenance)",
        estimatedHours = "3.5 घंटे",
        prerequisites = listOf("विंडोज की बुनियादी समझ"),
        iconKey = "build",
        modules = listOf(
            Module(
                id = "mod_trouble_1",
                courseId = "course_troubleshooting",
                title = "मॉड्यूल 1: धीमे कंप्यूटर की स्पीड बढ़ाना और टास्क मैनेजर",
                description = "Task Manager, डिस्क क्लीनअप (cleanmgr), टेम्परेरी फाइल्स (%temp%) डिलीट करना।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_trouble_slow_pc",
                        courseId = "course_troubleshooting",
                        moduleId = "mod_trouble_1",
                        title = "1.1 धीमे कंप्यूटर को तेज करना और डिस्क क्लीनअप",
                        englishTitle = "Speed Up Slow PC & Disk Cleanup",
                        durationMinutes = 25,
                        learningObjectives = listOf(
                            "कंप्यूटर धीमा होने के मुख्य कारणों (कम RAM, भरी हुई C: ड्राइव, बैकग्राउंड ऐप्स) को पहचानना",
                            "टास्क मैनेजर (Ctrl + Shift + Esc) से अनचाहे ऐप्स को बंद करना",
                            "टेम्परेरी कैशे फाइल्स (%temp%) को सुरक्षित हटाना",
                            "स्टार्टअप ऐप्स को अक्षम (Disable) करके बूट समय कम करना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "कंप्यूटर धीमा क्यों होता है?",
                                content = "कंप्यूटर के धीमे होने के मुख्य कारण:",
                                steps = listOf(
                                    "1. स्टार्टअप प्रोग्राम: कंप्यूटर चालू होते ही दर्जनों बैकग्राउंड ऐप्स अपने आप खुल जाना।",
                                    "2. टेम्परेरी फाइलों का ढेर: समय के साथ गैर-जरूरी कैशे डेटा जमा हो जाना।",
                                    "3. हार्ड डिस्क का 90% से अधिक भर जाना।",
                                    "4. मैलवेयर या वायरस का बैकग्राउंड में चलना।"
                                )
                            ),
                            LessonSection(
                                heading = "स्पीड बढ़ाने के 3 व्यावहारिक कदम",
                                content = "यह कदम कंप्यूटर को तुरंत राहत देते हैं:",
                                steps = listOf(
                                    "कदम 1: Win + R दबाएं, %temp% टाइप करके Enter दबाएं। खुली हुई सभी फाइलों को Ctrl + A करके Delete कर दें। (जो डिलीट न हों उन्हें Skip कर दें)।",
                                    "कदम 2: Ctrl + Shift + Esc दबाकर Task Manager खोलें। 'Startup Apps' टैब में जाएं और जिन ऐप्स की तुरंत जरूरत नहीं (जैसे Spotify, Skype) उन्हें Disable करें।",
                                    "कदम 3: Disk Cleanup चलाएं: स्टार्ट मेनू में 'Disk Cleanup' सर्च करें और C: ड्राइव चुनकर अनचाही फाइलों को हटाएं।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "टास्क मैनेजर में 'Windows Processes' या 'System' प्रक्रियाओं को कभी End Task न करें; इससे विंडोज क्रैश हो सकती है।"
                        ),
                        summary = listOf(
                            "Ctrl + Shift + Esc सीधे टास्क मैनेजर खोलता है।",
                            "%temp% फाइलों को हटाने से व्यक्तिगत फाइलों पर कोई असर नहीं पड़ता, केवल कैशे खाली होता है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_trouble_speed",
                            lessonId = "les_trouble_slow_pc",
                            title = "पीसी स्पीड ट्रिक्स टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_tr_1",
                                    question = "टास्क मैनेजर (Task Manager) को सीधे खोलने का सबसे तेज़ शॉर्टकट क्या है?",
                                    options = listOf("Ctrl + Shift + Esc", "Ctrl + Alt + Delete", "Win + T", "Alt + F4"),
                                    correctAnswerIndex = 0,
                                    explanation = "Ctrl + Shift + Esc बिना किसी अतिरिक्त स्क्रीन के सीधे टास्क मैनेजर खोलता है।"
                                ),
                                Question(
                                    id = "q_tr_2",
                                    question = "विंडोज में अस्थायी (Temporary) फाइलों का फोल्डर खोलने के लिए रन बॉक्स में क्या लिखते हैं?",
                                    options = listOf("%temp%", "temp.exe", "/delete_cache", "clean_pc"),
                                    correctAnswerIndex = 0,
                                    explanation = "Run डायलॉग (Win+R) में %temp% टाइप करने से करंट यूजर का टेम्परेरी फोल्डर खुलता है।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )
}
