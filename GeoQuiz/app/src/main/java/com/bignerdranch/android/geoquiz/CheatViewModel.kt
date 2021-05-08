package com.bignerdranch.android.geoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "CheatViewModel"

// viewModel to retain cheatActivity values during orientation changes
class CheatViewModel: ViewModel() {

    var cheated : Boolean  = false
    var answerText : String? = null

}