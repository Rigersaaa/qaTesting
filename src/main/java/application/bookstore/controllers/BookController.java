package application.bookstore.controllers;

import application.bookstore.models.Author;
import application.bookstore.models.Book;
import application.bookstore.models.Category;
import application.bookstore.views.BookView;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class BookController {
    private static BookView bookView;

    public BookController(BookView bookView) {
        this.bookView = bookView;
        setSaveListener();
        setEditListener();
        setDeleteListener();
        setSearchButton();
    }
    static String regex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

    private void setSaveListener() {
        bookView.getSaveBtn().setOnAction(e -> {
            String isbn = bookView.getIsbnField().getText();
            String title = bookView.getTitleField().getText();
            float purchasedPrice = Float.parseFloat(bookView.getPurchasedPriceField().getText());
            float sellingPrice = Float.parseFloat(bookView.getSellingPriceField().getText());
            Author author = bookView.getAuthorsComboBox().getValue();
            LocalDate date = bookView.getDateOfBook().getValue();
            String stock = bookView.getStockField().getText();
            Category category = bookView.getCategoryfield().getValue();
            int quantity = Integer.parseInt(bookView.getQuantityField().getText());
//            Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author);
            Book copyBook = new Book(isbn, title, purchasedPrice, sellingPrice, author, category, date, stock, quantity);
            System.out.println(Book.getBookByISBN(isbn));
            if (Book.getBookByISBN(isbn) != -1) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Book already exists");
                a.show();
            }
            if (Book.getBookByISBN(isbn) == -1 && isbn.matches(regex) && purchasedPrice > 0 && sellingPrice > 0 && quantity > 0 && date != null && title != null) {
                bookView.getTableView().getItems().add(copyBook);
//                        Book.getBooks().add(book);
                Book.getBooks2().add(copyBook);
                Book.saveToNewFile();
                Book.saveToNewFile2();
                bookView.getResultLabel().setText("Book created successfully");
                bookView.getResultLabel().setTextFill(Color.DARKGREEN);
                //BookOrder.RefreshView();

                resetFields();
            }
            if (!isbn.matches(regex)) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Wrong ISBN format");
                a.show();
            }
            if (purchasedPrice < 0 || sellingPrice < 0) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Enter a positive price");
                a.show();
            }
            if (quantity < 0) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Enter a positive number");
                a.show();
            }  if (author == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Select an author");
                a.show();
            }
            if (date == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Select a date");
                a.show();
            }



        });
    }

    private void setEditListener() {
        // anonymous inner class
        bookView.getIsbnCol().setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book BookToEdit = event.getRowValue();
                int index = Book.getBooks2().indexOf(BookToEdit);
                String newIsbn = event.getNewValue();

                    if(newIsbn.matches(regex)){
                        Book.getBooks().get(index).setIsbn(event.getNewValue());
                        Book.saveToNewFile2();
                    }
                    else{
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Please Enter a Valid ISBN");
                        a.show();

                    }



            }
        });
        // with lambda now
        bookView.getTitleCol().setOnEditCommit(event -> {
            Book bookToEdit = event.getRowValue();
            String titleBackUp = bookToEdit.getTitle();
            String title = event.getNewValue();
            int index = Book.getBooks2().indexOf(bookToEdit);
            if(title!=""){
                Book.getBooks().get(index).setTitle(event.getNewValue());
                Book.saveToNewFile2();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                Book.getBooks().get(index).setTitle(titleBackUp);
                a.setContentText("Please Enter a Valid Title");
                a.show();

            }



        });
        bookView.getPurchasedPriceCol().setOnEditCommit(event -> {
            Book bookToEdit = event.getRowValue();

            Float price = event.getNewValue();
            int index = Book.getBooks2().indexOf(bookToEdit);
            if(price>=0){
                Book.getBooks().get(index).setPurchasedPrice(event.getNewValue());
                Book.saveToNewFile2();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);

                a.setContentText("Please Enter a Positive Price");
                a.show();

            }
        });

        bookView.getSellingPriceCol().setOnEditCommit(event -> {
            Book bookToEdit = event.getRowValue();

            Float price = event.getNewValue();
            int index = Book.getBooks2().indexOf(bookToEdit);
            if(price>=0){
                Book.getBooks().get(index).setSellingPrice(event.getNewValue());
                Book.saveToNewFile2();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);

                a.setContentText("Please Enter a Positive Price");
                a.show();

            }



        });


        bookView.getQuantityCol().setOnEditCommit(event -> {
            Book bookToEdit = event.getRowValue();

            Integer quant = event.getNewValue();
            int index = Book.getBooks2().indexOf(bookToEdit);
            if(quant>=0){
                Book.getBooks().get(index).setQuantity(event.getNewValue());
                Book.saveToNewFile2();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);

                a.setContentText("Please Enter a Positive number for stock");
                a.show();

            }



        });

        /*bookView.getAuthorCol().setOnEditCommit(event -> {
            Book bookToEdit = event.getRowValue();

            Author author = Author.event.getNewValue();
            int index = Book.getBooks2().indexOf(bookToEdit);
            if(quant>=0){
                Book.getBooks().get(index).setQuantity(event.getNewValue());
                Book.saveToNewFile2();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);

                a.setContentText("Please Enter a Positive number for stock");
                a.show();

            }



        });*/


        // if the user clicks edit button, save the changes into the file
                /*authorView.getEditBtn().setOnAction(e -> {
                    try {
                        // todo change it
                        FileHandler.overwriteCurrentListToFile(Author.DATA_FILE, Author.getAuthors());
                        authorView.getResultLabel().setText("Authors were updated successfully");
                    } catch (IOException ex) {
                        authorView.getResultLabel().setText("Writing authors to the file failed!");
                        ex.printStackTrace();
                    }
                });*/
    }

    private void resetFields() {
        bookView.getIsbnField().setText("");
        bookView.getTitleField().setText("");
        bookView.getPurchasedPriceField().setText("");
        bookView.getSellingPriceField().setText("");
        bookView.getStockField().setText(" ");

    }

    private void setDeleteListener() {
    bookView.getDeleteBtn().setOnAction(event -> {
        Book book = bookView.getTableView().getSelectionModel().getSelectedItem();
        Book.getBooks2().remove(book);
        bookView.getTableView().getItems().remove(book);
        Book.saveToNewFile2();
    });



    }
    private void setSearchButton(){
        bookView.getSearchView().getSearchBtn().setOnAction(event -> {

            String searchedTtile = new String(bookView.getSearchView().getSearchField().getText().toLowerCase());
            if(titleInBook(searchedTtile)){
                bookView.getTableView().getItems().removeAll(bookView.getTableView().getItems());
                for(Book book : Book.getBooks2()){
                    if(searchedTtile.matches(book.getTitle().toLowerCase())){
                        bookView.getTableView().getItems().add(book);
                    }
                }

            }

        });
        bookView.getSearchView().getClearBtn().setOnAction(event -> {
            bookView.getTableView().getItems().removeAll(bookView.getTableView().getItems());
            bookView.getTableView().getItems().addAll(Book.getBooks2());
        });
    }

    private boolean titleInBook(String title){
        for(Book book : Book.getBooks()){
            if(title.matches(book.getTitle().toLowerCase())){
                return true;
            }
        }
        return false;
    }

}