package com.arindom.cashrecipt.views.widgets.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.WidgetUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

fun UserDetailsWidget(
    userDetails: UserDetails
): UserDetailsFragment {
    return UserDetailsFragment(userDetails = userDetails)
}

@AndroidEntryPoint
class UserDetailsFragment(private val userDetails: UserDetails) : Fragment() {
    private val mUserDetailsViewModel: UserDetailsViewModel by viewModels()
    private lateinit var mBinding: WidgetUserDetailsBinding
    private val userDetailsObserver: Observer<UserDetails> = Observer {
        mUserDetailsViewModel.notifyChange()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.widget_user_details, container, false)
        mBinding.userDetailVM = mUserDetailsViewModel
        mUserDetailsViewModel.onUserDetailsEventTrigger(UserDetailsWidgetEvent.FetchUserDetailsEvent(userDetails))
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserDetailsViewModel.getUserDetailsLiveData().observe(viewLifecycleOwner, userDetailsObserver)
    }
}