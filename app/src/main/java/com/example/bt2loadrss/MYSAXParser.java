package com.example.bt2loadrss;

import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class MYSAXParser {
    public static List<Item> xmlParser(InputStream inpS){
        List<Item> list = null;
        try {
            XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();

            XMLSAXHandler xmlsaxHandler = new XMLSAXHandler();
            reader.setContentHandler(xmlsaxHandler);

            reader.parse(new InputSource(inpS));

            list = xmlsaxHandler.getList();
        }catch (Exception e){
            Log.d("Error:" ,e.getMessage());
        }

        return list;
    }
}
