package com.fileee.payroll.repositories

import com.fileee.payroll.entities.Member
import com.fileee.payroll.entities.PayrollType
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.assertj.core.api.Assertions.assertThat

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val membersRepository: MembersRepository,
        val worklogRepository: WorklogRepository){

    @Test
    fun `When findById return Member`(){
        val jhon = Member("Jhon", PayrollType.Hourly, 30.0)
        entityManager.persist(jhon)
        entityManager.flush()
        val member = membersRepository.findById(jhon.id)
        assertThat(member).isEqualTo(jhon)
    }
}