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

package me.drakeet.multitype.extensions.databinding;

import android.databinding.BaseObservable;
import android.support.annotation.Nullable;
import java.util.List;

/**
 * @author CaMnter
 */

public abstract class BindingCollaborator extends BaseObservable
    implements BindingBinder.Collaborator {

    @Nullable
    protected List<?> items;


    @Nullable
    public List<?> getItems() {
        return items;
    }


    public void setItems(@Nullable final List<?> items) {
        this.items = items;
    }

}