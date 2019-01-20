package com.pppp.network.poko

import com.pppp.entites.Generator

data class RetrofitGenerator(
    override var content: String? = null,

    override var uri: String? = null
) : Generator
            