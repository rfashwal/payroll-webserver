package com.fileee.payroll.repositories

import com.fileee.payroll.entities.WorkLog
import org.springframework.data.repository.CrudRepository

interface WorklogRepository : CrudRepository<WorkLog, Long> {
    fun findAllByMemberId(memberId: Long): MutableIterable<WorkLog>
}