package com.pppp.network.poko

import com.pppp.entites.Category

data class RetrofitCategory(
    override var scheme: String? = null,

    override var term: String? = null
) : Category
            