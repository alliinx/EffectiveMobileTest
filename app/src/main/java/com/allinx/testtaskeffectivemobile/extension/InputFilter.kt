package com.allinx.testtaskeffectivemobile.extension

import android.text.InputFilter


object InputFilter {
    val cyrillicFilter = InputFilter { source, start, end, dest, dstart, dend ->
        for (i in start until end) {
            if ( (!Character.isLetter(source[i]) && source[i] != ' ') || !isCyrillic(source[i])) {
                return@InputFilter ""
            }
        }
        return@InputFilter null
    }

    private fun isCyrillic(c: Char): Boolean {
        return (c.code in 0x0400..0x04FF) || (c.code in 0x0500..0x052F) || c == ' '
    }
}