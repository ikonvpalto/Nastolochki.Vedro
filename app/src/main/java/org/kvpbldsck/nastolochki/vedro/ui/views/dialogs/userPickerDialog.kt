package org.kvpbldsck.nastolochki.vedro.ui.views.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.window.Dialog
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.models.User
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.views.Avatar

private data class UserPicker(
    val isOpened: Boolean,
    val searchString: String
)

@Composable
fun userPickerDialog(possibleUsers: List<User>, onUserSelected: (User) -> Unit): () -> Unit {

    var state by remember { mutableStateOf(UserPicker(false, "")) }
    val maxHeight = min(
        LocalConfiguration.current.screenHeightDp.dp - 96.dp,
        560.dp
    )

    if (state.isOpened) {
        Dialog(onDismissRequest = { state = state.copy(isOpened = false) }) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(maxHeight = maxHeight, maxWidth = 560.dp)
                    .clipToBounds()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.background,
                elevation = 24.dp
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = state.searchString, 
                        onValueChange = { state = state.copy(searchString = it) },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                        trailingIcon = { Icon(painter = painterResource(R.drawable.icon_search), contentDescription = stringResource(R.string.search)) },
                        placeholder = { Text(stringResource(R.string.search)) })

                    Spacer(modifier = Modifier.height(16.dp ))

                    possibleUsers
                        .filter { it.name.contains(state.searchString, ignoreCase = true) }
                        .forEach { user ->
                            Row(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier
                                        .wrapContentSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Avatar(user = user, size = 40.dp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = user.name)
                                }

                                Button(onClick = { onUserSelected(user) }) {
                                    Text(text = stringResource(R.string.to_invite))
                                }
                            }
                        }

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { state = state.copy(isOpened = false) }) {
                            Text(text = stringResource(R.string.cancel))
                        }
                    }
                }
            }
        }
    }

    return { state = state.copy(isOpened = true) }

}

@Composable
@Preview(showBackground = true)
fun UserPickerDialog_Preview() {
    NastolochkiVedroTheme {
        userPickerDialog(possibleUsers = User.getTestUsers(), {})()
    }
}