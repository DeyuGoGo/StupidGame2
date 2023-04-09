package go.deyu.stupidgame2.data.model

data class GuessResult(val isCorrect: Boolean, val message: String)


gameState.theme 是否为 null 来判断是否需要获取主题，并在需要时发送 GameIntent.FetchTheme 意图。

我们使用 GameContent 显示游戏内容。接下来，我们添加了问题输入框、获取线索按钮、猜测输入框和提交猜测按钮。在按钮的 onClick 事件中，我们发送相应的意图（GameIntent.AskQuestion 和 GameIntent.GuessMurderer）以调用 gameViewModel 中的方法。

请注意，为了使这个示例工作，您需要在 GameViewModel 中实现相应的 MVI模式，包括 GameIntent、GameAction 和 GameResult，并根据它们更新 gameState。



