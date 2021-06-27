package com.tn07.githubapp.common

import android.view.View

/**
 * Created by toannguyen
 * Jun 27, 2021 at 13:10
 */
fun View.fadeInAnimation(
    duration: Int = resources.getInteger(android.R.integer.config_shortAnimTime)
) {
    alpha = 0f
    visibility = View.VISIBLE

    animate()
        .alpha(1f)
        .setDuration(duration.toLong())
        .setListener(null)
}