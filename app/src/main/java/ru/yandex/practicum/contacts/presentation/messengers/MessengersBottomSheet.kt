package ru.yandex.practicum.contacts.presentation.messengers

import androidx.compose.foundation.clickable
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
import ru.yandex.practicum.contacts.data.models.MessagingApp
import ru.yandex.practicum.contacts.presentation.ui.components.CommonBottomSheet

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MessengersBottomSheet(
    selectedApps: Set<MessagingApp>,
    onAppsSelected: (Set<MessagingApp>) -> Unit,
    onDismiss: () -> Unit
) {
    CommonBottomSheet(
        title = stringResource(R.string.filter_by_messaging_app),
        items = MessagingApp.entries,
        selectedItems = selectedApps,
        onItemsSelected = onAppsSelected,
        onDismiss = onDismiss
    ) { messagingApp, isSelected ->
        MessengerOption(
            isSelected = isSelected,
            app = messagingApp,
            onAppSelected = { selected ->
                val newSelection = selectedApps.toMutableSet()
                if (isSelected) {
                    newSelection.remove(selected)
                } else {
                    newSelection.add(selected)
                }
                onAppsSelected(newSelection)
            }
        )
    }
}

@Composable
private fun MessengerOption(
    isSelected: Boolean,
    app: MessagingApp,
    onAppSelected: (MessagingApp) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAppSelected(app) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onAppSelected(app) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = app.name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
