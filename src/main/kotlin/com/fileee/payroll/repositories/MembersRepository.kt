package com.fileee.payroll.repositories

import com.fileee.payroll.entities.Member
import org.springframework.data.repository.CrudRepository

interface MembersRepository : CrudRepository<Member, Long>{
    //fun findAllOrderedByName(): Iterable<Member>
}