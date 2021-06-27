package com.tn07.githubapp.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.tn07.githubapp.R
import com.tn07.githubapp.presentation.browser.GitUserBrowserNavigator
import com.tn07.githubapp.presentation.detail.GitUserDetailFragmentArgs
import com.tn07.githubapp.presentation.detail.GitUserDetailNavigator
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by toannguyen
 * Jun 26, 2021 at 09:14
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), GitUserBrowserNavigator, GitUserDetailNavigator {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmet_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onOpenUserDetail(username: String) {
        val args = GitUserDetailFragmentArgs(username)
        navController.navigate(R.id.action_open_detail_fragment, args.toBundle())
    }

    override fun openDetailInGithub(htmlUrl: String) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(htmlUrl))
            startActivity(browserIntent)
        } catch (exception: ActivityNotFoundException) {
            Toast.makeText(this, "Error: ${exception.localizedMessage}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}