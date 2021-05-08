package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
        "com.bignerdranch.android.geoquiz.answer_is_true"

private val apiLevel : String = "API ${Build.VERSION.SDK_INT}"


//  class to represent CheatActivity
class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var sdkTextView: TextView

    private lateinit var showAnswerButton: Button

    private var answerIsTrue = false

    private val cheatViewModel: CheatViewModel by lazy {
        ViewModelProviders.of(this).get(CheatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        sdkTextView = findViewById(R.id.sdk_version)

        sdkTextView.text = apiLevel

        showAnswerButton.setOnClickListener {
            cheatViewModel.answerText = when {
                answerIsTrue -> "True"
                else -> "False"
            }
            cheatViewModel.cheated = true
            answerTextView.setText(cheatViewModel.answerText)
            setAnswerShownResult(cheatViewModel.cheated)
        }

        answerTextView.setText(cheatViewModel.answerText)
        setAnswerShownResult(cheatViewModel.cheated)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }


    // companion object to extend Intent for extra parameter
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}