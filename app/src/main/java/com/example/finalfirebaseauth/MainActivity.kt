package com.example.finalfirebaseauth


import SignInViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import androidx.compose.ui.unit.sp
import com.example.finalfirebaseauth.ui.theme.FinalFirebaseAuthTheme
import com.google.android.gms.common.api.ApiException

class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("201720164063-0mujpnsdhfe1fi89o90sa31b21s1rpi8.apps.googleusercontent.com") // Replace with your Web client ID
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            FinalFirebaseAuthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = SignInViewModel()
                    Greeting(viewModel)
                }
            }
        }
    }



}

@Composable
fun Greeting(viewModel: SignInViewModel) {

//    FloatingActionButton(
//        onClick = { /*TODO*/ },
//        containerColor = Color.White,
//        shape = RoundedCornerShape(8.dp),
//        modifier = Modifier
//            .fillMaxWidth(0.8f),
//        elevation = FloatingActionButtonDefaults.elevation(
//            pressedElevation = 16.dp,
//        )
//
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .padding(8.dp)
//        ) {
////            Image(
////                painter = painterResource(id = R.drawable.icongoogle),
////                contentDescription = null,
////                modifier = Modifier
////                    .scale(3f)
////                    .padding(end = 8.dp)
////            )
//            Text(
//                text = "Sign in with Google",
//                style = TextStyle(
//                    fontSize = 18.sp,
//                    color = Color.Blue
//                ),
//                modifier = Modifier
//                    .padding(start = 4.dp)
//            )
//        }
//
//
//        FloatingActionButton(
//            onClick = { /*TODO*/ },
//            containerColor = Color.Blue,
//            shape = RoundedCornerShape(8.dp),
//            modifier = Modifier
//                .fillMaxWidth(),
//            elevation = FloatingActionButtonDefaults.elevation(
//                pressedElevation = 16.dp,
//            )
//
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .padding(8.dp)
//            ) {
////                Image(
////                    painter = painterResource(id = R.drawable.hacker),
////                    contentDescription = null,
////                    modifier = Modifier
////                        .scale(2f)
////                        .padding(end = 4.dp)
////                )
//                Text(
//                    text = "Go Anonymous",
//                    style = TextStyle(
//                        color = Color.White,
//                        fontSize = 18.sp,
//                    ),
//                    modifier = Modifier
//                        .padding(start = 4.dp)
//                )
//            }
//        }
//    }




    @Composable
    fun SignInScreen(viewModel: SignInViewModel) {
        val signInState by viewModel.signInState.collectAsState()

        when (signInState) {
            is SignInState.Anonymous -> {
                FloatingActionButton(
                    onClick = {
                        viewModel.signInAnonymously()
                    },
                    containerColor = Color.Blue,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = FloatingActionButtonDefaults.elevation(
                        pressedElevation = 16.dp,
                    )

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.hacker),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .scale(2f)
//                                .padding(end = 4.dp)
//                        )
                        Text(
                            text = "Go Anonymous",
                            style = TextStyle(
                                color = Color.Companion.White,
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier
                                .padding(start = 4.dp)
                        )
                    }
                }
            }
            is SignInState.GoogleSignIn -> {
                FloatingActionButton(
                    onClick = {
                              viewModel.signInWithGoogle(googleIdToken)
                    },
                    containerColor = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    elevation = FloatingActionButtonDefaults.elevation(
                        pressedElevation = 16.dp,
                    )

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.icongoogle),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .scale(3f)
//                                .padding(end = 8.dp)
//                        )
                        Text(
                            text = "Sign in with Google",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Blue,
                            ),
                            modifier = Modifier
                                .padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }

}

