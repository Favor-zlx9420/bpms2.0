package com.rong.cms.consts;

/**
 * creator : whh-lether
 * date    : 2018/11/26 9:40
 * desc    :
 **/
public class CmsEnumContainer {
    public enum Top {
        /**
         * 未置顶
         */
        DID_NOT_ANSWER(Boolean.FALSE,"未置"),
        /**
         * 已置顶
         */
        ANSWER_UP(Boolean.TRUE,"置顶"),
        ;
        private final boolean value;
        private final String desc;
        Top(boolean value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public boolean getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 是否发布
     * @author lether
     *
     */
    public enum Published {
        /**
         * 未发布
         */
        NOT_RELEASE(0,"未发布"),
        /**
         *  已发布
         */
        RELEASED(1,"已发布")
        ;
        private final int value;
        private final String desc;
        Published(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 评论相关
     * @author lether
     *
     */
    public enum Commentable {
        /**
         *  禁止评论
         */
        PROHIBIT_COMMENTS(false,"禁止"),
        /**
         *  允许评论
         */
        ALLOW_COMMENTS(true,"允许"),
        ;
        private final boolean value;
        private final String desc;
        Commentable(boolean value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public boolean getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }















}
