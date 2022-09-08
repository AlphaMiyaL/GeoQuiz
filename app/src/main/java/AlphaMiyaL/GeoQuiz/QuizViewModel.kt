package AlphaMiyaL.GeoQuiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel(){
    var currentIndex = 0
    private val questionList = listOf(
        Question(R.string.question_iori, true),
        Question(R.string.question_azusa, false),
        Question(R.string.question_ch1, true),
        Question(R.string.question_ch2, false),
        Question(R.string.question_raids, false),
        Question(R.string.question_pvp, true))
    private var questionsAnswered = 0
    private var questionAnsweredList = IntArray(questionList.size)

    val questionListSize: Int
        get() = questionList.size
    val currentQuestionAnswer: Boolean
        get() = questionList[currentIndex].answer
    val currentQuestionText: Int
        get() = questionList[currentIndex].textResId
    val QuestionNotAnswered: Boolean
        get() = questionAnsweredList[currentIndex]==0
    val AllQuestionsAnswered: Boolean
        get() = questionsAnswered == questionList.size

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionList.size
    }
    fun moveToPrev(){
        currentIndex=(currentIndex-1)
        if(currentIndex<0){
            currentIndex=questionList.size-1
        }
    }
    fun recordingAnswer(correct: Boolean){
        questionAnsweredList[currentIndex] = if(correct){
            1
        }
        else{
            2
        }
        questionsAnswered++
    }

    fun questionsCorrect():Int{
        var questions_correct = 0
        for (i in 0 until questionsAnswered) {
            if (questionAnsweredList[i] == 1) {
                questions_correct++
            }
        }
        return questions_correct
    }
}