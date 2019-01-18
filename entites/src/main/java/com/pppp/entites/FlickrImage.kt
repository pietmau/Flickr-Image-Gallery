package com.pppp.entites

interface FlickrImage {
    var id: String?
    val content: Content?
    val author: Author?
    var title: String?
    val category: Category?
    var updated: String?
    val link: List<Link>?
    var published: String?
    var displaycategories: String?
    val imageUrl: String?
}