package retrofit

import dto.UserFullDto
import dto.UserShortDto
import retrofit2.http.GET
import retrofit2.http.Path

//Just interface for Retrofit. Use only two endpoints
interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserShortDto>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id : Int) : UserFullDto
}