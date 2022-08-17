package com.MedicalHistory.controllers;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class PaymentController {

    static Logger logger = LogManager.getLogger(PaymentController.class);

    //method for payment integration
    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Map<String, Object> data) throws Exception {

        logger.info("hey order function is executing");
        int amount = Integer.parseInt(data.get("amount").toString());

        RazorpayClient client = new RazorpayClient("rzp_test_BL0BgqpoAReFUm", "sWMcPgGPeitVpg4RayCDDJqX");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", amount * 100);
        jsonObject.put("currency", "INR");
        jsonObject.put("receipt", "txn_123454");

        Order order = client.Orders.create(jsonObject);

        logger.info("Printing order " + order);


        logger.info("Amount which are coming from frontend " + amount);

        return order.toString();
    }
}
