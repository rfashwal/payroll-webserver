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
    fun seedMembersOperation(membersRepository: MembersRepository) = ApplicationRunner {
        addMonthlyMemberHelper(membersRepository, "Arne")
        addMonthlyMemberHelper(membersRepository, "Marius")
        addMonthlyMemberHelper(membersRepository, "Eike")
        addMonthlyMemberHelper(membersRepository, "Rahel")
        addMonthlyMemberHelper(membersRepository, "Laura")
        addMonthlyMemberHelper(membersRepository, "Florian")
        addMonthlyMemberHelper(membersRepository, "Tom")
        addMonthlyMemberHelper(membersRepository, "Benni")
        addMonthlyMemberHelper(membersRepository, "Jasmin")
        addMonthlyMemberHelper(membersRepository, "Alina")
        addMonthlyMemberHelper(membersRepository, "Niklas")
        addMonthlyMemberHelper(membersRepository, "Steffen")
        addMonthlyMemberHelper(membersRepository, "Henning")
        addMonthlyMemberHelper(membersRepository, "Benji")
        addMonthlyMemberHelper(membersRepository, "Suneel")
        addMonthlyMemberHelper(membersRepository, "Daniel")
        addMonthlyMemberHelper(membersRepository, "Jasmin")
        addMonthlyMemberHelper(membersRepository, "Sebastian")
        addMonthlyMemberHelper(membersRepository, "Meggy")
        addMonthlyMemberHelper(membersRepository, "Pascal")
        addMonthlyMemberHelper(membersRepository, "Manu")
        addMonthlyMemberHelper(membersRepository, "Dhala")

        membersRepository.save(Member(
                name = "Rabieh",
                payrollType = PayrollType.Hourly,
                wage = 40.0,
        ))
    }

    fun addMonthlyMemberHelper(membersRepository: MembersRepository, name: String) {
        membersRepository.save(Member(
                name = name,
                payrollType = PayrollType.Monthly,
                wage = 2500.0
        ))
    }
}