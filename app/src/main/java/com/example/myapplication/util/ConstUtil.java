package com.example.myapplication.util;

public class ConstUtil {

    public static class SeatmateStatus{
        public static final int STATUS_FAIL = 0;
        public static final int STATUS_SUCCEED = 1;
        public static final int STATUS_PROCESSING = 2;
        public static final int STATUS_WAITING_ANOTHER_RESPONSE = 3;
        public static final int STATUS_NONESENSE = 5;

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
                case STATUS_WAITING_ANOTHER_RESPONSE:
                    desc = "等待对方答复";
                    break;
                case STATUS_NONESENSE:
                    desc = "无意义的条目";
                    break;
            }
            return desc;
        }
    }

    public static class SeatmateReplyType{
        public static final int TYPE_APPROVE = 0;
        public static final int TYPE_REFUSE = 1;

        public static String getTypeDesc(int code){
            String desc = "未知种类";
            switch (code){
                case TYPE_APPROVE:
                    desc = "同意";
                    break;
                case TYPE_REFUSE:
                    desc = "拒绝";
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

    public static class TeamNumberLimit{
        public static final int Type_simple = 100;
        public static final int Type_advance = 300;

        public static String getTypeDesc(int code){
            String desc = "未知上限";
            switch(code){
                case Type_simple:
                    desc = "普通队伍";
                    break;
                case Type_advance:
                    desc = "进阶队伍";
                    break;
            }
            return desc;
        }
    }

}
