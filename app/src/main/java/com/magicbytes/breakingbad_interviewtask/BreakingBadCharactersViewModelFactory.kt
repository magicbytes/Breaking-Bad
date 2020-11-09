import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BreakingBadCharactersViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BreakingBadCharactersViewModel() as T
    }
}