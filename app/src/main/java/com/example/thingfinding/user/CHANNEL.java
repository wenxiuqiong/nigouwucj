package com.example.thingfinding.user;

public enum CHANNEL {

        MY("全部订单", 0),

        DISCORY("客户选择中", 1),

        FRIEND("交易成功", 2),

        VIDEO("交易失败", 3);

        //所有类型标识
        public static final int MINE_ID = 0;
        public static final int DISCORY_ID = 1;
        public static final int FRIEND_ID =2;
        public static final int VIDEO_ID = 3;

        private final String key;
        private final int value;

        CHANNEL(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getKey() {
            return key;
        }
}

