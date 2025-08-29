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

//Main activity. We use single-activity structure to normal use of navHost from jetpack navigation
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

//Destination object and class with @Serializable for NavHost
//Normally should be in different files

@Serializable
object UserList
@Serializable
data class UserInfo(val id: Int)

//You need to call rememberNavController from @Composable
//This composable controls navigation
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
                    //to prevent from double adding next navigation to back stack
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