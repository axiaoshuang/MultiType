/*
 * Copyright 2017 CaMnter. https://github.com/CaMnter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.camnter.multitype.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.List;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author CaMnter
 */

public abstract class DataBindingBinder<T>
    extends ItemViewBinder<T, DataBindingViewHolder> {

    private WeakReference<VHandler> vHandler;


    public void setVHandler(VHandler vHandler) {
        this.vHandler = new WeakReference<>(vHandler);
    }


    protected abstract int getItemLayoutId();


    @NonNull @Override @SuppressWarnings("unchecked")
    protected DataBindingViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        final int layoutId = this.getItemLayoutId();
        if (layoutId == 0) {
            throw new LayoutIdNotFoundException(this.getClass());
        }
        return new DataBindingViewHolder(
            DataBindingUtil.inflate(inflater, layoutId, parent, false));
    }


    @Override @SuppressWarnings("unchecked")
    protected void onBindViewHolder(
        @NonNull DataBindingViewHolder holder, @NonNull T item) {
        try {
            final List<?> items = this.getAdapter().getItems();
            if (items == null) return;
            final T itemValue = (T) items.get(holder.getAdapterPosition());
            final ViewDataBinding binding = holder.getBinding();
            binding.setVariable(com.camnter.multitype.databinding.BR.position, holder.getAdapterPosition());
            binding.setVariable(com.camnter.multitype.databinding.BR.itemValue, itemValue);
            binding.setVariable(com.camnter.multitype.databinding.BR.vHandler, this.vHandler.get());
            binding.executePendingBindings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override protected void onBindViewHolder(
        @NonNull DataBindingViewHolder holder, @NonNull T item, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, item, payloads);
    }


    public interface VHandler {
    }

}