package com.pancho.demo.web;

import com.pancho.demo.exception.DataValidationException;
import com.pancho.demo.model.*;
import com.pancho.demo.service.CalculatorService;
import com.pancho.demo.service.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import trackia.app.Trackia;

import java.util.ArrayList;

import static com.pancho.demo.model.EnumOperation.ADDITION;

@Component
public class Mediator {

    @Autowired
    private CalculatorService calculatorService;

    @Trackia
    public ResponseEntity<APIResponse> handler (CalcRequest calcRequest) {

        CalcResponse calcResponse = new CalcResponse();
        try {

            if (!calcRequest.getOperation().isEmpty()) {

                switch (EnumOperation.valueOf(calcRequest.getOperation())) {
                    case ADDITION:
                        calcResponse = calculatorService.addition(calcRequest);
                        break;
                    case SUBTRACTION:
                        calcResponse = calculatorService.subtraction(calcRequest);
                        break;
                    case MULTIPLICATION:
                        calcResponse = calculatorService.multiplication(calcRequest);
                        break;
                    case DIVISION:
                        calcResponse = calculatorService.division(calcRequest);
                        break;
                    case SQUARE_ROOT:
                        calcResponse = calculatorService.squareRoot(calcRequest);
                        break;
                    case RANDOM_STRING:
                        calcResponse = calculatorService.randomString(calcRequest);
                        break;
                    case LOAD_DATA:
                        calculatorService.loadData();
                        break;
                }
            }


            APIResponse apiResponse = new APIResponse();
            apiResponse.setData(calcResponse);
            apiResponse.setResponseCode(HttpStatus.OK);
            apiResponse.setMessage("Successfully executed");

            return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());

        } catch (DataValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }

}
