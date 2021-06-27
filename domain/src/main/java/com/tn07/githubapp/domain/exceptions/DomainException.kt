package com.tn07.githubapp.domain.exceptions

/**
 * Created by toannguyen
 * Jun 26, 2021 at 08:42
 */
open class DomainException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)