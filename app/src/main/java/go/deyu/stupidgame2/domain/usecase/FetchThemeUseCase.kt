package go.deyu.stupidgame2.domain.usecase

import go.deyu.stupidgame2.data.model.Theme
import go.deyu.stupidgame2.data.repoository.GameRepository

class FetchThemeUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): Result<Theme> {
        return gameRepository.fetchTheme()
    }
}