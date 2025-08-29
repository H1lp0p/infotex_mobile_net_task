package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.User
import com.example.domain.reposiotories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//ViewModel for first screen. Just normal viewModel without using MVI

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList : StateFlow<List<User>> = _userList

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _pendingState = MutableStateFlow<Boolean>(false)
    val pending : StateFlow<Boolean> = _pendingState

    init {
        loadUserList()
    }

    fun loadUserList(){
        viewModelScope.launch {
            _pendingState.value = true
            try {
                val userList = userRepository.getUsers()
                _userList.value = userList
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _pendingState.value = false
            }
        }
    }
}
