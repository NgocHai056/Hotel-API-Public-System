package com.example.demo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ApiModel(description = "Represents a booking response")
public class BookingResponse {

    @ApiModelProperty(value = "Unique booking number", example = "BOOKING0006")
    private String bookingNumber;

    @ApiModelProperty(value = "Details of the hotel for the booking")
    private Object hotel;

    @ApiModelProperty(value = "Details of the booked room type")
    private Object roomType;

    @ApiModelProperty(value = "Details of the rate plan for the booking")
    private Object ratePlan;

    @ApiModelProperty(value = "Details of the guest who made the booking")
    private Object guest;

    @ApiModelProperty(value = "Check-in date for the booking, in YYYY-MM-DD format", example = "2024-01-15")
    private String checkInDate;

    @ApiModelProperty(value = "Check-out date for the booking, in YYYY-MM-DD format", example = "2024-01-20")
    private String checkOutDate;

    @ApiModelProperty(value = "Total price of the booking", example = "105.99")
    private double price;

    @ApiModelProperty(value = "Current status of the booking", example = "CONFIRMED")
    private String status;

    @ApiModelProperty(value = "Additional notes about the booking")
    private String notes;

}