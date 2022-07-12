package org.kvpbldsck.nastolochki.vedro.ui.screens.common.inputs

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.models.UserModel
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.screens.common.Avatar
import org.kvpbldsck.nastolochki.vedro.ui.screens.common.dialogs.userPickerDialog

@Composable
fun UserListInputWithPicker(
    selectedUsers: List<UserModel>,
    availableUsers: List<UserModel>,
    onUserAdded: (user: UserModel) -> Unit,
    onUserRemoved: (index: Int) -> Unit
) {
    val showUserPicker = userPickerDialog(
        possibleUsers = availableUsers,
        onUserSelected = onUserAdded
    )

    val removeUserLabel = stringResource(R.string.remove_participant)

    ListInputWithPicker(
        items = selectedUsers,
        removeActionDescription = { "$removeUserLabel ${it.name}" },
        addActionDescription = stringResource(R.string.add_participant),
        showAddPickerDialog = showUserPicker,
        onItemRemoved = onUserRemoved
    ) { _, participant ->
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(user = participant, size = 40.dp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = participant.name)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserListInputWithPicker_PreviewEmpty() {
    NastolochkiVedroTheme {
        UserListInputWithPicker(
            listOf(),
            listOf(),
            {},
            {})
    }
}

@Composable
@Preview(showBackground = true)
fun UserListInputWithPicker_Preview() {
    val selectedUsers = UserModel.getTestUsers().subList(0, 2)
    val availableUsers = UserModel.getTestUsers().minus(selectedUsers.toSet())

    NastolochkiVedroTheme {
        UserListInputWithPicker(selectedUsers, availableUsers, {}, {})
    }
}