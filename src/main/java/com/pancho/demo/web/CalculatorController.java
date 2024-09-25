package com.pancho.demo.web;

import com.pancho.demo.model.APIResponse;
import com.pancho.demo.model.CalcRequest;
import com.pancho.demo.model.EnumOperation;
import com.pancho.demo.service.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import trackia.app.Trackia;


@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    Mediator mediator;

    @Autowired
    private UserRecordService userRecordService;

    @Trackia
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> addition(@RequestBody CalcRequest calcRequest) {
        calcRequest.setOperation(EnumOperation.ADDITION.toString());
        return mediator.handler(calcRequest);
    }

    @Trackia
    @PostMapping("/sub")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> subtraction(@RequestBody CalcRequest calcRequest) {
        calcRequest.setOperation(EnumOperation.SUBTRACTION.toString());
        return mediator.handler(calcRequest);
    }

    @Trackia
    @PostMapping("/multi")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> multiplication(@RequestBody CalcRequest calcRequest) {
        calcRequest.setOperation(EnumOperation.MULTIPLICATION.toString());
        return mediator.handler(calcRequest);
    }

    @Trackia
    @PostMapping("/div")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> division(@RequestBody CalcRequest calcRequest) {
        calcRequest.setOperation(EnumOperation.DIVISION.toString());
        return mediator.handler(calcRequest);
    }

    @Trackia
    @PostMapping("/square")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> squareRoot(@RequestBody CalcRequest calcRequest) {
        calcRequest.setOperation(EnumOperation.SQUARE_ROOT.toString());
        return mediator.handler(calcRequest);
    }

    @Trackia
    @PostMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> randomString(@RequestBody CalcRequest calcRequest) {
        calcRequest.setOperation(EnumOperation.RANDOM_STRING.toString());
        return mediator.handler(calcRequest);
    }

    @Trackia
    @PostMapping("/load-data")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> loadData() {
        CalcRequest calcRequest = CalcRequest.builder().operation(EnumOperation.LOAD_DATA.toString()).build();
        return mediator.handler(calcRequest);
    }

    @Trackia
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> getUserRecords() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(userRecordService.findAll());
        apiResponse.setResponseCode(HttpStatus.OK);
        apiResponse.setMessage("Successfully executed");

        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }

    @Trackia
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIResponse> deleteUserRecord(@PathVariable String id) {
        try {
            userRecordService.delete(Long.valueOf(id));
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResponseCode(HttpStatus.OK);
            apiResponse.setMessage("Successfully executed");

            return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
