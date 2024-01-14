package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "Request object for updating an existing booking")
public class UpdateBookingRequest {

    @ApiModelProperty(value = "Unique booking number to identify the booking to be updated", example = "BOOKING0004")
    private String bookingNumber;

    @ApiModelProperty(value = "New check-in date for the booking, in YYYY-MM-DD format", example = "2024-01-20")
    private String checkInDate;

    @ApiModelProperty(value = "New check-out date for the booking, in YYYY-MM-DD format", example = "2024-01-23")
    private String checkOutDate;
}