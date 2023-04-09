package go.deyu.stupidgame2.domain.usecase

import go.deyu.stupidgame2.data.model.Clue
import go.deyu.stupidgame2.data.repoository.GameRepository

class FetchClueUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): Result<Clue> {
        return gameRepository.fetchClue()
    }
}