package monstercoin.rest;

import com.google.gson.Gson;
import monstercoin.entity.CryptoTransaction;
import monstercoin.entity.User;
import monstercoin.service.CryptoTransactionService;
import monstercoin.service.UserService;
import monstercoin.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/crypto-transaction")
public class CryptoTransactionRestController
{
    @Autowired
    CryptoTransactionService cryptoTransactionService;

    @Autowired
    UserService userService;

    @Autowired
    WalletService walletService;

    @CrossOrigin
    @GetMapping("/active-transactions")
    public String activeTransactionsPerUser(@RequestParam("user_id") int user_id){

        List<CryptoTransaction> activeTransactionsPerUser = cryptoTransactionService.activeTransactionsPerUser(user_id);

        Gson gson = new Gson();
        String active = gson.toJson(activeTransactionsPerUser);

        return active;
    }

    @CrossOrigin
    @GetMapping("/inactive-transactions")
    public String getInactiveTransactionsPerUser(@RequestParam("user_id") int user_id) {
        List<CryptoTransaction> inactiveTransactionsPerUser = cryptoTransactionService.inactiveTransactionsPerUser(user_id);

        Gson gson = new Gson();
        String inactive = gson.toJson(inactiveTransactionsPerUser);

        return inactive;
    }

    @CrossOrigin
    @PostMapping("/new-transaction")
    public int saveTransaction(@Valid @RequestBody CryptoTransaction cryptoTransaction, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){
            int tempId = cryptoTransaction.getUser_id(); // pobierz id usera
            double tempBallance = userService.getUserBallance(tempId);
            User theUser = new User(tempId);
            System.out.println("userService.getUserBallance(tempId): " + userService.getUserBallance(tempId));

            System.out.println("cryptoTransaction: " + cryptoTransaction);

            if(cryptoTransaction.getAction().equals("buy")){
                if(cryptoTransaction.getPrice() * cryptoTransaction.getAmount() <= tempBallance){
                    System.out.println("cryptoTransaction.getPrice(): |" + cryptoTransaction.getPrice() + "| * cryptoTransaction.getAmount(): |" +
                            cryptoTransaction.getAmount() + "| = " + cryptoTransaction.getPrice() * cryptoTransaction.getAmount());
                    cryptoTransactionService.saveTransaction(cryptoTransaction);
                    System.out.println("tempBallance-(cryptoTransaction.getPrice() * cryptoTransaction.getAmount()): " + (tempBallance-(cryptoTransaction.getPrice() * cryptoTransaction.getAmount())));
                    userService.updateAccountBallance(cryptoTransaction.getUser_id(), tempBallance-(cryptoTransaction.getPrice() * cryptoTransaction.getAmount()));
                    return 0;
                }
                else return 1;
            }
            else if(cryptoTransaction.getAction().equals("sell")){
                if(cryptoTransaction.getCurrency().equals("bitcoin")){
                    if(cryptoTransaction.getAmount() <= walletService.getWalletPerUser(cryptoTransaction.getUser_id()).getBitcoin_amount()){
                        cryptoTransactionService.saveTransaction(cryptoTransaction);
                        return 0;
                    }else return 1;
                }
                else if(cryptoTransaction.getCurrency().equals("ethereum")){
                    if(cryptoTransaction.getAmount() <= walletService.getWalletPerUser(cryptoTransaction.getUser_id()).getEthereum_amount()){
                        cryptoTransactionService.saveTransaction(cryptoTransaction);
                        return 0;
                    }else return 1;
                }
                else if(cryptoTransaction.getCurrency().equals("litecoin")){
                    if(cryptoTransaction.getAmount() <= walletService.getWalletPerUser(cryptoTransaction.getUser_id()).getLitecoin_amount()){
                        cryptoTransactionService.saveTransaction(cryptoTransaction);
                        return 0;
                    }else return 1;
                }
                else if(cryptoTransaction.getCurrency().equals("xrp")){
                    if(cryptoTransaction.getAmount() <= walletService.getWalletPerUser(cryptoTransaction.getUser_id()).getXrp_amount()){
                        cryptoTransactionService.saveTransaction(cryptoTransaction);
                        return 0;
                    }else return 1;
                }
                else if(cryptoTransaction.getCurrency().equals("eos")){
                    if(cryptoTransaction.getAmount() <= walletService.getWalletPerUser(cryptoTransaction.getUser_id()).getEos_amount()){
                        cryptoTransactionService.saveTransaction(cryptoTransaction);
                        return 0;
                    }else return 1;
                }else return 1;
            }else return 1;

        }else return 1;
    }

    @CrossOrigin
    @GetMapping("/delete-transaction")
    public int deleteTransaction(@Valid @RequestBody CryptoTransaction cryptoTransaction, BindingResult bindingResult){

        return 999;
    }
}
