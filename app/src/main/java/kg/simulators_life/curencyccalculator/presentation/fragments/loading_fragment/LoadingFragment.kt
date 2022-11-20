package kg.simulators_life.curencyccalculator.presentation.fragments.loading_fragment

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kg.simulators_life.core.ApiState
import kg.simulators_life.core.base.BaseFragment
import kg.simulators_life.curencyccalculator.R
import kg.simulators_life.curencyccalculator.databinding.FragmentLoadingBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoadingFragment : BaseFragment<FragmentLoadingBinding, LoadingViewModel>() {
    override val viewModel: LoadingViewModel by lazy {
        ViewModelProvider(this)[LoadingViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentLoadingBinding {
        return FragmentLoadingBinding.inflate(inflater)
    }

    override fun initView() {
        super.initView()
        viewModel.getCurrencies("02/03/2022")
    }

    override fun initViewModel() {
        super.initViewModel()
        initObserver()
    }

    private fun initObserver() = with(binding){
        safeFlowGather {
            viewModel.currencyState.collectLatest {
                when (it) {
                    is ApiState.Success -> {
                        println("Succes  ")
                        try {
                            findNavController().navigate(R.id.calculationFragment)
                        } catch (e: Exception){
                            println(e.message)
                        }
                    }
                    is ApiState.Failure -> {
                        progressBar.isVisible = false
                        println("----------"+it.msg.message)
                        Toast.makeText(requireContext(), it.msg.message, Toast.LENGTH_LONG).show()
                    }
                    is ApiState.Loading -> {
                        progressBar.isVisible = true
                    }
                }
            }
        }
    }

    private fun safeFlowGather(action: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                action()
            }
        }
    }
}