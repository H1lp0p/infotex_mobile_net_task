package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import components.UserListItem
import viewmodels.UserListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(
    modifier: Modifier = Modifier,
    onUserSelect: (id: Int) -> Unit = {},
    userListViewModel: UserListViewModel = hiltViewModel<UserListViewModel>()
) {
    val errorState by userListViewModel.error.collectAsState()
    val userListState by userListViewModel.userList.collectAsState()
    val pending by userListViewModel.pending.collectAsState()

    val onRefresh : () -> Unit = {userListViewModel.loadUserList()}

    PullToRefreshBox(
        isRefreshing = pending,
        onRefresh = onRefresh,
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        if (errorState !== null){
            Text(
                text = "Opps! There is an error:\n${errorState}",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }
        else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(userListState + userListState){ ind, user ->
                    UserListItem(
                        user = if (ind >= userListState.count())
                                    user.copy(name = "${user.name} the 2nd")
                                else user,
                        onClick = {id -> onUserSelect(id)   }
                    )
                }
            }
        }
    }
}