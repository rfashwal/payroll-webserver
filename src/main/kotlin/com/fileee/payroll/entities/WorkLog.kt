package com.fileee.payroll.entities

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class WorkLog(
        var date: LocalDate,
        var hours: Int,
        var memberId: Long,
        @Id @GeneratedValue var id: Long? = 0
)