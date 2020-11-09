import com.magicbytes.breakingbad_interviewtask.network.domain.BreakingBadCharactersNetworkModel
import retrofit2.Call
import retrofit2.http.GET

interface BreakingBadApiService {
    @GET("api/characters")
    fun getBreakingBadCharacters(): Call<List<BreakingBadCharactersNetworkModel>>
}