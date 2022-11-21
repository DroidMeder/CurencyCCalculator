package kg.simulators_life.currency_calculator.presentation.fragments.currency_select_fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kg.simulators_life.core.base.BaseFragment
import kg.simulators_life.currency_calculator.databinding.FragmentSelectctCurrenciesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectCurrenciesFragment : BaseFragment<FragmentSelectctCurrenciesBinding,
        SelectCurrenciesViewModel>(), CurrencyClicked {

    private var args: SelectCurrenciesFragmentArgs? = null
    private var adapter = SelectAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = SelectCurrenciesFragmentArgs.fromBundle(requireArguments())
    }

    override val viewModel: SelectCurrenciesViewModel by lazy {
        ViewModelProvider(this)[SelectCurrenciesViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSelectctCurrenciesBinding {
        return FragmentSelectctCurrenciesBinding.inflate(inflater)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.allCurrencies()
    }

    override fun initView() {
        super.initView()
        adapter.setClicker(this)
        adapter.chosenItem(args?.id)
        loadLocalState()
    }

    override fun initListener() {
        super.initListener()
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun clickOnItem(id: String) {
        println("0...........$id")
        if (id.isNotEmpty() && args != null && !args?.id.isNullOrEmpty()) {
            if (args!!.id!!.startsWith("R")) {
                findNavController().navigate(SelectCurrenciesFragmentDirections
                    .goCalculating("R$id"))
            } else {
                findNavController().navigate(SelectCurrenciesFragmentDirections
                    .goCalculating("L$id"))
            }
        }
    }

    private fun loadLocalState() {
        safeFlowGather {
            viewModel.values.collectLatest {
                adapter.setList(it)
                binding.rec.adapter = adapter
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