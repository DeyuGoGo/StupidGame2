package go.deyu.stupidgame2.ui.login

import com.google.firebase.auth.FirebaseUser

sealed class LoginState {
        data class Success(val user: FirebaseUser?) : LoginState()
        data class Error(val errorMessage: String) : LoginState()
    }