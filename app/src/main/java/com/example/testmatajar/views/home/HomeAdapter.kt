package com.example.testmatajar.views.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testmatajar.databinding.ItemCartBinding
import com.example.testmatajar.provider.OrderItemProvider



class HomeAdapter(private val itemInterface: ItemInterface) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    private var itemList: ArrayList<OrderItemProvider> = ArrayList()

    fun submitList(list: ArrayList<OrderItemProvider>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position], itemInterface, position)
    }

    fun getItem(position: Int): OrderItemProvider {
        return itemList[position]
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder private constructor(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OrderItemProvider, itemInterface: ItemInterface, position: Int) {
            binding.viewModel = item
            binding.itemInterface = itemInterface
            binding.position = position

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCartBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }




}

class ShopClickListener(val clickListener: (data: OrderItemProvider) -> Unit) {
    fun onClick(data: OrderItemProvider) = clickListener(data)
}