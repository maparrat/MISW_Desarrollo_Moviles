

//class MainActivity : AppCompatActivity() {
//    private lateinit var navController: NavController
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
//}





package com.example.vinilos.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.vinilos.R
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.vinilos.R.*


class ListMainAlbumActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            // Get the navigation host fragment from this Activity
            val navHostFragment = supportFragmentManager
                .findFragmentById(id.nav_host_fragment) as NavHostFragment
            // Instantiate the navController using the NavHostFragment
            navController = navHostFragment.navController
            // Make sure actions in the ActionBar get propagated to the NavController
            Log.d("act", navController.toString())
            setSupportActionBar(findViewById(id.my_toolbar))
            setupActionBarWithNavController(navController)


        val buttonView = findViewById<Button>(id.button_detail)

        buttonView.setOnClickListener {
            val intent = Intent(this, DetailMainAlbumActivity::class.java)
            startActivity(intent)
        }

        }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
