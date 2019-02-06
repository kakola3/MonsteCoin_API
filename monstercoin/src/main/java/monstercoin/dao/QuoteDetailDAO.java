package monstercoin.dao;

import monstercoin.entity.QuoteDetail;

import java.util.List;

public interface QuoteDetailDAO
{
    public List<QuoteDetail> getQuoteDetails();

    public void saveQuoteDetail(QuoteDetail quoteDetail, int i);
}
