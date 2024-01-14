package com.example.demo.controller;

import com.example.demo.common.BaseResponse;
import com.example.demo.common.HttpClientService;
import com.example.demo.dto.CreateBookingRequest;
import com.example.demo.dto.UpdateBookingRequest;
import com.example.demo.common.RequestMappingVersionConstants;
import com.example.demo.response.BookingResponse;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController(value = "booking" + RequestMappingVersionConstants.VERSION)
@RequestMapping(value = RequestMappingVersionConstants.VERSION_PATH + "/booking")
public class BookingController {

    public static final String BOOKING_DOMAIN = "http://localhost:9002/api/v1/";
    @Autowired
    private JmsTemplate jmsTemplate;

    @Operation(summary = "API create a new booking.", description = "API create a new booking.")
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody CreateBookingRequest createBookingRequest) throws Exception {

        BaseResponse<BookingResponse> response = new BaseResponse<>();

        // Kiểm tra ngày đến
        LocalDate checkInDate = LocalDate.parse(createBookingRequest.getCheckInDate());
        if (checkInDate.isBefore(LocalDate.now())) {
            response.setMessageError("Ngày đến không hợp lệ.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra ngày đi
        LocalDate checkOutDate = LocalDate.parse(createBookingRequest.getCheckOutDate());
        if (checkOutDate.isBefore(checkInDate)) {
            response.setMessageError("Ngày đi không hợp lệ.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        Map<String, String> headers = new HashMap<>();

        return new ResponseEntity<>(HttpClientService.post(this.BOOKING_DOMAIN + "booking", headers,
                createBookingRequest, Object.class, 5000), HttpStatus.OK);

    }

    @Operation(summary = "API change the arrival and departure date of a booking.", description = "API change the arrival and departure date of a booking.")
    @PutMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody UpdateBookingRequest request) throws Exception {

        Map<String, String> headers = new HashMap<>();

        return new ResponseEntity<>(HttpClientService.post(this.BOOKING_DOMAIN + "booking/update", headers,
                request, Object.class, 5000), HttpStatus.OK);

    }

    @Operation(summary = "API cancel a booking.", description = "API cancel a booking.")
    @DeleteMapping(value = "/{bookingNumber}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> cancel(
            @ApiParam(value = "Unique booking number to identify the booking to be cancelled", example = "BOOKING0004")
            @PathVariable("bookingNumber") String bookingNumber) throws Exception {

        Map<String, String> headers = new HashMap<>();

        return new ResponseEntity<>(HttpClientService.post(this.BOOKING_DOMAIN + "booking/" + bookingNumber + "/delete", headers,
                bookingNumber, Object.class, 5000), HttpStatus.OK);

    }
}
