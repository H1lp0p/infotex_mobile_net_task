package mappers

import com.example.domain.models.Address
import com.example.domain.models.Company
import com.example.domain.models.User
import dto.AddressDto
import dto.CompanyDto
import dto.UserFullDto
import dto.UserShortDto

fun UserShortDto.toModel(): User = User(
    id = this.id,
    name = this.name,
    userName = this.userName,
    email = this.email,
    address = Address(city = "none", street = "none"),
    company = Company(name = "none")
)

fun UserFullDto.toModel() : User = User(
    id = this.id,
    name = this.name,
    userName = this.userName,
    email = this.email,
    address = this.addressDto.toModel(),
    company = this.companyDto.toModel(),
)

fun AddressDto.toModel() : Address = Address(
    street = this.street,
    city = this.city
)

fun CompanyDto.toModel() : Company = Company(
    name = this.name
)