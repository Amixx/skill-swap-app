package com.iliepins.skillswap

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class OfferItem(context: Context, iconResId: Int, offerText: String) : LinearLayout(context) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.offer_item, this, true)

        val offerIcon: ImageView = findViewById(R.id.offer_icon)
        val offerTextView: TextView = findViewById(R.id.offer_text)

        offerIcon.setImageResource(iconResId)
        offerTextView.text = offerText
    }
}
