
package go.deyu.stupidgame2.domain.usecase

import go.deyu.stupidgame2.data.api.response.ChatResponse
import go.deyu.stupidgame2.data.repoository.GameRepository

class CreateNewGameUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): Result<ChatResponse> {
        return gameRepository.requestNewGameData()
    }
}