package kg.simulators_life.currency_calculator.presentation.fragments.currency_select_fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kg.simulators_life.currency_calculator.domain.entity.Valute
import kg.simulators_life.currency_calculator.databinding.ItemBinding

class SelectAdapter : RecyclerView.Adapter<SelectAdapter.SelectHolder>() {

    private var list: List<Valute> = arrayListOf()
    private var onItemClick: CurrencyClicked? = null
    private var chosenItem: String = "R01370"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectHolder {
        return SelectHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SelectHolder, position: Int) {
        holder.bind(list[position], chosenItem)
        holder.itemView.setOnClickListener {
            list[position].iD?.let { it1 -> onItemClick?.clickOnItem(it1) }
        }
    }

    override fun getItemCount(): Int = list.size

    fun setClicker(onItemClick: CurrencyClicked) {
        this.onItemClick = onItemClick
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Valute?>) {
        this.list = list.requireNoNulls()
        notifyDataSetChanged()
    }

    fun chosenItem(id: String?) {
        if (id != null) {
            chosenItem = id.drop(1)
        }
    }

    class SelectHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Valute, chosenItem: String) = with(binding) {
            tvCharCode.text = item.charCode.toString()
            tvName.text = item.name.toString()
            ivCheck.isVisible = item.iD == chosenItem
        }
    }
}