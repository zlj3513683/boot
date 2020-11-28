package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账单信息
 * </p>
 *
 * @author zoulinjun
 * @since 2020-08-10
 */
@Data
@Accessors(chain = true)
@TableName("buss_bill_info")
public class BussBillInfo extends Model<BussBillInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 账单编号
     */
    @TableId("bill_id")
    private String billId;
    /**
     * 机构编号
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 账套编号
     */
    @TableField("billset_id")
    private String billsetId;
    /**
     * 账单种类
     */
    @TableField("bill_type")
    private String billType;
    /**
     * 小区编号
     */
    @TableField("community_no")
    private String communityNo;
    /**
     * 月度
     */
    @TableField("bill_month")
    private String billMonth;
    /**
     * 账单名称
     */
    @TableField("bill_name")
    private String billName;
    /**
     * 门牌号/停车卡号
     */
    @TableField("door_car_no")
    private String doorCarNo;
    /**
     * 收费金额合计
     */
    @TableField("charge_total")
    private BigDecimal chargeTotal;
    /**
     * 模板编号
     */
    @TableField("template_id")
    private Integer templateId;
    /**
     * 广告1编号
     */
    @TableField("adv_1")
    private Integer adv1;
    /**
     * 广告2编号
     */
    @TableField("adv_2")
    private Integer adv2;
    /**
     * 广告3编号
     */
    @TableField("adv_3")
    private Integer adv3;
    /**
     * 广告4编号
     */
    @TableField("adv_4")
    private Integer adv4;
    /**
     * 缴费截止日期
     */
    @TableField("pay_abort_date")
    private Date payAbortDate;
    /**
     * 短信通知
     */
    @TableField("message_notice")
    private String messageNotice;
    /**
     * 邮件通知
     */
    @TableField("mail_notice")
    private String mailNotice;
    /**
     * 微信通知
     */
    @TableField("wechat_notice")
    private String wechatNotice;
    /**
     * APP通知
     */
    @TableField("app_notice")
    private String appNotice;
    /**
     * 审批状态
     */
    @TableField("audit_status")
    private String auditStatus;
    /**
     * 审批人
     */
    @TableField("audit_user")
    private String auditUser;
    /**
     * 审批日期
     */
    @TableField("audit_date")
    private Date auditDate;
    /**
     * 发送状态(0 未发送 1 发送失败 2 发送成功)
     */
    @TableField("send_status")
    private String sendStatus;
    /**
     * 缴费状态 0 未付款 1 已付款 2 部分付款 3 已撤销 
     */
    @TableField("pay_status")
    private String payStatus;
    /**
     * 申诉状态
     */
    @TableField("appeal_status")
    private String appealStatus;
    /**
     * 控制状态
     */
    @TableField("control_status")
    private String controlStatus;
    /**
     * 催缴次数
     */
    @TableField("urge_pay_count")
    private Integer urgePayCount;
    /**
     * 最后催缴日期
     */
    @TableField("last_urge_pay_date")
    private Date lastUrgePayDate;
    /**
     * 最后更新日期
     */
    @TableField("last_upd_time")
    private Date lastUpdTime;
    /**
     * 最后更新人
     */
    @TableField("last_upd_user")
    private String lastUpdUser;
    /**
     * 制单人
     */
    @TableField("make_user")
    private String makeUser;
    /**
     * 制单时间
     */
    @TableField("make_time")
    private Date makeTime;
    /**
     * 缴费日期
     */
    @TableField("charge_date")
    private Date chargeDate;
    /**
     * 开始日期
     */
    @TableField("start_date")
    private String startDate;
    /**
     * 结束日期
     */
    @TableField("end_date")
    private String endDate;
    /**
     * 结算状态 0-未结算 1-已结算
     */
    @TableField("settle_st")
    private String settleSt;
    /**
     * 备注
     */
    private String remark;
    /**
     * 账单编号:YYMMDD+HHMMSS+6位自增(100000)+2位类型
     */
    @TableField("base_bill_id")
    private String baseBillId;
    /**
     * 批次号:YYMMDD+HHMMSS+6位自增(100000)
     */
    @TableField("batch_id")
    private String batchId;
    /**
     * 1-月度
2-季度
3-半年
4-年度
     */
    @TableField("bill_period")
    private String billPeriod;


    @Override
    protected Serializable pkVal() {
        return this.billId;
    }

}
