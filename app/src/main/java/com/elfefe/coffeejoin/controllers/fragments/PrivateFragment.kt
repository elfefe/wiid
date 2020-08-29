package com.elfefe.coffeejoin.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.elfefe.coffeejoin.R
import com.elfefe.coffeejoin.models.Author
import com.elfefe.coffeejoin.models.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.fragment_private.*
import org.joda.time.DateTime

class PrivateFragment : Fragment() {
    val user: FirebaseUser by lazy {
        FirebaseAuth.getInstance().currentUser!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_private,
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
                user.uid,
                user.displayName.toString(),
                user.photoUrl.toString()
            )
        )

        val adapter = MessagesListAdapter<Message>("sender", ImageLoader { imageView, url, _ ->
            Glide.with(requireContext()).load(url).into(imageView)
        })
        private_message_messagelist.setAdapter(adapter)
        adapter.addToStart(message, true)

        private_message_input.setInputListener {
            adapter.addToStart(Message(DateTime.now().millis, it.toString()), true)
            true
        }

        private_message_title_button.setOnClickListener {
            when (private_layout.currentState) {
                R.id.both -> {
                    private_layout.transitionToState(R.id.message)
                    private_message_layout.transitionToStart()
                    private_actuality_layout.transitionToEnd()
                }
                R.id.actuality -> {
                    private_layout.transitionToState(R.id.both)
                    private_message_layout.transitionToStart()
                    private_actuality_layout.transitionToStart()
                }
                R.id.message -> {
                    private_layout.transitionToState(R.id.both)
                    private_actuality_layout.transitionToStart()
                    private_message_layout.transitionToStart()
                }
            }
        }

        private_actuality_title_button.setOnClickListener {
            when (private_layout.currentState) {
                R.id.both -> {
                    private_layout.transitionToState(R.id.actuality)
                    private_actuality_layout.transitionToStart()
                    private_message_layout.transitionToEnd()
                }
                R.id.message-> {
                    private_layout.transitionToState(R.id.both)
                    private_actuality_layout.transitionToStart()
                    private_message_layout.transitionToStart()
                }
                R.id.actuality -> {
                    private_layout.transitionToState(R.id.both)
                    private_actuality_layout.transitionToStart()
                    private_message_layout.transitionToStart()
                }
            }
        }
    }

    companion object {
        fun newInstance(): PrivateFragment {
            return PrivateFragment()
        }
    }
}