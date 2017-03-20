package com.frame.exception;

/**
 * 验证规则列表（规则、错误消息、错误码）
 * ValidatorPattern
 * author yefeng
 */
public enum VP {
	
    // 验证表达式
    /**
     * 必须是数字
     */
    NUMBER("^\\d+$", "必须是1位以上的数字", "100000",0),
    /**
     * 必须是10位以内的整数
     */
    NUMBER_MAX_LENGTH10("^\\d{1,10}$", "必须是10位以内的整数", "100001",0),
    /**
     * 必须是0或1
     */
    VALUE_0_OR_1("^0|1$", "必须是0或1", "100002",0),
    /**
     * 必须是中文或英文
     */
    REALNAME("^[\\Α-\\￥]+|[A-Za-z]+$", "必须是中文或英文", "100003",0),
    /**
     * 用户名格式
     */
    ACCOUNT("^[a-zA-Z][a-zA-Z0-9_]{4,15}$", "用户名不合法(字母开头,允许5-16个字,允许字母数字下划线)",
            "100004",0),
    /**
     * 必须是6~30位以内的整数
     */
    NUMBER_MAX_LENGTH30("^\\d{6,30}$", "必须是6~30位以内的整数", "100005",0),
    /**
     * 密码格式
     */
    PASSWORD("^\\w{6,30}$", "密码必须是长度6~30,由字母、数字、下划线组成!", "100006",1),
    /**
     * 手机号码格式
     */
    MOBILE("^\\d{11}$", "手机号格式不正确", "100007",0),
    /**
     * 邮箱格式
     */
    EMAIL("^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",
            "邮箱格式不正确", "100008",0),

    STRING_4_6("^.{4,6}$", "字符长度必须4至6位", "100009",0),

    /**
     * 金额格式校验:
     * 除了个位，十位以上不能以0开头
     * 小数部分可有可无
     * 小数点后可以一位或者二位
     */
    MONEY("^([1-9]\\d*|0)(\\.\\d{1,2})?$", "金额格式不正确", "100010",0),

    STRING_10("^.{1,10}$", "字符长度1至10位", "100011",0),

    STRING_20("^.{1,20}$", "字符长度1至20位", "100012",0),

    STRING_30("^.{1,30}$", "字符长度1至30位", "100013",0),

    STRING_50("^.{1,50}$", "字符长度1至50位", "100014",0),

    STRING_200("^.{1,200}$", "字符长度1至200位", "100015",0),

    FLOAT("^[+-]?\\d+(\\.\\d+)?$", "float数字格式异常", "100016",0),

    NUMBER_MAX_5("^[1-5]{1}$", "必须是5以内的正整数!", "100017",0),

    STRING_500("^.{1,500}$", "字符长度1至500位", "100018",0),

    DATE_TIME_ERROR("时间格式不正确", "100019",0),

    STRING_11("^.{11}$", "字符长度11位", "100020",0),

    NUMBER_MAX_LENGTH6("^\\d{1,6}$", "必须是6位以内的整数", "100021",0),

    STRING_100("^.{1,100}$", "字符长度1至50位", "100022",0),

    STRING_15("^.{1,15}$", "字符长度1至15位", "100023",0),

    LONGITUDE("^-?((0|[1-9]\\d?|1[1-7]\\d)(\\.\\d{1,6})?|180(\\.0{1,6})?)?$", "经度范围±180", "100023",0),

    LATITUDE("^^-?((0|[1-8]\\d?)(\\.\\d{1,6})?|90(\\.0{1,6})?)?$", "纬度范围±90", "100023",0),

    //----------服务器响应结果----------//

    /**
     * 正常请求
     */
    SUCCESS("", "000000",1),

    ERROR_REQ("非法请求", "000001",0),

    ERROR_VAL_CODE_LOSE("验证码已失效,请重新获取", "000002",0),

    ERROR_NO_ROLE("无访问权限", "000003",0),

    ERROR_NO_LOGIN("未登录,请先登录！", "000004",0),

    ERROR_EXISTS_CATALOG("栏目名称已存在", "000005",0),

    ERROR_EXISTS_ROLE_NAME("角色名称已存在！", "000007",0),

    ERROR_SYSTEM("系统异常,请联系管理员！", "000008",0),

    ERROR_EXISTS_ACCOUNT("账号已存在", "000009",0),

    ERROR_EXISTS_MOBILE("手机号已存在", "000010",0),

    ERROR_EXISTS_EMAIL("邮箱号已存在", "000011",0),

    ERROR_LOGIN_ACC_PWD("账号或密码错误", "000012",0),

    ERROR_PWD("密码错误", "000013",0),

