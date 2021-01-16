package com.fileee.payroll.services

import com.fileee.payroll.entities.Member
import com.fileee.payroll.repositories.MembersRepository
import com.fileee.payroll.exceptions.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam

@Service
class MembersService(private val repository: MembersRepository) {
    fun createMember(member: Member): Member {
        return repository.save(member)
    }

    fun list(page:Int, size:Int): Page<Member> {
        var pageable = PageRequest.of(page, size)
        return repository.findAll(pageable)
    }

    fun listBubbleSort(): List<Member> {
        var members = repository.findAll().toMutableList()
        for (i in 0 until (members.size - 1)) {
            for (currentPosition in 0 until (members.size - i - 1)) {
                if (members[currentPosition].name.compareTo(members[currentPosition + 1].name, true) > 0) {
                    val tempMember = members[currentPosition]
                    members[currentPosition] = members[currentPosition + 1]
                    members[currentPosition + 1] = tempMember
                }
            }
        }
        return members
    }

    fun get(id: Long): ResponseEntity<Member> {
        return repository.findById(id).map { m -> ResponseEntity.ok().body(m) }.orElseThrow {
            ResourceNotFoundException("member doesn't exist")
        }
    }

    fun delete(id: Long): ResponseEntity<*> {
        return repository.findById(id)
                .map { member ->
                    repository.deleteById(id)
                    ResponseEntity.ok().body(member)
                }.orElseThrow { ResourceNotFoundException("Member doesn't exist : $id") }
    }

    fun update(member: Member): ResponseEntity<Member> {
        return repository.findById(member.id)
                .map { record ->
                    record.name = member.name
                    record.payrollType = member.payrollType
                    val updated = repository.save(record)
                    ResponseEntity.ok().body(updated)
                }.orElseThrow { ResourceNotFoundException("Member doesn't exist : ${member.id}") }
    }
}