package go.deyu.stupidgame2.presentation.login

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient
) : ViewModel() {

    private var signInActivityResultLauncher: ActivityResultLauncher<Intent>? = null
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState>
        get() = _loginState

    init {
        checkUserLoggedIn()
    }

    fun register(activity: ComponentActivity) {
        signInActivityResultLauncher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                _loginState.value = LoginState.Error("Google sign-in failed")
            }
        }
    }

    private fun checkUserLoggedIn() {
        if (firebaseAuth.currentUser != null) {
            _loginState.value = LoginState.Success(firebaseAuth.currentUser)
        }
    }


    fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        signInActivityResultLauncher?.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                _loginState.value = LoginState.Success(firebaseAuth.currentUser)
            }
            .addOnFailureListener {
                _loginState.value = LoginState.Error("Firebase authentication failed $it")
            }
    }
}
