package sample.controller;

import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sample.model.FullName;
import sample.model.Student;
import sample.model.StudentsData;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileLoader {

    final static String COMMON_TAG = "students";
    final static String ELEMENT_TAG = "student";
    final static String NAME_TAG = "name";
    final static String SURNAME_TAG = "surname";
    final static String PATRONYMIC_TAG = "patronymic";
    final static String DATE_OF_BIRTH_TAG = "dateOfBirth";
    final static String DATE_OF_RECEIPT_TAG = "dateOfReceipt";
    final static String DATE_OF_GRADUATION_TAG = "dateOfGraduation";

    private StudentsData studentsData;

    public FileLoader(StudentsData studentsData) {
        this.studentsData = studentsData;
    }

    public void readFromXMLFile(String path) throws SAXException, ParserConfigurationException, IOException {
        class XMLReader extends DefaultHandler {


            private String name;
            private String surname;
            private String patronymic;
            private LocalDate dateOfBirth;
            private LocalDate dateOfReceipt;
            private LocalDate dateOfGraduation;


            private String thisElement;
            

            public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
                thisElement = qName;
            }

            public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

                if (surname == null) return;
                if (name == null) return;
                if (patronymic == null) return;
                if (dateOfBirth == null) return;
                if (dateOfReceipt == null) return;
                if (dateOfGraduation == null) return;

                new ChangerStudentsData(studentsData).addStudent(new Student(new FullName(surname, name, patronymic), dateOfBirth,
                        dateOfReceipt, dateOfGraduation));

                name = null;
                surname = null;
                patronymic = null;
                dateOfBirth = null;
                dateOfReceipt = null;
                dateOfGraduation = null;
                thisElement = "";
            }


            public void characters(char[] ch, int start, int length) throws SAXException {
                if (thisElement.equals(SURNAME_TAG)) {
                    surname = new String(ch, start, length);
                }
                if (thisElement.equals(NAME_TAG)) {
                    name = new String(ch, start, length);
                }
                if (thisElement.equals(PATRONYMIC_TAG)) {
                    patronymic = new String(ch, start, length);
                }
                if (thisElement.equals(DATE_OF_BIRTH_TAG)) {
                    dateOfBirth = LocalDate.parse(new String(ch, start, length));
                }
                if (thisElement.equals(DATE_OF_RECEIPT_TAG)) {
                    dateOfReceipt = LocalDate.parse(new String(ch, start, length));
                }
                if (thisElement.equals(DATE_OF_GRADUATION_TAG)) {
                    dateOfGraduation = LocalDate.parse(new String(ch, start, length));
                }

            }
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLReader saxp = new XMLReader();
        parser.parse(new File(path), saxp);
    }

    public void writeToXMLFile(String path) throws ParserConfigurationException,  TransformerException {
        ObservableList<Student> students = studentsData.getStudents();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        //Корневой элемент
        Document document = documentBuilder.newDocument();
        Element root = document.createElement(COMMON_TAG);
        document.appendChild(root);


        for (Student student : students) {

            Element childOfRoot = document.createElement(ELEMENT_TAG);
            root.appendChild(childOfRoot);

            Element surname = document.createElement(SURNAME_TAG);
            surname.appendChild(document.createTextNode(student.getFullName().getSurname()));
            childOfRoot.appendChild(surname);

            Element name = document.createElement(NAME_TAG);
            name.appendChild(document.createTextNode(student.getFullName().getName()));
            childOfRoot.appendChild(name);

            Element patronymic = document.createElement(PATRONYMIC_TAG);
            patronymic.appendChild(document.createTextNode(student.getFullName().getPatronymic()));
            childOfRoot.appendChild(patronymic);

            Element dateOfBirth = document.createElement(DATE_OF_BIRTH_TAG);
            dateOfBirth.appendChild(document.createTextNode(student.getDateOfBirth().toString()));
            childOfRoot.appendChild(dateOfBirth);

            Element dateOfReceipt = document.createElement(DATE_OF_RECEIPT_TAG);
            dateOfReceipt.appendChild(document.createTextNode(student.getDateOfReceipt().toString()));
            childOfRoot.appendChild(dateOfReceipt);

            Element dateOfGraduation = document.createElement(DATE_OF_GRADUATION_TAG);
            dateOfGraduation.appendChild(document.createTextNode(student.getDateOfGraduation().toString()));
            childOfRoot.appendChild(dateOfGraduation);

        }
            //Теперь запишем контент в FileLoader файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(path));

            transformer.transform(domSource, streamResult);
            System.out.println("Файл сохранен!");
        }
    }

