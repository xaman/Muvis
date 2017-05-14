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

package com.martinchamarro.muvis.presentation.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.martinchamarro.muvis.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EmptyView extends RelativeLayout {

    @BindView(R.id.picture) protected ImageView picture;
    @BindView(R.id.explanation) protected TextView explanation;

    public EmptyView(Context context) {
        super(context);
        initialize();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
        loadAttributes(context, attrs);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
        loadAttributes(context, attrs);
    }

    private void initialize() {
        inflate(getContext(), R.layout.layout_empty_view, this);
        ButterKnife.bind(this);
    }

    private void loadAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView);
        int iconRes = typedArray.getResourceId(R.styleable.EmptyView_ev_icon, -1);
        String textContent = typedArray.getString(R.styleable.EmptyView_ev_text);
        if (iconRes != -1) picture.setImageResource(iconRes);
        if (!TextUtils.isEmpty(textContent)) explanation.setText(textContent);
        typedArray.recycle();
    }

}
