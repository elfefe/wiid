package com.elfefe.wiid.utility

import com.elfefe.wiid.controllers.MainActivity
import com.google.android.gms.maps.model.LatLng
import com.pusher.chatkit.AndroidChatkitDependencies
import com.pusher.chatkit.ChatManager
import com.pusher.chatkit.ChatkitTokenProvider
import com.pusher.chatkit.CurrentUser
import com.pusher.util.Result
import elements.Error

class ChatConnector {



    val AMSTERADAM = LatLng(52.3362, 4.8985)
    val ENDPOINT = "https://us1.pusherplatform.io/services/chatkit_token_provider/v1/fc1c05cf-7b92-4e5a-9924-aae9c183d959/token"
    val INSTANCE_LOCATOR = "v1:us1:fc1c05cf-7b92-4e5a-9924-aae9c183d959"
    val USER_ID = "Felix"

    var currentUser: CurrentUser?
        get() = currentUser
        set(currentUser) {}

    init {
        val chatManager = ChatManager(
            instanceLocator = INSTANCE_LOCATOR,
            userId = USER_ID,
            dependencies = AndroidChatkitDependencies(
                tokenProvider = ChatkitTokenProvider(
                    endpoint = ENDPOINT,
                    userId = USER_ID
                )
            )
        )

        chatManager.connect { result ->
            run {
                when (result) {
                    is Result.Success -> {
                        currentUser = result.value
                    }

                    is Result.Failure -> {
                        handleConnectionError(result.error)
                    }
                }
            }
        }

    }

    private fun handleConnectionError(error: Error) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}