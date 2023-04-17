package com.example.mobileapptest.data.network.models.response

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("aml-check")
    val aml_check: AmlCheck,
    @SerializedName("bank-iban")
    val bank_iban: BankIban,
    @SerializedName("customer-birthday")
    val customer_birthday: CustomerBirthday,
    @SerializedName("customer-email")
    val customer_email: CustomerEmail,
    @SerializedName("customer-firstname")
    val customer_firstname: CustomerFirstname,
    @SerializedName("customer-gender")
    val customer_gender: CustomerGender,
    @SerializedName("customer-lastname")
    val customer_lastname: CustomerLastname,
    @SerializedName("customer-monthly-income")
    val customer_monthly_income: CustomerMonthlyIncome,
    @SerializedName("customer-personcode")
    val customer_personcode: CustomerPersoncode,
    @SerializedName("customer-phone")
    val customer_phone: CustomerPhone,
    @SerializedName("language")
    val language: Language,
    @SerializedName("pep-status")
    val pep_status: PepStatus
)