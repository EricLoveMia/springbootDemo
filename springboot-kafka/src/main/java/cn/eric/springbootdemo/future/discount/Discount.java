package cn.eric.springbootdemo.future.discount;

public enum Discount {
    /**
     * 没有等级
     **/
    NONE(0),
    /**
     * 银
     **/
    SILVER(5),
    /**
     * 金
     **/
    GOLD(10),
    /**
     * 没有等级
     **/
    PLATNUM(15),
    /**
     * 钻石
     **/
    DIAMOND(20);

    private final int percent;

    Discount(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }
}
