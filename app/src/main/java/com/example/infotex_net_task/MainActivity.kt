package com.example.infotex_net_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.infotex_net_task.ui.theme.Infotex_net_taskTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import screens.UserFullInfo
import screens.UserList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Infotex_net_taskTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                ) { innerPadding ->
                    MainComposable(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Serializable
object UserList
@Serializable
data class UserInfo(val id: Int)

@Composable
fun MainComposable(modifier: Modifier = Modifier){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = UserList,
        modifier = modifier
    ){
        composable<UserList> {
            UserList(onUserSelect = {id ->
                navController.navigate(UserInfo(id)){
                    launchSingleTop = true
                }
            })
        }
        composable<UserInfo> { backstackEntry ->
            val userInfo: UserInfo = backstackEntry.toRoute()
            UserFullInfo(
                id = userInfo.id,
                onGoBack = {navController.popBackStack()})
        }
    }
}