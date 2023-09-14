package com.example.cv

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var fullNameTextView: TextView
    private lateinit var slackUsernameTextView: TextView
    private lateinit var githubHandleTextView: TextView
    private lateinit var bioTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences

    // Define a request code for starting the editing activity
    private val EDIT_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE)

        // Initialize TextViews
        fullNameTextView = findViewById(R.id.fullNameTextView)
        slackUsernameTextView = findViewById(R.id.slackUsernameTextView)
        githubHandleTextView = findViewById(R.id.githubHandleTextView)
        bioTextView = findViewById(R.id.bioTextView)

        // Load CV data from SharedPreferences and display it
        loadCVData()
    }

    private fun loadCVData() {
        val fullName = sharedPreferences.getString("fullName", "Devraj Singh")
        val slackUsername = sharedPreferences.getString("slackUsername", "Devraj_Singh")
        val githubHandle = sharedPreferences.getString("githubHandle","2202devraj")
        val bio = sharedPreferences.getString("bio", "Android Intern")

        fullNameTextView.text = fullName
        slackUsernameTextView.text = slackUsername
        githubHandleTextView.text = githubHandle
        bioTextView.text = bio
    }

    fun editCV(view: View) {
        val intent = Intent(this, EditActivity::class.java)
        startActivityForResult(intent, EDIT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Data has been updated, reload it and refresh the UI
            loadCVData()
        }
    }
}
