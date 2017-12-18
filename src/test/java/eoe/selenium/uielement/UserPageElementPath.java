package eoe.selenium.uielement;

public class UserPageElementPath {
	
	/*	USER LIST PAGE	*/
	public static final String xpathUserTitle = "//div[@id='main-container']/eoe-end-user-list/div/h1";
	public static final String xpathUserMenu = "//div[@id='tool-bar']/div/div[2]/a/span";
	public static final String xpathLoggedInUserId = "//div[@id='loginfo-button-area']/a";
	
	public static final String xpathSearchCondSelect = "//div[@id='main-container']/eoe-end-user-list/eoe-search/div/form/md-select/div";//"//div[@id='main-container']/eoe-end-user-list/eoe-search/div/form/md-select/div/span[3]";
	                                                                                                       
						                                                                                   
	public static final String xpathEmailCond = "//md-option[1]";
                                                                                         	//div[@id='cdk-overlay-4']/div/div/md-option
                                                                                         	////div[@id='main-container']/eoe-end-user-list/eoe-search/div/form/md-select/div
	public static final String xpathSearchKeyword = "//div[@id='main-container']/eoe-end-user-list/eoe-search/div/md-input-container/div/div/div/input";
	public static final String xpathNickNameCond = "//md-option[5]";
	
	public static final String xpathOpenCreateUserBtn = "//div[@id='main-container']/eoe-end-user-list/eoe-end-user-table/div/button";
	
	public static final String xpathUserListCount = "//div[@id='main-container']/eoe-end-user-list/eoe-end-user-table/ngx-datatable/div/datatable-footer/div/div";
	public static final String xpathCreatedColumnHeader = "//div[@id='main-container']/eoe-end-user-list/eoe-end-user-table/ngx-datatable/div/datatable-header/div/div[2]/datatable-header-cell[6]/div/span";

	public static final String xpathFirstDataEmail = "//div[@id='main-container']/eoe-end-user-list/eoe-end-user-table/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div[2]/datatable-body-cell[1]/div";
	public static final String xpathFirstDataUserId = "//div[@id='main-container']/eoe-end-user-list/eoe-end-user-table/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div[2]/datatable-body-cell[2]/div";
	public static final String xpathFirstDataDeleteBtn = "//div[@id='main-container']/eoe-end-user-list/eoe-end-user-table/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div[2]/datatable-body-cell[8]/div/button";
	public static final String xpathDeleteConfirmOKBtn = "//eoe-message-dialog/div/md-dialog-actions/button/span";
	
	/*	CREATE USER PAGE	*/
	public static final String xpathCreateUserPopupTitle = "//md-grid-list[@id='user-add-modal']/div/md-grid-tile/figure";
	public static final String xpathAppSelect = "//md-grid-list[@id='user-add-modal']/div/md-grid-tile[2]/figure/md-select/div";
//	public static final String xpathAppFirstItem = "//div[@id='cdk-overlay-2']/div/div/md-option";////md-option
	public static final String xpathAppFirstItem = "//md-option";//
	public static final String xpathFirstNameInput ="//md-grid-list[@id='user-add-modal']/div/md-grid-tile[3]/figure/md-input-container/div/div/div/input";
	public static final String xpathMidNameInput = "//md-grid-list[@id='user-add-modal']/div/md-grid-tile[4]/figure/md-input-container/div/div/div/input";
	public static final String xpathLastNameInput = "//md-grid-list[@id='user-add-modal']/div/md-grid-tile[5]/figure/md-input-container/div/div/div/input";
	public static final String xpathEmailInput = "//md-grid-list[@id='user-add-modal']/div/md-grid-tile[6]/figure/md-input-container/div/div/div/input";
	public static final String xpathUserIDInput = "//md-grid-list[@id='user-add-modal']/div/md-grid-tile[7]/figure/md-input-container/div/div/div/input";
	public static final String xpathUserPWInput = "//md-grid-list[@id='user-add-modal']/div/md-grid-tile[8]/figure/md-input-container/div/div/div/input";
	public static final String xpathUserPWConfirmInput = "//md-grid-list[@id='user-add-modal']/div/md-grid-tile[9]/figure/md-input-container/div/div/div/input";
	public static final String xpathUserCreateSaveBtn = "//div[@id='cdk-overlay-3']/md-dialog-container/eoe-user-add/eoe-progress-spinder/div/form/md-grid-list[2]/div/md-grid-tile[2]/figure/button";
	public static final String xpathUserCreateBodyToEscape = "//md-grid-list[3]/div/md-grid-tile/figure";
	public static final String xpathUserCreatedMessage = "//div[@id='cdk-overlay-0']/md-dialog-container/eoe-user-add/eoe-progress-spinder/div/form/md-grid-list[2]/div/md-grid-tile[2]/figure/button";
	
