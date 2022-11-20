package kg.simulators_life.curencyccalculator.presentation.fragments.currency_select_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kg.simulators_life.curencyccalculator.databinding.ItemBinding
import kg.simulators_life.curencyccalculator.domain.entity.Valute
import java.nio.charset.Charset

class SelectAdapter : RecyclerView.Adapter<SelectAdapter.SelectHolder>() {

    private var list: List<Valute> = arrayListOf()
    private var onItemClick: CurrencyClicked? = null
    private var chosenItem: String = "R01370"

    class SelectHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Valute) = with(binding) {
            tvCharCode.text = item.charCode.toString()
            val a = item.name
            if (a != null) {
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1250")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1251")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1252")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1253")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1254")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1255")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1256")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1257")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-1258")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("windows-31j")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-2")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-3")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-4")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-5")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-6")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-7")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-8")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-9")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-13")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-15")))
                println("-----1--" + String(a.toByteArray(), Charset.forName("ISO-8859-11")))

            }
            tvName.text = item.name.toString()
            if (item.isChosen != null) {
                ivCheck.isVisible = item.isChosen!!
            } else {
                ivCheck.isVisible = false
            }
        }
    }

    fun setClicker(onItemClick: CurrencyClicked) {
        this.onItemClick = onItemClick
    }

    fun setList(list: List<Valute?>) {
        this.list = list.requireNoNulls()
    }

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
        holder.bind(list[position])
        println("ad------" + list.toString())
        holder.itemView.setOnClickListener {
            val chosenItem = list[position]
            chosenItem.isChosen = true
            onItemClick?.clickOnItem(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    fun chosenItem(id: String?) {
        if (id != null) {
            chosenItem = id
        }
    }
}