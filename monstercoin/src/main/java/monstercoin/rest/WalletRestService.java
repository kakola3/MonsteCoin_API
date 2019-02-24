package monstercoin.rest;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import monstercoin.csv_pdf.CSVUtils;
import monstercoin.csv_pdf.PDFUtils;
import monstercoin.entity.Wallet;
import monstercoin.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @CrossOrigin
    @GetMapping("/save-wallet-csv")
    public int saveWalletToCSV(@RequestParam("id") int id) throws IOException {
        if(id != 0) {
            JFileChooser f = new JFileChooser();
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);

            String pathToSave = f.getSelectedFile().getAbsolutePath() + "\\wallet.csv";
            System.out.println(pathToSave);

            FileWriter writer = new FileWriter(pathToSave);
            System.out.println(writer);

            Wallet wallet = walletService.getWalletPerUser(id);

            CSVUtils.writeLine(writer, Arrays.asList("bitcoin", "ethereum", "litecoin", "xrp", "eos"));

            List<String> ballancesArray = new ArrayList<>();
            ballancesArray.add(Double.toString(wallet.getBitcoin_amount()));
            ballancesArray.add(Double.toString(wallet.getEthereum_amount()));
            ballancesArray.add(Double.toString(wallet.getLitecoin_amount()));
            ballancesArray.add(Double.toString(wallet.getXrp_amount()));
            ballancesArray.add(Double.toString(wallet.getEos_amount()));

            CSVUtils.writeLine(writer, ballancesArray);
            writer.append((char) wallet.getBitcoin_amount()).append((char) wallet.getBitcoin_amount());
            writer.flush();
            writer.close();
            return 0;
        }
        else return 1;
    }

    @CrossOrigin
    @GetMapping("/save-wallet-pdf")
    public int saveWalletToPDF(@RequestParam("id") int id) throws IOException, DocumentException {
        if(id != 0){
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);

        String pathToSave = f.getSelectedFile().getAbsolutePath()+ "\\wallet.pdf";
        System.out.println(pathToSave);

        FileWriter writer = new FileWriter(pathToSave);
        System.out.println(writer);

        Wallet wallet = walletService.getWalletPerUser(id);

        PDFUtils pdfUtils = new PDFUtils();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathToSave));

        document.open();
        pdfUtils.addMetaData(document);
        pdfUtils.addTitlePage(document);
        document.add(new Paragraph("Bitcoin: " + String.valueOf(wallet.getBitcoin_amount())));
        document.add(new Paragraph("Ethereum: " + String.valueOf(wallet.getEthereum_amount())));
        document.add(new Paragraph("Litecoin: " + String.valueOf(wallet.getLitecoin_amount())));
        document.add(new Paragraph("XRP: " + String.valueOf(wallet.getXrp_amount())));
        document.add(new Paragraph("EOS: " + String.valueOf(wallet.getEos_amount())));
        document.close();
        return 0;
    }
    else return 1;
    }
}
