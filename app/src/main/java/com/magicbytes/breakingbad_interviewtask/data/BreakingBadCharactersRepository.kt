import com.magicbytes.breakingbad_interviewtask.domain.BreakingBadCharacter

class BreakingBadCharactersRepository(private val dataSource: DataSource) {

    suspend fun getAll(): List<BreakingBadCharacter> = dataSource.getAll()

    interface DataSource {
        suspend fun getAll(): List<BreakingBadCharacter>
    }
}