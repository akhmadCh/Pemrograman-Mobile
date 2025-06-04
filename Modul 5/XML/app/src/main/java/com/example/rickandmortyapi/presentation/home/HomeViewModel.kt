import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.Resource
import com.example.rickandmortyapi.data.model.CharacterDto
import com.example.rickandmortyapi.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CharacterRepository): ViewModel() {
    private val _characterState = MutableStateFlow<Resource<List<CharacterDto>>>(Resource.Loading())
    val characterState: StateFlow<Resource<List<CharacterDto>>> = _characterState.asStateFlow()

    init {
        getCharacters()
    }

    fun getCharacters () {
        viewModelScope.launch {
            try {
                _characterState.value = Resource.Loading()
                val result = repository.fetchCharacters()

                // mapping Dto
                val mapped = result.map {
                    CharacterDto(
                        id = it.id,
                        name = it.name,
                        species = it.species,
                        status = it.status,
                        image = it.image
                    )
                }

                _characterState.value = Resource.Success(mapped)
            } catch (e: Exception) {
                _characterState.value = Resource.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun addFavorite (character: CharacterDto) {
        viewModelScope.launch {
            repository.addToFavorite(character)
        }
    }
}