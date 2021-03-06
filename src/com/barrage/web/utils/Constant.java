package com.barrage.web.utils;

import java.nio.charset.Charset;

public interface Constant
{
    Charset GBK = Charset.forName("GBK");
    
    Charset UTF8 = Charset.forName("utf-8");
    /**
     * url 路径分隔符   
     */
    String url_separator = "/";
    
    /**
     * 数据库，数据默认分隔符
     */
    String data_separator = ",";

    /**
     * 注册用户
     */
    String DONOR_TYPE_IN = "InternalPers";
    
    /**
     * 企业用户
     */
    public static String enterpriseUsers = "enterpriseUsers"; 
    /**
     * 个人用户
     */
    public static String individualUsers = "individualUsers";
    
    /**
     * 未注册用户
     */
    String DONOR_TYPE_OUT = "externalPers";
    
    /**
     * 企业助善
     */
    String COMPANY_GOODHELP = "enterpriseGoodHelp";

    /**
     *  企业助捐
     */
    String ENTERPRISE_DONATION = "enterpriseDonation";

    interface ConfigParam
    {
        //焦点图
        String focusMap = "focusMap";
        
        //文件基本路径
        String fileBasicURL = "fileBasicURL";
        
        //热门项目
        String hotItem = "hotItem";
    }
    
    /**
     * 配置文件的key值
     * @author phx
     *
     */
    interface Config
    {
        String fileStoragePath = "file.storage.path";
        
        String res_url_pic = "res.url.pic";
    }
    
    /**
     * 未捐款、未打款、未支付
     */
    public static final int PAY_STATE_300 = 300;
    
    /**
     * 失败
     */
    public static final int PAY_STATE_301 = 301;
    
    /**
     * 成功
     */
    public static final int PAY_STATE_302 = 302;
    
    /**
     * 批量捐成功 大订单待处理
     */
    public static final int PAY_STATE_305 = 305;
    
    /**
     * 资金进
     */
    public static final int CAPITAL_IN = 0;
    
    /**
     * 资金出
     */
    public static final int CAPITAL_OUT = 1;
    
    /**
     * 资金进(转捐)
     */
    public static final Integer TRANSFER_DONATE_INT = 11;
    /**
     * 资金进(批量捐)
     */
    public static final Integer BATCH_DONATE_INT = 12;
    /**
     * 银行卡默认选中
     */
    public static final int CARD_SELECTED = 0;
    
    /**
     * 银行卡默认不选中
     */
    public static final int CARD_NOSELECTED = 1;

}
