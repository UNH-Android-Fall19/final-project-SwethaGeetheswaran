package com.example.booksharingapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle

class HomeActivity : AppCompatActivity() {

    private lateinit var drawer_layout: DrawerLayout
    private lateinit var drawer_toggle: ActionBarDrawerToggle
    private lateinit var navig_view: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_screen)
        drawer_layout = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer_toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.Open, R.string.Close)

        drawer_layout.addDrawerListener(drawer_toggle)
        drawer_toggle.syncState()

        supportActionBar?.setTitle(R.string.home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navig_view = findViewById(R.id.navig_header) as NavigationView
        navig_view.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val id = item.getItemId()
                when (id) {
                    R.id.profile -> startActivity(user_profile_activity.getLaunchIntent(this@HomeActivity))
                    R.id.signout -> signOut()
                    else -> return true
                }

                return false

            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(drawer_toggle.onOptionsItemSelected(item)) return true

        return super.onOptionsItemSelected(item)
    }
    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    private fun signOut() {
        startActivity(MainActivity.getLaunchIntent(this))
        FirebaseAuth.getInstance().signOut()
    }
}