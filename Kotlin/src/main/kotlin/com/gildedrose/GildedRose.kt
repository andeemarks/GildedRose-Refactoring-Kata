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
        for (i in items.indices) {
            val item = items[i]
            when (item.name) {
                AGED_BRIE -> {
                    updateBrie(item)
                }

                BACKSTAGE_PASSES -> {
                    updateBackstagePasses(item)
                }

                SULFURAS -> {
                    updateSulfuras(item)
                }

                else -> {
                    updateItem(item)
                }
            }
        }
    }

    private fun updateItem(item: Item) {
        item.quality = max(ITEM_MIN_QUALITY, item.quality - 1)

        updateSellInDays(item)

        if (item.sellInDays < 0) {
            item.quality = max(ITEM_MIN_QUALITY, item.quality - 1)
        }
    }

    private fun updateBrie(brie: Item) {
        updateItemQuality(brie)
        updateSellInDays(brie)
    }

    private fun updateSulfuras(sulfuras: Item) {
        //        NOOP
    }

    private fun updateBackstagePasses(backstagePass: Item) {
        updateBackstagePassQuality(backstagePass)

        updateSellInDays(backstagePass)

        if (backstagePass.sellInDays < 0) {
            backstagePass.quality = 0
        }
    }

    private fun updateSellInDays(item: Item) {
        item.sellInDays--
    }

    private fun updateItemQuality(item: Item) {
        item.quality = min(ITEM_MAX_QUALITY, item.quality + 1)
    }

    private fun updateBackstagePassQuality(backstagePass: Item) {
        updateItemQuality(backstagePass)
        if (backstagePass.quality < ITEM_MAX_QUALITY) {
            if (backstagePass.sellInDays < BACKSTAGE_PASS_THRESHOLD_1) {
                backstagePass.quality++
            }

            if (backstagePass.sellInDays < BACKSTAGE_PASS_THRESHOLD_2) {
                backstagePass.quality++
            }
        }
    }

}

