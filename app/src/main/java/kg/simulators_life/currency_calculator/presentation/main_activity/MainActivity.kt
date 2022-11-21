package kg.simulators_life.currency_calculator.presentation.main_activity

import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import kg.simulators_life.core.base.BaseActivity
import kg.simulators_life.core.base.BaseViewModel
import kg.simulators_life.currency_calculator.R
import kg.simulators_life.currency_calculator.databinding.ActivityMainBinding
import kg.simulators_life.currency_calculator.presentation.fragments.calculation_fragment.CalculationFragment
import kg.simulators_life.currency_calculator.presentation.fragments.loading_fragment.LoadingFragment

@AndroidEntryPoint
class MainActivity :  BaseActivity<BaseViewModel, ActivityMainBinding>(){
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                when(fragment) {
                    is LoadingFragment, is CalculationFragment -> finish()
                    else -> onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }
}