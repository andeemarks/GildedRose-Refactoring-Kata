package com.gildedrose

open class Item(var name: String, var sellInDays: Int, var quality: Int) {
    override fun toString(): String {
        return this.name + ", " + this.sellInDays + ", " + this.quality
    }
}