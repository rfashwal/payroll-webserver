package com.fileee.payroll.controller

import com.fileee.payroll.entities.Member
import com.fileee.payroll.entities.Payroll
import com.fileee.payroll.entities.WorkLog
import com.fileee.payroll.services.MembersService
import com.fileee.payroll.services.PayrollService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

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
    @GetMapping(path = ["/{id}"], params = ["from", "to"])
    fun generatePayroll(@PathVariable id: Long, @RequestParam("from") from: LocalDate, @RequestParam("to") to: LocalDate): ResponseEntity<Payroll> {
        return payrollService.generatePayroll(id, from, to)
    }
}