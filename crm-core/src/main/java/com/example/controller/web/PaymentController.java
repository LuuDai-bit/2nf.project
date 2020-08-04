package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.PaymentDTO;
import com.example.entity.other.ListDTO;
import com.example.paging.PageRequest;
import com.example.paging.Pageable;
import com.example.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;

    @RequestMapping(value = "/payment/{id}", method = RequestMethod.GET)
    public PaymentDTO getPayment(@PathVariable Long id){
        PaymentDTO paymentDTO = paymentService.getOnePaymentById(id);
        return paymentDTO;
    }

    @RequestMapping(value="/addPayment", method= RequestMethod.POST)
    @ResponseBody
//    @PostMapping(value = "/addPayment")
    public String submit(@RequestBody PaymentDTO paymentDTO) {
        paymentService.savePayment(paymentDTO);
        return "success";
    }

    @RequestMapping(value="/payment/list", method = RequestMethod.GET)
    public ModelAndView getPayments(@ModelAttribute(SystemConstant.PAYMENT) PaymentDTO model,
                                     HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/payment/list");
        Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems());

        List<PaymentDTO> payments = paymentService.searchPayments(model, pageable);
        int totalPayments = paymentService.getTotalItems(model);
        model.setListResult(payments);
        model.setTotalItems(totalPayments);
        mav.addObject(SystemConstant.PAYMENT, model);
        return mav;
    }

    @RequestMapping(value="/deletePayments", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePayments(@RequestBody ListDTO checkedPayments){
        if(checkedPayments.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        paymentService.deletePayments(checkedPayments.getItems());
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @RequestMapping(value="/editPayment/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updatePayment(PaymentDTO paymentDTO,@PathVariable Long id) {
        paymentDTO.setId(id);
        paymentService.savePayment(paymentDTO);
        return "success";
    }
}
