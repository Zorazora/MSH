package ui.viewcontroller.staff;

import bl.blfactory.BLFactoryImpl;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mydatepicker.MyDatePicker;
import component.statebutton.StateButton;
import javafx.fxml.FXML;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_EnterpriseVO;

/**
 * Created by vivian on 16/12/9.
 */
public class HotelPromotion_EnterpriseAddViewController {
    private PromotionVO promotionVO;
    private HotelPromotionViewController hotelPromotionViewController;
    private PromotionBLService promotionBLService;

    private boolean isEdit = false;
    private String promotionID = null;
    private UserBLInfo userBLInfo = new BLFactoryImpl().getUserBLInfo_Staff();

    @FXML
    private CommonTextField nameTextField;

    @FXML
    private StateButton typeButton;

    @FXML
    private CommonTextField discountTextField;

    @FXML
    private MyDatePicker startTime;

    @FXML
    private MyDatePicker endTime;

    @FXML
    private CommonTextField enterpriseTextField;


    public void setHotelPromotionViewController(HotelPromotionViewController hotelPromotionViewController) {
        this.hotelPromotionViewController = hotelPromotionViewController;

        typeButton.setText(PromotionType.Hotel_Enterprise.getType());
        typeButton.setColorProperty(PromotionType.Hotel_Enterprise.getColor());
    }

    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    public void clickCancelButton(){
        hotelPromotionViewController.refreshHotelPromotionList();
        if(isEdit){
            hotelPromotionViewController.back();
        }
        hotelPromotionViewController.back();
    }

    public void clickSaveButton(){
        promotionVO = new Promotion_EnterpriseVO(nameTextField.getText(),PromotionType.Hotel_Enterprise, Double.valueOf(discountTextField.getText()),
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()),
                enterpriseTextField.getText(), userBLInfo.getHotelIDByStaffID(userBLInfo.getCurrentStaffID()));
        if(isEdit){
            promotionVO.promotionID = promotionID;
            promotionBLService.updatePromotion(promotionVO);
            System.out.println("update successfully!");
        }else {
            promotionBLService.addPromotion(promotionVO);
            System.out.println("save successfully!");
        }

    }

    public void showEditView(PromotionVO promotionVO){
        Promotion_EnterpriseVO promotion_enterpriseVO = (Promotion_EnterpriseVO) promotionVO;
        nameTextField.setText(promotionVO.promotionName);
        discountTextField.setText(promotionVO.promotionDiscount+"");
        enterpriseTextField.setText(promotion_enterpriseVO.enterpriseName+"");
        isEdit = true;
        this.promotionID = promotionVO.promotionID;
    }
}
