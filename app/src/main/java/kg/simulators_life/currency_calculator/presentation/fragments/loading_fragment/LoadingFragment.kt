package kg.simulators_life.currency_calculator.presentation.fragments.loading_fragment

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
import kg.simulators_life.currency_calculator.R
import kg.simulators_life.currency_calculator.databinding.FragmentLoadingBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
        initObserver()
    }

    private fun getDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
        val date = Date()
        return formatter.format(date)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getCurrencies(getDate())
    }

    private fun initObserver() = with(binding) {
        safeFlowGather {
            viewModel.currencyState.collectLatest {
                when (it) {
                    is ApiState.Success -> {
                        try {
                            findNavController().navigate(R.id.calculationFragment)
                        } catch (e: Exception) {
                            println(e.message)
                        }
                    }
                    is ApiState.Failure -> {
                        progressBar.isVisible = false
                        Toast.makeText(requireContext(), it.msg.message, Toast.LENGTH_LONG).show()
                        checkDatabase()
                    }
                    is ApiState.Loading -> {
                        progressBar.isVisible = true
                    }
                }
            }
        }
    }

    private fun checkDatabase() {
        safeFlowGather {
            viewModel.values.take(1).collectLatest {
                if (it) {
                    findNavController().navigate(R.id.calculationFragment)
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