/*
 * Copyright (C) 2016 - 2018 ResearchIME Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.lmu.ifi.researchime.data.keyboard;

import android.content.Context;

import de.lmu.ifi.researchime.data.base.RestGateway;
import de.lmu.ifi.researchime.data.keyboard.model.KeyboardContainer;
import de.lmu.ifi.researchime.registration.UserRegistrationHandler;

public class KeyboardGateway extends RestGateway<KeyboardContainer> {

    private KeyboardStorage storage;

    public KeyboardGateway(Context context, KeyboardStorage storage){
        super(context, KeyboardContainer.class);
        this.storage = storage;
    }

    @Override
    public KeyboardContainer fetch() throws Exception {
        String userId = UserRegistrationHandler.getUserId();
        String layoutId = storage.get().getEnabledKeyboardId();
        //return getJsonParser().fromJson(getClient().getLayout(userId, layoutId), getType());
        return null;
    }

    @Override
    public KeyboardContainer create() {
        return storage.get();
    }
}
