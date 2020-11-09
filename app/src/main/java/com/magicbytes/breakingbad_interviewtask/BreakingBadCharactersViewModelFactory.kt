import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BreakingBadCharactersViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val api = BreakingBadApiNetwork.instance.get()
        val repo = BreakingBadCharactersRepository(BreakingBadCharactersRemoteDataSource(api))
        return BreakingBadCharactersViewModel(repo) as T
    }
}