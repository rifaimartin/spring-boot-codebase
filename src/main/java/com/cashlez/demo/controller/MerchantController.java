package com.cashlez.demo.controller;

import com.cashlez.demo.dto.SearchByMerchantId;
import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.Merchant;
import com.cashlez.demo.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping()
    public ResponseEntity<GeneralResponse> getAllMerchant( @RequestParam Integer pageNo, Integer pageSize, String keyword) {
        return new ResponseEntity<>(merchantService.getAllMerchantService(pageNo, pageSize, keyword), HttpStatus.OK);
    }

    @PostMapping("/get_one_merchant")
    public ResponseEntity<GeneralResponse> getOneMerchant (@RequestBody SearchByMerchantId searchByMerchantId){
        return new ResponseEntity<>(merchantService.getOneMerchant(searchByMerchantId.getMerchantId()), HttpStatus.OK);
    }

    @PostMapping("/add_merchant")
    public ResponseEntity<GeneralResponse> addMerchantController(@RequestBody List<Merchant> merchants) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(merchantService.addMultipleNewMerchant(merchants), HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<GeneralResponse> loginMerchant(@RequestBody Merchant merchant) {
//        return new ResponseEntity<>(merchantService.loginMerchant(merchant), HttpStatus.OK);
//    }

    @PostMapping("/update_merchant")
    public ResponseEntity<GeneralResponse> updateMerchantController(@RequestBody Merchant merchant){
        return new ResponseEntity<>(merchantService.updateMerchantService(merchant), HttpStatus.OK);
    }

    @PostMapping("/delete_merchant")
    public ResponseEntity<GeneralResponse> deleteMerchantController (@RequestBody SearchByMerchantId searchByMerchantId){
        return new ResponseEntity<>(merchantService.deleteMerchantService(searchByMerchantId.getMerchantId()), HttpStatus.OK);
    }
}
