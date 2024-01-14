package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "Request object for creating a booking")
public class CreateBookingRequest {

    @ApiModelProperty(value = "ID of the hotel for the booking", example = "1")
    private Long hotelId;

    @ApiModelProperty(value = "ID of the room type for the booking", example = "2")
    private Long roomTypeId;

    @ApiModelProperty(value = "ID of the rate plan for the booking", example = "1")
    private Long ratePlanId;

    @ApiModelProperty(value = "ID of the guest making the booking", example = "1")
    private Long guestId;

    @ApiModelProperty(value = "Check-in date in YYYY-MM-DD format", example = "2024-01-15")
    private String checkInDate;

    @ApiModelProperty(value = "Check-out date in YYYY-MM-DD format", example = "2024-01-20")
    private String checkOutDate;

}
