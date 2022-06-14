package com.viniciuscoscia.catchacat.domain.entity.imagesearch

import com.viniciuscoscia.catchacat.domain.entity.imagesearch.params.ImageSearchParamName
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.params.MimeTypes
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.params.OrderTypes
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.params.SizeParam

sealed class ImageSearchParam(paramName: ImageSearchParamName) {
    val paramName = paramName.name.lowercase()

    class Size(size: SizeParam) : ImageSearchStringParam(
        paramName = ImageSearchParamName.SIZE,
        value = size.name.lowercase()
    )

    class Order(orderType: OrderTypes) : ImageSearchStringParam(
        paramName = ImageSearchParamName.ORDER,
        value = orderType.name.lowercase()
    )

    class Format(format: String) : ImageSearchStringParam(
        paramName = ImageSearchParamName.FORMAT,
        value = format
    )

    class BreedId(breedId: String) : ImageSearchStringParam(
        paramName = ImageSearchParamName.BREED_ID,
        value = breedId
    )

    class Limit(limit: Int) : ImageSearchIntParam(
        paramName = ImageSearchParamName.LIMIT,
        value = limit
    )

    class Page(page: Int) : ImageSearchIntParam(
        paramName = ImageSearchParamName.PAGE,
        value = page
    )

    class CategoryIds(category: Int) : ImageSearchIntParam(
        paramName = ImageSearchParamName.CATEGORY_IDS,
        value = category
    )

    class MimeType(mimeTypes: List<MimeTypes>) : ImageSearchStringListParam(
        paramName = ImageSearchParamName.MIME_TYPES,
        value = mimeTypes.map {
            it.name.lowercase()
        }
    )

    abstract fun toMap(): Map<String, List<String>>
}

sealed class ImageSearchStringListParam(
    paramName: ImageSearchParamName,
    val value: List<String>
) : ImageSearchParam(paramName) {
    override fun toMap(): Map<String, List<String>> = hashMapOf(Pair(paramName, value))
}

sealed class ImageSearchIntParam(
    paramName: ImageSearchParamName,
    val value: Int
) : ImageSearchParam(paramName) {
    override fun toMap(): Map<String, List<String>> =
        hashMapOf(Pair(paramName, listOf(value.toString())))
}

sealed class ImageSearchStringParam(
    paramName: ImageSearchParamName,
    val value: String
) : ImageSearchParam(paramName) {
    override fun toMap(): Map<String, List<String>> = hashMapOf(Pair(paramName, listOf(value)))
}