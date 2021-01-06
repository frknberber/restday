package restday.api.handler;

import restday.RestdayApplication;

public enum DayOffValidationRule {

    DAY_OFF_NOT_ENOUGH_REMAINING_DAY,
    REQUEST_CAN_NOT_BE_EMPTY,
    RECORD_NOT_FOUND ;


    public static String getRule(DayOffValidationRule value) {

        if(RestdayApplication.language.equals("tr")){
            switch (value) {
                case RECORD_NOT_FOUND:
                    return "Kayıt bulunamamıştır.";
                case REQUEST_CAN_NOT_BE_EMPTY:
                    return "İstek bilgileri boş olamaz.";
                case DAY_OFF_NOT_ENOUGH_REMAINING_DAY:
                    return "Yeterli izin günü bulunmamaktadır.";
                default:
                    return "0xffffff00";
            }
        }
        else{
            switch (value) {
                case RECORD_NOT_FOUND:
                    return "Record not found";
                case REQUEST_CAN_NOT_BE_EMPTY:
                    return "Request can not be empty";
                case DAY_OFF_NOT_ENOUGH_REMAINING_DAY:
                    return "Not enough remaining day";
                default:
                    return "0xffffff00";
            }
        }
    }

     DayOffValidationRule() {

    }

}
