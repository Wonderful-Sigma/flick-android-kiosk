package newjeans.bunnies.main.state

data class CreateProductState(
    val isSuccess: Boolean = false,
    val error: String = "",
)
