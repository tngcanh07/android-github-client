package com.tn07.githubapp.domain.entities

/**
 * Created by toannguyen
 * Jun 25, 2021 at 22:01
 */
interface Pageable<T> {
    val totalCount: Int
    val page: Int
    val pageSize: Int
    val items: List<T>
}