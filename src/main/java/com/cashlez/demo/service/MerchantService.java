package com.cashlez.demo.service;

import com.cashlez.demo.dto.MerchantStatus;
import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.Category;
import com.cashlez.demo.model.Merchant;
import com.cashlez.demo.repo.MerchantRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public GeneralResponse getAllMerchantService(){
        merchantRepository.findAll();
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.success("200", "List Of Merchant", merchantRepository.findAll());
        return generalResponse;
    }

    public GeneralResponse addMultipleNewMerchant (List<Merchant> merchants) throws NoSuchAlgorithmException {

        GeneralResponse generalResponse = new GeneralResponse();

        for (Merchant merchant : merchants){
            String hashedPassword = Hashing.sha256()
                    .hashString(merchant.getPassword(), StandardCharsets.UTF_8)
                    .toString();
            merchant.setPassword(hashedPassword) ;
            merchantRepository.save(merchant);
            generalResponse.success("201", "Success add merchant !", merchants);
        }
        return generalResponse;
    }

    public GeneralResponse loginMerchant (Merchant merchant) {
        GeneralResponse generalResponse = new GeneralResponse();
        Optional<Merchant> merchantOptional = merchantRepository.findByUserName(merchant.getUserName());
        if(merchantOptional.isPresent()){
            String passwordData = merchantOptional.get().getPassword();
            String passwordPayload = merchant.getPassword();
            System.out.println(passwordData);
            System.out.println(passwordPayload);

            if (!passwordData.equals(passwordPayload)){
                System.out.println(passwordData);
                System.out.println(passwordPayload);

                System.out.println("masuk sini");

                return generalResponse.fail("403", "Incorrect username or password!");
            }
            generalResponse.success("200", "Success Login merchant !", merchant);
        } else {
            generalResponse.fail("404", "Merchant not found !");
        }
        return generalResponse;
    }


    public GeneralResponse updateMerchantService (Merchant merchant){

        Date date = new Date();
        Optional<Merchant> merchantOptional = merchantRepository.findByIdAndStatus(merchant.getId(), MerchantStatus.ACTIVE);

        GeneralResponse generalResponse = new GeneralResponse();

        Merchant newMerchant = new Merchant();
        if (merchantOptional.isPresent()){
            newMerchant = merchantOptional.get();
            newMerchant.setModifiedDate(date);
            if (merchant.getMerchantName() != null){
                newMerchant.setMerchantName(merchant.getMerchantName());
            }
            if (merchant.getAddress() != null){
                newMerchant.setAddress(merchant.getAddress());
            }
            merchantRepository.save(newMerchant);
            generalResponse.success("200","Success update merchant !", newMerchant);
        }else {
            generalResponse.fail("404", "Merchant not found !");
        }
        return generalResponse;
    }

    public GeneralResponse deleteMerchantService (long merchantId){

        Date date = new Date();

        Merchant deletedMerchant;

        GeneralResponse generalResponse = new GeneralResponse();

        Optional<Merchant> merchantOptional = merchantRepository.findByIdAndStatus(merchantId, MerchantStatus.ACTIVE);

        if (merchantOptional.isPresent()){
            deletedMerchant = merchantOptional.get();
            deletedMerchant.setModifiedDate(date);
            deletedMerchant.setStatus(MerchantStatus.DELETED);
            merchantRepository.save(deletedMerchant);
            generalResponse.success("200", "Success delete merchant !", deletedMerchant);
        }else {
            generalResponse.fail("404", "Merchant not found !");
        }
        return generalResponse;
    }

    public GeneralResponse getOneMerchant (long merchantId){
        GeneralResponse generalResponse = new GeneralResponse();

        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            generalResponse.success("200", "Detail of merchant !", merchantOptional);
        }else  {
            generalResponse.fail("404", "Merchant not found !");
        }
        return generalResponse;
    }
}
