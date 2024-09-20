package com.colson.web.controller;

import com.colson.service.RepaymentService;
import com.colson.service.context.RepaymentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @PostMapping(value = "/repayment", name = "通用还款接口")
    public ResponseEntity<String> process(@RequestBody RepaymentContext context) {
        repaymentService.process(context);
        return ResponseEntity.ok("success");
    }
}