    ERROR_LOGIN_DISABLE("该用户已被禁用", "000014",0),

    ERROR_VAL_CODE("亲^_^图形验证码错误哦", "000017",0),

    ERROR_CHILEN_COUNT("还有个{0}子集未删除,删除失败", "000018",0),

    ERROR_FUND_NO_EXPS("可用资金不足", "000019",0),

    ERROR_FUND_NO_FREEZE("可以资金不足,冻结失败", "000020",0),

    ERROR_FUND_UNFREEZE("冻结资金不足,解冻失败", "000021",0),

    ERROR_UPLOAD_TYPE("上传文件格式不正确", "000022",0),

    ERROR_UPLOAD_SIZE("上传文件大小超过限制", "000023",0),

    ERROR_UPLOAD_ALLOW_COUNT("上传图片数量超过限制", "000024",0),

    ERROR_UPLOAD_NULL("没有文件,上传失败", "000025",0),

    ERROR_VAL_MOBILE_ERROR("发送短信手机号与提交手机号不一致", "000026",0),

    ERROR_LOGIN_OVERDUE("登录已过期,请重新登录", "000027",0),

    ERROR_LONGITUDE_LATITUDE("经纬度不正确", "000028",0),

    ERROR_SMS_VAL_CODE("短信验证码错误", "000029",0),

    ERROR_COMMENT_EXISTS("已评论", "000030",0),

    ERROR_RED_PACKAGE_NOT_EXISTS("红包已使用", "000031",0),

    ERROR_COUPON_NOT_EXISTS("优惠券已使用", "000032",0),

    ERROR_USABLE_ERROR("余额支付出错", "000033",0),

    ERROR_TOKEN_INVALID("令牌已失效,请重新获取", "000034",0),

    ERROR_USER_COUPON_IS_GET("用户已领取商家优惠券", "000035",0),

    ERROR_USER_COUPON_NOT_FIND("商家没有优惠券,敬请期待", "000036",0),

    ERROR_MERCHANT_NOT_FIND("商家不存在", "000037",0),

    ERROR_MERCHANT_EXISTS("商户已存在", "000038",0),

    ERROR_MERCHANT_GROUP_EXIST_PRODUCT("请先删除该分组下的产品", "000039",0),

    SUCCESS_ADD("添加成功", "000040",0),

    SUCCESS_DELETE("删除成功", "000041",0),

    SUCCESS_UPDATE("修改成功", "000042",0),

    SUCCESS_OPERATE("操作成功", "000043",0),

    ERROR_OPERATE_TIMEOUT("系统繁忙,请稍后再试", "000044",0),

    ERROR_USABLE_NOT_ENOUGH("余额不足", "000045",0),

    ERROR_NOT_BINDING("未绑定快递中心,请联系管理员", "000046",0),

    ERROR_MERCHANT_REST("店铺休息中", "000047",0),

    ERROR_BUSINESS_TIME("营业时间格式异常", "000048",0),

    ERROR_PICKUP_NOT_FINISH("取件未完成,请稍后再试", "000049",0),

    ERROR_FRESH_ADD_CONSIGNEE_OVERFLOW("收货人最多10个,可以将以前不用的地址修改并使用!", "000050",0),

    ERROR_FRESH_ADD_ORDER_STANDARD_NOT_FUND("您要的产品已下架", "000051",0),

    ERROR_FRESH_CLOSE_WORKS("商城休息中", "000052",0),

    ERROR_FRESH_REQUEST_STATUS("请联系客服尽快进行审核!", "000053",0),

    ERROR_FRESH_NOT_OPEN("下单开关未开启,请联系专属客服!", "000054",0),

    ERROR_FRESH_NOT_VIP("只有成为VIP才能下单,请联系专属客服!", "000055",0),

    ERROR_NO_EXISTS_MOBILE("手机号还未注册", "000056",0),

    ERROR_EXISTS_BINDING_WECHAT("您的微信已绑定过账号,请联系客服先解除绑定!", "000057",0),

    ERROR_PAY_ORDER_MONEY_LIMIT("下单金额必须大于{0}!", "000058",0),

    ERROR_NOT_EDIT_ORDER_STATUS("不可随意更改订单状态!", "000059",0);


    private String pattern;
    private String message;
    private String code;
    private int status;  //1是success 0是error

	VP(String message, String code,int status) {
        this.message = message;
        this.code = code;
    }
    
    VP(String pattern, String message, String code,int status) {
        this.pattern = pattern;
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public String getPattern() {
        return pattern;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }
    
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

    public String toString() {
        return "message:" + this.message + "-code:" + this.code + "-pattern:" + this.pattern+"-status:"+status;
    }
}
