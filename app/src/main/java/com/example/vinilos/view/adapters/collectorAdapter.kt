package com.example.vinilos.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.ActivityListColeccionistaBinding
//import com.example.vinilos.view.CollectorFragment
import com.example.vinilos.databinding.ActivityMainListColeccionistaBinding
import com.example.vinilos.model.CollectorModel
//import com.example.vinilos.view.detailMainCollectorActivity


class CollectorsAdapter: RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>() {

    var collectors: List<CollectorModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CollectorViewHolder(val viewDataBinding: ActivityListColeccionistaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        val context = viewDataBinding.root.context

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.activity_list_coleccionista
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: ActivityListColeccionistaBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.coleccionista = collectors[position]
        }

    }

    override fun getItemCount(): Int {
        return collectors.size
    }




}