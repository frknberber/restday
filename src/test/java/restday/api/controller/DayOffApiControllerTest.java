package restday.api.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import restday.api.handler.DayOffValidationRule;
import restday.api.request.CreateDayOffRequest;
import restday.api.response.DayOffResponse;
import restday.dto.EmployeeDTO;
import restday.service.DayOffCommandService;
import restday.testbase.DayOffDTOFactory;

import java.util.ArrayList;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


class DayOffApiControllerTest {

    private DayOffApiController dayOffApiController ;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DayOffCommandService dayOffCommandService;
    private Object EmployeeDTO;

    private DayOffDTOFactory dayOffDTOFactory ;

    DayOffApiControllerTest (){
        this.dayOffDTOFactory = new DayOffDTOFactory() ;
    }

    @Test
    void testCreateEmployeeList() {


    }

    @Test
    void createHolidays() {
    }

    @Test
    public void testCreateDayOff() {


    }

    @Test
    void testUpdateDayOff() throws Exception {



    }

    @Test
    void getDayOff() {
    }

    @Test
    void getDayOffs() {
    }
}