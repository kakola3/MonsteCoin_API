package monstercoin.service;

import monstercoin.dao.QuoteDetailDAO;
import monstercoin.entity.QuoteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuoteDetailServiceImpl implements QuoteDetailService
{
    @Autowired
    QuoteDetailDAO quoteDetailDAO;

    @Override
    @Transactional
    public void saveQuoteDetail(QuoteDetail quoteDetail, int i) {

    }

    @Override
    @Transactional
    public double getPrice(int id) {
       return quoteDetailDAO.getPrice(id);
    }

    @Override
    @Transactional
    public List<QuoteDetail> getQuoteDetails() {
        return quoteDetailDAO.getQuoteDetails();
    }
}
