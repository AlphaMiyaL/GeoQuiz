package AlphaMiyaL.GeoQuiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlin.math.round

//Main Activity

//Ch1-3 done
//Prev Button Challenge done
//Button to ImageButton Challenge done
//Preventing Repeat Answers Challenge done
//Graded Quiz Challenge done


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private var currentIndex = 0
    private val questionList = listOf(
        Question(R.string.question_iori, true),
        Question(R.string.question_azusa, false),
        Question(R.string.question_ch1, true),
        Question(R.string.question_ch2, false),
        Question(R.string.question_raids, false),
        Question(R.string.question_pvp, true))

    private var questionsAnswered = 0
    private var questionAnsweredList = IntArray(questionList.size)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d(TAG, "OnCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

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
            currentIndex=(currentIndex+1)%questionList.size
            updateQuestion()
            buttonEnablerDisabler()
        }
        prevButton.setOnClickListener { view: View ->
            currentIndex=(currentIndex-1)
            if(currentIndex<0){
                currentIndex=questionList.size-1
            }
            updateQuestion()
            buttonEnablerDisabler()
        }

        updateQuestion()
    }

//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart() called")
//    }
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume() called")
//    }
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause() called")
//    }
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop() called")
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy() called")
//    }

    private fun updateQuestion(){
        val questionTextResId = questionList[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun buttonEnablerDisabler(){
        //re-enable true false buttons if not answered question
        if(questionAnsweredList[currentIndex]==0){
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
        if(questionsAnswered == questionList.size) {
            var questions_correct = 0
            for (i in 0 until questionsAnswered) {
                if (questionAnsweredList[i] == 1) {
                    questions_correct++
                }
            }
            //displays toast of percentage done
            val messageResId = getString(R.string.questions_correct) + round((questions_correct.toDouble() / questionList.size) * 100) + getString(R.string.percent)
            val toasty: Toast = Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
            toasty.setGravity(Gravity.TOP, 0, 0)
            toasty.show()
        }
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionList[currentIndex].answer
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
        questionAnsweredList[currentIndex] = if(correct){
            1
        }
        else{
            2
        }
        questionsAnswered++
        quizFinished()

        //Set gravity and show will both return null which is why it made into a val
//        val Toasty: Toast = Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
//        Toasty.setGravity(Gravity.TOP, 0, 0)
//        Toasty.show()
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