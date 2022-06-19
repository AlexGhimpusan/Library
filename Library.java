import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class Library {

    List<Book>              booksList       = new LinkedList<Book>();

    static final String     FILE_PATH       = "src\\dataBooks.json";


    public JSONArray readJSONFile() {
        try(FileReader  fileReader = new FileReader(FILE_PATH)) {

            Object mainObject = (Object) new JSONParser().parse(fileReader);
            fileReader.close();

            return (JSONArray) mainObject;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeJSONFile(JSONArray mainArray) {
        //Create a file writer to write the updated array to JSON file
        try(FileWriter fileWriter = new FileWriter(FILE_PATH)) {

            fileWriter.write("[\n");
            for(int index = 0; index < mainArray.size(); index++) {
                JSONObject jsonObject = (JSONObject) mainArray.get(index);

                fileWriter.write("\t");
                fileWriter.write(jsonObject.toString());
                if(index != mainArray.size() - 1) {
                    fileWriter.write(",\n");
                }

            }
            fileWriter.write("\n]");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBooks() {

        //Open the file and parse the JSON from it to jsonArray variable
        try(FileReader  file        = new FileReader(FILE_PATH)){
            Object      object 	    = (Object) new JSONParser().parse(file);
            JSONArray   jsonArray   = (JSONArray) object;

            //Take every jsonobject from the array and put it in Book objects
            for(int index = 0; index < jsonArray.size(); index++) {
                //Read the jsonobject from the jsonarray
                JSONObject jsonBook = (JSONObject) jsonArray.get(index);

                //Create a new book based on the json atributes
                Book newBook = new Book(
                        (String)    jsonBook.get("title"),
                        (String)    jsonBook.get("author"),
                        Integer.parseInt(String.valueOf(jsonBook.get("year"))),
                        Double.parseDouble(String.valueOf(jsonBook.get("price")))
                );

                //Add the new book to the list of books
                booksList.add(newBook);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void addBook() {

        //Take the details from user
        Scanner scanner = new Scanner(System.in);

        System.out.println("Title: ");
        String newBookTitle = scanner.nextLine();

        System.out.println("Author: ");
        String newBookAuthor = scanner.nextLine();

        System.out.println("Published year: ");
        int newBookYear = Integer.parseInt(scanner.nextLine());

        System.out.println("Price: ");
        double newBookPrice = Double.parseDouble(scanner.nextLine());

        //Open the file and read the JSON from it
        JSONArray mainArray = this.readJSONFile();

        //Create the new book and add it to the existing json array
        JSONObject newBook = new JSONObject();

        newBook.put("title",    newBookTitle);
        newBook.put("author",   newBookAuthor);
        newBook.put("year",     newBookYear);
        newBook.put("price",    newBookPrice);

        mainArray.add(newBook);

        //Write the new json array to the file
        this.writeJSONFile(mainArray);

        //Update the Library
        this.loadBooks();

    }

    public void removeBook() {
        //Take the title of the book to be removed from the user
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nYou chose to delete a book!");
        System.out.println("Enter the title: ");
        String removeTitle = scanner.nextLine();

        //Open the file and read the JSON from it
        JSONArray mainArray = readJSONFile();

        //Effective remove from the json array

        for(int index = 0; index < mainArray.size(); index++) {
            JSONObject jsonObject = (JSONObject) mainArray.get(index);

            if(jsonObject.get("title").equals(removeTitle)) {
                mainArray.remove(index);
            }
        }
        //Write the new json array to the file
        this.writeJSONFile(mainArray);

        //Update the Library
        this.loadBooks();
    }

    public void displayLibrary() {
        booksList.forEach(Book::displayBook);
    }
}