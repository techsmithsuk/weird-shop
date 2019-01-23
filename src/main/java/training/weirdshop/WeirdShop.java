package training.weirdshop;

class WeirdShop {
    private Item[] items;

    public WeirdShop(Item[] items) {
        this.items = items;
    }

    void updateQuality() {
        for (Item item : items) {
            updateItemSellIn(item);
            updateItemQuality(item);
        }
    }

    private void updateItemSellIn(Item item) {
        if (isGoldCoin(item)) {
            // "sell-in" never changes for Gold Coins
        }
        else {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateItemQuality(Item item) {
        if (isAgedBrie(item)) {
            updateAgedBrieQuality(item);

        } else if (isBackstagePass(item)) {
            updateBackstagePassQuality(item);

        } else if (isGoldCoin(item)) {
            // Gold Coins don't change the "quality"

        } else {
            updateOtherItemQuality(item);
        }

        enforceQualityLimit(item);
    }

    private void updateAgedBrieQuality(Item item) {
        int change = 1;
        if (hasSellByDatePassed(item)) {
            change = change * 2;
        }
        item.quality = item.quality + change;
    }

    private void updateBackstagePassQuality(Item item) {
        if (hasSellByDatePassed(item)) {
            item.quality = 0;
        }
        else {
            int change;
            if (item.sellIn <= 5) {
                change = 3;
            } else if (item.sellIn <= 10) {
                change = 2;
            } else {
                change = 1;
            }
            item.quality = item.quality + change;
        }
    }

    private void updateOtherItemQuality(Item item) {
        int change = -1;
        if (hasSellByDatePassed(item)) {
            change = change * 2;
        }
        item.quality = item.quality + change;
    }

    private void enforceQualityLimit(Item item) {
        if (!isGoldCoin(item)) {
            if (item.quality < 0) {
                item.quality = 0;
            }
            if (item.quality > 50) {
                item.quality = 50;
            }
        }
    }

    private boolean hasSellByDatePassed(Item item) {
        return item.sellIn < 0;
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage Pass");
    }

    private boolean isGoldCoin(Item item) {
        return item.name.equals("Gold Coin");
    }
}