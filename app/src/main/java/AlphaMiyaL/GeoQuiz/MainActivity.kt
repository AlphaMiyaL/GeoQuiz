package AlphaMiyaL.GeoQuiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

//Ch1 done
//Challenge 1 done

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        //Have to make separate functions due to setGravity and show both returning void
        fun trueButtonPress(trueBut: Toast=Toast.makeText(
            this,
            R.string.correct_toast,
            Toast.LENGTH_SHORT)){
            trueBut.setGravity(Gravity.TOP, 0, 0)
            trueBut.show()
        }

        fun falseButtonPress(falseBut: Toast=Toast.makeText(
        this,
        R.string.incorrect_toast,
        Toast.LENGTH_SHORT)){
            falseBut.setGravity(Gravity.TOP, 0, 0)
            falseBut.show()
        }

        trueButton.setOnClickListener{view ->
//            Toast.makeText(
//                this,
//                R.string.correct_toast,
//                Toast.LENGTH_SHORT)
//                .setGravity(Gravity.TOP, 0, 0)
            trueButtonPress()
        }
        falseButton.setOnClickListener{view: View ->
//            Toast.makeText(
//                this,
//                R.string.incorrect_toast,
//                Toast.LENGTH_SHORT)
//                .setGravity(Gravity.TOP, 0, 0)
            falseButtonPress()
        }
    }
}