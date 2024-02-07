package com.example.shemajamebelin8.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shemajamebelin8.data.global.common.Resource
import com.example.shemajamebelin8.domain.usecase.GetCitiesFromApiUseCase
import com.example.shemajamebelin8.presentation.mapper.toPresentation
import com.example.shemajamebelin8.presentation.model.CitiesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getCitiesFromApiUseCase: GetCitiesFromApiUseCase) :
    ViewModel() {
    private val _citiesStateFlow = MutableStateFlow(CitiesState())
    val citiesStateFlow = _citiesStateFlow.asStateFlow()

    init {
        getCities()
    }

    private fun getCities() {
        viewModelScope.launch {
            getCitiesFromApiUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _citiesStateFlow.update { currentState -> currentState.copy(isLoading = resource.loading) }
                    }

                    is Resource.Success -> {
                        _citiesStateFlow.update { currentState -> currentState.copy(cities = resource.response.map { it.toPresentation() }) }

                    }

                    is Resource.Error -> {
                        _citiesStateFlow.update { currentState ->
                            currentState.copy(errorMessage = resource.message)
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}

data class CitiesState(
    val isLoading: Boolean = false,
    val cities: List<CitiesUiModel>? = null,
    val errorMessage: String? = null,
)