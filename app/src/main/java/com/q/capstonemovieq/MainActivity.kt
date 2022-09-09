package com.q.capstonemovieq

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.q.capstonemovieq.databinding.ActivityMainBinding
import com.q.capstonemovieq.favorite.FavoriteActivity
import com.q.capstonemovieq.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportFragmentManager.backStackEntryCount == 0 && savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentActivity, HomeFragment.newInstance())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favoriteMenu -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
                true
            }
            else -> true
        }
    }
}