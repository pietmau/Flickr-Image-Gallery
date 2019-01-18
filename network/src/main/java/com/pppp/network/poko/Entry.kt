package com.pppp.network.poko

interface Entry {
    var id: String?
    var content: Content?
    var author: Author?
    var title: String?
    var category: Category?
    var updated: String?
    var link: List<Link>?
    var published: String?
    var displaycategories: String?
    val imageUrl: String?
}