package com.ahmed.orangechallenge.util

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.ahmed.orangechallenge.R
import com.ahmed.orangechallenge.databinding.LayoutProgressBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProgressUtil @Inject constructor(private val context: Context) {

    private var dialog: AlertDialog? = null
    private lateinit var fromRight: Animation
    private lateinit var binding: LayoutProgressBinding

    init {
        init()
    }

    private fun init() {
        fromRight = AnimationUtils.loadAnimation(context, R.anim.anim_bell)
        binding = LayoutProgressBinding.inflate(LayoutInflater.from(context))

        dialog = AlertDialog.Builder(context).create()
        dialog?.setView(binding.root)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog?.setCancelable(false)

        if (dialog?.window != null) {
            dialog?.window!!
                .setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        }
    }

    fun statusProgress(show: Boolean) {
        if (show) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showProgress() {
        if (dialog != null && dialog?.isShowing == false) {
            dialog?.show()
            binding.ivLogo.startAnimation(fromRight)
        }
    }

    private fun hideProgress() {
        if (dialog?.isShowing == true) {
            dialog?.cancel()
            dialog?.hide()
        }
    }

    fun isLoading(): Boolean {
        return dialog?.isShowing == true
    }
}