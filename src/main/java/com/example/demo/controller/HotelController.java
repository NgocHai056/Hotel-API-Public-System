package com.example.demo.controller;

import com.example.demo.common.BaseResponse;
import com.example.demo.common.HttpClientService;
import com.example.demo.common.RequestMappingVersionConstants;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController(value = "hotels" + RequestMappingVersionConstants.VERSION)
@RequestMapping(value = RequestMappingVersionConstants.VERSION_PATH + "/hotels")
public class HotelController {

    public static final String INVENTORY_DOMAIN = "http://localhost:9001/api/v1/";
    @Autowired
    private JmsTemplate jmsTemplate;

    @Operation(summary = "API get the room rate and room availability.", description = "API et the room rate and room availability.")
    @GetMapping(value = "/{hotelId}/rates-and-availability", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> get(
            @ApiParam(value = "ID of the hotel for which to retrieve data", example = "1")
            @PathVariable Long hotelId,

            @ApiParam(value = "Start date of the booking availability range, in YYYY-MM-DD format", example = "2024-01-05")
            @RequestParam String startDate,

            @ApiParam(value = "End date of the booking availability range, in YYYY-MM-DD format", example = "2024-02-20")
            @RequestParam String endDate) throws Exception {

        BaseResponse<Object> response = new BaseResponse<>();

        // Kiểm tra ngày đi
        LocalDate checkOutDate = LocalDate.parse(endDate);
        if (checkOutDate.isBefore(LocalDate.parse(startDate))) {
            response.setMessageError("Ngày bắt đầu phải sau ngày kết thúc.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        Map<String, String> headers = new HashMap<>();

        Map<String, String> roomMessage = new HashMap<>();
        roomMessage.put("hotelId", hotelId.toString());
        roomMessage.put("startDate", startDate);
        roomMessage.put("endDate", endDate);


        return new ResponseEntity<>(HttpClientService.get(this.INVENTORY_DOMAIN + "rooms", headers,
                roomMessage, Object.class, 5000), HttpStatus.OK);
    }
}
