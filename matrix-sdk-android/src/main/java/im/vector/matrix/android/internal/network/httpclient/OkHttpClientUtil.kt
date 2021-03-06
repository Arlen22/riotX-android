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

package im.vector.matrix.android.internal.network.httpclient

import im.vector.matrix.android.internal.network.AccessTokenInterceptor
import im.vector.matrix.android.internal.network.interceptors.CurlLoggingInterceptor
import im.vector.matrix.android.internal.network.token.AccessTokenProvider
import okhttp3.OkHttpClient

internal fun OkHttpClient.addAccessTokenInterceptor(accessTokenProvider: AccessTokenProvider): OkHttpClient {
    return newBuilder()
            .apply {
                // Remove the previous CurlLoggingInterceptor, to add it after the accessTokenInterceptor
                val existingCurlInterceptors = interceptors().filterIsInstance<CurlLoggingInterceptor>()
                interceptors().removeAll(existingCurlInterceptors)

                addInterceptor(AccessTokenInterceptor(accessTokenProvider))

                // Re add eventually the curl logging interceptors
                existingCurlInterceptors.forEach {
                    addInterceptor(it)
                }
            }
            .build()
}
