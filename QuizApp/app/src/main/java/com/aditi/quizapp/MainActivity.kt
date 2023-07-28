package com.aditi.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aditi.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf("What is an activity in Android?",
        "What is the time limit of broadcast receiver in android?",
        "What is singleton class in android?")

    private val options = arrayOf(arrayOf("Activity performs the actions on the screen","Screen UI "," None of the above"),
        arrayOf("15 sec", "10 sec", "5 sec"),
        arrayOf("A class that can create only one object","Java class", " Manifest file"))

    private val correctAns = arrayOf(0,1,0)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()
        binding.option1.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3.setOnClickListener {
            checkAnswer(2)
        }
        binding.btnRestart.setOnClickListener {
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex: Int) {
        when(buttonIndex) {
            0 -> binding.option1.setBackgroundColor(Color.GREEN)
            1 -> binding.option2.setBackgroundColor(Color.GREEN)
            2 -> binding.option3.setBackgroundColor(Color.GREEN)
        }
    }

        private fun wrongButtonColors(buttonIndex: Int) {
            when (buttonIndex) {
                0 -> binding.option1.setBackgroundColor(Color.RED)
                1 -> binding.option2.setBackgroundColor(Color.RED)
                2 -> binding.option3.setBackgroundColor(Color.RED)
            }
        }

        private fun resetButtonColors() {
            binding.option1.setBackgroundColor(Color.rgb(50,59,96))
            binding.option2.setBackgroundColor(Color.rgb(50,59,96))
            binding.option3.setBackgroundColor(Color.rgb(50,59,96))
        }

        private fun showResults(){
            Toast.makeText(this,"Your score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
            binding.btnRestart.isEnabled = true
        }

        private fun displayQuestion() {
            binding.questionText.text = questions[currentQuestionIndex]
            binding.option1.text = options[currentQuestionIndex][0]
            binding.option2.text = options[currentQuestionIndex][1]
            binding.option3.text = options[currentQuestionIndex][2]
            resetButtonColors()
        }

        private fun checkAnswer(selectedAnswerIndex:Int) {
            val correctAnswerIndex = correctAns[currentQuestionIndex]

            if(selectedAnswerIndex == correctAnswerIndex) {
                score++
                correctButtonColors(selectedAnswerIndex)
            } else {
                wrongButtonColors(selectedAnswerIndex)
                correctButtonColors(correctAnswerIndex)
            }
            if(currentQuestionIndex < questions.size -1) {
                currentQuestionIndex++
                binding.questionText.postDelayed({displayQuestion()}, 1000)
            } else {
                showResults()
            }
        }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.btnRestart.isEnabled = false
    }
}