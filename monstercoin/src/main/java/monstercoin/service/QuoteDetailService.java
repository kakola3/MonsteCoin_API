package monstercoin.service;

import monstercoin.entity.QuoteDetail;

import java.util.List;


public interface QuoteDetailService
{
    public List<QuoteDetail> getQuoteDetails();

    public void saveQuoteDetail(QuoteDetail quoteDetail, int i);

    public double getPrice(int id);
}
