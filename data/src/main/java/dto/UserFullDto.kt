package dto

import com.google.gson.annotations.SerializedName

//DTO for Retrofit
//Also used there @SerializedName just to try it

data class UserFullDto (

    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("username") val userName: String,


    @SerializedName("address") val addressDto: AddressDto,
    @SerializedName("company") val companyDto: CompanyDto,
)