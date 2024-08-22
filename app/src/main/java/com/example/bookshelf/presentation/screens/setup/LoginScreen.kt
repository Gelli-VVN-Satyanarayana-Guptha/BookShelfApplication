package com.example.bookshelf.presentation.screens.setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookshelf.R

@Composable
fun LoginScreen (
    navController: NavController,
) {
    val viewModel = hiltViewModel<AuthViewModel>()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    MaterialTheme {
        Scaffold { paddingValues ->
            Column (
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text (
                    text = "Login",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(64.dp))
                OutlinedTextField (
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email
                    ),
                    trailingIcon = {
                        Icon (
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Password Visibility Toggle"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField (
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon (
                                painter = painterResource(
                                    if (!isPasswordVisible)
                                        R.drawable.visibility
                                    else
                                        R.drawable.visibility_off
                                ),
                                contentDescription = "Password Visibility Toggle"
                            )
                        }
                    },
                    visualTransformation = if (!isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (isError) {
                    Text (
                        text = "This Email or Password is incorrect",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button (
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    onClick = {
                        if(viewModel.login(email, password)) {
                            navController.navigate("guide")
                        } else {
                            isError = true
                        }
                    },
                    shape = RoundedCornerShape(10),
                ) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row (
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text (
                        text = "Don't have an account?",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text (
                        modifier = Modifier.clickable {
                            navController.navigate("signup")
                        },
                        text = "SignUp",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Blue
                    )
                }
            }
        }
    }
}