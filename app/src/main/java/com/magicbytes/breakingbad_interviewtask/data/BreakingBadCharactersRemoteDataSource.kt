import com.magicbytes.breakingbad_interviewtask.domain.BreakingBadCharacter
import com.magicbytes.breakingbad_interviewtask.network.domain.BreakingBadCharactersNetworkModel

class BreakingBadCharactersRemoteDataSource(private val api: BreakingBadApiService) :
    BreakingBadCharactersRepository.DataSource {

    override suspend fun getAll(): List<BreakingBadCharacter> {
        val responseBody = api.getBreakingBadCharacters().execute().body()
        return responseBody?.map { it.toCharacterModel() }.orEmpty()
    }

    private fun BreakingBadCharactersNetworkModel.toCharacterModel(): BreakingBadCharacter {
        return BreakingBadCharacter(name, img, status, nickname, appearance.orEmpty(), occupation)
    }
}