package com.example.skillswap

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.skillswap.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var joinAsMentorButton: Button
    private lateinit var joinAsMenteeButton: Button
    private lateinit var viewOffersButton: ExtendedFloatingActionButton
    private var isMentor = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val afterRegistrationLayout: LinearLayout = findViewById(R.id.afterRegistrationLayout)
        val greetingTextView: TextView = findViewById(R.id.greetingTextView)
        val offersLinkTextView: TextView = findViewById(R.id.offersLinkTextView)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        joinAsMentorButton = findViewById(R.id.joinAsMentorButton)
        joinAsMenteeButton = findViewById(R.id.joinAsMenteeButton)
        viewOffersButton = findViewById(R.id.viewOffersButton)

        joinAsMentorButton.alpha = 0.5f
        joinAsMenteeButton.alpha = 0.5f

        joinAsMentorButton.setOnClickListener {
            registerUser(
                nameEditText,
                emailEditText,
                afterRegistrationLayout,
                greetingTextView,
                "mentor"
            )
        }

        joinAsMenteeButton.setOnClickListener {
            registerUser(
                nameEditText,
                emailEditText,
                afterRegistrationLayout,
                greetingTextView,
                "mentee"
            )
        }

        nameEditText.addTextChangedListener(inputWatcher)
        emailEditText.addTextChangedListener(inputWatcher)

        viewOffersButton.setOnClickListener {
            val intent = Intent(this@MainActivity, OffersActivity::class.java)
            intent.putExtra("registrationType", if (isMentor) "mentor" else "mentee")
            startActivity(intent)
        }
        viewOffersButton.hide()

        offersLinkTextView.setOnClickListener {
            // Redirect the user to the offers page
        }
    }

    private val inputWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            val inputsAreValid = name.isNotEmpty()
                    && email.isNotEmpty()
                    && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

            if (inputsAreValid) {
                joinAsMentorButton.alpha = 1f
                joinAsMenteeButton.alpha = 1f
            } else {
                joinAsMentorButton.alpha = 0.5f
                joinAsMenteeButton.alpha = 0.5f
            }
        }

        override fun afterTextChanged(s: Editable) {}
    }

    private fun registerUser(
        nameEditText: EditText,
        emailEditText: EditText,
        afterRegistrationLayout: LinearLayout,
        greetingTextView: TextView,
        role: String
    ) {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()

        if (name.isEmpty()) {
            nameEditText.error = "Name is required"
            return
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Please enter a valid email"
            return
        }

        nameEditText.visibility = View.GONE
        emailEditText.visibility = View.GONE
        findViewById<Button>(R.id.joinAsMentorButton).visibility = View.GONE
        findViewById<Button>(R.id.joinAsMenteeButton).visibility = View.GONE

        afterRegistrationLayout.visibility = View.VISIBLE
        greetingTextView.text = "Welcome $name!"
        viewOffersButton.show()

        val waitingListTextView: TextView = findViewById(R.id.waitingListTextView)
        isMentor = role === "mentor"
        val waitingListMessage = if (isMentor) {
            "Thank you for joining as a mentor! You have been added to the waiting list, and we will contact you shortly with potential mentees."
        } else {
            "Thank you for joining as a mentee! You have been added to the waiting list, and we will contact you shortly with potential mentors."
        }
        waitingListTextView.text = waitingListMessage
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (
            item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}