package AlphaMiyaL.GeoQuiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

//Main Activity

//Ch1 done
//Challenge 1 done

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private var currentIndex = 0
    private val questionList = listOf(
        Question(R.string.question_iori, true),
        Question(R.string.question_azusa, false),
        Question(R.string.question_ch1, true),
        Question(R.string.question_ch2, false),
        Question(R.string.question_raids, false),
        Question(R.string.question_pvp, true))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
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
        }

        updateQuestion()
    }

    private fun updateQuestion(){
        val questionTextResId = questionList[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionList[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        }
        else{
            R.string.incorrect_toast
        }

        //Set gravity and show will both return null which is why it made into a val
        val Toasty: Toast = Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
        Toasty.setGravity(Gravity.TOP, 0, 0)
        Toasty.show()
    }
}