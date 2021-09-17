package moc.spn.sbil.lellaw.utils

import android.annotation.SuppressLint
import android.util.Log
import moc.spn.sbil.lellaw.utils.ZDateUtils.DateFormat.*
import java.text.SimpleDateFormat
import java.util.*

class ZDateUtils {
    enum class DateFormat {
        AD, BS
    }

    data class DateObject(val year: Int, val month: Int, val date: Int)

    companion object {
        private val bs: Array<Array<Int>> =
                arrayOf(
                        arrayOf(2000, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2001, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2002, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2003, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2004, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2005, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2006, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2007, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2008, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31),
                        arrayOf(2009, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2010, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2011, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2013, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2013, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2014, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2015, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2016, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
                        arrayOf(2017, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2018, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2019, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2020, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2021, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2022, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
                        arrayOf(2023, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2024, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2025, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2026, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2027, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2028, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2029, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2030, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2031, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2032, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2033, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2034, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2035, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31),
                        arrayOf(2036, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2037, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2038, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2039, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
                        arrayOf(2040, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2041, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2042, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2043, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
                        arrayOf(2044, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2045, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2046, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2047, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2048, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2049, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
                        arrayOf(2050, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2051, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2052, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2053, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
                        arrayOf(2054, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2055, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2056, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2057, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2058, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2059, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2060, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2061, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2062, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31),
                        arrayOf(2063, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2064, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2065, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2066, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31),
                        arrayOf(2067, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2068, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2069, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2070, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
                        arrayOf(2071, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2072, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30),
                        arrayOf(2073, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
                        arrayOf(2074, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2075, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2076, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
                        arrayOf(2077, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
                        arrayOf(2078, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2079, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
                        arrayOf(2080, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
                        arrayOf(2081, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30),
                        arrayOf(2082, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30),
                        arrayOf(2083, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30),
                        arrayOf(2084, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30),
                        arrayOf(2085, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30),
                        arrayOf(2086, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30),
                        arrayOf(2087, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30),
                        arrayOf(2088, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30),
                        arrayOf(2089, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30),
                        arrayOf(2090, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30)
                )

        fun validateYear(year: Int, format: DateFormat):String? {

            return if (format == AD) {
                when (year) {
                    in 1943..2033 -> {
                        null
                    }
                    else -> "Year should be between 1943 - 2033"
                }
            }else{
                when (year) {
                    in 2000..2090 -> {
                        null
                    }
                    else -> "Year should be between 2000..2090"
                }

            }
        }
        fun getMaxDaysIn(month: Int, year: Int, format: DateFormat = AD): Int {
            return when (format) {
                AD -> {
                    val c = Calendar.getInstance()
                    c.set(Calendar.YEAR, year)
                    c.set(Calendar.MONTH, month - 1)
                    c.getActualMaximum(Calendar.DATE)
                }
                BS -> {
                    for (b in bs)
                        if (b[0] == year)
                            return b[month]
                    return 0
                }
            }
        }

        fun isLeapYear(year: Int): Boolean =
                if (year % 100 == 0) {
                    year % 400 == 0
                } else {
                    year % 4 == 0
                }

        fun isEngDateInRange(yy: Int, mm: Int, dd: Int): Boolean =
                !(yy < 1944 || yy > 2033) && !(mm < 1 || mm > 12) && !(dd < 1 || dd > 31)

        fun engToNep(yy: Int, mm: Int, dd: Int): DateObject? {

//        if (!isEngDateInRange(yy:yy, mm:mm, dd:dd)) {
//            return;
//        }
            try {

                val month = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
                val lmonth = arrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

                val def_eyy = 1944
                val def_nyy = 2000
                val def_nmm = 9
                val def_ndd = 17 - 1        //spear head nepali date...
                var total_eDays = 0
                var total_nDays = 0
                var a = 0
                var day = 7 - 1        //all the initializations...
                var m = 0
                var y = 0
                var numDay = 0

                for (k in 0 until (yy - def_eyy))
                    if (isLeapYear(def_eyy + k))
                        for (l in 0 until 12)
                            total_eDays += lmonth[l]
                    else
                        for (l in 0 until 12)
                            total_eDays += month[l]

                for (i in 0 until (mm - 1))
                    if (isLeapYear(yy))
                        total_eDays += lmonth[i]
                    else
                        total_eDays += month[i]

                total_eDays += dd

                total_nDays = def_ndd
                m = def_nmm
                y = def_nyy
                var j = def_nmm
                var i = 0
                //count nepali date from array
                while (total_eDays != 0) {
                    val bsData = bs[i]

                    a = bsData[j]
                    total_nDays += 1
                    day += 1
                    if (total_nDays > a) {
                        m += 1
                        total_nDays = 1
                        j += 1
                    }

                    if (day > 7) day = 1

                    if (m > 12) {
                        y += 1
                        m = 1
                    }

                    if (j > 12) {
                        j = 1
                        i += 1
                    }
                    total_eDays -= 1
                }

                numDay = day;
                val convertedYear = y;
                val convertedMonth = m;
                val convertedDate = total_nDays;
                val convertedDay = getDayOfWeek(day);
                val convertedNepMonth = getNepaliMonth(m);
                val convertedNubDay = day
                return DateObject(
                        convertedYear,
                        convertedMonth,
                        convertedDate
                )
            }catch (e:Exception){

            }

            return null;
        }

        fun convert(year: Int,month: Int,day: Int,format: DateFormat = DateFormat.AD): DateObject? {
            return if (format == DateFormat.AD){ nepToEng(year,month,day) } else{ engToNep(year,month,day)}
        }

        fun nepToEng(yy: Int, mm: Int, dd: Int) : DateObject? {
            try {
                val def_eyy = 1943;
                val def_emm = 4;
                val def_edd = 14 - 1;        // init english date.
                val def_nyy = 2000;
                val def_nmm = 1;
                val def_ndd = 1;        // equivalent nepali date.
                var total_eDays = 0;
                var total_nDays = 0;
                var a = 0;
                var day = 4 - 1;        // initializations...
                var m = 0;
                var y = 0;
                var i = 0;
                var k = 0;
                var numDay = 0;

                val month = arrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
                val lmonth = arrayOf(0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)


                // count total days in-terms of year
                for (i in 0 until (yy - def_nyy)) {
                    for (j in 1..12) {
                        val bsData = bs[k]
                        total_nDays += bsData[j]
                    }
                    k += 1
                }

                // count total days in-terms of month
                for (j in 1 until mm) {
                    val bsData = bs[k]
                    total_nDays += bsData[j];
                }

                // count total days in-terms of dat
                total_nDays += dd;

                //calculation of equivalent english date...
                total_eDays = def_edd;
                m = def_emm;
                y = def_eyy;
                while (total_nDays != 0) {
                    a = if (isLeapYear(y)) {
                        lmonth[m];
                    } else {
                        month[m];
                    }
                    total_eDays += 1;
                    day += 1;
                    if (total_eDays > a) {
                        m += 1;
                        total_eDays = 1;
                        if (m > 12) {
                            y += 1;
                            m = 1;
                        }
                    }
                    if (day > 7) {
                        day = 1;
                    }
                    total_nDays -= 1;
                }
                numDay = day;

                val convertedYear = y;
                val convertedMonth = m;
                val convertedDate = total_eDays;

//            convertedDay = getDayOfWeek(day: day);
//            convertedNepMonth = getEnglishMonth(month: m);
//            convertedNubDay = day;

                return DateObject(
                        year = convertedYear,
                        month = convertedMonth,
                        date = convertedDate
                )
            }catch (e:Exception){

            }
            return null
        }

        fun getDayOfWeek(day: Int): String {

            var dayOfWeek = "";
            when (day) {
                1-> {
                    dayOfWeek = "Sunday";
                }
                2 -> {
                    dayOfWeek = "Monday";
                }
                3 -> {
                    dayOfWeek = "Tuesday";
                }
                4 -> {
                    dayOfWeek = "Wednesday";
                }
                5 -> {
                    dayOfWeek = "Thursday";
                }
                6 -> {
                    dayOfWeek = "Friday";
                }
                7 -> {
                    dayOfWeek = "Saturday";
                }
            }
            return dayOfWeek;
        }

        fun getDaysCount(year: Int, month: Int): Int {
            if (year < 2000 || year > 2090 || month < 1 || month > 12) {
                Log.w("Date conversion", "Invalid data supplied $year $month")
                return 32
            }

            return bs[year-2000][month]
        }

        @SuppressLint("SimpleDateFormat")
        fun getTodayDate(format: DateFormat): DateObject? {
            val cal = SimpleDateFormat("yyyy-MM-dd")
            val dates = cal.format(Date()).split("-")
            return if (format == BS) {
                engToNep(dates[0].toInt(), dates[1].toInt(), dates[2].toInt())
            } else {
                DateObject(dates[0].toInt(), dates[1].toInt(), dates[2].toInt())
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun getOffsetDate(offsetCount: Int): DateObject {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_YEAR, offsetCount)
            val tomorrow = cal.time

            val df = SimpleDateFormat("yyyy-MM-dd")
            val next = df.format(tomorrow).split("-").map { it.toInt() }

            return DateObject(
                    next[0],
                    next[1],
                    next[2]
            )
        }

        val nepaliMonths = arrayOf("बैशाख", "जेष्ठ", "अषाढ", "श्रावण", "भाद्र", "असोज", "कात्तिक", "मंसिर", "पौष", "माघ", "फाल्गुन", "चैत्र")
        val englishMonths = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

        fun getNepaliMonth(month: Int): String = nepaliMonths[month - 1]

        fun getEnglishMonth(month: Int): String = englishMonths[month - 1]

        fun getMonth(month: Int,format: DateFormat):String{
            return if (format == AD){
                getEnglishMonth(month)
            }else{
                getNepaliMonth(month)
            }
        }
    }



}