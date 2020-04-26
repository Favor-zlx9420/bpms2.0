package com.rong.common.consts;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-15 09:53
 **/
public class BusinessEnumContainer {

    /**
     * 搜索类型
     */
    public enum SearchType {
        私募产品(1,"私募产品"),
        公司(2,"公司"),
        经理(3,"经理"),
        公募(4,"公募")
        ;
        private final int value;
        private final String desc;
        SearchType(int value, String desc){
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

    public enum InvestStrategyType {
        股票策略(1,"股票策略"),
        相对价值(2,"相对价值"),
        宏观策略(3,"宏观策略"),
        事件驱动(4,"事件驱动"),
        组合基金(5,"组合基金"),
        债券策略(6,"债券策略"),
        管理期货(7,"管理期货"),
        复合策略(8,"复合策略"),
        其他策略(9,"其他策略")
        ;
        private final int value;
        private final String desc;
        InvestStrategyType(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(int value) {
            for (InvestStrategyType ele : values()) {
                if(ele.getValue() == value ) return ele.getDesc();
            }
            return null;
        }
    }

    public enum StatusType {
        正常(100,"正常"),
        开放运行(101,"开放运行"),
        封闭运行(102,"封闭运行"),
        延期清算(103,"延期清算"),
        募集中(104,"募集中"),
        终止(200,"终止"),
        提前清算(201,"提前清算"),
        到期清算(202,"到期清算"),
        发行失败(301,"发行失败")
        ;
        private final int value;
        private final String desc;
        StatusType(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(int value) {
            for (StatusType ele : values()) {
                if(ele.getValue() == value ) return ele.getDesc();
            }
            return null;
        }
    }

    public enum WindowType {
        近一个月(1,"近一个月"),
        近三个月(2,"近三个月"),
        近半年(3,"近半年"),
        年初至今(4,"年初至今"),
        近一年(5,"近一年"),
        近两年(6,"近二年"),
        近三年(7,"近三年"),
        成立以来(8,"成立以来"),
        ;
        private final int value;
        private final String desc;
        WindowType(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(int value) {
            for (WindowType ele : values()) {
                if(ele.getValue() == value ) return ele.getDesc();
            }
            return null;
        }
    }

    public enum RankingSearchTyp {
        区间收益("0","区间收益"),
        年度收益("1","年度收益")
        ;
        private final String value;
        private final String desc;
        RankingSearchTyp(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (RankingSearchTyp ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }

    public enum RankingIntervalOrderBy {
        nav("nav","nav"),
        returnOfEstablish("returnOfEstablish","return_of_establish"),
        one("one","RETURN_RATE_1M"),
        two("two","RETURN_RATE_3M"),
        three("three","RETURN_RATE_6M"),
        four("four","RETURN_RATE_1Y"),
        five("five","RETURN_RATE_2Y"),
        six("six","RETURN_RATE_3Y"),
        seven("seven","RETURN_RATE_5Y"),
        eight("eight","RETURN_RATE_EST"),
        nine("nine","RETURN_RATE_YTD")
        ;
        private final String value;
        private final String desc;
        RankingIntervalOrderBy(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (RankingIntervalOrderBy ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }

    public enum RankingYearOrderBy {
        nav("nav","nav"),
        returnOfEstablish("returnOfEstablish","return_of_establish"),
        one("one","RETURN_RATE_EST"),
        two("two","RETURN_RATE_YTD"),
        three("three","RETURN_RATE_1A"),
        four("four","RETURN_RATE_2A"),
        five("five","RETURN_RATE_3A"),
        six("six","RETURN_RATE_4A"),
        seven("seven","RETURN_RATE_5A"),
        eight("eight","RETURN_RATE_6A"),
        nine("nine","RETURN_RATE_7A")
        ;
        private final String value;
        private final String desc;
        RankingYearOrderBy(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (RankingYearOrderBy ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }

    public enum CompanyRankingOrderBy {
        numAll("numAll","NUM_ALL"),
        sharpeRatio("sharpeRatio","SHARPE_RATIO_"),
        returnRate("returnRate","RETURN_RATE_"),
        nav("nav","NAV"),
        returnAccum("returnAccum", "RETURN_ACCUM")
        ;
        private final String value;
        private final String desc;
        CompanyRankingOrderBy(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value, String suffix) {
            for (CompanyRankingOrderBy ele : values()) {
                if(ele.getValue().equals(value)) {
                    //sharpeRatio和returnRate需要拼接后缀
                    if (ele.getDesc().equals("SHARPE_RATIO_") || ele.getDesc().equals("RETURN_RATE_")) {
                        return ele.getDesc() + suffix;
                    }
                    return ele.getDesc();
                }
            }
            return null;
        }
    }

    public enum CompanyRankingSuffix {
        one("1","1M"),
        two("2","3M"),
        three("3","6M"),
        four("4","1Y"),
        five("5","2Y"),
        six("6","3Y"),
        seven("7","EST"),
        eight("8","YTD")
        ;
        private final String value;
        private final String desc;
        CompanyRankingSuffix(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (CompanyRankingSuffix ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }

    public enum SearchPfundOrderBy {
        establishDate("establishDate","a.ESTABLISH_DATE"),
        nav("nav","d.nav"),
        accumNav("accumNav","f.return_accum"),
        annualTotalReturn("annualTotalReturn","g.return_of_establish")
        ;
        private final String value;
        private final String desc;
        SearchPfundOrderBy(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (SearchPfundOrderBy ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }

    public enum SearchPfundInstOrderBy {
        numAll("numAll","b.NUM_ALL"),
        recordDate("recordDate","a.RECORD_DATE"),
        totalReturn("totalReturn","b.RETURN_RATE_EST")
        ;
        private final String value;
        private final String desc;
        SearchPfundInstOrderBy(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (SearchPfundInstOrderBy ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }

    public enum SearchPfundManagerOrderBy {
        numAll("numAll","NUM_ALL"),
        totalReturn("totalReturn","RETURN_RATE_EST")
        ;
        private final String value;
        private final String desc;
        SearchPfundManagerOrderBy(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (SearchPfundManagerOrderBy ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }

    public enum SearchFundClassOrderBy {
        establishDate("establishDate","a.ESTABLISH_DATE"),
        nav("nav","c.nav"),
        accumNav("accumNav","f.return_rate_est"),
        annualTotalReturn("annualTotalReturn","g.WEEKLY_YIELD")
        ;
        private final String value;
        private final String desc;
        SearchFundClassOrderBy(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (SearchFundClassOrderBy ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }

    public enum FundType {
        私募(0,"私募"),
        公募(1,"公募"),
        ;
        private final int value;
        private final String desc;
        FundType(int value, String desc){
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


    public enum RankingSimpleSuffix {
        one("1","1M"),
        two("2","3M"),
        three("3","6M"),
        four("4","1Y"),
        five("5","2Y"),
        six("6","3Y"),
        seven("7","5Y"),
        eight("8","EST"),
        nine("9","YTD")
        ;
        private final String value;
        private final String desc;
        RankingSimpleSuffix(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
        public static String getDesc(String value) {
            for (RankingSimpleSuffix ele : values()) {
                if(ele.getValue().equals(value)) return ele.getDesc();
            }
            return null;
        }
    }
}
