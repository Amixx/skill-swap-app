// SignInActivity.kt
package com.iliepins.skillswap

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.iliepins.skillswap.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailEditText = findViewById(R.id.emailSignInEditText)
        passwordEditText = findViewById(R.id.passwordSignInEditText)
        signInButton = findViewById(R.id.signInButton)

        signInButton.setOnClickListener {
            val intent = Intent(this@SignInActivity, OffersActivity::class.java)
            var isMentor = false; //TODO: fix
            intent.putExtra("registrationType", if (isMentor) "mentor" else "mentee")
            startActivity(intent)
        }
    }
}
