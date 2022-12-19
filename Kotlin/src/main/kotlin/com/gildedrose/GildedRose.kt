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
            if (items[i].name != AGED_BRIE && items[i].name != BACKSTAGE_PASSES) {
                if (items[i].quality > ITEM_MIN_QUALITY) {
                    if (items[i].name != SULFURAS) {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (items[i].quality < ITEM_MAX_QUALITY) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == BACKSTAGE_PASSES) {
                        if (items[i].sellInDays < BACKSTAGE_PASS_THRESHOLD_1) {
                            if (items[i].quality < ITEM_MAX_QUALITY) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellInDays < BACKSTAGE_PASS_THRESHOLD_2) {
                            if (items[i].quality < ITEM_MAX_QUALITY) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != SULFURAS) {
                items[i].sellInDays = items[i].sellInDays - 1
            }

            if (items[i].sellInDays < 0) {
                if (items[i].name != AGED_BRIE) {
                    if (items[i].name != BACKSTAGE_PASSES) {
                        if (items[i].quality > ITEM_MIN_QUALITY) {
                            if (items[i].name != SULFURAS) {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < ITEM_MAX_QUALITY) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

}

