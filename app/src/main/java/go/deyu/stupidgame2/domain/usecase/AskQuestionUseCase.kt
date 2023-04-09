
package go.deyu.stupidgame2.domain.usecase

import go.deyu.stupidgame2.data.model.Answer
import go.deyu.stupidgame2.data.repoository.GameRepository

class AskQuestionUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(question: String): Result<Answer> {
        return gameRepository.askQuestion(question)
    }
}