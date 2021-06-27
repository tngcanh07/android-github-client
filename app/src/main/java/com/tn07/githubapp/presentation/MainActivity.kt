package com.tn07.githubapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.tn07.githubapp.R
import com.tn07.githubapp.presentation.browser.GitUserBrowserNavigator
import com.tn07.githubapp.presentation.detail.GitUserDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by toannguyen
 * Jun 26, 2021 at 09:14
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), GitUserBrowserNavigator {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_browser,
                R.id.navigation_detail
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onOpenUserDetail(username: String) {
        val args = GitUserDetailFragmentArgs(username)
        navController.navigate(R.id.action_open_detail_fragment, args.toBundle())
    }
}