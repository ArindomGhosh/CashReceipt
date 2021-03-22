package com.arindom.cashrecipt.views.widgets.productinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.ItemProductDetailsBinding
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.widgets.productinfo.ProductListAdapter.ProductListViewHolder

class ProductListAdapter(
    private val mContext: Context,
    private val productList: List<CashReceipt.ItemWithPrice>,
    private val onProductSelectedListener: ((CashReceipt.ItemWithPrice) -> Unit)?
) : RecyclerView.Adapter<ProductListViewHolder>() {

    inner class ProductListViewHolder(private val mBinding: ItemProductDetailsBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(product: CashReceipt.ItemWithPrice) {
            mBinding.itemWithPrice = product
            if (product.productName.equals("bread", true)) {
                mBinding.imgProduct.setImageDrawable(
                    ContextCompat.getDrawable(
                        mContext,
                        R.drawable.bread
                    )
                )
            }
            if (product.productName.equals("oranges", true)) {
                mBinding.imgProduct.setImageDrawable(
                    ContextCompat.getDrawable(
                        mContext,
                        R.drawable.oranges
                    )
                )
            }
            mBinding.root.setOnClickListener {
                onProductSelectedListener?.invoke(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_details,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(product = productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}