package com.yt.greenarchitectapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yt.greenarchitectapp.R
import com.yt.greenarchitectapp.ui.theme.green
import com.yt.greenarchitectapp.ui.theme.lightorange

import androidx.compose.ui.res.painterResource
import com.yt.greenarchitectapp.commonui.CommonIconButton
import com.yt.greenarchitectapp.ui.theme.orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(green)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonIconButton(icon = R.drawable.back) {

                }
                Text(
                    text = "Мои заметки",
                    modifier = Modifier.weight(1f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                IconButton(onClick = { onEvent(NotesEvent.SortNotes) }) {
                    Image(
                        painter = painterResource(id = R.drawable.sort),
                        contentDescription = "Sort Notes",
                        modifier = Modifier.size(35.dp),
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    state.title.value = ""
                    state.description.value = ""
                    navController.navigate("AddNoteScreen")
                },
                containerColor = orange
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add new note",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.catfon),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            items(state.notes.size) { index ->
                NoteItem(
                    state = state,
                    index = index,
                    onEvent = onEvent
                )
            }

        }

    }
}

@Composable
fun NoteItem(
    state: NoteState,
    index: Int,
    onEvent: (NotesEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFFFF4F9))
            .padding(15.dp)

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = state.notes[index].title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.notes[index].description,
                fontSize = 16.sp,
                color = Color.Black
            )

        }

        IconButton(
            onClick = {
                onEvent(NotesEvent.DeleteNote(state.notes[index]))
            }
        ) {

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Note",
                modifier = Modifier.size(35.dp),
                tint = Color(0xFF173111)
            )

        }

    }
}













