package ru.yandex.practicum.contacts.presentation.country

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.yandex.practicum.contacts.R
import ru.yandex.practicum.contacts.data.models.CountryCode
import ru.yandex.practicum.contacts.presentation.ui.components.CommonBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeBottomSheet(
    selectedCodes: Set<CountryCode>,
    onCodesSelected: (Set<CountryCode>) -> Unit,
    onDismiss: () -> Unit
) {
    CommonBottomSheet(
        title = stringResource(R.string.filter_by_country_code),
        items = CountryCode.COMMON_CODES,
        selectedItems = selectedCodes,
        onItemsSelected = { selected ->
            onCodesSelected(selected)
        },
        onDismiss = onDismiss
    ) { countryCode, isSelected ->
        CountryCodeOption(
            isSelected = isSelected,
            countryCode = countryCode,
            onCountryCodeSelected = { selected ->
                val newSelection = selectedCodes.toMutableSet()
                if (isSelected) {
                    newSelection.remove(selected)
                } else {
                    newSelection.add(selected)
                }
                onCodesSelected(newSelection)
            }
        )
    }
}

@Composable
private fun CountryCodeOption(
    isSelected: Boolean,
    countryCode: CountryCode,
    onCountryCodeSelected: (CountryCode) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCountryCodeSelected(countryCode) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onCountryCodeSelected(countryCode) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = countryCode.code,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = countryCode.country,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
