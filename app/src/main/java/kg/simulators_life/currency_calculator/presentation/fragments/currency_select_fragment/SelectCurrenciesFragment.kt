package kg.simulators_life.currency_calculator.presentation.fragments.currency_select_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kg.simulators_life.core.base.BaseFragment
import kg.simulators_life.currency_calculator.databinding.FragmentSelectCurrenciesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectCurrenciesFragment : BaseFragment<FragmentSelectCurrenciesBinding,
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

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSelectCurrenciesBinding {
        return FragmentSelectCurrenciesBinding.inflate(inflater)
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

    private fun hideKeyboard() {
        requireActivity().let {
            val imm = ContextCompat.getSystemService(it, InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    override fun initListener() {
        super.initListener()
        hideKeyboard()

        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun clickOnItem(id: String) {
        if (id.isNotEmpty() && args != null && !args?.id.isNullOrEmpty() &&
            !args?.idState.isNullOrEmpty() && !args?.inputField.isNullOrEmpty()) {
            if (args!!.id!!.startsWith("R")) {
                findNavController().navigate(SelectCurrenciesFragmentDirections
                    .goCalculating("R$id", args!!.idState!!, args!!.inputField!!))
            } else {
                findNavController().navigate(SelectCurrenciesFragmentDirections
                    .goCalculating("L$id", args!!.idState!!, args!!.inputField!!))
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