package restday.api.response;

import io.swagger.models.Response;
import restday.domain.DayOff;


import java.util.List;

public class DayOffListResponse {

    private List<DayOff> list;

    public List<DayOff> getList() {
        return list;
    }

    public void setList(List<DayOff> list) {
        this.list = list;
    }
}
