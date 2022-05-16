package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.quizapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(),View.OnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private var count = 0
    private val userAns = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.ansBtn1.setOnClickListener(this)
        binding.ansBtn2.setOnClickListener(this)
        binding.ansBtn3.setOnClickListener(this)
        binding.ansBtn4.setOnClickListener(this)
        binding.backBtn.setOnClickListener {
            startGame()
            updateUI()
        }
        startGame()
        updateUI()
        return binding.root
    }

    override fun onClick(p0: View?) {
        userAns.add((p0 as Button).text.toString())
        count++
        if(count < questionList.size){
            updateUI()
        }
        else{
            endGame()
            showResult()
        }
    }

    private fun startGame() {
        count = 0
        userAns.clear()
        binding.quesTV.visibility = View.VISIBLE
        binding.quesBtnLayout1.visibility = View.VISIBLE
        binding.quesBtnLayout2.visibility = View.VISIBLE
        binding.scoreTV.visibility = View.GONE
        binding.backBtn.visibility = View.GONE
    }

    private fun endGame() {
        binding.quesTV.visibility = View.GONE
        binding.quesBtnLayout1.visibility = View.GONE
        binding.quesBtnLayout2.visibility = View.GONE
        binding.scoreTV.visibility = View.VISIBLE
        binding.backBtn.visibility = View.VISIBLE
    }

    private fun updateUI() {
        binding.quesTV.text = "${count + 1}. ${questionList.get(count).question}"
        binding.ansBtn1.text = questionList.get(count).answers.get(0)
        binding.ansBtn2.text = questionList.get(count).answers.get(1)
        binding.ansBtn3.text = questionList.get(count).answers.get(2)
        binding.ansBtn4.text = questionList.get(count).answers.get(3)

    }

    private fun showResult() {
        var correct = 0
        userAns.forEachIndexed { index, s ->
            if(s == questionList.get(index).correctAns){
                correct++
            }
        }
        binding.scoreTV.text = "You have scored: $correct"
    }
}

val questionList = listOf(
    QuestionSet("What is android?", listOf("OS","Hardware","Software","Food"),"OS"),
    QuestionSet("What is Kotlin?", listOf("Programming language","Hardware","Software","Food"),"Programming language"),
    QuestionSet("Who introduced Kotlin?", listOf("JetBrains","FCB","Open Source Software","Microsoft"),"JetBrains"),
    QuestionSet("Under which of the following Android is licensed?", listOf("OSS","Sourceforge","Apache/MIT","None of these"),"Apache/MIT"),
    QuestionSet("For which of the following Android is mainly developed?", listOf("Servers","Desktops","Laptops","Mobile devices"),"Mobile devices"),
    QuestionSet("Android is based on which of the following language?", listOf("Java","C++","C","None of these"),"Java"),
    QuestionSet("APK stands for -", listOf("Android Phone Kit","Android Page Kit","Android Package Kit","None of these"),"Android Package Kit"),
    QuestionSet("What is an activity in android?", listOf("android class","android package","A single screen in an application with supporting java code","None of these"),"A single screen in an application with supporting java code"),
    QuestionSet("How can we kill an activity in android?", listOf("Using finish() method","Using finishActivity(int requestCode)","Both (a) and (b)","Neither (a) nor (b)"),"Both (a) and (b)"),
    QuestionSet("ADB stands for -", listOf("Android debug bridge","Android delete bridge","Android destroy bridge","None of these"),"Android debug bridge")
)
data class QuestionSet(
    val question: String,
    val answers: List<String>,
    val correctAns: String
)