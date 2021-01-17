package com.fileee.payroll.entities

import javax.persistence.*

@Entity
class Member(
        var name: String,
        var payrollType: PayrollType,
        var wage: Double,
        @Id @GeneratedValue var id: Long = 0
)
