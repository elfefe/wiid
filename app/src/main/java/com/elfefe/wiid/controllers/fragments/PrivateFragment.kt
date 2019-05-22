package com.elfefe.wiid.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.elfefe.wiid.R
import com.elfefe.wiid.controllers.MainActivity
import com.elfefe.wiid.utility.ChatConnector
import com.pusher.chatkit.CurrentUser
import com.pusher.chatkit.messages.Message
import com.pusher.chatkit.rooms.Room
import com.pusher.util.Result

class PrivateFragment : Fragment() {

    lateinit var imageButton: ImageButton
    lateinit var textView: TextView

    private val lobby: Room? = null

    var currentUser : CurrentUser?
        get() {return currentUser}
        set(currentUser) {this.currentUser = currentUser}

    var messages: List<Message>
        get() {return messages}
        set(messages) {this.messages = messages}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_private, container, false)

        imageButton = view.findViewById(R.id.fragment_private_sendImage)
        textView = view.findViewById(R.id.fragment_private_sendText)

        textView.text = "Ca fonctionne"

        currentUser = ChatConnector().currentUser

        return view
    }

    override fun onResume() {
        super.onResume()

        currentUser!!.sendMessage("lobbyTest", textView.text.toString(), callback = { result ->  })

        imageButton.setOnClickListener {
                Log.d("Current User", currentUser!!.name)

                currentUser!!.fetchMessages("lobbyTest", callback = { result ->
                    when(result) {
                        is Result.Success ->
                            messages = result.value
                        is Result.Failure ->
                            result.error
                    }

                })

            Log.d("MESSSSAGE", messages!!.get(0).text)
            }
    }

    companion object {

        fun newInstance(): PrivateFragment {
            return PrivateFragment()
        }
    }
}
