package com.example.matromonialportel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class Registration : AppCompatActivity() {
    private lateinit var editTextNewUsername: EditText
    private lateinit var editTextNewPassword: EditText
    private lateinit var buttonSignup: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editTextNewUsername = findViewById(R.id.editTextNewUsername)
        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        buttonSignup = findViewById(R.id.buttonSignup)
        auth = FirebaseAuth.getInstance()

        buttonSignup.setOnClickListener {
            val email = editTextNewUsername.text.toString()
            val password = editTextNewPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    } else {
                        val exception = task.exception
                        if (exception is FirebaseAuthUserCollisionException) {
                            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            findViewById<TextView>(R.id.tvLogin).setOnClickListener {
                startActivity(Intent(this,SignInActivity::class.java))
            }
        }
    }
}
