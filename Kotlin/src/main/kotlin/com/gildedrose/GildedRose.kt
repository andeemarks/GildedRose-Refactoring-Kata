package com.gildedrose

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
            if (item.name != AGED_BRIE && item.name != BACKSTAGE_PASSES) {
                if (item.quality > ITEM_MIN_QUALITY) {
                    if (item.name != SULFURAS) {
                        item.quality--
                    }
                }
            } else {
                updateItemQuality(item)
            }

            if (item.name != SULFURAS) {
                item.sellInDays--
            }

            if (item.sellInDays < 0) {
                if (item.name != AGED_BRIE) {
                    if (item.name != BACKSTAGE_PASSES) {
                        if (item.quality > ITEM_MIN_QUALITY) {
                            if (item.name != SULFURAS) {
                                item.quality--
                            }
                        }
                    } else {
                        item.quality = 0
                    }
                } else {
                    if (item.quality < ITEM_MAX_QUALITY) {
                        item.quality++
                    }
                }
            }
        }
    }

    private fun updateItemQuality(item: Item) {
        if (item.quality < ITEM_MAX_QUALITY) {
            item.quality++

            if (item.name == BACKSTAGE_PASSES) {
                updateBackstagePassQuality(item)
            }
        }
    }

    private fun updateBackstagePassQuality(backstagePass: Item) {
        if (backstagePass.sellInDays < BACKSTAGE_PASS_THRESHOLD_1) {
            if (backstagePass.quality < ITEM_MAX_QUALITY) {
                backstagePass.quality++
            }
        }

        if (backstagePass.sellInDays < BACKSTAGE_PASS_THRESHOLD_2) {
            if (backstagePass.quality < ITEM_MAX_QUALITY) {
                backstagePass.quality++
            }
        }
    }

}

