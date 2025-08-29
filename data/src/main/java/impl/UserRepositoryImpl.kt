package impl

import com.example.domain.models.User
import com.example.domain.reposiotories.UserRepository
import mappers.toModel
import retrofit.UserApiService
import javax.inject.Inject

//And there we implement interface from domain module
class UserRepositoryImpl @Inject constructor(
    private val retrofit: UserApiService
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return retrofit.getUsers().map { it ->
            it.toModel()
        }
    }

    override suspend fun getUserById(id: Int): User {
        return retrofit.getUserById(id).toModel()
    }
}
