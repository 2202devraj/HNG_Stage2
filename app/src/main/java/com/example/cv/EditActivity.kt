package com.example.cv

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class EditActivity : AppCompatActivity() {
    private lateinit var fullNameEditText: EditText
    private lateinit var slackUsernameEditText: EditText
    private lateinit var githubHandleEditText: EditText
    private lateinit var bioEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("CVData", MODE_PRIVATE)

        // Initialize EditTexts
        fullNameEditText = findViewById(R.id.fullNameEditText)
        slackUsernameEditText = findViewById(R.id.slackUsernameEditText)
        githubHandleEditText = findViewById(R.id.githubHandleEditText)
        bioEditText = findViewById(R.id.bioEditText)

        // Load existing CV data for editing
        loadCVData()
    }

    private fun loadCVData() {
        val fullName = sharedPreferences.getString("fullName", "Devraj Singh")
        val slackUsername = sharedPreferences.getString("slackUsername", "2022devraj")
        val githubHandle = sharedPreferences.getString("githubHandle", "Devraj_Singh")
        val bio = sharedPreferences.getString("bio", "Android Intern")

        fullNameEditText.setText(fullName)
        slackUsernameEditText.setText(slackUsername)
        githubHandleEditText.setText(githubHandle)
        bioEditText.setText(bio)
    }

    fun saveCV(view: View) {
        val editor = sharedPreferences.edit()
        editor.putString("fullName", fullNameEditText.text.toString())
        editor.putString("slackUsername", slackUsernameEditText.text.toString())
        editor.putString("githubHandle", githubHandleEditText.text.toString())
        editor.putString("bio", bioEditText.text.toString())
        editor.apply()

        // Set the result to indicate that data has been updated
        setResult(Activity.RESULT_OK)

        // Finish the activity
        finish()
    }
}
