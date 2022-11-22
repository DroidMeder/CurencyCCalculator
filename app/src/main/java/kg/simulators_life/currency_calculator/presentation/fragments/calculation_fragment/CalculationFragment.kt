package kg.simulators_life.currency_calculator.presentation.fragments.calculation_fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kg.simulators_life.core.base.BaseFragment
import kg.simulators_life.currency_calculator.databinding.FragmentCalculationBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalculationFragment : BaseFragment<FragmentCalculationBinding, CalculationViewModel>() {

    private var args: CalculationFragmentArgs? = null
    private var idA: String = ""
    private var idB: String = ""
    private var swipeRight = false
    private var swipeLeft = false

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

    override fun initView() {
        super.initView()
        println("")

        if (args != null) {
            val temp = args!!.id
            if (temp != null) {
                if (temp.startsWith("R")) {
                    getSavedInstance(temp, "R")
                } else {
                    getSavedInstance(temp, "")
                }
            } else {
                println("tttt")
                setLeftValues()
                setRightValues()
            }
        } else {
            setLeftValues()
            setRightValues()
        }
    }

    private fun getSavedInstance(value: String, id: String) = with(binding) {
        //swipeRight = true
        etLeft.isFocusable = true
        etLeft.requestFocus()
        etRight.clearFocus()
        val temp = value.drop(1)
        println("--$temp----${args!!.idState!!}----------${args?.inputField.toString()}---------------")
        if (id == "R") {
            println(".R--------")
            safeFlowGather {
                println("1")
                //swipeLeft = true

                setRightValues(temp, true)
                println("2 -----")
                setLeftValues(args!!.idState!!, true)
                println("3")

                /*viewModel.getCalculation(args!!.idState!!, temp,
                    args?.inputField.toString(), "*")*/
                println("4")

                println("6")

                //getResult()
                //initWatchers()
                println("7")

            }

        } else {
            println("L------")
            safeFlowGather {

                //swipeRight = true

                setLeftValues(value.drop(1), true)
                setRightValues(args!!.idState!!, true)
                /*viewModel.getCalculation(value.drop(1), args!!.idState!!,
                    args?.inputField.toString(), "*")*/
                etLeft.setText(args?.inputField.toString())
                // viewModel.getCalculation(idA, idB, etLeft.text.toString(), "*")
                //getResult()
                //initWatchers()
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

    private fun setRightValues(drop: String = "R01235", b: Boolean = false) {
        println("set 1  -----------$drop-----------")
        viewModel.getCurrency(drop, "R")
        println("set 2  -----------$drop-----------")
        safeFlowGather {
            viewModel.getRightEntity.collectLatest {
                println("set 3  -----------${it.toString()}-----------")
                binding.tvRightCurrency.text = it.charCode
                idB = it.iD.toString()

                if (b) {
                    println("nnnnnnnnnn1")
                    binding.etLeft.setText(args?.inputField.toString())
                }
            }

        }
    }

    private fun setLeftValues(drop: String = "R01370", b: Boolean = false) {
        println("set 01  -----------$drop-----------")

        viewModel.getCurrency(drop, "L")
        println("set 02  -----------$drop-----------")
        safeFlowGather {
            viewModel.getLeftEntity.collectLatest {
                println("set 03  -----------${it.toString()}-----------")
                binding.tvLeftCurrency.text = it.charCode
                idA = it.iD.toString()

                if (b) {
                    println("nnnnnnnnnn1")
                    binding.etLeft.setText(args?.inputField.toString())
                }
            }

        }

    }

    override fun initListener() = with(binding) {
        super.initListener()
        tvBtnLeft.setOnClickListener {
            findNavController().navigate(
                CalculationFragmentDirections.goSelecting("L$idA", idB, etLeft.text.toString())
            )
        }

        tvBtnRight.setOnClickListener {
            findNavController().navigate(
                CalculationFragmentDirections.goSelecting("R$idB", idA, etLeft.text.toString())
            )
        }

        etRight.setOnClickListener {
            etLeft.isFocusable = false
            it.isFocusableInTouchMode = true
            it.isFocusable = true
            it.requestFocus()
            etRight.setSelection(etRight.text.length)
            etLeft.clearFocus()
        }

        etLeft.setOnClickListener {
            etRight.isFocusable = false
            it.isFocusableInTouchMode = true
            it.isFocusable = true
            it.requestFocus()
            etLeft.setSelection(etLeft.text.length)
            etRight.clearFocus()
        }

        vRight.setOnClickListener {
            with(binding) {
                swipeRight = true
                removeFocusFromFields()
                etRight.setText(etLeft.text.toString())
            }
        }

        vLeft.setOnClickListener {
            with(binding) {
                swipeLeft = true
                removeFocusFromFields()
                etLeft.setText(etRight.text.toString())
            }
        }

        initWatchers()
    }

    private fun removeFocusFromFields() = with(binding) {
        etRight.isFocusable = false
        etLeft.isFocusable = false
    }

    private fun initWatchers() = with(binding) {
        var clickedRight = false
        var clickedLeft = false
        etRight.setOnFocusChangeListener { _, hasFocus ->
            clickedRight = hasFocus
        }

        etLeft.setOnFocusChangeListener { _, hasFocus ->
            clickedLeft = hasFocus
        }


        etRight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                when (s?.length) {
                    in 0..10 -> {
                        etRight.textSize = 28.0f
                    }
                    in 11..15 -> {
                        etRight.textSize = 18f
                    }
                    in 16..20 -> {
                        etRight.textSize = 13f
                    }
                    else -> {
                        etRight.textSize = 11f
                    }
                }
                println("wat   1----$clickedRight---------$swipeRight--------")
                if (clickedRight || swipeRight) {
                    println("wat   .2-----")
                    viewModel.getCalculation(idA, idB, etRight.text.toString(), "/")
                    swipeRight = false
                    getResult(1)
                }
            }
        })

        etLeft.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                when (s?.length) {
                    in 0..10 -> {
                        etLeft.textSize = 28.0f
                    }
                    in 11..15 -> {
                        etLeft.textSize = 18f
                    }
                    in 16..20 -> {
                        etLeft.textSize = 13f
                    }
                    else -> {
                        etLeft.textSize = 11f
                    }
                }
                println("wat   3---$clickedLeft-------$swipeLeft----")

                if (clickedLeft || swipeLeft) {
                    println("wat   .4-----")

                    if (idA.isNotEmpty() && idB.isNotEmpty()){
                    viewModel.getCalculation(idA, idB, etLeft.text.toString(), "*")
                    swipeLeft = false
                    getResult()
                    }
                }
            }
        })
    }

    private fun getResult(i: Int = 0) = with(binding) {
        safeFlowGather {
            viewModel.getResult.take(1).collectLatest {
                if (i == 0) {
                    etRight.setText(formatStr(it))
                } else {
                    etLeft.setText(formatStr(it))
                }
            }
        }
    }

    private fun formatStr(it: Double): String {
        val tmp = it.toString()
        val indexOfDot = tmp.indexOf(".")
        return if (tmp.length > 7 && tmp.contains('.') && !tmp.contains('E')) {
            tmp.dropLast(tmp.length - indexOfDot - 6)
        } else {
            tmp
        }
    }
}