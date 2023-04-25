package go.deyu.stupidgame2.di.domain.usecase

import go.deyu.stupidgame2.data.api.response.ChatResponse
import go.deyu.stupidgame2.data.model.Message
import go.deyu.stupidgame2.data.repoository.GameRepository

class RequestChatUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(message: List<Message>): Result<ChatResponse> {
        return gameRepository.requestChat(messages = message)
    }
}