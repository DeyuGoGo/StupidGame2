package go.deyu.stupidgame2.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import go.deyu.stupidgame2.R
import go.deyu.stupidgame2.data.api.ChatApi
import go.deyu.stupidgame2.data.repoository.GameRepository
import go.deyu.stupidgame2.domain.GameModel
import go.deyu.stupidgame2.domain.usecase.RequestChatUseCase
import go.deyu.stupidgame2.domain.usecase.RequestImageUseCase
import go.deyu.stupidgame2.domain.usecase.RequestNewGameUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(@ApplicationContext context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }


    @Provides
    @Singleton
    fun provideGameRepository(gameApi: ChatApi): GameRepository {
        return GameRepository(gameApi)
    }

    @Provides
    fun provideAskQuestionUseCase(gameRepository: GameRepository): RequestChatUseCase {
        return RequestChatUseCase(gameRepository)
    }
    @Provides
    fun provideImageUseCase(gameRepository: GameRepository): RequestImageUseCase {
        return RequestImageUseCase(gameRepository)
    }

    @Provides
    fun provideRequestNewGameUseCase(): RequestNewGameUseCase {
        return RequestNewGameUseCase()
    }

    @Provides
    @Singleton
    fun provideGameModel(
        requestChatUseCase: RequestChatUseCase,
        requestImageUseCase: RequestImageUseCase,
        requestNewGameUseCase: RequestNewGameUseCase
    ): GameModel {
        return GameModel(
            requestChatUseCase,
            requestImageUseCase,
            requestNewGameUseCase
        )
    }
}