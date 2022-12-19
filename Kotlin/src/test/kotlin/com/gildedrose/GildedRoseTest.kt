package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun itemNamesArePersistentAfterUpdate() {
        val item = updateQualityOf(Item("foo", 0, 0))

        assertEquals("foo", item.name)
    }

    @Test
    fun qualityDecreasesWithEachPassingDayUpToSellInDays() {
        val item = updateQualityOf(Item("foo", 5, 10))

        assertEquals(9, item.quality)
    }

    @Test
    fun qualityDecreasesFasterWithEachPassingDayBeyondSellInDays() {
        val item = updateQualityOf(Item("foo", 0, 10))

        assertEquals(8, item.quality)
    }

    @Test
    fun sellInDaysDecreasesWithEachPassingDay() {
        val item = updateQualityOf(Item("foo", 10, 0))

        assertEquals(9, item.sellInDays)
    }

    @Test
    fun agedBrieQualityIncreasesWithEachPassingDay() {
        val item = updateQualityOf(Item("Aged Brie", 10, 5))

        assertEquals(6, item.quality)
    }

    @Test
    fun sulfurasQualityNeverDecreases() {
        val item = updateQualityOf(Item("Sulfuras, Hand of Ragnaros", 10, 5))

        assertEquals(5, item.quality)
    }

    @Test
    fun backstagePassQualityIncreasesAsTheSellInDaysApproaches() {
        val startingQuality = 5
        val itemBefore10Days = updateQualityOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, startingQuality))
        assertEquals(startingQuality + 1, itemBefore10Days.quality)

        val itemBefore5Days = updateQualityOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 5))
        assertEquals(startingQuality + 2, itemBefore5Days.quality)

        val itemAfter5Days = updateQualityOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 5))
        assertEquals(startingQuality + 3, itemAfter5Days.quality)
    }

    @Test
    fun itemQualityNeverDropsBelow0() {
        val item = updateQualityOf(Item("foo", 10, 0))

        assertEquals(0, item.quality)
    }

    @Test
    fun itemQualityNeverExceeds50() {
        val item = updateQualityOf(Item("Aged Brie", 10, 50))

        assertEquals(50, item.quality)
    }

    private fun updateQualityOf(item: Item): Item {
        val items = arrayOf(item)
        val app = GildedRose(items)
        app.updateQuality()

        return app.items[0]
    }

}


