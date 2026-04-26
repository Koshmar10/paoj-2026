package com.pao.laboratory07.exercise1;

public enum OrderState {
    PLACED {
        @Override
        public OrderState next() { return PROCESSED; }
    },
    PROCESSED {
        @Override
        public OrderState next() { return SHIPPED; }
    },
    SHIPPED {
        @Override
        public OrderState next() { return DELIVERED; }
    },
    DELIVERED {
        @Override
        public OrderState next() { return DELIVERED; }
        @Override
        public boolean isFinal() { return true; }
    },
    CANCELED {
        @Override
        public OrderState next() { return CANCELED; }
        @Override
        public boolean isFinal() { return true; }
    };

    public abstract OrderState next();

    public boolean isFinal() { return false; }
}
