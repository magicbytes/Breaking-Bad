import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magicbytes.breakingbad_interviewtask.features.charactersList.adapter.BreakingBadCharactersViewHolder
import com.magicbytes.breakingbad_interviewtask.domain.BreakingBadCharacter

class BreakingBadCharactersAdapter(private val onClick: (breakingBadCh: BreakingBadCharacter) -> Unit) :
    RecyclerView.Adapter<BreakingBadCharactersViewHolder>() {

    var breakingBadCharacters: List<BreakingBadCharacter> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingBadCharactersViewHolder {
        return BreakingBadCharactersViewHolder(parent) { onClick(breakingBadCharacters[it]) }
    }

    override fun getItemCount(): Int = breakingBadCharacters.size

    override fun onBindViewHolder(holder: BreakingBadCharactersViewHolder, position: Int) {
        holder.onBind(breakingBadCharacters[position])
    }
}