package com.fileee.payroll.services

import org.springframework.stereotype.Service

@Service
class SmallLogicTasks {
    fun starringString(input: String, result: String, index: Int): String {
        var newResult = result + input[index]
        //Recursion Base Case to avoid infinite loop
        if (index == input.length - 1) {
            return newResult
        }
        if (input[index] == input[index + 1]) {
            newResult += "*"
        }
        // Recursive Case
        return starringString(input, newResult, index + 1)
    }

    fun isEmailValid(email: String): Boolean {
        //This algo or use regex :)
        if (!email[0].isLetter()) {
            return false
        }

        var atIndex = -1
        var dotIndex = -1
        var atExists = false
        for (i in email.indices) {
            if (email[i] == '@') {
                //Check duplicate @
                if (atExists) {
                    return false
                }
                atExists = true
                atIndex = i
            } else if (email[i] == '.') {
                dotIndex = i
            }
        }

        if (atIndex == -1 || dotIndex == -1 ||
                !email[dotIndex - 1].isLetterOrDigit() ||
                !email[atIndex - 1].isLetterOrDigit()) {
            return false
        }

        if (atIndex > dotIndex) {
            return false
        }

        return dotIndex < (email.length - 1)
    }

}