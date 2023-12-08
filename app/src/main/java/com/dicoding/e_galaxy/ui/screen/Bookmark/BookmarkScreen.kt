package com.example.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.e_galaxy.R
import com.dicoding.e_galaxy.data.Injection
import com.dicoding.e_galaxy.data.model.Galaxy
import com.dicoding.e_galaxy.data.model.ViewModelFactory
import com.dicoding.e_galaxy.ui.item.EmptyList
import com.dicoding.e_galaxy.ui.screen.Bookmark.BookmarkViewModel
import com.dicoding.e_galaxy.ui.screen.Home.ListGalaxy
import com.dicoding.e_galaxy.ui.utils.UiState

@Composable
fun BookmarkScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getBookmarkNews()
            }
            is UiState.Success -> {
                BookmarkInformation(
                    listGalaxy = uiState.data,
                    navigateToDetail = navigateToDetail,
                    onBookmarkIconClicked = {id, newState ->
                        viewModel.updateGalaxy(id, newState)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun BookmarkInformation(
    listGalaxy : List<Galaxy>,
    navigateToDetail: (Int) -> Unit,
    onBookmarkIconClicked: (id:Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        if (listGalaxy.isNotEmpty()){
            ListGalaxy(
                listGalaxy = listGalaxy,
                onBookmarkIconClicked = onBookmarkIconClicked,
                contentPaddingTop = 16.dp,
                navigateToDetail = navigateToDetail
            )
        }else{
           EmptyList(Warning = stringResource(R.string.empty_bookmark) )
        }
    }
}