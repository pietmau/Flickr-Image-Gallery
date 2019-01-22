package com.pppp.network.pokos

import com.pppp.entites.Content

data class RetrofitContent(
    override var content: String? = null,

    override var type: String? = null
) : Content