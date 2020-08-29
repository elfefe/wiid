package com.elfefe.coffeejoin.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.elfefe.coffeejoin.R

class PrivateFragment : Fragment() {
    private var imageButton: ImageButton? = null
    private var textView: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_private, container, false)
        imageButton =
            view.findViewById<View>(R.id.fragment_private_sendImage) as ImageButton
        textView = view.findViewById<View>(R.id.fragment_private_sendText) as TextView
        textView!!.text = "Test texte"
        return view
    }

    companion object {
        fun newInstance(): PrivateFragment {
            val args = Bundle()
            val fragment = PrivateFragment()
            fragment.arguments = args
            return fragment
        }
    }
}