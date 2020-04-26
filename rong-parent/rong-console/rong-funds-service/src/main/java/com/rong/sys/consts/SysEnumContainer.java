package com.rong.sys.consts;

public class SysEnumContainer {

    /**
     * banner对应页面
     *
     * @author lether
     */
    public enum BannerPage {
        /**
         * PC首页
         */
        PC_HOME_PAGE(0, "PC首页"),
        /**
         * APP首页
         */
        APP_HOMEPAGE(5, "APP首页"),
        /**
         * H5首页
         */
        H5_HOMEPAGE(8, "H5首页"),
        /**
         * 路演首页
         */
        ROADSHOW_HOMEPAGE(11, "路演首页"),
        ;
        private final int value;
        private final String desc;

        BannerPage(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 公共类型
     *
     * @author lether 2016年03月19日12:05:42
     */
    public enum SysType {
        /**
         * 图片信息
         */
        PICTORIAL_INFORMATION(3, "imagesams"),
        /**
         * 文件类别
         */
        FILE_CLASS(4, "文件类别"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        SysType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 定时任务日志类型
     */
    public enum TaskLogType {
        /**
         * 日志收集
         */
        LOG_COLLECTION(0, "日志收集"),
        /**
         * 图片定存
         */
        IMAGE_BANK_DEPOSIT(1, "图片定存"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        TaskLogType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }


    /**
     * 字典类型
     */
    public enum DictionaryType {
        /**
         * 会员相关
         */
        MEMBERS_RELATED(6, "会员相关"),
        /**
         * 后台管理用户
         */
        BACKGROUND_MANAGEMENT_USER(209, "后台管理用户"),
        /**
         * 系统配置
         */
        SYSTEM_CONFIGURATION(125, "系统配置"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        DictionaryType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

    }

    /**
     * 广告类型
     */
    public enum AdvertiseType {
        /**
         * 信息广告
         */
        INFORMATION_ON_ADVERTISING(1, "信息广告"),
        /**
         * 产品广告
         */
        PRODUCT_ADVERTISING(2, "产品广告"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        AdvertiseType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 标签类型
     */
    public enum LabelType {
        /**
         * 商品标签
         */
        BRAND_LABEL(1, "商品标签"),
        /**
         * 信息标签
         */
        THE_LABEL_INFORMATION(2, "信息标签"),
        /**
         * 图片标签
         */
        IMAGE_LABEL(3, "图片标签"),
        /**
         * 路演标签
         */
        ROADSHOW_LABEL(4, "路演标签"),
        /**
         * 系统消息
         */
        SYSTEM_MESSAGES(5, "系统消息"),
        /**
         * 用户标签
         */
        USER_LABEL(6, "用户标签"),
        /**
         * 文件类别
         */
        FILE_CLASS(7, "文件类别"),
        /**
         * 直营店产品标签
         */
        PRODUCT_LABEL_OF_DIRECT_SALE_STORE(8, "直营店产品标签"),
        /**
         * 直营店标签
         */
        STORE_LABEL(9, "直营店标签"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        LabelType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }


}
