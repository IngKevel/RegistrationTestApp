package com.example.mobileapptest.ui.registration

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileapptest.R
import com.example.mobileapptest.domain.models.FieldModel
import java.util.regex.Pattern

/**
 * Created by Kevel on 4/15/2023.
 */

/**
 *When displaying this view the function to get the fields is called.
 */
@Composable
fun RegistrationView(
    viewModel: RegistrationViewModel = hiltViewModel()
) {

    val context: Context = LocalContext.current

    val fieldsList = remember {
        viewModel.fieldsList
    }

    val validFields = remember {
        viewModel.validFields
    }

    val registrationState = remember {
        viewModel.registrationState
    }

    LaunchedEffect(true) {
        viewModel.getRegistration()
    }

    RegistrationContent(
        fields = fieldsList.value,
        validFields = validFields.value,
        registrationState= registrationState.value,
        viewModel = viewModel,
        context = context
    )
}

/**
 * Depending on the value of registrationState different Views will be displayed.
 */

@Composable
fun RegistrationContent(
    fields: List<FieldModel>,
    validFields: Boolean,
    registrationState: RegistrationState,
    viewModel: RegistrationViewModel,
    context: Context
) {
    when (registrationState){
        is RegistrationState.Error -> {
            RegistrationErrorView(details = registrationState.message) {
                viewModel.getRegistration()
            }
        }
        is RegistrationState.Idle -> {
            RegistrationIdleView(
                fields = fields,
                validFields = validFields,
                viewModel = viewModel,
                context = context
            )
        }
        is RegistrationState.Loading -> RegistrationLoadingView()
    }
}

@Composable
fun RegistrationIdleView(
    fields: List<FieldModel>,
    validFields: Boolean,
    viewModel: RegistrationViewModel,
    context: Context
) {
    val message = stringResource(id = R.string.registration_valid_fields)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FieldListView(
            modifier = Modifier
                .fillMaxWidth(),
            fields = fields,
            viewModel = viewModel
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                Toast.makeText(context, "$message $validFields", Toast.LENGTH_LONG).show()
            }) {
            Text(
                text = stringResource(id = R.string.registration_send),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun FieldListView(
    modifier: Modifier,
    fields: List<FieldModel>,
    viewModel: RegistrationViewModel
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(fields){field ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                FieldItemView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    field = field,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun FieldItemView(
    modifier: Modifier,
    field: FieldModel,
    viewModel: RegistrationViewModel
) {

    var text by remember {
        mutableStateOf("")
    }

    var validField by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .testTag(field.name),
            text = field.name,
            style = MaterialTheme.typography.subtitle1
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            isError = !validField,
            value = text,
            onValueChange = { newText ->
                if (field.maxLength == null || newText.length <= field.maxLength) {
                    if (field.regex != null) {
                        val valid = Pattern.compile(
                            field.regex,
                            Pattern.CASE_INSENSITIVE
                        ).matcher(newText).find()

                        validField = valid
                        viewModel.checkFields(validField)
                    }

                    text = newText
                }
            }
        )
    }
}

@Composable
fun RegistrationLoadingView() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.primary)){
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = stringResource(id = R.string.registration_loading),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun RegistrationErrorView(
    details: String,
    action: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.error)){
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = details,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onError
            )
            Button(
                onClick = { action.invoke() }
            ) {
                Text(
                    text = stringResource(id = R.string.registration_retry)
                )
            }
        }
    }
}