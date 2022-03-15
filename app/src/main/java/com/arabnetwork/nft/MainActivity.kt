package com.arabnetwork.nft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.arabnetwork.nft.R
import com.arabnetwork.nft.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * binding
     */
    private var _binding : ActivityMainBinding? = null
    private val mBinding get() = _binding

    /**
     * variables
     */
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        // bottomNavigationView is the navigation that access this view from our activity
        mBinding?.bottomNavView?.setupWithNavController(navController)
    }

    // for navigating app
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}