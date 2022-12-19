package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun itemNamesArePersistentAfterUpdate() {
        val item = itemAfterEndOfDay(Item("foo", 0, 0))

        assertEquals("foo", item.name)
    }

    @Test
    fun qualityDecreasesWithEachPassingDayUpToSellInDays() {
        val item = itemAfterEndOfDay(Item("foo", 5, 10))

        assertEquals(9, item.quality)
    }

    @Test
    fun qualityDecreasesFasterWithEachPassingDayBeyondSellInDays() {
        val item = itemAfterEndOfDay(Item("foo", 0, 10))

        assertEquals(8, item.quality)
    }

    @Test
    fun sellInDaysDecreasesWithEachPassingDay() {
        val item = itemAfterEndOfDay(Item("foo", 10, 0))

        assertEquals(9, item.sellInDays)
    }

    @Test
    fun agedBrieQualityIncreasesWithEachPassingDay() {
        val item = itemAfterEndOfDay(Item("Aged Brie", 10, 5))

        assertEquals(6, item.quality)
    }

    @Test
    fun sulfurasQualityNeverDecreases() {
        val item = itemAfterEndOfDay(Item("Sulfuras, Hand of Ragnaros", 10, 5))

        assertEquals(5, item.quality)
    }

    @Test
    fun backstagePassQualityIncreasesAsTheSellInDaysApproaches() {
        val startingQuality = 5
        val itemBefore10DaysToConcert = itemAfterEndOfDay(Item("Backstage passes to a TAFKAL80ETC concert", 11, startingQuality))
        assertEquals(startingQuality + 1, itemBefore10DaysToConcert.quality)

        val itemBefore5DaysToConcert = itemAfterEndOfDay(Item("Backstage passes to a TAFKAL80ETC concert", 10, 5))
        assertEquals(startingQuality + 2, itemBefore5DaysToConcert.quality)

        val itemWithin5DaysToConcert = itemAfterEndOfDay(Item("Backstage passes to a TAFKAL80ETC concert", 5, 5))
        assertEquals(startingQuality + 3, itemWithin5DaysToConcert.quality)

        val itemAfterConcert = itemAfterEndOfDay(Item("Backstage passes to a TAFKAL80ETC concert", 0, 5))
        assertEquals(0, itemAfterConcert.quality)
    }

    @Test
    fun itemQualityNeverDropsBelow0() {
        val item = itemAfterEndOfDay(Item("foo", 10, 0))

        assertEquals(0, item.quality)
    }

    @Test
    fun itemQualityNeverExceeds50() {
        val item = itemAfterEndOfDay(Item("Aged Brie", 10, 50))

        assertEquals(50, item.quality)
    }

    private fun itemAfterEndOfDay(item: Item): Item {
        val app = GildedRose(arrayOf(item))
        app.endOfDay()

        return app.items[0]
    }

}


