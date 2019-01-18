package com.pppp.network.poko

import com.pppp.entites.Author

data class RetrotifAuthor(
    override var name: String? = null,

    override var uri: String? = null
) : Author