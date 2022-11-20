package kg.simulators_life.curencyccalculator.presentation.main_activity

import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import kg.simulators_life.core.base.BaseActivity
import kg.simulators_life.core.base.BaseViewModel
import kg.simulators_life.curencyccalculator.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity :  BaseActivity<BaseViewModel, ActivityMainBinding>(){
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
}