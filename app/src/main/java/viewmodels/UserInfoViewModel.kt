package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.User
import com.example.domain.reposiotories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user : StateFlow<User?> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _pending = MutableStateFlow<Boolean>(false)
    val pending : StateFlow<Boolean> = _pending

    init {

    }

    fun loadUser(id: Int){
        viewModelScope.launch {
            _pending.value = true
            try {
                _user.value = userRepository.getUserById(id)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
            finally {
                _pending.value = false
            }
        }
    }
}