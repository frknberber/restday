package restday.api.handler;

import restday.RestdayApplication;

public class DayOffValidationRule {

    /*DAY_OFF_CREATE_SUCCESS("İzin girişi oluşturulmuştur."),
    DAY_OFF_UPDATE_SUCCESS("İzin güncellemesi oluşturulmuştur."),
    DAY_OFF_NOT_ENOUGH_REMAINING_DAY("Yeterli izin günü bulunmamaktadır."),
    REQUEST_CAN_NOT_BE_EMPTY("İnput bilgileri boş olamaz."),
    RECORD_NOT_FOUND("Kayıt bulunamadı");*/

    public static String DAY_OFF_NOT_ENOUGH_REMAINING_DAY ;
    public static String RECORD_NOT_FOUND ;
    public static String REQUEST_CAN_NOT_BE_EMPTY ;

    //private final String description;
    private final String language ;

    public DayOffValidationRule() {
        this.language = RestdayApplication.language ;
        configureLanguage();

    }

    private DayOffValidationRule(String description) {
        this.language = RestdayApplication.language ;
        //this.description = description;
        configureLanguage();

    }

    private void configureLanguage(){
        System.out.println("lananalnallna--- " + this.language);
        if(this.language.equals("tr")==true)
            setTR();
        else
            setEN();

    }

    private void setTR(){
        DAY_OFF_NOT_ENOUGH_REMAINING_DAY = "Yeterli izin günü bulunmamaktadır." ;
        RECORD_NOT_FOUND = "Kayıt bulunamadı";
        REQUEST_CAN_NOT_BE_EMPTY ="İnput bilgileri boş olamaz."  ;
    }

    private void setEN(){
        DAY_OFF_NOT_ENOUGH_REMAINING_DAY = "setENsetENsetEN" ;
        RECORD_NOT_FOUND = "setENsetENsetEN";
        REQUEST_CAN_NOT_BE_EMPTY ="setENsetENsetEN"  ;
    }

}
