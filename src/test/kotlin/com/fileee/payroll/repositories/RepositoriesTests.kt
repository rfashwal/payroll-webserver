package com.fileee.payroll.repositories

import com.fileee.payroll.entities.Member
import com.fileee.payroll.entities.PayrollType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val membersRepository: MembersRepository) {

    @Test
    fun testGet() {
        val jhon = Member("Jhon", PayrollType.Hourly, 30.0)
        entityManager.persist(jhon)
        entityManager.flush()
        membersRepository.findById(jhon.id).map {
            assertThat(it).isEqualTo(jhon)
        }

    }
}