package monstercoin.rest;

import monstercoin.entity.Wallet;
import monstercoin.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletRestService
{
    @Autowired
    WalletService walletService;

    @CrossOrigin
    @GetMapping("/get-wallet")
    public Wallet getWalletPerUser(@RequestParam("user_id") int user_id){
        Wallet wallet = walletService.getWalletPerUser(user_id);
        return wallet;
    }
}
