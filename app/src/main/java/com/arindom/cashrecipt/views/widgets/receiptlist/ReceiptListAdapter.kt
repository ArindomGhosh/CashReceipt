package com.arindom.cashrecipt.views.widgets.receiptlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.ItemReceiptListBinding
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.Listener

class ReceiptListAdapter(
    private val receiptList: List<CashReceipt>,
    private val action: ((CashReceipt) -> Unit)? = null,
    private val showCategory: Boolean
) :
    RecyclerView.Adapter<ReceiptListAdapter.ReceiptListViewHolder>() {
    inner class ReceiptListViewHolder(private val mBinding: ItemReceiptListBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(cashReceipt: CashReceipt) {
            mBinding.showCategory = showCategory
            mBinding.cashReceipt = cashReceipt
            mBinding.root.setOnClickListener {
                action?.invoke(cashReceipt)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptListViewHolder {
        return ReceiptListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_receipt_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReceiptListViewHolder, position: Int) {
        holder.bind(receiptList[position])
    }

    override fun getItemCount() = receiptList.size
}