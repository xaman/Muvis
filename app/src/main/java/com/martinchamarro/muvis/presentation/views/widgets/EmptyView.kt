/*
 * Copyright 2017 Martin Chamarro (@martinchamarro)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.martinchamarro.muvis.presentation.views.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.martinchamarro.muvis.R
import kotlinx.android.synthetic.main.layout_empty_view.view.*


class EmptyView : RelativeLayout {

    constructor(context: Context) : super(context) { initialize() }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
        loadAttributes(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
        loadAttributes(context, attrs)
    }

    private fun initialize() = View.inflate(context, R.layout.layout_empty_view, this)

    private fun loadAttributes(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView)
        val iconRes = typedArray.getResourceId(R.styleable.EmptyView_ev_icon, -1)
        if (iconRes != -1) pictureView.setImageResource(iconRes)
        val textContent = typedArray.getString(R.styleable.EmptyView_ev_text)
        explanationView.text = textContent.orEmpty()
        typedArray.recycle()
    }

}
