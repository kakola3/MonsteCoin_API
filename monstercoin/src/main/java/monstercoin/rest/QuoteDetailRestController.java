package monstercoin.rest;

import com.google.gson.Gson;
import monstercoin.entity.QuoteDetail;
import monstercoin.service.QuoteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quote-details")
public class QuoteDetailRestController
{
    @Autowired
    QuoteDetailService quoteDetailService;

    @CrossOrigin
    @GetMapping("/get-quote-details")
    public String getQuoteDetails(){
        List<QuoteDetail> quoteDetails = quoteDetailService.getQuoteDetails();

        Gson gson = new Gson();
        String getQuoteDetails = gson.toJson(quoteDetails);

        return getQuoteDetails;
    }

    @CrossOrigin
    @GetMapping("get-price")
    public String getPrice(@RequestParam("id") int id){
        double price = quoteDetailService.getPrice(id);

        Gson gson = new Gson();
        String getPrice = gson.toJson(price);

        return getPrice;
    }
}
