/*
 * Copyright (c) 2020 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.riotx.features.roomprofile.uploads

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.args
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import im.vector.matrix.android.api.util.toMatrixItem
import im.vector.riotx.R
import im.vector.riotx.core.platform.VectorBaseFragment
import im.vector.riotx.core.resources.StringProvider
import im.vector.riotx.features.home.AvatarRenderer
import im.vector.riotx.features.roomprofile.RoomProfileArgs
import kotlinx.android.synthetic.main.fragment_room_uploads.*
import javax.inject.Inject

class RoomUploadsFragment @Inject constructor(
        private val viewModelFactory: RoomUploadsViewModel.Factory,
        private val stringProvider: StringProvider,
        private val avatarRenderer: AvatarRenderer
) : VectorBaseFragment(), RoomUploadsViewModel.Factory by viewModelFactory {

    private val roomProfileArgs: RoomProfileArgs by args()

    private val viewModel: RoomUploadsViewModel by fragmentViewModel()

    override fun getLayoutResId() = R.layout.fragment_room_uploads

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = RoomUploadsPagerAdapter(childFragmentManager, stringProvider)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        setupToolbar(matrixProfileToolbar)

        // Initialize your view, subscribe to viewModel...
    }

    /*
    override fun onDestroyView() {
        super.onDestroyView()
        // Clear your view, unsubscribe...
    }

     */

    override fun invalidate() = withState(viewModel) { state ->
        renderRoomSummary(state)
    }

    private fun renderRoomSummary(state: RoomUploadsViewState) {
        state.roomSummary()?.let {
            matrixProfileToolbarTitleView.text = it.displayName
            avatarRenderer.render(it.toMatrixItem(), matrixProfileToolbarAvatarImageView)
        }
    }
}
