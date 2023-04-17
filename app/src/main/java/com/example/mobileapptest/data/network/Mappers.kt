package com.example.mobileapptest.data.network

import com.example.mobileapptest.data.network.models.response.RegistrationDTOResponse
import com.example.mobileapptest.domain.models.FieldModel
import com.example.mobileapptest.domain.utils.*

/**
 * Created by Kevel on 4/16/2023.
 */

/**
 * Function extensions are useful for maintaining the open - close principle.
 */

fun RegistrationDTOResponse.toList(): List<FieldModel> {
    val registrationList: MutableList<FieldModel> = mutableListOf()

    this.data.also { data ->
        registrationList
            .add(FieldModel(name = CUSTOMER_LAST_NAME, visible = data.customer_lastname.visible, order = data.customer_lastname.order, maxLength = data.customer_lastname.maxlength, regex = data.customer_lastname.regex))

        registrationList
            .add(FieldModel(name = CUSTOMER_PHONE, visible = data.customer_phone.visible, order = data.customer_phone.order, maxLength = data.customer_phone.maxlength, regex = data.customer_phone.regex))

        registrationList
            .add(FieldModel(name = CUSTOMER_MONTHLY_INCOME, visible = data.customer_monthly_income.visible, order = data.customer_monthly_income.order, maxLength = data.customer_monthly_income.maxlength, regex = data.customer_monthly_income.regex))

        registrationList
            .add(FieldModel(name = BANK_IBAN, visible = data.bank_iban.visible, order = data.bank_iban.order, maxLength = data.bank_iban.maxlength, regex = null))

        registrationList
            .add(FieldModel(name = LANGUAGE, visible = data.language.visible, order = data.language.order, maxLength = data.language.maxlength, regex = null, valuesLanguage = data.language.values))

        registrationList
            .add(FieldModel(name = CUSTOMER_PERSON_CODE, visible = data.customer_personcode.visible, order = data.customer_personcode.order, maxLength = data.customer_personcode.maxlength, regex = data.customer_personcode.regex))

        registrationList
            .add(FieldModel(name = CUSTOMER_EMAIL, visible = data.customer_email.visible, order = data.customer_email.order, maxLength = data.customer_email.maxlength, regex = data.customer_email.regex))

        registrationList
            .add(FieldModel(name = CUSTOMER_FIRST_NAME, visible = data.customer_firstname.visible, order = data.customer_firstname.order, maxLength = data.customer_firstname.maxlength, regex = data.customer_firstname.regex))

        registrationList
            .add(FieldModel(name = CUSTOMER_GENDER, visible = data.customer_gender.visible, order = data.customer_gender.order, maxLength = data.customer_gender.maxlength, regex = null, valuesGender = data.customer_gender.values))

        registrationList
            .add(FieldModel(name = CUSTOMER_BIRTHDAY, visible = data.customer_birthday.visible, order = data.customer_birthday.order, maxLength = data.customer_birthday.maxlength, regex = null))

        registrationList
            .add(FieldModel(name = PEP_STATUS, visible = data.pep_status.visible, order = data.pep_status.order, maxLength = data.pep_status.maxlength, regex = data.pep_status.regex))

        registrationList
            .add(FieldModel(name = AML_CHECK, visible = data.aml_check.visible, order = data.aml_check.order, maxLength = data.aml_check.maxlength, regex = data.aml_check.regex))
    }

    return registrationList
}