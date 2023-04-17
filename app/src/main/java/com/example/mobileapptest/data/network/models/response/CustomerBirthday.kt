package com.example.mobileapptest.data.network.models.response

data class CustomerBirthday(
    val auto_approve: Boolean,
    val cl_visible: Boolean,
    val clientzone_check: Any,
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
    val req: Boolean,
    val split: Boolean,
    val step: Int,
    val type: String,
    val visible: Boolean
)