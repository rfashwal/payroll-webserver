package com.fileee.payroll.config

import com.fileee.payroll.entities.Member
import com.fileee.payroll.entities.PayrollType
import com.fileee.payroll.entities.WorkLog
import com.fileee.payroll.repositories.MembersRepository
import com.fileee.payroll.repositories.WorklogRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class SeedMembers {
    @Bean
    fun seedMembersOperation(membersRepository: MembersRepository, worklogRepository: WorklogRepository) = ApplicationRunner {
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

      var member=  membersRepository.save(Member(
                name = "Rabieh",
                payrollType = PayrollType.Hourly,
                wage = 40.0,
        ))

        worklogRepository.save(WorkLog(LocalDate.now(), 10, member.id))
        worklogRepository.save(WorkLog(LocalDate.now().minusDays(2), 5, member.id))
        worklogRepository.save(WorkLog(LocalDate.now().minusDays(1), 2, member.id))
    }

    fun addMonthlyMemberHelper(membersRepository: MembersRepository, name: String) {
        membersRepository.save(Member(
                name = name,
                payrollType = PayrollType.Fixed,
                wage = 2500.0
        ))
    }
}