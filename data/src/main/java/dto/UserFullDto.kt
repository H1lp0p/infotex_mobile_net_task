package dto

import com.google.gson.annotations.SerializedName

data class UserFullDto (

    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("username") val userName: String,


    @SerializedName("address") val addressDto: AddressDto,
    @SerializedName("company") val companyDto: CompanyDto,
)