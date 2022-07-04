package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.Breed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedResponseItem(
    val adaptability: Int?,
    @SerialName("affection_level")
    val affectionLevel: Int?,
    @SerialName("alt_names")
    val altNames: String?,
    @SerialName("cat_friendly")
    val catFriendly: Int?,
    @SerialName("cfa_url")
    val cfaUrl: String?,
    @SerialName("child_friendly")
    val childFriendly: Int?,
    @SerialName("country_code")
    val countryCode: String?,
    @SerialName("country_codes")
    val countryCodes: String?,
    val description: String?,
    @SerialName("dog_friendly")
    val dogFriendly: Int?,
    @SerialName("energy_level")
    val energyLevel: Int?,
    val experimental: Int?,
    val grooming: Int?,
    val hairless: Int?,
    @SerialName("health_issues")
    val healthIssues: Int?,
    val hypoallergenic: Int?,
    val id: String?,
    val image: ImageResponseItem?,
    val indoor: Int?,
    val intelligence: Int?,
    val lap: Int?,
    @SerialName("life_span")
    val lifeSpan: String?,
    val name: String?,
    val natural: Int?,
    val origin: String?,
    val rare: Int?,
    @SerialName("reference_image_id")
    val referenceImageId: String?,
    val rex: Int?,
    @SerialName("shedding_level")
    val sheddingLevel: Int?,
    @SerialName("short_legs")
    val shortLegs: Int?,
    @SerialName("social_needs")
    val socialNeeds: Int?,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int?,
    @SerialName("suppressed_tail")
    val suppressedTail: Int?,
    val temperament: String?,
    @SerialName("vcahospitals_url")
    val vcaHospitalsUrl: String?,
    @SerialName("vetstreet_url")
    val vetStreetUrl: String?,
    val vocalisation: Int?,
    val weight: WeightResponseItem?,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String?
)

fun BreedResponseItem.toDomain(): Breed? {
    if (id == null || name == null) return null

    return Breed(
        id = id,
        name = name,
        adaptability = adaptability,
        affectionLevel = affectionLevel,
        altNames = altNames,
        catFriendly = catFriendly,
        cfaUrl = cfaUrl,
        childFriendly = childFriendly,
        countryCode = countryCode,
        countryCodes = countryCodes,
        description = description,
        dogFriendly = dogFriendly,
        energyLevel = energyLevel,
        experimental = experimental,
        grooming = grooming,
        hairless = hairless,
        healthIssues = healthIssues,
        hypoallergenic = hypoallergenic,
        image = image?.toDomain(),
        indoor = indoor,
        intelligence = intelligence,
        lap = lap,
        lifeSpan = lifeSpan,
        natural = natural,
        origin = origin,
        rare = rare,
        referenceImageId = referenceImageId,
        rex = rex,
        sheddingLevel = sheddingLevel,
        shortLegs = shortLegs,
        socialNeeds = socialNeeds,
        strangerFriendly = strangerFriendly,
        suppressedTail = suppressedTail,
        temperament = temperament,
        vcaHospitalsUrl = vcaHospitalsUrl,
        vetStreetUrl = vetStreetUrl,
        vocalisation = vocalisation,
        weight = weight?.toDomain(),
        wikipediaUrl = wikipediaUrl
    )
}

fun List<BreedResponseItem>.toDomain() = map { it.toDomain() }