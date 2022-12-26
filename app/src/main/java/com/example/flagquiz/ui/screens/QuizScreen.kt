package com.example.flagquiz.ui.screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flagquiz.R
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity
import com.example.flagquiz.data.model.FinishData
import com.example.flagquiz.databinding.FragmentQuizScreenBinding
import com.example.flagquiz.viewmodels.QuizScreenViewModel
import com.example.flagquiz.viewmodels.impl.QuizScreenViewModelImpl
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizScreen : Fragment(R.layout.fragment_quiz_screen) {
    private val binding: FragmentQuizScreenBinding by viewBinding()
    private val viewModel: QuizScreenViewModel by viewModels<QuizScreenViewModelImpl>()
    private val args: QuizScreenArgs by navArgs()
    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getQuiz(args.id)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.currentPositionLiveData.observe(viewLifecycleOwner) {
            binding.levelTextQuiz.text = it
        }
        viewModel.correctAnswersLiveData.observe(viewLifecycleOwner) {
            binding.scoreTxtQuiz.text = it.toString()

        }
        viewModel.questionLiveData.observe(viewLifecycleOwner) {
            binding.imageQuiz.setImageResource(it.image)
            binding.aBtn.text = it.option1
            binding.bBtn.text = it.option2
            binding.cBtn.text = it.option3
            binding.dBtn.text = it.option4
        }

        binding.aBtn.setOnClickListener {
            viewModel.nextQuiz(binding.aBtn.text.toString())
        }
        binding.bBtn.setOnClickListener {
            viewModel.nextQuiz(binding.bBtn.text.toString())
        }
        binding.cBtn.setOnClickListener {
            viewModel.nextQuiz(binding.cBtn.text.toString())
        }
        binding.dBtn.setOnClickListener {
            viewModel.nextQuiz(binding.dBtn.text.toString())
        }


        viewModel.finishLiveData.observe(viewLifecycleOwner) {
            finishDialog(it)
        }
    }


    @SuppressLint("SetTextI18n")
    fun finishDialog(new: FinishData) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val customLayout: View = layoutInflater.inflate(R.layout.dialog_finish_game, null)
        val save = customLayout.findViewById<Button>(R.id.saqlash)
        val score = customLayout.findViewById<TextView>(R.id.score_text)
        val scoreFoiz = customLayout.findViewById<TextView>(R.id.foiz_score)
        val scoreImage = customLayout.findViewById<ImageView>(R.id.image_score)
        val foiz: Int = (new.correctAnswers.toDouble() / new.size.toDouble() * 100).toInt()
        val returnBtn = customLayout.findViewById<MaterialButton>(R.id.returnGame)
        score.text = new.correctAnswers.toString() + " / " + new.size
        scoreFoiz.text = "$foiz%"

        if (foiz > 90) {
            scoreImage.setImageResource(R.drawable.winner)
        }
        if (foiz in 71..89) {
            scoreImage.setImageResource(R.drawable.check)
        }
        if (foiz < 70) {
            scoreImage.setImageResource(R.drawable.sad)
        }

        builder.setView(customLayout)
        if(new.correctAnswers>new.levelsEntity.correct) {
            viewModel.update(
                LevelsEntity(
                    args.id,
                    new.level,
                    new.correctAnswers,
                    new.size,
                    new.userId
                )
            )
        }
        save.setOnClickListener {
            builder.cancel()
            findNavController().navigateUp()
        }
        returnBtn.setOnClickListener {
            viewModel.getQuiz(args.id)
            builder.cancel()
        }
        builder.setCancelable(false)
        builder.show()
    }
}