package crawler;

import com.google.common.base.Joiner;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PersistProducts {

    public void persist(List<Product> products) throws IOException {
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateFormat.format(date);
        try(
                OutputStream outputStream = new FileOutputStream("matchesfashion-" + stringDate + ".csv");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8"))
        {
            TsvWriter writer = new TsvWriter(outputStreamWriter, new TsvWriterSettings()) ;
            writer.writeHeaders(Product.header);
            for(Product product : products) {
                writer.addValue(product.code);
                writer.addValue(product.title);
                writer.addValue(product.lister__item__details);
                writer.addValue(product.lister__item__price);
                writer.addValue(Joiner.on(",")
                        .skipNulls()
                        .join(product.sizes)) ;
                writer.addValue(product.productUrl);
                writer.addValue(product.on_off_shelf);
                writer.addValue(product.on_shelf_date);
                writer.addValue(product.on_shelf_date);
                writer.addValue(product.sizes_in_short);
                writer.addValue(product.sale_off_rate);
                writer.addValue(product.complements);
                writer.addValue(product.complement_date);
                //flushes all values to the output, creating a row.
                writer.writeValuesToRow();
            }
        }
    }
}