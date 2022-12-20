package com.gildedrose

import java.lang.Integer.max
import java.lang.Integer.min

private const val AGED_BRIE = "Aged Brie"
private const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
private const val SULFURAS = "Sulfuras, Hand of Ragnaros"
private const val ITEM_MAX_QUALITY = 50
private const val DAYS_TO_CONCERT_THRESHOLD_1 = 10
private const val DAYS_TO_CONCERT_THRESHOLD_2 = 5
private const val ITEM_MIN_QUALITY = 0

class GildedRose(var items: Array<Item>) {

    fun endOfDay() {
        items.forEach {
            when (it.name) {
                AGED_BRIE -> updateBrie(it)
                BACKSTAGE_PASSES -> updateBackstagePass(it)
                SULFURAS -> {}
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

    private fun updateBackstagePass(backstagePass: Item) {
        decreaseSellInDays(backstagePass)
        increaseBackstagePassQuality(backstagePass)
    }

    private fun decreaseQuality(item: Item) {
        item.quality = max(ITEM_MIN_QUALITY, item.quality - 1)
        val isPastSellByDate = item.sellInDays < 0
        if (isPastSellByDate) {
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
        if (backstagePass.sellInDays < 0) {
            backstagePass.quality = 0

            return
        }

        increaseQuality(backstagePass)

        if (backstagePass.sellInDays <= DAYS_TO_CONCERT_THRESHOLD_1) {
            increaseQuality(backstagePass)
        }

        if (backstagePass.sellInDays <= DAYS_TO_CONCERT_THRESHOLD_2) {
            increaseQuality(backstagePass)
        }
    }

}

