package com.dullyoung.androidtest1.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * @author Dullyoung   2021/9/29
 */
class CommonUtils {
    companion object {
        /**
         * @param aLong timestrap , ms
         */
        fun formatDate(aLong: Long): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            return dateFormat.format(aLong)
        }


    }
}