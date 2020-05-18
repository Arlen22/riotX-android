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

package im.vector.riotx.features.roomprofile.uploads.child

import com.airbnb.mvrx.parentFragmentViewModel
import im.vector.riotx.R
import im.vector.riotx.core.platform.VectorBaseFragment
import im.vector.riotx.features.roomprofile.uploads.RoomUploadsViewModel
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class RoomUploadsFilesFragment @Inject constructor() : VectorBaseFragment() {

    private val uploadsViewModel by parentFragmentViewModel(RoomUploadsViewModel::class)

    override fun getLayoutResId() = R.layout.fragment_generic_recycler
}
