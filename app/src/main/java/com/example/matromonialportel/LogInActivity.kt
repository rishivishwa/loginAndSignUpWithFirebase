package com.example.matromonialportel


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {
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
                        val user = auth.currentUser
                        // Login successful, navigate to the main activity
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        // Handle login failure
                        val exception = task.exception
                        if (exception is FirebaseAuthInvalidUserException) {
                            // User not found
                            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                        } else {
                            // Other authentication error
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
