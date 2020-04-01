package com.eduardonunes.selectablelivedata.ui.main.models

import com.eduardonunes.selectablelistlivedata.infrastructure.SelectableItem

class SelectableDumbData(
    val text: String,
    override var isActive: Boolean = false
) : SelectableItem