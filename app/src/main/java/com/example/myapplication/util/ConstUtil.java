package com.example.myapplication.util;

public class ConstUtil {

    public static class SeatmateStatus{
        public static final int STATUS_FAIL = 0;
        public static final int STATUS_SUCCEED = 1;
        public static final int STATUS_PROCESSING = 2;

        public static String getStatusDesc(int code){
            String desc = "未知状态";
            switch (code){
                case STATUS_FAIL:
                    desc = "失败";
                    break;
                case STATUS_SUCCEED:
                    desc = "成功";
                    break;
                case STATUS_PROCESSING:
                    desc = "正在进行";
                    break;
            }
            return desc;
        }
    }

    public static class PlanType{
        public static final int TYPE_PERSONAL = 0;
        public static final int TYPE_TEAM = 1;

        public static String getTypeDesc(int code){
            String desc = "未知种类";
            switch (code){
                case TYPE_PERSONAL:
                    desc = "个人";
                    break;
                case TYPE_TEAM:
                    desc = "团队";
                    break;
            }
            return desc;
        }
    }

    public static class MailPublicType{
        public static final int TYPE_NOTPUBLIC = 0;
        public static final int TYPE_PUBLIC = 1;

        public static String getTypeDesc(int code){
            String desc = "未知种类";
            switch (code){
                case TYPE_PUBLIC:
                    desc = "公开";
                    break;
                case TYPE_NOTPUBLIC:
                    desc = "不公开";
                    break;
            }
            return desc;
        }
    }

    public static class MailDealyType{
        public static final int TYPE_NOTDELAY = 0;
        public static final int TYPE_DELAY = 1;

        public static String getTypeDesc(int code){
            String desc = "未知种类";
            switch (code){
                case TYPE_DELAY:
                    desc = "延迟";
                    break;
                case TYPE_NOTDELAY:
                    desc = "不延迟";
                    break;
            }
            return desc;
        }
    }
}
