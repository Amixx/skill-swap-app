package com.iliepins.skillswap

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.appcompat.widget.Toolbar

class OffersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)

        val toolbar: Toolbar = findViewById(R.id.toolbar_offers)
        setSupportActionBar(toolbar)

        // Set up the back button
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val gridLayout: GridLayout = findViewById(R.id.gridLayout)

        val registrationType = intent.getStringExtra("registrationType") ?: "mentor"

        val icons = arrayOf(
            android.R.drawable.ic_menu_compass, // Learn Web Development
            android.R.drawable.ic_menu_edit, // Develop Graphic Design Skills
            android.R.drawable.ic_menu_camera, // Improve Photography
            android.R.drawable.ic_menu_share // Master Digital Marketing
        )

        val offerTexts = if (registrationType == "mentor") arrayOf(
            "Teach Web Development",
            "Share Graphic Design Skills",
            "Mentor in Photography",
            "Guide in Digital Marketing"
        ) else arrayOf(
            "Learn Web Development",
            "Develop Graphic Design Skills",
            "Improve Photography",
            "Master Digital Marketing"
        )

        for (i in 0..3) {
            val offerItem = OfferItem(this, icons[i], offerTexts[i])
            gridLayout.addView(offerItem)
        }
    }

    // Implement the back button functionality
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
