package com.fileee.payroll.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class WorkLog(
        var name: String,
        var payrollType : PayrollType,
        @Id @GeneratedValue var id: Long? = 0
)