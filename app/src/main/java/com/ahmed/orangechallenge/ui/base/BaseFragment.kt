package com.ahmed.orangechallenge.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.ahmed.orangechallenge.util.Inflate
import com.ahmed.orangechallenge.util.ProgressUtil
import com.ahmed.orangechallenge.util.ToastType
import com.ahmed.orangechallenge.util.showToast
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment(),
    NetworkExtensionsActions {

    private val viewModel by viewModels<BaseViewModel>()

    @Inject
    lateinit var progressUtil: ProgressUtil

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = inflate.invoke(inflater, container, false)
            _binding!!.root.layoutDirection = View.LAYOUT_DIRECTION_LOCALE
        }
        afterCreateView()
        handleClicks()
        return binding.root
    }

    open fun afterCreateView() {

    }

    open fun handleClicks() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleObservers()
        handleBackPressed()
    }

    open fun handleObservers() {

    }

    open fun handleBackPressed() {
//        requireView().findViewById<AppCompatImageView>(R.id.iv_back)?.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onLoad(showLoading: Boolean) {
        progressUtil.statusProgress(showLoading)
    }

    override fun onCommonError(exceptionMsgId: Int) {
        onLoad(false)
        progressUtil.statusProgress(false)
        requireContext().showToast(getString(exceptionMsgId), ToastType.ERROR)
    }

    override fun onShowSuccessToast(msg: String?) {
        requireContext().showToast(msg, ToastType.SUCCESS)
    }

    override fun onFail(msg: String?) {
        requireContext().showToast(msg, ToastType.ERROR)
    }

    protected fun observe(observer: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                observer()
            }
        }
    }
}