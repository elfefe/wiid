package com.elfefe.wiid.models

import androidx.fragment.app.Fragment
import com.elfefe.wiid.controllers.fragments.CommunityFragment
import com.elfefe.wiid.controllers.fragments.MapsFragment
import com.elfefe.wiid.controllers.fragments.PrivateFragment

enum class Pages (public var title: String, public var fragment: Fragment) {
    MAPS("Map", MapsFragment.newInstance()),
    COMMUNITY("Community", CommunityFragment.newInstance()),
    PRIVATE("Private", PrivateFragment.newInstance())
}