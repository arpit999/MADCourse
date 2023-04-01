package com.example.madcourse.domain.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Profile(
    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("employment")
    val employment: Employment,

    @SerializedName("email")
    val email: String,

    @SerializedName("address")
    val address: Address,

    @SerializedName("avatar")
    val picture: String,

    @SerializedName("subscription")
    val subscription: Subscription
) {
    fun getFullName() = "$firstName $lastName"
}

data class Employment(@SerializedName("title") val title: String)
data class Address(
    @SerializedName("street_name") val streetName: String,
    @SerializedName("city") val city: String,
    @SerializedName("street_address") val streetAddress: String,
    @SerializedName("zip_code") val zipCode: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
)

data class Subscription(
    @SerializedName("plan") val plan: String,
    @SerializedName("status") val status: String,
    @SerializedName("term") val term: String,
    @SerializedName("payment_method") val paymentMethod: String,
)