package com.tn07.githubapp.domain.exceptions

/**
 * Created by toannguyen
 * Jun 27, 2021 at 12:34
 */
class ApiException(val httpCode: Int, cause: Throwable? = null) : DomainException(cause = cause) {
    override fun toString(): String {
        return "HTTP Failed. Error $httpCode \nCause: ${cause?.localizedMessage}"
    }
}