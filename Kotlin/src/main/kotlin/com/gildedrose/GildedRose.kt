package com.gildedrose

import java.lang.Integer.max
import java.lang.Integer.min

private const val AGED_BRIE = "Aged Brie"
private const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
private const val SULFURAS = "Sulfuras, Hand of Ragnaros"
private const val ITEM_MAX_QUALITY = 50
private const val BACKSTAGE_PASS_THRESHOLD_1 = 11
private const val BACKSTAGE_PASS_THRESHOLD_2 = 6
private const val ITEM_MIN_QUALITY = 0

class GildedRose(var items: Array<Item>) {

    fun endOfDay() {
        items.forEach {
            when (it.name) {
                AGED_BRIE -> updateBrie(it)
                BACKSTAGE_PASSES -> updateBackstagePasses(it)
                SULFURAS -> doNothing()
                else -> updateItem(it)
            }
        }
    }

    private fun updateItem(item: Item) {
        decreaseSellInDays(item)
        decreaseQuality(item)
    }

    private fun updateBrie(brie: Item) {
        increaseQuality(brie)
        decreaseSellInDays(brie)
    }

    private fun doNothing() {}

    private fun updateBackstagePasses(backstagePass: Item) {
        increaseBackstagePassQuality(backstagePass)
        decreaseSellInDays(backstagePass)

        if (backstagePass.sellInDays < 0) {
            backstagePass.quality = 0
        }
    }

    private fun decreaseQuality(item: Item) {
        item.quality = max(ITEM_MIN_QUALITY, item.quality - 1)
        if (item.sellInDays < 0) {
            item.quality = max(ITEM_MIN_QUALITY, item.quality - 1)
        }
    }

    private fun decreaseSellInDays(item: Item) {
        item.sellInDays--
    }

    private fun increaseQuality(item: Item) {
        item.quality = min(ITEM_MAX_QUALITY, item.quality + 1)
    }

    private fun increaseBackstagePassQuality(backstagePass: Item) {
        increaseQuality(backstagePass)
        if (backstagePass.quality >= ITEM_MAX_QUALITY) {
            return
        }

        if (backstagePass.sellInDays < BACKSTAGE_PASS_THRESHOLD_1) {
            backstagePass.quality++
        }

        if (backstagePass.sellInDays < BACKSTAGE_PASS_THRESHOLD_2) {
            backstagePass.quality++
        }
    }

}

