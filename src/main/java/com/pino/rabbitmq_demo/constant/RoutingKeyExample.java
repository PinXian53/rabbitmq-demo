package com.pino.rabbitmq_demo.constant;

public enum RoutingKeyExample {

    QUICK_ORANGE_RABBIT("quick.orange.rabbit"),
    LAZY_ORANGE_ELEPHANT("lazy.orange.elephant"),
    QUICK_ORANGE_FOX("quick.orange.fox"),
    LAZY_BROWN_FOX("lazy.brown.fox");

    String val;

    RoutingKeyExample(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
