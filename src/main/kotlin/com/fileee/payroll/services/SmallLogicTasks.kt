package com.fileee.payroll.services

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SmallLogicTasks {
    private val log = LoggerFactory.getLogger(SmallLogicTasks::class.java)
    fun starringString(input: String, result: String, index: Int): String {
        var newResult = result + input[index]
        log.info("result $result is now = $newResult")
        //Recursion Base Case to avoid infinite loop
        if (index == input.length - 1) {
            return newResult
        }
        if (input[index] == input[index + 1]) {
            log.info("adding a star to result $newResult")
            newResult += "*"
        }
        // Recursive Case
        return starringString(input, newResult, index + 1)
    }

    fun isEmailValid(email: String): Boolean {
        //This algo or use regex :)
        if (!email[0].isLetter()) {
            log.error("email doesn't start with a letter")
            return false
        }

        var atIndex = -1
        var dotIndex = -1
        var atExists = false
        for (i in email.indices) {
            if (email[i] == '@') {
                //Check duplicate @
                if (atExists) {
                    log.error("email has a duplicate @")
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
            log.error("error validating email $email")
            return false
        }

        if (atIndex > dotIndex) {
            log.error("error validating email $email")
            return false
        }

        return dotIndex < (email.length - 1)
    }

}