package components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.Address
import com.example.domain.models.Company
import com.example.domain.models.User
import com.example.infotex_net_task.ui.theme.Infotex_net_taskTheme


//Single item from user list

@Composable
fun UserListItem(
    user: User,
    onClick: (id: Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {onClick(user.id)}
    ){
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
            ){
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(
    name = "Light"
)
@Composable
fun UserListItemPreviewLight(){
    val mockUser = User(
        id = 1,
        name = "Test Testificate",
        userName = "Nag1b4t0or_3000",
        email = "email.email@gmail.com",
        address = Address("City 17", "Pushkina"),
        company = Company("Aperture science")
    )

    Infotex_net_taskTheme {
        UserListItem(mockUser)
    }
}

@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun UserListItemPreviewDark(){
    val mockUser = User(
        id = 1,
        name = "Test Testificate",
        userName = "Nag1b4t0or_3000",
        email = "email.email@gmail.com",
        address = Address("City 17", "Pushkina"),
        company = Company("Aperture science")
    )

    Infotex_net_taskTheme {
        UserListItem(mockUser)
    }
}