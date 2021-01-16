package com.fileee.payroll.controller

import com.fileee.payroll.entities.Member
import com.fileee.payroll.exceptions.ResourceNotFoundException
import com.fileee.payroll.repositories.MembersRepository
import com.fileee.payroll.services.MembersService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.jvm.Throws

@Api(value = "StaffController", description = "Restful APIs related to Staff Members")
@RestController
@RequestMapping("api/members")
class StaffController(private val membersService: MembersService) {

    //getAllMembers
    @ApiOperation(value = "Get list of all members", response = Member::class, tags = ["Staff Members"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"))
    @GetMapping(params = ["page", "size"])
    fun list(@RequestParam("page") page:Int, @RequestParam("size") size:Int): Page<Member> {
        return membersService.list(page, size)
    }

    //getAllMembers Bubble Sorted
    @ApiOperation(value = "Get list of all members sorted using Bubble Sort", response = Member::class, tags = ["Staff Members"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"))
    @GetMapping("/sorted")
    fun listBubbleSort(): List<Member> {
        return membersService.listBubbleSort()
    }

    //getMemberById
    @ApiOperation(value = "Get staff member by Id", response = Member::class, tags = ["Staff Members"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"), ApiResponse(code = 404, message = "not found"))
    @GetMapping(path = ["/{id}"])
    @Throws(ResourceNotFoundException::class)
    fun get(@PathVariable id: Long): ResponseEntity<Member> {
        return membersService.get(id)
    }

    //addMember
    @ApiOperation(value = "create new Staff Member", response = Member::class, tags = ["Staff Members"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"))
    @PostMapping
    fun add(@RequestBody member: Member): Member {
        return membersService.createMember(member)
    }

    //updateMember
    @ApiOperation(value = "Update member object by Id", response = Member::class, tags = ["Staff Members"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"), ApiResponse(code = 404, message = "not found"))
    @PatchMapping(path = ["/{id}"])
    @Throws(ResourceNotFoundException::class)
    fun update(@RequestBody member: Member): ResponseEntity<Member> {
        return membersService.update(member)
    }

    //deleteMember
    @ApiOperation(value = "delete member object by Id", response = Member::class, tags = ["Staff Members"])
    @ApiResponses(ApiResponse(code = 200, message = "Success|OK"), ApiResponse(code = 404, message = "not found"))
    @DeleteMapping(path = ["/{id}"])
    @Throws(ResourceNotFoundException::class)
    fun remove(@PathVariable id: Long): ResponseEntity<*> {
        return membersService.delete(id)
    }
}
