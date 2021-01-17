package com.fileee.payroll.services

import com.fileee.payroll.entities.*
import com.fileee.payroll.exceptions.ResourceNotFoundException
import com.fileee.payroll.exceptions.WorklogCreateException
import com.fileee.payroll.repositories.MembersRepository
import com.fileee.payroll.repositories.WorklogRepository
import com.sun.org.apache.bcel.internal.generic.SWITCH
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PayrollService(private val worklogRepository: WorklogRepository, private val memberRepo: MembersRepository) {

    fun addWorklog(workLog: WorkLog): ResponseEntity<WorkLog> {
        var member = Member("", PayrollType.Monthly,0.0)
        memberRepo.findById(workLog.memberId).map { m ->
            member = m
        }.orElseThrow { ResourceNotFoundException("Member doesn't exist : ${workLog.memberId}") }

        if (member.payrollType == PayrollType.Monthly) {
            throw WorklogCreateException("Can't add worklog for monthly payroll member type")
        }
        if (workLog.hours <= 0) {
            throw WorklogCreateException("hours is not valid")
        }
        return ResponseEntity<WorkLog>(worklogRepository.save(workLog), HttpStatus.OK)
    }

    fun generatePayroll(memberId: Long, from: LocalDate, to: LocalDate): ResponseEntity<Payroll> {
        var member = Member("", PayrollType.Monthly, 0.0)
        memberRepo.findById(memberId).map { m ->
            member = m
        }.orElseThrow { ResourceNotFoundException("Member doesn't exist : $memberId") }

        var workLogs = worklogRepository.findAllByMemberId(memberId)

        var payrollCalculator = when (member.payrollType) {
            PayrollType.Monthly -> FixedPayrollPayment(member.wage)
            PayrollType.Hourly -> HourlyPayrollPayment(member.wage, workLogs.toMutableList())
        }

        var paymentAmount = payrollCalculator.calculateMemberPayment(Pair(from, to))

        return ResponseEntity(Payroll(member, paymentAmount, from, to), HttpStatus.OK)
    }
}