package com.example.data

import com.example.domain.models.Address
import com.example.domain.models.Company
import com.example.domain.models.User
import dto.AddressDto
import dto.CompanyDto
import dto.UserFullDto
import dto.UserShortDto
import mappers.toModel

import org.junit.Test
import org.junit.Assert.*

class MapperUnitTest {

    //Positive test on mapper from UserFullDto to User
    @Test
    fun userFullDto_to_User_positive(){
        val mockDto = UserFullDto(
            id = 1,
            name = "Mock User",
            email = "mock.user@email.com",
            userName = "Mocky",
            addressDto = AddressDto(
                city = "Mock",
                street = "Mocked"
            ),
            companyDto = CompanyDto(
                name = "Mock Corp"
            )
        )

        val mockUser = User(
            id = 1,
            name = "Mock User",
            userName = "Mocky",
            email = "mock.user@email.com",
            address = Address(
                city = "Mock",
                street = "Mocked"
            ),
            company = Company(
                name = "Mock Corp"
            )
        )

        val res = mockDto.toModel()

        assertEquals(mockUser, res)
    }

    //Positive test for mapper from UserShortDto to User
    @Test
    fun userShortDto_to_User_positive(){
        val mockDto = UserShortDto(
            id = 1,
            name = "short",
            email = "short.eml@email.com",
            username = "shorty"
        )

        val expect = User(
            id = 1,
            name = "short",
            userName = "shorty",
            email = "short.eml@email.com",
            address = Address("none", "none"),
            company = Company("none")
        )

        val res = mockDto.toModel()

        assertEquals(expect, res)
    }
}