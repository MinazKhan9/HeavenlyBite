package com.example.heavenlybite

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.heavenlybite.databinding.ActivityDetailsBinding
import com.example.heavenlybite.model.CartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var auth: FirebaseAuth
    private var foodName: String? = null
    private var foodPrice: String? = null
    private var foodDescriptions: String? = null
    private var foodImage: String? = null
    private var foodIngredients: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        foodName = intent.getStringExtra("MenuItemName")
        foodPrice = intent.getStringExtra("MenuItemPrice")
        foodDescriptions = intent.getStringExtra("MenuItemDescription")
        foodImage = intent.getStringExtra("MenuItemImage")
        foodIngredients = intent.getStringExtra("MenuItemIngredients")

        with(binding) {
            detailFoodName.text = foodName
            detailDescription.text = foodDescriptions
            detailIngredients.text = foodIngredients
            Glide.with(this@DetailsActivity).load(Uri.parse(foodImage)).into(detailFoodImage)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
        binding.addToCartButton.setOnClickListener {
            addItemToCart()
        }
    }

    private fun addItemToCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auth.currentUser?.uid ?: ""

        val cartItem = CartItems(
            foodName.toString(),
            foodPrice.toString(),
            foodDescriptions.toString(),
            foodImage.toString(),
            foodIngredients.toString(),
            1
        )
        database.child("user").child(userId).child("CartItems").push().setValue(cartItem)
            .addOnSuccessListener {
                Toast.makeText(this, "Items added into cart successfully", Toast.LENGTH_SHORT)
                    .show()
            }.addOnFailureListener {
                Toast.makeText(this, "Items not added", Toast.LENGTH_SHORT).show()
            }

    }
}