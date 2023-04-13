package go.deyu.stupidgame2.domain.usecase

import go.deyu.stupidgame2.data.api.response.ChatResponse
import go.deyu.stupidgame2.data.model.Message
import go.deyu.stupidgame2.data.repoository.GameRepository

class CreateNewGameUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(message: Message): Result<ChatResponse> {
        return gameRepository.requestNewGameData(message = message)
    }
}