package com.fileee.payroll.controller

import com.fileee.payroll.entities.Member
import com.fileee.payroll.services.MembersService
import com.fileee.payroll.services.SmallLogicTasks
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(value = "SmallLogicTasksController", description = "Restful APIs related to Small Logic Tasks")
@RestController
@RequestMapping("api/logiceee")
class SmallLogicTasksController (private val logicService: SmallLogicTasks){
    //starringString
    @ApiOperation(value = "a recursive method where identical chars that are adjacent in the original string will be separated by a \"*\".", response = String::class, tags = ["Logic Tasks"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"))
    @GetMapping("/starrings/{value}")
    fun staringString(@PathVariable value: String): String {
        return logicService.starringString(value, "", 0)
    }

    //verifyEmail
    @ApiOperation(value = "a method that accepts a string and checks if this is a valid email address", response = String::class, tags = ["Logic Tasks"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"))
    @GetMapping("/verify/{email}")
    fun verifyEmail(@PathVariable email: String): Boolean {
        return logicService.isEmailValid(email)
    }
}