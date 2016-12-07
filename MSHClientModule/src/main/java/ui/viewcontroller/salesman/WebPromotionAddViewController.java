package ui.viewcontroller.salesman;

import bl.blfactory.BLFactoryImpl;
import bl.promotionbl.PromotionBLServiceImpl;
import bl.promotionbl.Promotion_WebClientGrade;
import bl.userbl.UserBLFactory;
import blservice.promotionblservice.PromotionBLService;
import blservice.userblservice.UserBLInfo;
import component.commontextfield.CommonTextField;
import component.mychoicebox.MyChoiceBox;
import component.mydatepicker.MyDatePicker;
import component.rectbutton.RectButton;
import component.statebutton.StateButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import util.DateUtil;
import util.PromotionType;
import vo.PromotionVO;
import vo.Promotion_ClientGradeVO;

import java.time.LocalDate;

/**
 * Created by vivian on 16/12/2.
 */
public class WebPromotionAddViewController {
    private PromotionVO promotionVO;
    private WebPromotionViewController webPromotionViewController;
    private PromotionBLService promotionBLService;

    @FXML
    private CommonTextField nameTextField;

    @FXML
    private MyChoiceBox typeChoiceBox;

    @FXML
    private StateButton typeButton;

    @FXML
    private MyChoiceBox levelChoiceBox;

    @FXML
    private MyChoiceBox cityChoiceBox;

    @FXML
    private MyChoiceBox placeChoiceBox;

    @FXML
    private MyDatePicker startTime;

    @FXML
    private MyDatePicker endTime;

    @FXML
    private RectButton cancelButton;

    @FXML
    private RectButton saveButton;

    public void setWebPromotionViewController(WebPromotionViewController webPromotionViewController){
        this.webPromotionViewController = webPromotionViewController;

         ObservableList observableList = FXCollections.observableArrayList();
        UserBLInfo userBLInfo = UserBLFactory.getUserBLServiceImpl_Salesman();
        for(int i=0;i<userBLInfo.getAllLevel().size();i++){
            observableList.add(i+1);
        }

        levelChoiceBox.setItems(observableList);
    }

    public void setPromotionBLService(PromotionBLService promotionBLService){
        this.promotionBLService = promotionBLService;
    }

    public void clickCancelButton(){
        webPromotionViewController.refreshWebPromotionList();
        webPromotionViewController.back();
    }

    // TODO: 16/12/1 need to discuss the diferrence between ui and logic, add discount
    public void clickSaveButton(){
        PromotionType promotionType = PromotionType.Web_SpecilaDate;
        switch ((String)typeChoiceBox.getValue()){
            case "会员等级折扣":
                promotionType = PromotionType.Web_ClientGrade;
                break;
            case "会员商圈折扣":
                promotionType = PromotionType.Web_SpecilPlace;
                break;
            case "特定期间折扣":
                promotionType = PromotionType.Web_SpecilaDate;
                break;
        }

        int clientGrade = (int)levelChoiceBox.getValue();

        promotionVO = new Promotion_ClientGradeVO(nameTextField.getText(),promotionType, 0.8,
                new DateUtil(startTime.getDate()), new DateUtil(endTime.getDate()),
                clientGrade);
//        promotionBLService = new BLFactoryImpl().getPromotionBLService();
        promotionBLService.addPromotion(promotionVO);

    }

}