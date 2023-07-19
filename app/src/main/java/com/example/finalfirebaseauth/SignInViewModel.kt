import androidx.lifecycle.ViewModel
import com.example.finalfirebaseauth.SignInState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val _signInState = MutableStateFlow<SignInState>(SignInState.Anonymous)
    val signInState: StateFlow<SignInState> = _signInState.asStateFlow()

    fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _signInState.value = SignInState.Anonymous
                } else {
                    // Handle sign-in failure
                }
            }
    }

    fun signInWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _signInState.value = SignInState.GoogleSignIn
                } else {
                    // Handle sign-in failure
                }
            }
    }
}
