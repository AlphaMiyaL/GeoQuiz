package AlphaMiyaL.GeoQuiz

import androidx.annotation.StringRes //helps code inspector(Lint) verify at compile
                                    // usage of constructor provides valid string resource ID

data class Question(@StringRes val textResId: Int, val answer: Boolean) {
}