package go.deyu.stupidgame2.di.domain.usecase

import go.deyu.stupidgame2.data.api.response.ChatResponse
import go.deyu.stupidgame2.data.model.Message
import go.deyu.stupidgame2.data.repoository.GameRepository

class CreateNewGameUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(prompt: String): Result<Any> {
        return gameRepository.requestImage(prompt = prompt)
    }
}