package vo;

import po.PromotionPO;
import util.DateUtil;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_HotelSpecialDateVO extends Promotion_HotelVO{

    /**
     * 策略执行开始日期
     */
    public DateUtil startDate;

    /**
     * 策略执行结束日期
     */
    public DateUtil endDate;

    /**
     *
     * @param promotionID 策略编号
     * @param promotionType 策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate 策略起始日期
     * @param endDate 策略截止日期
     */
    public Promotion_HotelSpecialDateVO(String promotionID, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate) {
        super(promotionID, promotionType, promotionDiscount);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean equals(Object o){
        if (o instanceof Promotion_HotelSpecialDateVO) {
            Promotion_HotelSpecialDateVO promotion_HotelSpecialDateVO = (Promotion_HotelSpecialDateVO) o;
            return compareData(promotion_HotelSpecialDateVO);
        }
        return false;
    }

        public int hashCode() {
            return promotionID.hashCode();
        }

    private boolean compareData(Promotion_HotelSpecialDateVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate,this.startDate)
                && judgeEqual(pvo.endDate,this.endDate)
                && judgeEqual(pvo.promotionDiscount,this.promotionDiscount);
    }

    public PromotionPO toPO(Promotion_HotelSpecialDateVO pvo) {
        return new PromotionPO(pvo.promotionID, pvo.promotionType, pvo.promotionDiscount,
                pvo.startDate.toString(), pvo.endDate.toString(),
                null, null, 0, null, 0);
    }
}
