package com.example.data.content

import com.example.data.model.*

object CourseCatalogE_F {

    val courseE = Course(
        id = "course_excel",
        title = "E. माइक्रोसॉफ्ट एक्सेल (Microsoft Excel)",
        englishTitle = "Microsoft Excel",
        hindiTitle = "माइक्रोसॉफ्ट एक्सेल",
        description = "वर्कबुक, शीट्स, सेल एड्रेस, फॉर्मूले (SUM, AVERAGE, IF, MIN, MAX, COUNT, COUNTIF, SUMIF), सॉर्टिंग, फिल्टरिंग, डेटा एंट्री और मार्कशीट निर्माण।",
        level = CourseLevel.INTERMEDIATE,
        category = "ऑफिस टूल्स (Office Tools)",
        estimatedHours = "6 घंटे",
        prerequisites = listOf("मूलभूत गणितीय गणनाओं और कीबोर्ड की समझ"),
        iconKey = "table_chart",
        modules = listOf(
            Module(
                id = "mod_excel_1",
                courseId = "course_excel",
                title = "मॉड्यूल 1: एक्सेल की मूल बातें और सेल संरचना",
                description = "वर्कबुक, वर्कशीट, रो, कॉलम, सेल एड्रेस और डेटा एंट्री।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_excel_grid",
                        courseId = "course_excel",
                        moduleId = "mod_excel_1",
                        title = "1.1 वर्कबुक, वर्कशीट और सेल एड्रेस (Cell Address)",
                        englishTitle = "Workbook, Worksheet & Cell Addresses",
                        durationMinutes = 20,
                        learningObjectives = listOf(
                            "Workbook (फाइल) और Worksheet (शीट) का फर्क समझना",
                            "Row (संख्या 1, 2, 3...) और Column (अक्षर A, B, C...) को पहचानना",
                            "Cell Address (जैसे A1, B5, C10) को पढ़ना और नेम बॉक्स में देखना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "एक्सेल ग्रिड संरचना (The Excel Grid)",
                                content = "एक्सेल एक स्प्रेडशीट प्रोग्राम है जो संख्याओं और तालिकाओं के साथ काम करने के लिए बना है:",
                                steps = listOf(
                                    "Row (पंक्ति): क्षैतिज (Horizontal) रेखाएं, जिन्हें 1, 2, 3... संख्याओं से दर्शाया जाता है।",
                                    "Column (स्तंभ): लंबवत (Vertical) रेखाएं, जिन्हें A, B, C... अक्षरों से दर्शाया जाता है।",
                                    "Cell (सेल): जहां एक Row और एक Column आपस में कटते हैं, उस आयताकार डिब्बे को सेल कहते हैं।",
                                    "Cell Address (सेल का पता): कॉलम के अक्षर और रो की संख्या को मिलाकर बनता है। जैसे Column 'B' और Row '4' के मिलन स्थल को 'B4' कहेंगे।"
                                )
                            )
                        ),
                        summary = listOf(
                            "एक्सेल में प्रत्येक सेल का एक विशिष्ट नाम (Cell Reference) होता है।",
                            "एक्सेल में कोई भी सूत्र (Formula) हमेशा बराबर के निशान (=) से शुरू होता है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_excel_grid",
                            lessonId = "les_excel_grid",
                            title = "एक्सेल ग्रिड टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_ex_1",
                                    question = "कॉलम C और रो 7 के मिलन वाले सेल का पता क्या होगा?",
                                    options = listOf("7C", "C7", "C:7", "Cell 7"),
                                    correctAnswerIndex = 1,
                                    explanation = "सेल एड्रेस में पहले कॉलम का अक्षर आता है और फिर रो की संख्या, इसलिए 'C7' सही है।"
                                ),
                                Question(
                                    id = "q_ex_2",
                                    question = "एक्सेल में कोई भी फॉर्मूला शुरू करने से पहले कौन सा चिन्ह लगाना अनिवार्य है?",
                                    options = listOf("+", "=", "@", "#"),
                                    correctAnswerIndex = 1,
                                    explanation = "एक्सेल में सभी फॉर्मूले बराबर के निशान (=) से शुरू होते हैं।"
                                )
                            )
                        )
                    )
                )
            ),
            Module(
                id = "mod_excel_2",
                courseId = "course_excel",
                title = "मॉड्यूल 2: आवश्यक फॉर्मूले (SUM, AVERAGE, MIN, MAX, IF)",
                description = "जोड़, औसत, न्यूनतम, अधिकतम और शर्त आधारित गणनाएं।",
                order = 2,
                lessons = listOf(
                    Lesson(
                        id = "les_excel_formulas",
                        courseId = "course_excel",
                        moduleId = "mod_excel_2",
                        title = "2.1 फॉर्मूले: SUM, AVERAGE, MIN, MAX और IF",
                        englishTitle = "Essential Formulas & Conditional Logic",
                        durationMinutes = 35,
                        learningObjectives = listOf(
                            "योग निकालने के लिए =SUM(A1:A5) का सही उपयोग",
                            "औसत निकालने के लिए =AVERAGE(B1:B10) का उपयोग",
                            "अधिकतम व न्यूनतम मान के लिए =MAX() और =MIN()",
                            "शर्त आधारित परिणाम के लिए =IF(C2>=33, \"Pass\", \"Fail\")"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "प्रमुख गणितीय फॉर्मूले (Core Math Formulas)",
                                content = "एक्सेल में फॉर्मूला दर्ज करने के वास्तविक उदाहरण:",
                                steps = listOf(
                                    "1. SUM (जोड़ना): =SUM(A1:A5) -> यह सेल A1 से लेकर A5 तक की सभी संख्याओं को जोड़ देगा। शॉर्टकट: Alt + =",
                                    "2. AVERAGE (औसत): =AVERAGE(B1:B5) -> कुल योग को संख्याओं की गिनती से भाग देकर औसत निकालता है।",
                                    "3. MIN (सबसे छोटी संख्या): =MIN(C1:C10) -> दी गई रेंज में सबसे छोटा मान खोजता है।",
                                    "4. MAX (सबसे बड़ी संख्या): =MAX(C1:C10) -> दी गई रेंज में सबसे बड़ा मान खोजता है।"
                                )
                            ),
                            LessonSection(
                                heading = "तार्किक सूत्र (Logical IF Formula)",
                                content = "यदि आप किसी शर्त (Condition) के आधार पर अलग-अलग परिणाम दिखाना चाहते हैं:",
                                steps = listOf(
                                    "सिंटेक्स: =IF(शर्त, यदि_शर्त_सही_हो, यदि_शर्त_गलत_हो)",
                                    "मार्कशीट उदाहरण: =IF(E2>=33, \"PASS\", \"FAIL\") -> यदि सेल E2 का अंक 33 या अधिक है तो 'PASS' दिखेगा, अन्यथा 'FAIL' दिखेगा।"
                                )
                            )
                        ),
                        safetyNotes = listOf(
                            "यदि सेल में '##### ' दिखाई दे, तो घबराएं नहीं! इसका अर्थ है कि संख्या लंबी है और कॉलम की चौड़ाई कम है। कॉलम के शीर्ष पर डबल क्लिक करके चौड़ाई बढ़ाएं।"
                        ),
                        summary = listOf(
                            "SUM से जोड़ते हैं, AVERAGE से औसत निकालते हैं।",
                            "IF फंक्शन निर्णय लेने और ऑटोमैटिक रिजल्ट तैयार करने के लिए सबसे शक्तिशाली टूल है।"
                        ),
                        practicalTasks = listOf(
                            PracticalTask(
                                id = "task_excel_formula_lab",
                                title = "प्रैक्टिकल लैब में एक्सेल फॉर्मूला चलाएं",
                                instructions = listOf(
                                    "ऐप के 'प्रैक्टिकल लैब' में जाएं और =SUM(10, 20, 30) की गणना का लाइव परीक्षण करें।"
                                ),
                                isTechnicallyVerifiable = true,
                                verificationType = VerificationType.INTERACTIVE_SIMULATOR
                            )
                        ),
                        quiz = Quiz(
                            id = "quiz_excel_formulas",
                            lessonId = "les_excel_formulas",
                            title = "एक्सेल फॉर्मूला टेस्ट",
                            questions = listOf(
                                Question(
                                    id = "q_ex_f1",
                                    question = "सेल B1 से B10 तक की सभी संख्याओं को जोड़ने का सही फॉर्मूला कौन सा है?",
                                    options = listOf("=ADD(B1:B10)", "=SUM(B1:B10)", "=TOTAL(B1:B10)", "=PLUS(B1-B10)"),
                                    correctAnswerIndex = 1,
                                    explanation = "=SUM(B1:B10) एक्सेल का मानक जोड़ फॉर्मूला है।"
                                ),
                                Question(
                                    id = "q_ex_f2",
                                    question = "=IF(A1>50, \"High\", \"Low\") में यदि A1 का मान 60 है, तो परिणाम क्या होगा?",
                                    options = listOf("Low", "High", "Error", "50"),
                                    correctAnswerIndex = 1,
                                    explanation = "चूंकि 60 > 50 शर्त सच (True) है, इसलिए परिणाम 'High' होगा।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    val courseF = Course(
        id = "course_powerpoint",
        title = "F. माइक्रोसॉफ्ट पावरपॉइंट (Microsoft PowerPoint)",
        englishTitle = "Microsoft PowerPoint",
        hindiTitle = "माइक्रोसॉफ्ट पावरपॉइंट",
        description = "प्रभावशाली प्रेजेंटेशन, स्लाइड लेआउट, थीम्स, शेप्स, स्मार्टआर्ट, ट्रांजिशन्स, एनिमेशन और फुल-स्क्रीन स्लाइड शो (F5)।",
        level = CourseLevel.INTERMEDIATE,
        category = "ऑफिस टूल्स (Office Tools)",
        estimatedHours = "3.5 घंटे",
        prerequisites = listOf("वर्ड और टेक्स्ट फॉर्मेटिंग का सामान्य ज्ञान"),
        iconKey = "slideshow",
        modules = listOf(
            Module(
                id = "mod_ppt_1",
                courseId = "course_powerpoint",
                title = "मॉड्यूल 1: स्लाइड निर्माण और प्रेजेंटेशन डिजाइन",
                description = "न्यू स्लाइड, थीम चुनना, बुलेट प्वाइंट्स, ट्रांजिशन और स्लाइड शो।",
                order = 1,
                lessons = listOf(
                    Lesson(
                        id = "les_ppt_presentation",
                        courseId = "course_powerpoint",
                        moduleId = "mod_ppt_1",
                        title = "1.1 स्लाइड निर्माण, ट्रांजिशन और स्लाइड शो (F5)",
                        englishTitle = "Slide Creation, Transitions & Slide Show",
                        durationMinutes = 20,
                        learningObjectives = listOf(
                            "PowerPoint में New Slide (Ctrl + M) जोड़ना",
                            "स्लाइड लेआउट (Title, Title & Content, Blank) चुनना",
                            "स्लाइड्स के बीच Transitions (Fade, Push, Wipe) लगाना",
                            "F5 दबाकर शुरू से फुल स्क्रीन स्लाइड शो चलाना"
                        ),
                        sections = listOf(
                            LessonSection(
                                heading = "पावरपॉइंट प्रेजेंटेशन के बुनियादी नियम",
                                content = "एक अच्छी प्रेजेंटेशन दर्शकों का ध्यान आकर्षित करती है:",
                                steps = listOf(
                                    "1. New Slide (Ctrl + M): नई स्लाइड जोड़ने का शॉर्टकट है (ध्यान रहे: Ctrl + N से नई फाइल खुलती है, जबकि Ctrl + M से नई स्लाइड जुड़ती है)।",
                                    "2. 6x6 का नियम: प्रत्येक स्लाइड पर 6 से अधिक बुलेट प्वाइंट न रखें और प्रत्येक लाइन में 6 से अधिक शब्द न रखें ताकि पढ़ने में आसानी हो।",
                                    "3. Transitions: एक स्लाइड से दूसरी स्लाइड पर जाते समय का दृश्य प्रभाव।",
                                    "4. Animations: स्लाइड के अंदर टेक्स्ट या फोटो पर गति (Motion) का प्रभाव।"
                                )
                            ),
                            LessonSection(
                                heading = "स्लाइड शो शॉर्टकट्स (Slide Show Shortcuts)",
                                content = "प्रेजेंटेशन पेश करते समय काम आने वाले शॉर्टकट:",
                                steps = listOf(
                                    "F5: प्रेजेंटेशन को पहली स्लाइड (Start) से फुल स्क्रीन पर चलाना।",
                                    "Shift + F5: वर्तमान स्लाइड (Current Slide) से शो शुरू करना।",
                                    "Esc (Escape): स्लाइड शो बंद करके वापस एडिट मोड में आना।",
                                    "B की दबाना: शो के दौरान स्क्रीन को अस्थायी रूप से ब्लैक (Black) करना।"
                                )
                            )
                        ),
                        proTips = listOf(
                            "बहुत अधिक भड़कीले एनिमेशन न लगाएं; कॉर्पोरेट प्रेजेंटेशन में सिंपल Fade या Wipe ट्रांजिशन सबसे सम्मानित माने जाते हैं।"
                        ),
                        summary = listOf(
                            "पावरपॉइंट विचारों को दृश्यों (Visuals) में बदलने का बेहतरीन माध्यम है।",
                            "F5 से पहली स्लाइड से और Shift+F5 से वर्तमान स्लाइड से शो चलता है।"
                        ),
                        quiz = Quiz(
                            id = "quiz_ppt_show",
                            lessonId = "les_ppt_presentation",
                            title = "पावरपॉइंट क्विज",
                            questions = listOf(
                                Question(
                                    id = "q_ppt_1",
                                    question = "प्रेजेंटेशन में नई स्लाइड (New Slide) जोड़ने का शॉर्टकट क्या है?",
                                    options = listOf("Ctrl + N", "Ctrl + M", "Alt + S", "Ctrl + Shift + S"),
                                    correctAnswerIndex = 1,
                                    explanation = "Ctrl + M नई स्लाइड जोड़ने का शॉर्टकट है।"
                                ),
                                Question(
                                    id = "q_ppt_2",
                                    question = "शुरुआत से फुल-स्क्रीन स्लाइड शो चलाने के लिए कौन सी फंक्शन की दबाते हैं?",
                                    options = listOf("F1", "F2", "F5", "F11"),
                                    correctAnswerIndex = 2,
                                    explanation = "F5 दबाने से स्लाइड शो पहली स्लाइड से शुरू हो जाता है।"
                                )
                            )
                        )
                    )
                )
            )
        )
    )
}
