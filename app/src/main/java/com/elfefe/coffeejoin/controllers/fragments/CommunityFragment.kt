package com.elfefe.coffeejoin.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.elfefe.coffeejoin.R
import com.elfefe.coffeejoin.models.Author
import com.elfefe.coffeejoin.models.Message
import com.elfefe.coffeejoin.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.fragment_community.*
import org.joda.time.DateTime

class CommunityFragment : Fragment() {

    val user = User.INFO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_community,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = Message(
            DateTime.now().millis,
            "Hello world !",
            Author(
                user.id,
                user.name.toString(),
                user.photoUrl.toString()
            ))

        val adapter = MessagesListAdapter<Message>("sender", ImageLoader { imageView, url, _ ->
            Glide.with(requireContext()).load(url).into(imageView)
        })

        community_message_messagelist.setAdapter(adapter)
        adapter.addToStart(message, true)

        community_message_input.setInputListener {
            adapter.addToStart(Message(DateTime.now().millis, it.toString()), true)
            true
        }

        community_message_title_button.setOnClickListener {
            when (community_layout.currentState) {
                R.id.both -> {
                    community_layout.transitionToState(R.id.message)
                    community_message_layout.transitionToStart()
                    community_actuality_layout.transitionToEnd()
                }
                R.id.actuality -> {
                    community_layout.transitionToState(R.id.both)
                    community_message_layout.transitionToStart()
                    community_actuality_layout.transitionToStart()
                }
                R.id.message -> {
                    community_layout.transitionToState(R.id.both)
                    community_actuality_layout.transitionToStart()
                    community_message_layout.transitionToStart()
                }
            }
        }

        community_actuality_title_button.setOnClickListener {
            when (community_layout.currentState) {
                R.id.both -> {
                    community_layout.transitionToState(R.id.actuality)
                    community_actuality_layout.transitionToStart()
                    community_message_layout.transitionToEnd()
                }
                R.id.message-> {
                    community_layout.transitionToState(R.id.both)
                    community_actuality_layout.transitionToStart()
                    community_message_layout.transitionToStart()
                }
                R.id.actuality -> {
                    community_layout.transitionToState(R.id.both)
                    community_actuality_layout.transitionToStart()
                    community_message_layout.transitionToStart()
                }
            }
        }
    }

    companion object {
        fun newInstance(): CommunityFragment {
            return CommunityFragment()
        }
    }
}