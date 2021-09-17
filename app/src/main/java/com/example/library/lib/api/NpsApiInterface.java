package com.example.library.lib.api;


import com.example.library.lib.model.User;
import com.example.library.lib.utils.NpsData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NpsApiInterface {
    @POST("Connect")
    Call<User> login(@Body NpsData data);
//
//    @POST("Connect")
//    Call<Register> registerUser(@Body NpsData data);
//
//    @POST("Connect")
//    Call<RegisterVerification> verifyCode(@Body NpsData data);
//
//    @POST("Connect")
//    Call<RegisterPassword> setRegisterPassword(@Body NpsData data);
//
//    @POST("Connect")
//    Call<User> setRegisterPin(@Body NpsData data);
//
//    @POST("Connect")
//    Call<Reset> resetPassword(@Body NpsData data);
//
//    @POST("Connect")
//    Call<Reset> verifyResetCode(@Body NpsData data);
//
//    @POST("Connect")
//    Call<SetPassword> setVerifyPassword(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ChangePassword> changePassword(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ChangePin> changePin(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Notification> getNotifications(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<NotificationRemove> removeNotification(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Statement> getTransactions(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<StatementDetail> getTransactionDetail(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<FundTransfer> transferUserFund(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<RequestTransfer> userRequestFund(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<TransferDetail> userGetDetailSendRequest(@Body NpsData data);
//
//
//    @POST("LoggedIn")
//    Call<CardList> getCardList(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<CardDetail> getCardDetail(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<AddCard> addCard(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Gift> giftCard(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<WithDrawAmount> withDrawAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<AddAmount> addAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Transaction> doTransaction(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<KYCModel> doKYC(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<KycDetail> getKycDetail(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<UserDetail> getUserDetail(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Logout> logout(@Body NpsData data);
//
//    @Multipart
//    @POST("Image")
//    Call<ImageResponse> uploadImage(@Part("FunctionName") RequestBody FunctionName,
//                                    @Part("Data") RequestBody Data,
//                                    @Part("Signature") RequestBody Signature,
//                                    @Part MultipartBody.Part Image);
//
//    @POST("Connect")
//    Call<Resend> resendRegisterVerification(@Body NpsData data);
//
//    @POST("Connect")
//    Call<moc.spn.sbil.lellaw.auth.model.Resend> resendVerification(@Body NpsData data);
//
//    @POST("Connect")
//    Call<ChangePassword> changeLoginPassword(@Body NpsData mData);
//
//    @POST("Connect")
//    Call<ChangePin> changeLoginPin(@Body NpsData mData);
//
//    @POST("LoggedIn")
//    Call<Occupation> getOccupation(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Municipality> getMunicipality(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<District> getDistrict(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<QrResponse> getQRResponse(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<QrMerchantPayment> doQrMerchantPayment(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<StoreTokenModel> storeToken(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Bank> getBankList(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<LoadFund> loadFund(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<LoadFundReceipt> loadFundReceipt(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Gender> getGender(@Body NpsData data);
//
//    @POST("Connect")
//    Call<Gender> getGenderSignUp(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ConnectIPSBank> getConnectIpsBankList(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<MaritalStatus> getMaritalStatus(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<TextValueDropDown> getTextValueDropDown(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Province> getProvince(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<DocumentType> getDocumentType(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Purpose> getPurposes(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Services> getServices(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<NeaOffice> getNeaOffice(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<NeaBillEnquiry> enquiryBill(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<NeaPayment> doNeaPayment(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ServiceCharge> getNeaServiceCharge(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<WaterOffice> getWaterOffice(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<WaterBillEnquiry> waterEnquiryBill(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<WaterPayment> doWaterPayment(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<VianetPlanAmount> vianetPlanAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Payment> doISPPayment(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<WLinkPlanAmount> wLinkPlanAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ClassicTechPlanAmount> classicPlanAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Denomination> getDenominations(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<DishHomeBillEnquiry> getDishHomeBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<Payment> doTvBillPayment(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<MeroTvPlanAmount> getMeroTvPlanAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<SimTvBillEnquiry> getSimTvPlanAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<PrabhuTvOttTopup> getPrabhuTvOTTplanAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<MaxTvBillEnquiry> getMaxTvplanAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<PrabhuTv> getPrabhuTvplanAmount(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<PromotionalImage> getPromotionalImages(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<SkyEnquiry> getSkyBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<WebSurferPlanAmount> getWebSurferBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ArrowNetPlanAmount> getArrowNetBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<TechmindsPlanAmount> getTechMindBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<PrabhuNetPlanAmount> getPrabhuNetBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<BroadLinkPlanAmount> getBroadlinkBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<LoopNetworkPlanAmount> getLoopNetworkBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<UltraNetPlanAmount> getUltraNetBillEnquiry(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<FirstLinkPlanAmount> getFirstLinkBillEnquiry(@Body NpsData data);
//
//    //
//    @POST("LoggedIn")
//    Call<ConnectIPSReceipt> getConnectIPSReceipt(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ConnectIPSFundTransfer> getConnectIPSFundTransfer(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ConnectIPSLogin> connectIPSLogin(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ShaswatDham> shaswatDham(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ShaswatDhamTicket> shaswatDhamTicket(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<ConnectIPSFundTransfer> connectIpsFundTransfer(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<CIPSTrafficFineBillPayment> getConnectIPSTrafficFineBillPayment(@Body NpsData data);
//
//    @POST("LoggedIn")
//    Call<CIPSServiceCharge> getConnectIPSServiceChargeBillPayment(@Body NpsData data);
}