	public static final String xpathUserCreateErrorMessageArea = "//md-grid-list[3]/div/md-grid-tile/figure";
	
	/*	USER DETAIL PAGE	*/
	public static final String xpathUserDetailTitle = "//div[@id='main-container']/eoe-end-user-detail/div/h1";
	public static final String xpathGoToUserList = "//div[@id='main-container']/eoe-end-user-detail/div/div/a/i";
	
	public static final String xpathUserDPName = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div/md-card/md-card-header/div[2]/md-card-subtitle";
	
	public static final String xpathUserDetailUserId = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div/md-input-container/div/div/div/input";
	public static final String xpathUserDetailApplication = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[2]/md-select/div/span[2]/span";
	public static final String xpathUserDetailFirstName = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[3]/md-input-container/div/div/div/input";
	public static final String xpathUserDetailMiddleName = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[4]/md-input-container/div/div/div/input";
	public static final String xpathUserDetailLastName = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[5]/md-input-container/div/div/div/input";
	public static final String xpathUserDetailNickName = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[6]/md-input-container/div/div/div/input";
	public static final String xpathUserDetailEmail = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[7]/md-input-container/div/div/div/input";
	public static final String xpathUserDetailMobile= "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[8]/md-input-container/div/div/div/input";
	public static final String xpathUserDetailOpenBirthDatePopup = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[9]/md-input-container/div/div/div[2]/button";
                                                                                                                          	//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[9]/md-input-container/div/div/div[2]/button
	public static final String xpathUserDetailBirthDatePopupBody = "//md-calendar/div/div";
	
	public static final String xpathUserDetailStatus = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[10]/md-select/div";
	public static final String xpathUserDetailStatusCreated = "//md-option[1]";//"//md-option"
	public static final String xpathUserDetailStatusActivated = "//md-option[2]";
	
	public static final String xpathUserDetailGender = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[11]/md-select/div";////div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[11]/md-select/div/span[2]/span";
	public static final String xpathUserDetailGenderMale ="//md-option[1]";//"//md-option"
	public static final String xpathUserDetailGenderFemale ="//md-option[2]";
	
	public static final String xpathUserDetailCountry = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[12]/md-select/div/span";
	public static final String xpathUserDetailAddress = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[13]/md-input-container/div/div/div/textarea";
	public static final String xpathUserDetailVerifiedEmail = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[14]/md-slide-toggle/label/div/div/div";
	public static final String xpathUserDetailVerifiedMobile = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[15]/md-slide-toggle/label/div/div/div";
	public static final String xpathUserDetailCreatedDate = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[16]/md-input-container/div/div/div/input";
	public static final String xpathUserDetailAccessedDate = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div/div[17]/md-input-container/div/div/div/input";
	public static final String xpathUserDetailSaveBtn = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div[2]/div/button[2]";
	public static final String xpathUserDetailResetBtn = "//div[@id='main-container']/eoe-end-user-detail/div[2]/div[2]/md-card/eoe-end-user-profile/form/div[2]/div/button";
	
}
