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
import com.eduardonunes.selectablelivedata.ui.extensions.changeVisibility
import com.eduardonunes.selectablelivedata.ui.main.adapter.DumbAdapter
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
        initViews()
        initData()
        initListeners()
    }

    private fun initViews() {
        setDumbAdapter()
    }

    private fun setDumbAdapter() {
        singleSelectionList.adapter = DumbAdapter(viewModel.singleSelectionData, viewModel::singleSelect)
        multiSelectionList.adapter = DumbAdapter(viewModel.multiSelectionData, viewModel::multiSelect)
    }

    private fun initData() = with(viewModel) {
        message.observe(viewLifecycleOwner, Observer(::bindMessage))
        multiSelectionData.observe(viewLifecycleOwner, Observer(::bindMultiList))
        singleSelectionData.observe(viewLifecycleOwner, Observer(::bindSingleList))
    }

    private fun bindMessage(message: String?) {
        messageView.text = message
        messageView.changeVisibility(!message.isNullOrEmpty())
    }

    private fun bindMultiList(listHolder: ListHolder<SelectableDumbData>) {
        if (listHolder.size() < 1) return
        if (multiSelectionList.adapter == null) setDumbAdapter()
        listHolder.applyChange(multiSelectionList.adapter!!)
    }

    private fun bindSingleList(listHolder: ListHolder<SelectableDumbData>) {
        if (listHolder.size() < 1) return
        if (singleSelectionList.adapter == null) setDumbAdapter()
        listHolder.applyChange(singleSelectionList.adapter!!)
    }

    private fun initListeners() {
        messageView.setOnClickListener { viewModel.initData() }
        selectableTypeGroup.setOnCheckedChangeListener { _, checkedId ->
            multiSelectionList.changeVisibility(checkedId == R.id.multipleSelectionOption)
            singleSelectionList.changeVisibility(checkedId == R.id.singleSelectionOption)
        }
    }
}