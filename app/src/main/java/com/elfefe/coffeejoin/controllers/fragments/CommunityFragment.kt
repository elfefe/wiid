package com.elfefe.wiid.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.elfefe.wiid.R
import com.elfefe.wiid.models.Message
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.fragment_community.*

class CommunityFragment : Fragment() {
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

        val adapter = MessagesListAdapter<Message>("sender", ImageLoader() { imageView, url, _ ->
            Glide.with(requireContext()).load(url).into(imageView)
        })
        community_message_messagelist.setAdapter(adapter)
        adapter.addToStart(Message("ID", "Hello world"), true)

        community_input.setInputListener {
            adapter.addToStart(Message("ID", it.toString()), true)
            true
        }

        community_message_title_button.setOnClickListener {
            when (community_layout.currentState) {
                R.id.both -> {
                    community_layout.transitionToState(R.id.actuality)
                    community_message_layout.transitionToEnd()
                    community_actuality_layout.transitionToStart()
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
                    community_layout.transitionToState(R.id.message)
                    community_actuality_layout.transitionToEnd()
                    community_message_layout.transitionToStart()
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