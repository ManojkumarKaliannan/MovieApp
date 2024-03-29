package com.backbase.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


class BaseRecyclerViewAdapter<T, V : ViewDataBinding>   (@LayoutRes
                                                         private val layoutResourceId: Int,
                                                         private val bindVariableId: Int,
                                                         private val items: MutableList<T>,
                                                         private val onDataBindCallback: OnDataBindCallback<V>?
) : androidx.recyclerview.widget.RecyclerView.Adapter<BaseViewHolder<V>>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutResourceId, parent, false), onDataBindCallback)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        setFadeAnimationOnScroll(holder.itemView)
        holder.viewDataBinding.setVariable(bindVariableId, getItem(position))
        holder.viewDataBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun getItem(position: Int): T {
        return items[position]
    }
    fun addDataSet(data:List<T>){
        items.addAll(data)
    }

    fun getItems():List<T>{
        return items
    }

    fun cleatDataSet(){
        items.clear()
    }

    private fun setFadeAnimationOnScroll(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }

}