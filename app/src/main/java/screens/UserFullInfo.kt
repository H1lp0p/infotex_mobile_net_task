package screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import viewmodels.UserInfoViewModel

//Second screen with user info. Just make User model from domain looks pretty
//And we use LaunchedEffect(id) to prevent multiple fetches to view model

@Composable
fun UserFullInfo(
    id: Int,
    userInfoViewModel: UserInfoViewModel = hiltViewModel<UserInfoViewModel>(),
    onGoBack: () -> Unit = {}
) {
    val user by userInfoViewModel.user.collectAsState()
    val pending by userInfoViewModel.pending.collectAsState()
    val error by userInfoViewModel.error.collectAsState()

    LaunchedEffect(id) {
        userInfoViewModel.loadUser(id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ){
        if (pending){
            Log.i("FullInfo", "pending")
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.titleLarge
            )
        }
        else{
            if (error !== null){
                Text(
                    text = "Opps! Something went wrong:\n$error",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            else{
                user?.let {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(0.dp, 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier,
                            ) {
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = it.email,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            Text(
                                text = it.userName,
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Address",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${it.address.city}, ${it.address.street}"
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = "Company:",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = it.company.name
                                )
                            }
                        }
                        Button(
                            onClick = onGoBack,
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(0.dp, 16.dp)
                        ) {
                            Text("Back")
                        }
                    }
                }
            }
        }
    }
}