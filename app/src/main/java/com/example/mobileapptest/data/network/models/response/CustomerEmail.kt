package com.example.mobileapptest.data.network.models.response

data class CustomerEmail(
    val auto_approve: Boolean,
    val cl_visible: Boolean,
    val clientzone_check: String,
    val clientzone_editable: Boolean,
    val clientzone_required: Boolean,
    val clientzone_visible: Boolean,
    val condition: List<Any>,
    val condition_type: Int,
    val group: String,
    val hidetitle: Boolean,
    val mapper: String,
    val maxlength: Int,
    val newline: Boolean,
    val order: Int,
    val regex: String,
    val req: Boolean,
    val split: Boolean,
    val step: Int,
    val type: String,
    val visible: Boolean
)