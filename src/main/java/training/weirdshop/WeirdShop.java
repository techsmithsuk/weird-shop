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
        if (!isGoldCoin(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateItemQuality(Item item) {
        if (isAgedBrie(item) || isBackstagePass(item)) {
            item.quality = item.quality + 1;

            if (isBackstagePass(item)) {
                if (item.sellIn <= 10) {
                    item.quality = item.quality + 1;
                }

                if (item.sellIn <= 5) {
                    item.quality = item.quality + 1;
                }
            }
        }
        else {
            if (!isGoldCoin(item)) {
                item.quality = item.quality - 1;
            }
        }

        if (item.sellIn < 0) {
            if (isAgedBrie(item)) {
                item.quality = item.quality + 1;
            } else if (isBackstagePass(item)) {
                item.quality = 0;
            } else {
                if (!isGoldCoin(item)) {
                    item.quality = item.quality - 1;
                }
            }
        }

        enforceQualityLimit(item);
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