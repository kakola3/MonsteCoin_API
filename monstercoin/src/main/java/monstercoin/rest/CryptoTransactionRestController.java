package monstercoin.rest;

import monstercoin.entity.CryptoTransaction;
import monstercoin.entity.User;
import monstercoin.service.CryptoTransactionService;
import monstercoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/crypto-transaction")
public class CryptoTransactionRestController
{
    @Autowired
    CryptoTransactionService cryptoTransactionService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/new-transaction")
    public int saveTransaction(@Valid @RequestBody CryptoTransaction cryptoTransaction, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){
            int tempId = cryptoTransaction.getUser_id(); // pobierz id usera
            double tempBallance = userService.getUserBallance(tempId);
            User theUser = new User(tempId);
            System.out.println("userService.getUserBallance(tempId): " + userService.getUserBallance(tempId));

            System.out.println("cryptoTransaction: " + cryptoTransaction);

            if(cryptoTransaction.getPrice() * cryptoTransaction.getAmount() <= tempBallance){
                System.out.println("cryptoTransaction.getPrice(): |" + cryptoTransaction.getPrice() + "| * cryptoTransaction.getAmount(): |" +
                        cryptoTransaction.getAmount() + "| = " + cryptoTransaction.getPrice() * cryptoTransaction.getAmount());
                cryptoTransactionService.saveTransaction(cryptoTransaction);
                System.out.println("tempBallance-(cryptoTransaction.getPrice() * cryptoTransaction.getAmount()): " + (tempBallance-(cryptoTransaction.getPrice() * cryptoTransaction.getAmount())));
                userService.updateAccountBallance(theUser, tempBallance-(cryptoTransaction.getPrice() * cryptoTransaction.getAmount()));
                return 0;
            }
            else return 1;

        }else return 1;
    }
}
