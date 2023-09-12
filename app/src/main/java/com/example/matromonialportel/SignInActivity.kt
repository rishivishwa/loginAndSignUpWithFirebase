package com.example.matromonialportel

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class SignInActivity : AppCompatActivity() {
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        auth = FirebaseAuth.getInstance()

        buttonLogin.setOnClickListener {
            val email = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("username", email)
                        editor.putBoolean("isLogin", true)
                        editor.apply()
                        val user = auth.currentUser
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        val exception = task.exception
                        if (exception is FirebaseAuthInvalidUserException) {
                            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
        findViewById<TextView>(R.id.tvSignUp).setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
        }
    }
}
