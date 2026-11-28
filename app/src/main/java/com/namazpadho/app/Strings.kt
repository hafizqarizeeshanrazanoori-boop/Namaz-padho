package com.namazpadho.app

object L {
    val languages = listOf("English", "हिन्दी", "اردو", "العربية")
    val rtl = setOf(2, 3)

    private val data: Map<String, List<String>> = mapOf(
        "assalamu" to listOf("Assalamu Alaikum", "अस्सलामु अलैकुम", "السلام علیکم", "السلام عليكم"),
        "first_name" to listOf("First Name", "पहला नाम", "پہلا نام", "الاسم الأول"),
        "last_name" to listOf("Last Name", "उपनाम", "خاندانی نام", "اسم العائلة"),
        "language" to listOf("Language", "भाषा", "زبان", "اللغة"),
        "today" to listOf("Today", "आज", "آج", "اليوم"),
        "namaz_timetable" to listOf("Namaz Timetable", "नमाज़ टाइम टेबल", "نماز ٹائم ٹیبل", "جدول الصلاة"),
        "name" to listOf("Name", "नाम", "نام", "الاسم"),
        "start" to listOf("Start", "शुरू", "شروع", "البداية"),
        "end" to listOf("End", "ख़त्म", "ختم", "النهاية"),
        "fajr" to listOf("Fajr", "फ़ज्र", "فجر", "الفجر"),
        "zohr" to listOf("Zohr", "ज़ुहर", "ظہر", "الظهر"),
        "asr" to listOf("Asr", "अस्र", "عصر", "العصر"),
        "maghrib" to listOf("Maghrib", "मग़रिब", "مغرب", "المغرب"),
        "isha" to listOf("Isha", "इशा", "عشاء", "العشاء"),
        "home" to listOf("Home", "होम", "ہوم", "الرئيسية"),
        "calling_alarm" to listOf("Calling Alarm", "कॉलिंग अलार्म", "کالنگ الارم", "منبه المكالمة"),
        "omr_calendar" to listOf("OMR Calendar", "OMR कैलेंडर", "OMR کیلنڈر", "تقويم OMR"),
        "islamic_calc" to listOf("Islamic Calculation", "इस्लामी हिसाब", "اسلامی حساب", "الحسابات الإسلامية"),
        "nearby_masjid" to listOf("Nearby Masjid", "पास की मस्जिद", "قریبی مسجد", "المساجد القريبة"),
        "my_calling_alarm" to listOf("My Calling Alarm", "मेरा कॉलिंग अलार्म", "میرا کالنگ الارم", "منبه مكالماتي"),
        "coming_soon" to listOf("Coming soon", "जल्द आ रहा है", "جلد آ رہا ہے", "قريباً")
    )

    fun t(key: String, lang: Int): String =
        data[key]?.getOrNull(lang) ?: data[key]?.get(0) ?: key
}
