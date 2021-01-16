package com.fileee.payroll.controller

import com.fileee.payroll.entities.Member
import com.fileee.payroll.repositories.MembersRepository
import com.fileee.payroll.services.MembersService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class StaffController(private val membersService: MembersService) {
    @RequestMapping("/api/members")
    fun list(): List<Member> {
        return membersService.list()
    }

    @GetMapping("/api/members/{id}")
    fun get(@PathVariable id: Long): Optional<Member> {
        return membersService.get(id)
    }

    @PostMapping("/api/members")
    fun add(@RequestBody member: Member): Member {
        return membersService.createMember(member)
    }

    @PatchMapping("/api/members")
    fun update(@RequestBody member: Member): Member {
        return membersService.update(member)
    }

    @DeleteMapping("/api/members/{id}")
    fun remove(@PathVariable id: Long): String {
        membersService.delete(id)
        return "User deleted successfully"
    }

}
