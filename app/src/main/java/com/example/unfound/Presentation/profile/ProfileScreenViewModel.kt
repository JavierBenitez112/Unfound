package com.example.unfound.Presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//class ProfileScreenViewModel(
//    private val userPrefs: DataStoreUserPrefs
//) : ViewModel() {
//
//    private val _state = MutableStateFlow(ProfileScreenState())
//    val state: StateFlow<ProfileScreenState> = _state.asStateFlow()
//
//    val userNameState = userPrefs.getUserName().stateIn(
//        viewModelScope,
//        SharingStarted.WhileSubscribed(5000),
//        null
//    )
//
//    val visitedPlacesState = userPrefs.getVisitedPlaces().stateIn(
//        viewModelScope,
//        SharingStarted.WhileSubscribed(5000),
//        emptySet()
//    )
//
//    fun onEvent(event: ProfileScreenEvent) {
//        when (event) {
//            is ProfileScreenEvent.NameChange -> {
//                _state.update { it.copy(name = event.name) }
//            }
//            ProfileScreenEvent.SaveName -> saveName()
//        }
//    }
//
//    private fun saveName() {
//        viewModelScope.launch {
//            userPrefs.setUserName(_state.value.name)
//        }
//    }
//}