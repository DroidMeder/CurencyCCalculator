package kg.simulators_life.curencyccalculator.presentation.fragments.currency_select_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Adapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kg.simulators_life.core.base.BaseFragment
import kg.simulators_life.curencyccalculator.databinding.FragmentSelectctCurrenciesBinding
import kg.simulators_life.curencyccalculator.domain.entity.Valute
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
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

    override fun initView() {
        super.initView()
        viewModel.allCurrencies()
        adapter.setClicker(this)
        adapter.chosenItem(args?.id)
        loadLocalState()
    }

    private fun goNextPage() {
        println("iiiiihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh")
        binding.rec.adapter = adapter
    }

    override fun clickOnItem(valute: Valute) {
        println("ckckc lll")
    }

    private fun loadLocalState() {
        safeFlowGather {
            viewModel.values.collectLatest {
                println("0----------"+it.toString())
                adapter.setList(it)
                goNextPage()
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