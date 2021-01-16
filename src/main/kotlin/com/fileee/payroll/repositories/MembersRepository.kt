package com.fileee.payroll.repositories

import com.fileee.payroll.entities.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface MembersRepository : PagingAndSortingRepository<Member, Long>{
}