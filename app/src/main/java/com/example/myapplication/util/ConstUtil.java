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

        public static final int TYPE_LAST = 2;
        public static final int TYPE_NOT_LAST = 3;

        public static String getTypeDesc(int code){
            String desc = "未知种类";
            switch (code){
                case TYPE_PERSONAL:
                    desc = "个人";
                    break;
                case TYPE_TEAM:
                    desc = "团队";
                    break;
                case TYPE_LAST:
                    desc = "最后一层计划";
                    break;
                case TYPE_NOT_LAST:
                    desc = "非最后一层计划";
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


    public static class MailSendStatus{
        public static final int REACHED = 1;
        public static final int UNREACHED = 0;

        public static String getTypeDesc(int code){
            String desc = "未知状态";
            switch (code){
                case REACHED:
                    desc = "•已到达";
                    break;
                case UNREACHED:
                    desc = "•未到达";
                    break;
            }
            return desc;
        }

    }


    public static class BulletinType{
        public static final int HELP = 0;
        public static final int ANNOUNCEMENT = 1;

        public static String getTypeDesc(int code){
            String desc = "未知种类";
            switch (code){
                case HELP:
                    desc = "帮助";
                    break;
                case ANNOUNCEMENT:
                    desc = "公告";
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


    public static class RequestCode{
        public static final int REQUEST_CODE_UPDATE = 1;
        public static final int REQUEST_CODE_DELETE = 2;
        public static final int REQUEST_CODE_INCREASE = 3;
        public static final int REQUEST_CODE_DETAIL = 4;
    }


    public static class ResponseCode{
        public static final int RESPONSE_CODE_REFRESH = 1;
        public static final int RESPONSE_CODE_FINISH = 2;
    }


    public static class PlanHolidayStatus{
        public static final int PLAN_ON_HOLIDAY = 1;
        public static final int PLAN_NOT_ON_HOLIDAY = 2;
    }


    public static class PlanPunchStatus{
        public static final int PLAN_ON_PUNCH = 1; //打卡
        public static final int PLAN_NOT_ON_PUNCH = 2; //未打卡
    }


    public static class SysNoteType{
        public static final int SYS_NOTE_SEATMATE_INVITATION = 1;   //邀请
        public static final int SYS_NOTE_SEATMATE_SUCCESS = 2;  //成功
        public static final int SYS_NOTE_SEATMATE_FAIL = 3; //失败
        public static final int SYS_NOTE_SEATMATE_RECEIVE = 4;  //接受
        public static final int SYS_NOTE_SEATMATE_REJECT = 5;   //拒绝
    }


    public static class SysNoteRead{
        public static final int SYS_NOTE_ON_READ = 1; //已读
        public static final int SYS_NOTE_NOT_ON_READ = 2; //未读
    }


    public static class PunchAttitude{
        public static final int PUNCH_STATISFIED = 1;
        public static final int PUNCH_SIMPLE = 2;
        public static final int PUNCH_DISSTATISFIED = 3;

        public static String getTypeDesc(int code){
            String desc = "未知";
            switch(code){
                case PUNCH_STATISFIED:
                    desc = "满意";
                    break;
                case PUNCH_SIMPLE:
                    desc = "一般";
                    break;
                case PUNCH_DISSTATISFIED:
                    desc = "不满意";
                    break;
            }
            return desc;
        }
    }
}
