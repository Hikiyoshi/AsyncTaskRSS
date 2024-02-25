package com.example.bt2loadrss;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLSAXHandler extends DefaultHandler {
    private List<Item> list;
    private Item item;
    private String temp;
    private boolean flagStart = false;

    public XMLSAXHandler() {
        list = new ArrayList<>();
    }

    public List<Item> getList() {
        return list;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if(flagStart == true){
            temp = new String(ch,start,length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //Tên thẻ bắt đầu: qName = item
        Log.d("test2",qName);
        if(qName.equalsIgnoreCase("item")){
            item = new Item();
            flagStart = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if(qName.equalsIgnoreCase("item")){
            list.add(item);
        }
        else if(qName.equalsIgnoreCase("title") && flagStart){
            item.setTitle(temp);
        }
        if(qName.equalsIgnoreCase("description") && flagStart){
            item.setDescription(temp);
        }
        if(qName.equalsIgnoreCase("link") && flagStart){
            item.setLink(temp);
        }
    }
}
