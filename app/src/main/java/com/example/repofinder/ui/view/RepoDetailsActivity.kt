package com.example.repofinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.repofinder.R
import com.example.repofinder.model.Repository

class RepoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        val repo: Repository = intent.getSerializableExtra("REPO") as Repository

        findViewById<TextView>(R.id.repo_name).text = repo.name
        findViewById<TextView>(R.id.repo_description).text = repo.description
        findViewById<TextView>(R.id.repo_link).text = repo.html_url

        findViewById<TextView>(R.id.repo_link).setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("URL", repo.html_url)
            startActivity(intent)
        }
    }
}