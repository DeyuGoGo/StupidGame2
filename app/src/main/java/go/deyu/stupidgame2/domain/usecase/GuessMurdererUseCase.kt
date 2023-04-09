package go.deyu.stupidgame2.domain.usecase

import go.deyu.stupidgame2.data.model.GuessResult
import go.deyu.stupidgame2.data.repoository.GameRepository

class GuessMurdererUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(murderer: String): Result<GuessResult> {
        return gameRepository.guessMurderer(murderer)
    }
}