package com.example.finalfirebaseauth

sealed class SignInState {
    object Anonymous : SignInState()
    object GoogleSignIn : SignInState()
}
