package com.fileee.payroll.config

import com.fileee.payroll.entities.Member
import com.fileee.payroll.entities.PayrollType
import com.fileee.payroll.repositories.MembersRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SeedMembers {
    @Bean
    fun seedMemebers(memebersRepository: MembersRepository) = ApplicationRunner {
        memebersRepository.save(Member(
                name = "Arne",
                payrollType = PayrollType.Monthly
        ))
        memebersRepository.save(Member(
                name = "Rabieh",
                payrollType = PayrollType.Hourly
        ))
    }
}