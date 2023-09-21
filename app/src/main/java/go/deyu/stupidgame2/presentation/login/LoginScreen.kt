package go.deyu.stupidgame2.presentation.login

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    val loginState = viewModel.loginState.observeAsState().value
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                when (loginState) {
                    is LoginState.Success -> {
                        Text(
                            text = "Logged in as ${loginState.user?.displayName}",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    is LoginState.Error -> {
                        Text(
                            text = "Error: ${loginState.errorMessage}",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    else -> { /* show nothing */ }
                }
                Button(
                    onClick = { viewModel.signInWithGoogle() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Sign in with Google")
                }
            }
        }
    )
}