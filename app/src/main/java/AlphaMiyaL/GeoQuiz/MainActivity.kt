package AlphaMiyaL.GeoQuiz

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlin.math.round

//Main Activity

//Ch1-4 done
//Prev Button Challenge done
//Button to ImageButton Challenge done
//Preventing Repeat Answers Challenge done
//Graded Quiz Challenge done


private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?:0
        quizViewModel.currentIndex=currentIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener{view ->
            checkAnswer(true)
        }
        falseButton.setOnClickListener{view: View ->
            checkAnswer(false)
        }
        nextButton.setOnClickListener{view: View ->
            quizViewModel.moveToNext()
            updateQuestion()
            buttonEnablerDisabler()
        }
        prevButton.setOnClickListener { view: View ->
           quizViewModel.moveToPrev()
            updateQuestion()
            buttonEnablerDisabler()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun buttonEnablerDisabler(){
        //re-enable true false buttons if not answered question
        if(quizViewModel.QuestionNotAnswered){
            enableButton(trueButton)
            enableButton(falseButton)
        }
        else{
            disableButton(trueButton)
            disableButton(falseButton)
        }
    }

    private fun quizFinished(){
        //check if all questions were answered
        if(quizViewModel.AllQuestionsAnswered) {
            var questionsCorrect = quizViewModel.questionsCorrect()
            //displays toast of percentage done
            val messageResId = getString(R.string.questions_correct) + round((questionsCorrect.toDouble() / quizViewModel.questionListSize) * 100) + getString(R.string.percent)
            val handler = Handler()
            handler.postDelayed(Runnable {
                val toasty: Toast = Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
                toasty.setGravity(Gravity.TOP, 0, 0)
                toasty.show()
            }, 2000)
        }
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val correct = userAnswer == correctAnswer
        val messageResId = if (correct){
            R.string.correct_toast
        }
        else{
            R.string.incorrect_toast
        }

        //disable button after one answer for no repeats
        disableButton(trueButton)
        disableButton(falseButton)
        quizViewModel.recordingAnswer(correct)
        quizFinished()

        //Set gravity and show will both return null which is why it made into a val
        val Toasty: Toast = Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
        Toasty.setGravity(Gravity.TOP, 0, 0)
        Toasty.show()
    }

    private fun disableButton(button: Button){
         button.isEnabled = false
         button.isClickable = false
    }

    private fun enableButton(button: Button){
        button.isEnabled = true
        button.isClickable = true
    }


}