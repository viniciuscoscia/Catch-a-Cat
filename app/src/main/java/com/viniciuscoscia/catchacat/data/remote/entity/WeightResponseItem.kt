package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.Weight
import kotlinx.serialization.Serializable

@Serializable
data class WeightResponseItem(
    val imperial: String?,
    val metric: String?
)

fun WeightResponseItem.toDomain() = Weight(
    imperial = imperial,
    metric = metric
)
