package com.eduardonunes.selectablelivedata.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eduardonunes.selectablelistlivedata.infrastructure.ListHolder
import com.eduardonunes.selectablelivedata.R
import com.eduardonunes.selectablelivedata.ui.main.models.DumbData
import com.eduardonunes.selectablelivedata.ui.main.models.SelectableDumbData
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initListeners()
    }

    private fun initListeners() {
        messageView.setOnClickListener { viewModel.initData() }
    }

    private fun initData() = with(viewModel) {
        message.observe(viewLifecycleOwner, Observer(::bindMessage))
        dumbListData.observe(viewLifecycleOwner, Observer(::bindDumbList))
        multiSelectionData.observe(viewLifecycleOwner, Observer(::bindMultiList))
        singleSelectionData.observe(viewLifecycleOwner, Observer(::bindSingleList))
    }

    private fun bindMessage(message: String?) {
        messageView.text = message
        messageView.changeVisibility(!message.isNullOrEmpty())
    }

    private fun bindDumbList(listHolder: ListHolder<DumbData>) {

    }

    private fun bindMultiList(listHolder: ListHolder<SelectableDumbData>) {

    }

    private fun bindSingleList(listHolder: ListHolder<SelectableDumbData>) {

    }

}

fun View.changeVisibility(show: Boolean, typeHide: Int = View.GONE) {
    if (this.visibility == View.VISIBLE && show) return
    visibility = if (show) View.VISIBLE else typeHide
}