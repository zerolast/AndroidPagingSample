package com.stupid.techie.sampleassignment.Utils

import java.text.SimpleDateFormat
import java.util.*


class DateUtils {

    companion object {

        //1 minute = 60 seconds
        //1 hour = 60 x 60 = 3600
        //1 day = 3600 x 24 = 86400
        fun printDifference(startDate: String): String {
            //milliseconds

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")

            val date1 = simpleDateFormat.parse(startDate)
            val date2 = Calendar.getInstance().time

            var different = date2.getTime() - date1.getTime()

            println("startDate : $startDate")
            println("endDate : $date2")
            println("different : $different")

            val secondsInMilli: Long = 1000
            val minutesInMilli = secondsInMilli * 60
            val hoursInMilli = minutesInMilli * 60
            val daysInMilli = hoursInMilli * 24

            val elapsedDays = different / daysInMilli
            different = different % daysInMilli

            val elapsedHours = different / hoursInMilli
            different = different % hoursInMilli

            val elapsedMinutes = different / minutesInMilli
            different = different % minutesInMilli

            val elapsedSeconds = different / secondsInMilli

            System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds
            )
            var result = ""
            if (elapsedDays > 0)
                result = "$elapsedDays days"
            else if (elapsedHours > 0)
                result = "$elapsedHours hours"
            else if (elapsedMinutes > 0)
                result = "$elapsedMinutes minutes"
            else if (elapsedSeconds > 0)
                result = "$elapsedSeconds seconds"

            return result
        }

    }

}