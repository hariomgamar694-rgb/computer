package com.example.data.content

import com.example.data.model.ShortcutItem
import com.example.data.model.TroubleshootingGuide

object ShortcutsAndTroubleshootingData {

    val allShortcuts = listOf(
        // General Windows
        ShortcutItem("sc_copy", "Ctrl + C", "कॉपी करें (Copy)", "Copy selected item/text", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_cut", "Ctrl + X", "कट करें (Cut)", "Cut selected item/text", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_paste", "Ctrl + V", "पेस्ट करें (Paste)", "Paste copied/cut item", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_undo", "Ctrl + Z", "पूर्ववत करें (Undo)", "Undo previous action", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_redo", "Ctrl + Y", "पुनः करें (Redo)", "Redo undone action", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_save", "Ctrl + S", "सुरक्षित सहेजें (Save)", "Save current file", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_select_all", "Ctrl + A", "सब चुनें (Select All)", "Select all content", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_find", "Ctrl + F", "खोजें (Find)", "Find text/content", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_print", "Ctrl + P", "प्रिंट करें (Print)", "Open print dialog", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_close_window", "Alt + F4", "विंडो बंद करें / शटडाउन", "Close window or shutdown", "सामान्य (General)", "विंडोज"),
        ShortcutItem("sc_switch_apps", "Alt + Tab", "खुले ऐप्स के बीच स्विच करें", "Switch between open apps", "विंडोज (Windows)", "विंडोज"),
        ShortcutItem("sc_desktop", "Win + D", "डेस्कटॉप दिखाएं/छिपाएं", "Show/Hide Desktop", "विंडोज (Windows)", "विंडोज"),
        ShortcutItem("sc_file_explorer", "Win + E", "फाइल एक्सप्लोरर खोलें", "Open File Explorer", "विंडोज (Windows)", "विंडोज"),
        ShortcutItem("sc_run", "Win + R", "रन बॉक्स खोलें (Run Dialog)", "Open Run dialog", "विंडोज (Windows)", "विंडोज"),
        ShortcutItem("sc_task_mgr", "Ctrl + Shift + Esc", "टास्क मैनेजर सीधे खोलें", "Directly open Task Manager", "विंडोज (Windows)", "विंडोज"),
        ShortcutItem("sc_lock_pc", "Win + L", "कंप्यूटर को लॉक करें", "Lock your computer", "विंडोज (Windows)", "विंडोज"),
        ShortcutItem("sc_new_folder", "Ctrl + Shift + N", "नया फोल्डर बनाएं", "Create new folder", "विंडोज (Windows)", "विंडोज"),
        ShortcutItem("sc_rename", "F2", "नाम बदलें (Rename)", "Rename selected file/folder", "विंडोज (Windows)", "विंडोज"),
        ShortcutItem("sc_refresh", "F5", "रिफ्रेश करें (Refresh)", "Refresh current page/folder", "विंडोज (Windows)", "विंडोज"),

        // Word Specific
        ShortcutItem("sc_bold", "Ctrl + B", "बोल्ड (गहरा मोटा टेक्स्ट)", "Make text bold", "माइक्रोसॉफ्ट वर्ड", "वर्ड"),
        ShortcutItem("sc_italic", "Ctrl + I", "इटैलिक (तिरछा टेक्स्ट)", "Make text italic", "माइक्रोसॉफ्ट वर्ड", "वर्ड"),
        ShortcutItem("sc_underline", "Ctrl + U", "अंडरलाइन (नीचे रेखा)", "Underline text", "माइक्रोसॉफ्ट वर्ड", "वर्ड"),
        ShortcutItem("sc_align_center", "Ctrl + E", "सेंटर अलाइनमेंट", "Center align text", "माइक्रोसॉफ्ट वर्ड", "वर्ड"),
        ShortcutItem("sc_align_justify", "Ctrl + J", "जस्टिफाई अलाइनमेंट", "Justify align text", "माइक्रोसॉफ्ट वर्ड", "वर्ड"),
        ShortcutItem("sc_align_left", "Ctrl + L", "लेफ्ट अलाइनमेंट", "Left align text", "माइक्रोसॉफ्ट वर्ड", "वर्ड"),
        ShortcutItem("sc_align_right", "Ctrl + R", "राइट अलाइनमेंट", "Right align text", "माइक्रोसॉफ्ट वर्ड", "वर्ड"),

        // Excel Specific
        ShortcutItem("sc_auto_sum", "Alt + =", "ऑटो सम फॉर्मूला जोड़ें", "Insert AutoSum formula", "माइक्रोसॉफ्ट एक्सेल", "एक्सेल"),
        ShortcutItem("sc_edit_cell", "F2", "सेल एडिट मोड में जाएं", "Edit current cell", "माइक्रोसॉफ्ट एक्सेल", "एक्सेल"),
        ShortcutItem("sc_new_sheet", "Shift + F11", "नई वर्कशीट जोड़ें", "Insert new worksheet", "माइक्रोसॉफ्ट एक्सेल", "एक्सेल"),

        // PowerPoint Specific
        ShortcutItem("sc_new_slide", "Ctrl + M", "नई स्लाइड जोड़ें", "Insert new slide", "पावरपॉइंट", "पावरपॉइंट"),
        ShortcutItem("sc_slide_show", "F5", "शुरू से स्लाइड शो चलाएं", "Start slide show from beginning", "पावरपॉइंट", "पावरपॉइंट"),
        ShortcutItem("sc_slide_show_current", "Shift + F5", "वर्तमान स्लाइड से शो चलाएं", "Start show from current slide", "पावरपॉइंट", "पावरपॉइंट")
    )

    val allTroubleshootingGuides = listOf(
        TroubleshootingGuide(
            id = "tb_slow_computer",
            symptomHindi = "कंप्यूटर बहुत धीमा चल रहा है या बार-बार हैंग हो रहा है",
            symptomEnglish = "Computer is running very slow or freezing frequently",
            category = "परफॉर्मेंस (Performance)",
            possibleCauses = listOf(
                "कंप्यूटर में कई भारी प्रोग्राम एक साथ बैकग्राउंड में खुले हैं",
                "C: ड्राइव की स्टोरेज 95% से अधिक भर चुकी है",
                "स्टार्टअप ऐप्स की संख्या बहुत अधिक है",
                "टेम्परेरी कैशे फाइल्स बहुत अधिक जमा हो गई हैं"
            ),
            stepByStepSolution = listOf(
                "1. Ctrl + Shift + Esc दबाकर Task Manager खोलें और जो ऐप अधिक CPU/RAM ले रहा है उसे 'End Task' करें।",
                "2. Win + R दबाकर %temp% लिखें, Enter करें और सभी फाइलों को Ctrl+A करके डिलीट करें।",
                "3. Start मेनू में 'Disk Cleanup' चलाकर गैर-जरूरी सिस्टम फाइल्स साफ करें।",
                "4. Task Manager के 'Startup' टैब में जाकर अनचाहे ऐप्स को Disable करें।",
                "5. कंप्यूटर को रीस्टार्ट (Restart) करें।"
            ),
            safetyWarning = "सावधानी: टास्क मैनेजर में केवल उन्हीं ऐप्स को बंद करें जिन्हें आप पहचानते हैं। सिस्टम या विंडोज प्रोसेस बंद न करें।"
        ),
        TroubleshootingGuide(
            id = "tb_no_internet",
            symptomHindi = "इंटरनेट या वाई-फाई कनेक्ट नहीं हो रहा है ('No Internet')",
            symptomEnglish = "Wi-Fi is connected but showing 'No Internet Access'",
            category = "नेटवर्क (Network)",
            possibleCauses = listOf(
                "वाई-फाई राउटर का इंटरनेट लिंक डाउन है",
                "कंप्यूटर का IP एड्रेस कॉन्फ्लिक्ट हो गया है",
                "कंप्यूटर में Airplane Mode गलती से ऑन हो गया है"
            ),
            stepByStepSolution = listOf(
                "1. टास्कबार में देखें कि कहीं 'Airplane Mode' चालू तो नहीं है; यदि है तो उसे ऑफ करें।",
                "2. अपने वाई-फाई राउटर का पावर स्विच 30 सेकंड के लिए बंद करें और फिर चालू करें।",
                "3. कंप्यूटर में वाई-फाई को बंद करके 10 सेकंड बाद पुनः चालू करें।",
                "4. Win + R दबाएं, cmd टाइप करें और 'ping 8.8.8.8' लिखकर Enter दबाएं। यदि 'Reply' आता है तो DNS की समस्या हो सकती है।"
            ),
            safetyWarning = "सावधानी: राउटर के पीछे दिए गए छोटे 'Reset' बटन को कभी न दबाएं, अन्यथा उसकी पूरी कॉन्फ़िगरेशन मिट जाएगी।"
        ),
        TroubleshootingGuide(
            id = "tb_no_sound",
            symptomHindi = "कंप्यूटर से कोई आवाज नहीं आ रही है (No Audio/Sound)",
            symptomEnglish = "No audio output from speakers or headphones",
            category = "हार्डवेयर/ऑडियो (Hardware)",
            possibleCauses = listOf(
                "सिस्टम वॉल्यूम म्यूट (Mute) है",
                "गलत ऑडियो आउटपुट डिवाइस (जैसे मॉनिटर स्पीकर) चुना हुआ है",
                "ऑडियो जैक ढीला लगा है"
            ),
            stepByStepSolution = listOf(
                "1. टास्कबार में नीचे दाईं ओर स्पीकर आइकन पर क्लिक करें और देखें कि वह Mute तो नहीं है। वॉल्यूम को 70% पर ले जाएं।",
                "2. स्पीकर आइकन के बगल वाले तीर (>) पर क्लिक करके सही आउटपुट डिवाइस (Headphones या Realtek Speakers) चुनें।",
                "3. यदि बाहरी स्पीकर हैं तो उनकी 3.5mm ऑडियो पिन को हरे (Green) रंग वाले ऑडियो पोर्ट में ठीक से दबाकर लगाएं।",
                "4. डिवाइस मैनेजर (devmgmt.msc) में जाकर 'Sound, video and game controllers' के ड्राइवर अपडेट करें।"
            ),
            safetyWarning = "नोट: कभी भी किसी मेटल पिन से कंप्यूटर के ऑडियो जैक में खुरचें नहीं।"
        ),
        TroubleshootingGuide(
            id = "tb_printer_not_printing",
            symptomHindi = "प्रिंटर चालू है लेकिन प्रिंट नहीं निकाल रहा है",
            symptomEnglish = "Printer is ON but not printing documents",
            category = "प्रिंटिंग (Printing)",
            possibleCauses = listOf(
                "प्रिंटर की USB केबल ढीली है या वाई-फाई कनेक्शन टूट गया है",
                "प्रिंटर में कागज (Paper Jam) फंस गया है या पेपर ट्रे खाली है",
                "प्रिंट क्यू (Print Queue) में कोई खराब डॉक्यूमेंट अटका हुआ है"
            ),
            stepByStepSolution = listOf(
                "1. प्रिंटर की स्क्रीन पर देखें कि कोई लाल बत्ती (Red Light) या Paper Jam की एरर तो नहीं है।",
                "2. प्रिंटर का पावर बटन दबाकर उसे बंद करें, 10 सेकंड रुकें और दोबारा चालू करें।",
                "3. विंडोज सेटिंग्स में जाएं -> Bluetooth & devices -> Printers & Scanners -> अपने प्रिंटर पर क्लिक करें।",
                "4. 'Open print queue' पर क्लिक करें। अटके हुए डॉक्यूमेंट पर राइट क्लिक करके 'Cancel' करें।",
                "5. टेस्ट पेज (Print test page) प्रिंट करके देखें।"
            ),
            safetyWarning = "सावधानी: फंसे हुए कागज को दोनों हाथों से धीरे-धीरे खींचें ताकि अंदर कोई पुर्जा या रोलर न टूटे।"
        )
    )
}
