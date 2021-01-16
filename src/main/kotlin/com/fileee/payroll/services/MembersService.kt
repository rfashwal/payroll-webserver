package com.fileee.payroll.services

import com.fileee.payroll.entities.Member
import com.fileee.payroll.repositories.MembersRepository
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.util.*

@Service
class MembersService(private val repository: MembersRepository) {
    fun createMember(member: Member): Member {
        return repository.save(member)
    }

    fun list(): List<Member> {
        return repository.findAll().toList()
    }

    fun get(id: Long): Optional<Member> {
        return repository.findById(id)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun update(member: Member): Member {
        return repository.save(member)
    }
}