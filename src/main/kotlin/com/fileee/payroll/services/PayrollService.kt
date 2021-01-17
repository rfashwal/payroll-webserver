package com.fileee.payroll.services

import org.slf4j.LoggerFactory
import com.fileee.payroll.entities.*
import com.fileee.payroll.exceptions.InvalidPeriodRange
import com.fileee.payroll.exceptions.ResourceNotFoundException
import com.fileee.payroll.exceptions.WorklogCreateException
import com.fileee.payroll.payroll.FixedPayrollPayment
import com.fileee.payroll.payroll.HourlyPayrollPayment
import com.fileee.payroll.repositories.MembersRepository
import com.fileee.payroll.repositories.WorklogRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class PayrollService(private val worklogRepository: WorklogRepository, private val memberRepo: MembersRepository) {
    private val log = LoggerFactory.getLogger(PayrollService::class.java)
    fun addWorklog(workLog: WorkLog): ResponseEntity<WorkLog> {
        log.info("addWorklog")
        var member = Member("", PayrollType.Fixed, 0.0)
        memberRepo.findById(workLog.memberId).map { m ->
            member = m
        }.orElseThrow {
            log.error("Member doesn't exist : ${workLog.memberId}")
            ResourceNotFoundException("Member doesn't exist : ${workLog.memberId}")
        }

        if (member.payrollType == PayrollType.Fixed) {
            log.error("Can't add worklog for monthly payroll member type")
            throw WorklogCreateException("Can't add worklog for monthly payroll member type")
        }
        if (workLog.hours <= 0) {
            log.error("hours is not valid")
            throw WorklogCreateException("hours is not valid")
        }
        return ResponseEntity<WorkLog>(worklogRepository.save(workLog), HttpStatus.OK)
    }

    fun generatePayroll(memberId: Long, from: String, to: String): ResponseEntity<Payroll> {
        return ResponseEntity<Payroll>(getPayroll(memberId, from, to), HttpStatus.OK)
    }


    fun getPayroll(memberId: Long, from: String, to: String): Payroll {
        var df = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        var fromDate = LocalDate.parse(from, df)
        var toDate = LocalDate.parse(to, df)
        if (fromDate.isAfter(toDate)) {
            log.error("From $from is greater than To $to")
            throw InvalidPeriodRange("From $from is greater than To $to")
        }

        var member = Member("", PayrollType.Fixed, 0.0)
        memberRepo.findById(memberId).map { m ->
            member = m
        }.orElseThrow {
            log.error("Member doesn't exist : $memberId")
            ResourceNotFoundException("Member doesn't exist : $memberId")
        }

        var workLogs = worklogRepository.findAllByMemberId(memberId)

        var payrollCalculator = when (member.payrollType) {
            PayrollType.Fixed -> FixedPayrollPayment(member.wage)
            PayrollType.Hourly -> HourlyPayrollPayment(member.wage, workLogs.toMutableList())
        }

        var paymentAmount = payrollCalculator.calculateMemberPayment(Pair(fromDate, toDate))

        return Payroll(member, paymentAmount, fromDate, toDate)
    }
}