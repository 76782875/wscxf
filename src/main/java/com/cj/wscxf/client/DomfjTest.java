package com.cj.wscxf.client;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class DomfjTest {
    public static void main(String[] args) {
        DomfjTest.success();

        DomfjTest.failure("描述失败的原因");
    }


    public static void parseXML(){
        File myXML = new File("d:\\wc.xml");
        SAXReader sr = new SAXReader();
        try {
            Document doc  =  sr.read(myXML);
            Element root = doc.getRootElement();
            for (Iterator fathers = root.elementIterator(); fathers.hasNext();) {
                Element father = (Element) fathers.next();
                for (Iterator childs = father.elementIterator();childs.hasNext();) {
                    Element child = (Element) childs.next();
                    for (Iterator nodes = child.elementIterator();nodes.hasNext();) {
                        Element node = (Element) nodes.next();
                        System.out.println(node.getName()+"-----"+node.getText());
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void success() {
        Document doc = DocumentHelper.createDocument();
        Element o = doc.addElement("o");
        Element resultValue = o.addElement("resultValue");
        resultValue.addAttribute("class","object");

        Element items = resultValue.addElement("items");
        items.addAttribute("class","array");

        Element e = items.addElement("e");
        e.addAttribute("class","object");

        Element msg = e.addElement("msg");
        msg.addAttribute("type","string");
        msg.setText("数据同步成功");

        Element successful = o.addElement("successful");
        successful.addAttribute("type","boolean");
        successful.setText("true");

        try {
            PrintWriter pw = new PrintWriter("d:\\success.xml");
            XMLWriter xw = new XMLWriter(pw);
            xw.write(doc);
            xw.flush();
            xw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Over");
        }
    }


    public static void failure(String message) {
        Document doc = DocumentHelper.createDocument();
        Element o = doc.addElement("o");
        Element resultValue = o.addElement("resultValue");
        resultValue.addAttribute("class","object");

        Element items = resultValue.addElement("items");
        items.addAttribute("class","array");

        Element e = items.addElement("e");
        e.addAttribute("class","object");

        Element msg = e.addElement("msg");
        msg.addAttribute("type","string");
        msg.setText(message);

        Element successful = o.addElement("successful");
        successful.addAttribute("type","boolean");
        successful.setText("false");

        try {
            PrintWriter pw = new PrintWriter("d:\\failure.xml");
            XMLWriter xw = new XMLWriter(pw);
            xw.write(doc);
            xw.flush();
            xw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Over");
        }
    }
}
