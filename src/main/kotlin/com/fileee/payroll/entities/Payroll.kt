package com.fileee.payroll.entities

import java.time.LocalDate
import javax.persistence.Entity

enum class PayrollType {
    Hourly, Fixed;
}


data class Payroll(val member: Member, val paymentAmount: Double, val from: LocalDate, val to: LocalDate)
