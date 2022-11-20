package kg.simulators_life.curencyccalculator.presentation.fragments.calculation_fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kg.simulators_life.core.base.BaseFragment
import kg.simulators_life.curencyccalculator.databinding.FragmentCalculationBinding

@AndroidEntryPoint
class CalculationFragment : BaseFragment<FragmentCalculationBinding, CalculationViewModel>() {

    private var args: CalculationFragmentArgs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = CalculationFragmentArgs.fromBundle(requireArguments())
    }

    override val viewModel: CalculationViewModel by lazy {
        ViewModelProvider(this)[CalculationViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCalculationBinding {
        return FragmentCalculationBinding.inflate(inflater)
    }

    override fun initListener() = with(binding){
        super.initListener()
        tvBtnLeft.setOnClickListener {
            findNavController().navigate(CalculationFragmentDirections.goSelecting("R01760"))
        }
    }
}