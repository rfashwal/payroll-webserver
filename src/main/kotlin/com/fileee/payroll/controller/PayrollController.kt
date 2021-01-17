package com.fileee.payroll.controller

import com.fileee.payroll.entities.Member
import com.fileee.payroll.entities.Payroll
import com.fileee.payroll.entities.WorkLog
import com.fileee.payroll.payroll.PayrollDocumentExporter
import com.fileee.payroll.services.MembersService
import com.fileee.payroll.services.PayrollService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.servlet.http.HttpServletResponse
import java.text.SimpleDateFormat

import java.text.DateFormat
import java.util.*


@Api(value = "PayrollController", description = "Restful APIs related to Payroll")
@RestController
@RequestMapping("api/payroll")
class PayrollController(private val payrollService: PayrollService) {
    //addWorklog
    @ApiOperation(value = "create new worklog", response = WorkLog::class, tags = ["Payroll"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"))
    @PostMapping("/worklog")
    fun add(@RequestBody workLog: WorkLog): ResponseEntity<WorkLog> {
        return payrollService.addWorklog(workLog)
    }

    //generatePayroll
    @ApiOperation(value = "generate payroll for a staff member in a given period", response = Payroll::class, tags = ["Payroll"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"))
    @GetMapping(path = ["/{memberId}"], params = ["from", "to"])
    fun generatePayroll(@PathVariable memberId: Long, @RequestParam("from") from: String, @RequestParam("to") to: String): ResponseEntity<Payroll> {
        return payrollService.generatePayroll(memberId, from, to)
    }

    //generatePayroll
    @ApiOperation(value = "generate payroll document for a staff member in a given period", response = Payroll::class, tags = ["Payroll"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"))
    @GetMapping(path = ["/{memberId}/export"], params = ["from", "to"])
    fun export(@PathVariable memberId: Long, @RequestParam("from") from: String, @RequestParam("to") to: String, response: HttpServletResponse) {
        response.contentType = "application/pdf"
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")
        val currentDateTime = dateFormatter.format(Date())

        val headerKey = "Content-Disposition"


        var payroll = payrollService.getPayroll(memberId, from, to)
        val headerValue = "attachment; filename=payroll_${payroll.member.name}_$currentDateTime.pdf"
        response.setHeader(headerKey, headerValue)
        val exporter = PayrollDocumentExporter(payroll)
        exporter.export(response)
    }
}