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

package de.lmu.ifi.researchime.messaging;

//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import de.lmu.ifi.researchime.registration.UserRegistrationHandler;

public class MessagingInstanceIdService extends FirebaseMessagingService {

    //@Override
    public void onTokenRefresh() {
        Task<String> tokenExtracted = FirebaseMessaging.getInstance().getToken();
        String token = tokenExtracted.toString();
        if(token != null){
            UserRegistrationHandler.updatePushToken(getApplicationContext(), token);
        }
    }
}