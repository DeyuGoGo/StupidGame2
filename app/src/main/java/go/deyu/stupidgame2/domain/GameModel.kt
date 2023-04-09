package go.deyu.stupidgame2.domain

import go.deyu.stupidgame2.data.model.Answer
import go.deyu.stupidgame2.data.model.Clue
import go.deyu.stupidgame2.data.model.GuessResult
import go.deyu.stupidgame2.data.model.Theme
import go.deyu.stupidgame2.data.repoository.GameRepository
import go.deyu.stupidgame2.domain.usecase.AskQuestionUseCase
import go.deyu.stupidgame2.domain.usecase.FetchClueUseCase
import go.deyu.stupidgame2.domain.usecase.FetchThemeUseCase
import go.deyu.stupidgame2.domain.usecase.GuessMurdererUseCase

class GameModel(
    private val gameRepository: GameRepository,
    private val fetchThemeUseCase: FetchThemeUseCase,
    private val askQuestionUseCase: AskQuestionUseCase,
    private val fetchClueUseCase: FetchClueUseCase,
    private val guessMurdererUseCase: GuessMurdererUseCase
) {
    private var theme: Theme? = null
    private var cluesCollected: Int = 0
    private var clues: MutableList<Clue> = ArrayList()

    suspend fun fetchTheme() {
        val result = fetchThemeUseCase()
        if (result.isSuccess) {
            theme = result.getOrNull()
        } else {
            // handle error
        }
    }

    suspend fun askQuestion(question: String): Answer? {
        val result = askQuestionUseCase(question)
        return if (result.isSuccess) {
            result.getOrNull()
        } else {
            // handle error
            null
        }
    }

    suspend fun fetchClue(): Clue? {
        if (cluesCollected >= 3) {
            return null
        }

        val result = fetchClueUseCase()
        return if (result.isSuccess) {
            val clue = result.getOrNull()
            clue?:return null
            clues.add(clue)
            cluesCollected++
            clue
        } else {
            // handle error
            null
        }
    }


    suspend fun guessMurderer(murderer: String): GuessResult? {
        val result = guessMurdererUseCase(murderer)
        return if (result.isSuccess) {
            result.getOrNull()
        } else {
            // handle error
            null
        }
    }
}

data class GameState(
    val theme: Theme? = null,
    val clueCount: Int = 0,
    val clues: List<Clue> = listOf(),
    val guesses: Int = 0
)