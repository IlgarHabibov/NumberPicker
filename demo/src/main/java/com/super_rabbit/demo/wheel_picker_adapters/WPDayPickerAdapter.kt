package com.super_rabbit.demo.wheel_picker_adapters

import com.super_rabbit.wheel_picker.WheelAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Custom wheel picker adapter for implementing a date picker
 */
class WPDayPickerAdapter : WheelAdapter {
    //get item value based on item position in wheel
    override fun getValue(position: Int): String {
        if (position == 0)
            return "Today"

        if(position == -1)
            return "Yesterday"

        if (position == 1)
            return "Tomorrow"

        val curDate = Date(System.currentTimeMillis())
        val rightNow = Calendar.getInstance()
        rightNow.time = curDate;
        rightNow.add(Calendar.DATE, position)

        val simpleDateFormat = SimpleDateFormat("MMM d, yyyy")
        return simpleDateFormat.format(rightNow.time)
    }

    //get item position based on item string value
    override fun getPosition(vale: String): Int {
        return 0
    }

    //return a string with the approximate longest text width, for supporting WRAP_CONTENT
    override fun getTextWithMaximumLength(): String {
        return "Mmm 00, 0000"
    }

    //return the maximum index
    override fun getMaxIndex(): Int {
        return Integer.MAX_VALUE
    }

    //return the minimum index
    override fun getMinIndex(): Int {
        return Integer.MIN_VALUE
    }
}