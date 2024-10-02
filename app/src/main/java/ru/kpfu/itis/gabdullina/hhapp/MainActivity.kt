package ru.kpfu.itis.gabdullina.hhapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kpfu.itis.gabdullina.hh.favourites.ui.VacancyFragment
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.main.MainFragment


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentManager = supportFragmentManager

        if(fragmentManager.findFragmentById(R.id.container) == null) {
            fragmentManager
                .beginTransaction()
                .add(R.id.container, MainFragment())
                .commit()
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_search -> {
                    loadFragment(MainFragment())
                    true
                }
                R.id.ic_favorite -> {
                    loadFragment(VacancyFragment())
                    true
                }
                else -> {
                    loadFragment(PlugFragment())
                    true
                }
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}
