package com.bridgelabz.addressbook;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AddressBookTest {
    PersonDetails person1;
    PersonDetails person2;
    @Before
    public void initialize() {
        person1 = new PersonDetails();
        person1.setFirstName("sushant");
        person1.setLastName("lad");
        person1.setAddress("balajinagar");
        person1.setCity("pune");
        person1.setState("maharashtra");
        person1.setPinCode(411043);
        person1.setPhoneNumber("8830602356");
        person1.setEmail("sushant@gmail.com");

        person2 = new PersonDetails();
        person2.setFirstName("pratik");
        person2.setLastName("lad");
        person2.setAddress("kavalapur");
        person2.setCity("sangli");
        person2.setState("maharashtra");
        person2.setPinCode(416416);
        person2.setPhoneNumber("9096420921");
        person2.setEmail("pratik@gmail.com");
    }


    @Test
    public void givenAContact_WhenAddedToList_ShouldReturnCorrectSize() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(person1, IOService.LIST_DS_IO);
        addressBook.addPerson(person2, IOService.LIST_DS_IO);
        Assert.assertEquals(2,addressBook.referenceBook.size());
    }

    @Test
    public void givenAContact_WhenAddedToFile_ShouldReturnCorectSize() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(person1, IOService.TXT_FILE_IO);
        addressBook.addPerson(person2, IOService.TXT_FILE_IO);
        long size = 0;
        try {
            size = Files.lines(Paths.get("AddressBook-file.txt")).count();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2,size);
    }

    @Test
    public void whenCalled_ReadFromListMethod_ShouldPrintList() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(person1, IOService.LIST_DS_IO);
        addressBook.addPerson(person2, IOService.LIST_DS_IO);
        long size  = addressBook.readData(IOService.LIST_DS_IO);
        Assert.assertEquals(2,size);
    }

    @Test
    public void whenCalled_ReadFromFileMethod_ShouldPrintFile() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(person1, IOService.TXT_FILE_IO);
        addressBook.addPerson(person2, IOService.TXT_FILE_IO);
        long size  = addressBook.readData(IOService.TXT_FILE_IO);
        Assert.assertEquals(2,size);
    }

    @Test
    public void givenAContact_WhenAddedToCSVFile_ShouldReturnCorrectSize() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(person1, IOService.CSV_IO);
        addressBook.addPerson(person2, IOService.CSV_IO);
        long size = 0;
        try {
            size = Files.lines(Paths.get("AddressBook-file.csv")).count();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2,size-1);
    }

    @Test
    public void whenCalled_ReadFromCSVMethod_ShouldPrintFile() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(person1, IOService.CSV_IO);
        addressBook.addPerson(person2, IOService.CSV_IO);
        long size  = addressBook.readData(IOService.CSV_IO);
        Assert.assertEquals(2,size);
    }

    @Test
    public void givenAContact_WhenAddedToJSONFile_ShouldReturnCorrectSize() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(person1, IOService.JSON_IO);
        addressBook.addPerson(person2, IOService.JSON_IO);
        long count =0;
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader("AddressBook-file.json"));
            PersonDetails[] usrObj = gson.fromJson(br, PersonDetails[].class);
            List<PersonDetails> csvUSerList = Arrays.asList(usrObj);
            count = csvUSerList.size();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2,count);
    }

    @Test
    public void whenCalled_ReadFromJsonMethod_ShouldPrintFile() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(person1, IOService.JSON_IO);
        addressBook.addPerson(person2, IOService.JSON_IO);
        long size  = addressBook.readData(IOService.JSON_IO);
        Assert.assertEquals(2,size);
    }
}
