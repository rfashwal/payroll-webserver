package com.fileee.payroll.payroll

import com.fileee.payroll.entities.WorkLog
import java.time.LocalDate


typealias Interval = Pair<LocalDate, LocalDate>

interface PayrollPayment {
    fun calculateMemberPayment(interval: Interval): Double
}

data class FixedPayrollPayment(val salary: Double) : PayrollPayment {
    override fun calculateMemberPayment(interval: Interval): Double {
        return salary
    }
}

data class HourlyPayrollPayment(val hourlyWage: Double, val workLogs: MutableList<WorkLog> = mutableListOf()) : PayrollPayment {
    override fun calculateMemberPayment(interval: Interval): Double {
        var total = 0.0
        workLogs.forEach {
            if (isInInterval(it.date, interval)) {
                total += hourlyWage * it.hours
            }
        }
        return total
    }
}

fun isInInterval(date: LocalDate, interval: Interval): Boolean {
    return date >= interval.first && date <= interval.second
}