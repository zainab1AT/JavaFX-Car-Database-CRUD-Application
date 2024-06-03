package lab6Data;

//search in one table 

import java.sql.*;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mysql.cj.x.protobuf.MysqlxCrud.DataModel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.util.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;


public class AssignmentDataBase extends Application {
      //private ObservableList<ObservableList> data;
	private ObservableList<ObservableList> data = FXCollections.observableArrayList();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
    }
 
	private Connection c;
	private ResultSet rs;
    private TableView tableview;
	private ComboBox tablesList;
	private Group group2;
	private Group group;
	private HBox header;
	private VBox leftBox;
	private GridPane grid;
	private VBox clearExit;
	private Button insert;
	private Button delete;
	private Button update;
	private Button search;
	private Button clear;
	private Button all;
	private Button exit;
	private VBox vbox1;
	private VBox vbox2;
	private VBox vbox3;
	private VBox vbox4;
	private VBox vbox5;
	private VBox vbox6;
	private VBox vbox7;
	private TextField id;
	private TextField country;
	private TextField city;
	private TextField street;
	private TextField buidling;
	private TextField year;
	private TextField model;
	private TextField name;
	private ComboBox made;
	private ComboBox car;
	private ComboBox part;
	private TextField idC;
	private TextField fName;
	private TextField lname;
	private ComboBox address;
	private TextField job;
	private TextField no;
	private TextField nameDe;
	private TextField price;
	private TextField weight;
	private ComboBox madeDe;
	private TextField nameManu;
	private TextField type;
	private TextField cityManu;
	private TextField countryManu;
	private TextField idOr;
	private TextField date;
	private ComboBox customer;
	private ComboBox carOr;
  
    
    public static void main(String[] args) {
        launch(args); 
    }


    @Override
    public void start(Stage stage) throws Exception {
    	
        stage.setTitle("Project Database");
        
        Image im1=new Image("logo.png");
		stage.getIcons().add(im1);
        
        //First Scene  
        
        Label hello=new Label("Hello everyone , In");
        hello.setFont(Font.font("Lato", 26));
	    hello.setStyle("-fx-font-weight: bold;");
	    hello.setTextFill((Color.LIGHTGRAY));	
		hello.setLayoutX(30);hello.setLayoutY(235);
	    
        Label zaina=new Label("Project Database");
        zaina.setFont(Font.font("Lato", 48));
	    zaina.setTextFill((Color.WHITE));	
        zaina.setStyle("-fx-font-weight: bold;");
    	zaina.setLayoutX(30);zaina.setLayoutY(270);
        
        Label roll=new Label("By Zainab Atwa | 2003");
        roll.setFont(Font.font("Lato", 20));
        roll.setStyle("-fx-font-weight: bold;");
	    roll.setTextFill((Color.WHITE));
    	roll.setLayoutX(30);roll.setLayoutY(340);
        
    	Button go =new Button("Go to DB");
    	go.setPrefWidth(125);
        go.setLayoutX(30);go.setLayoutY(440);
        go.setTextFill(Color.BLACK);
        go.setStyle(  "-fx-padding: 7;" + "-fx-background-color:white; "+ "-fx-border-width: 3;"+"-fx-font-weight:bold;" );
        go.setOnMouseEntered(e -> go.setStyle("-fx-background-color: GAINSBORO;"));
        go.setOnMouseExited (e -> go.setStyle( "-fx-padding: 7;" + "-fx-background-color:white; "+ "-fx-border-width: 3;"+"-fx-font-weight:bold;"));

       	ImageView user=new ImageView(new Image("laptob.png"));
		user.setFitHeight(570);
		user.setFitWidth(570);
		user.setLayoutY(30);
		user.setLayoutX(430);

		
        group2=new Group();
        group2.getChildren().addAll(hello,go,zaina,roll,user);
		Scene scene2=new Scene(group2,1020,700);
		scene2.setFill(Color.DARKCYAN);
        stage.setScene(scene2);
        
        
        //Basic Scene
	    group=new Group();
		Scene scene1=new Scene(group,1020,700);
		scene1.setFill(Color.LIGHTGRAY);

	
		//label
		Label database=new Label("Access Database Connection by JavaFX");
		database.setLayoutX(400);
		database.setLayoutY(20);
		database.setStyle("-fx-font-weight: bold;");
		database.setFont(Font.font("Cambria", 30));
	    database.setTextFill((Color.WHITE));	
			
	    
		ImageView full=new ImageView(new Image("square.png"));
		full.setFitHeight(400);
		full.setFitWidth(400);
		full.setLayoutY(180);
		full.setLayoutX(430);
	    
	    //HBox
	    
	    header=new HBox();
	    header.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));//DARKCYAN
	    header.setPrefWidth(720);
	    header.setPrefHeight(700);//80
	    header.setLayoutX(300);
	    header.setLayoutY(0);
	    header.setOpacity(0.4);
	    
	    
		//line 
		Line line=new Line(0,80,300,80);
		line.prefWidth(300);
	    line.setStroke(Color.LIGHTGRAY);
		
	    
	    Line line2=new Line(0,400,300,400);
		line2.prefWidth(300);
	    line2.setStroke(Color.LIGHTGRAY);
	    
	    Line line3=new Line(300,80,1020,80);
		line3.prefWidth(700);
	    line3.setStroke(Color.DARKCYAN);
	    
//tablesList
		
        String ta[] = {"Address Table","Car Table", "Car_Part Table","Customer Table","Device Table","Manufacture Table","Orders Table"};
        
        tablesList=new ComboBox<>(FXCollections.observableArrayList(ta));
		tablesList.setPromptText(" Choose Table ");
        tablesList.setLayoutX(45);
		tablesList.setLayoutY(20);
		tablesList.setVisible(true);
        tablesList.setPrefWidth(210);
        tablesList.setStyle(  "-fx-background-color:White; ");//LIGHTGRAY
		
		
//VBox
	    leftBox=new VBox();
		leftBox.setLayoutX(0);
		leftBox.setLayoutY(0);
		leftBox.setPrefHeight(700);
		leftBox.setPrefWidth(300);
		leftBox.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));


		
// table 
    
        tableview = new TableView<>();
        tableview.setPrefWidth(720);
        tableview.setPrefHeight(300); //260

        tableview.setLayoutX(300);
        tableview.setLayoutY(400);   //400
        tableview.setVisible(false);
        
//operationsList
        
	  
       grid=new GridPane();
       grid.setLayoutX(20);
       grid.setLayoutY(430);
       grid.setHgap(15);
       grid.setVgap(15);      

       clearExit=new VBox();
       clearExit.setPrefWidth(270);
       clearExit.setLayoutX(20);
       clearExit.setLayoutY(530);
       clearExit.setSpacing(15);
       

        insert=new Button("Insert");  
        insert.setPrefWidth(125);
        insert.setTextFill(Color.BLACK);
        insert.setStyle(  "-fx-padding: 7;" + 
           "-fx-background-color:white; "+
                "-fx-border-width: 3;"
                );
        
      
       
        delete=new Button("Delete");
        delete.setPrefWidth(125);
        delete.setTextFill(Color.BLACK);
        delete.setStyle(  "-fx-padding: 7;" + 
              "-fx-background-color:white; "+
                "-fx-border-width: 3;"
                );
        
        update=new Button("Update");
        update.setPrefWidth(125);
        update.setTextFill(Color.BLACK);
        update.setStyle(  "-fx-padding: 7;" + 
          "-fx-background-color:white; "+
                "-fx-border-width: 3;"
                );
        
        
        search=new Button("Search");
        search.setPrefWidth(125); 
        search.setTextFill(Color.BLACK);
        search.setStyle(  "-fx-padding: 7;" + 
             "-fx-background-color:white; "+
                "-fx-border-width: 3;"
                );
 
        
        clear=new Button("Clear");
        clear.setPrefWidth(265);  
        clear.setTextFill(Color.BLACK);
        clear.setStyle(  "-fx-padding: 7;" + 
             "-fx-background-color:white; "+
                "-fx-border-width: 3;"
                );

        
        all=new Button("Table");
        all.setPrefWidth(265);
        all.setTextFill(Color.BLACK);
        all.setStyle(  "-fx-padding: 7;" +  "-fx-background-color:white; "+
                "-fx-border-width: 3;"
                ); 
    
        
        
        exit=new Button("Exit");
        exit.setPrefWidth(265);
        exit.setTextFill(Color.WHITE);
        exit.setStyle(  "-fx-padding: 7;" + 
               "-fx-background-color:black; "+
                "-fx-border-width: 3;"
                );
        
       

grid.add(update, 0, 0);
grid.add(delete, 1, 0);
grid.add(search, 0, 1);
grid.add(insert, 1, 1);
clearExit.getChildren().addAll(clear,all,exit);
grid.setVisible(false);
clearExit.setVisible(false);
// address table   

        c=getConnection();
        
        vbox1=new VBox();
        
        vbox1.setLayoutX(40);
        vbox1.setLayoutY(125);  
		vbox1.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));

        
         id=new TextField();
        id.setPromptText("ID");
                		
         buidling=new TextField();
        buidling.setPromptText("Buidling");
        
         street=new TextField();
        street.setPromptText("Street");
        
         city=new TextField();
        city.setPromptText("City");
        
         country=new TextField();
        country.setPromptText("Country");
        
        vbox1.getChildren().addAll(id,buidling,street,city,country);
        vbox1.setSpacing(10);
        vbox1.setStyle("-fx-padding: 10;" + 
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" + 
                "-fx-border-radius: 5;" );
        
        vbox1.setVisible(false);
        
        

// Car Table      
        
   
        vbox2=new VBox();
        
        vbox2.setLayoutX(40);
        vbox2.setLayoutY(150);       
		vbox2.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));

        

         name=new TextField();
        name.setPromptText("Name");
        
         model=new TextField();
        model.setPromptText("Model");
        
         year=new TextField();
        year.setPromptText("Year");
        
         made = new ComboBox<>(gett("name","manufacture"));
        made.setPrefWidth(200);
        made.setPromptText("Made");

        
        vbox2.getChildren().addAll(name,model,year,made);
        vbox2.setSpacing(10);
        vbox2.setStyle("-fx-padding: 10;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" + 
                "-fx-border-radius: 5;" );
        
        vbox2.setVisible(false);
          
        
        
        

// Car_Part Table      
             vbox3=new VBox();
             
             vbox3.setLayoutX(40);
             vbox3.setLayoutY(150);        
     		 vbox3.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));

             
              car = new ComboBox<>(gett("name","car"));
             car.setPrefWidth(200);
             car.setPromptText("Car");
             
              part = new ComboBox<>(gett("no","device"));
             part.setPrefWidth(200);
             part.setPromptText("Part");
             
             vbox3.getChildren().addAll(car,part);
             vbox3.setSpacing(10);
             vbox3.setStyle("-fx-padding: 10;" + 
                     "-fx-border-style: solid inside;" + 
                     "-fx-border-width: 2;" +
                     "-fx-border-insets: 5;" + 
                     "-fx-border-radius: 5;" );
             
             vbox3.setVisible(false);
        
        
 // Customer table 
             
             vbox4=new VBox();
             
             vbox4.setLayoutX(40);
             vbox4.setLayoutY(125);        
     		 vbox4.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));

             
              idC=new TextField();
             idC.setPromptText("ID");
             
              fName=new TextField();
             fName.setPromptText("f_name");
             
              lname=new TextField();
             lname.setPromptText("l_name");
             
             
              address = new ComboBox<>(gett("id","address"));
             address.setPrefWidth(200);
             address.setPromptText("Address");
             
              job=new TextField();
             job.setPromptText("Job");
             
             vbox4.getChildren().addAll(idC,fName,lname,address,job);
             vbox4.setSpacing(10);
             vbox4.setStyle("-fx-padding: 10;" + 
                     "-fx-border-style: solid inside;" + 
                     "-fx-border-width: 2;" +
                     "-fx-border-insets: 5;" + 
                     "-fx-border-radius: 5;" );
             
             vbox4.setVisible(false);
             
             
 // Device table 
             
             vbox5=new VBox();
             
             vbox5.setLayoutX(40);
             vbox5.setLayoutY(125);        
     		 vbox5.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));

             
              no=new TextField();
             no.setPromptText("no");
             
              nameDe=new TextField();
             nameDe.setPromptText("Name");
             
              price=new TextField();
             price.setPromptText("Price");
             
              weight=new TextField();
             weight.setPromptText("Weight");
             
              madeDe = new ComboBox<>(gett("name","manufacture"));
             madeDe.setPrefWidth(200);
             madeDe.setPromptText("Made");
             
             vbox5.getChildren().addAll(no,nameDe,price,weight,madeDe);
             vbox5.setSpacing(10);
             vbox5.setStyle("-fx-padding: 10;" + 
                     "-fx-border-style: solid inside;" + 
                     "-fx-border-width: 2;" +
                     "-fx-border-insets: 5;" + 
                     "-fx-border-radius: 5;" );
             
             vbox5.setVisible(false);
             
             
 //manufacture table 
             
             vbox6=new VBox();
             
             vbox6.setLayoutX(40);
             vbox6.setLayoutY(125);        
     		 vbox6.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));

             
              nameManu=new TextField();
             nameManu.setPromptText("Name");
             
              type=new TextField();
             type.setPromptText("Type");
             
              cityManu=new TextField();
             cityManu.setPromptText("City");
             
              countryManu=new TextField();
             countryManu.setPromptText("Country");
             
             vbox6.getChildren().addAll(nameManu,type,cityManu,countryManu);
             vbox6.setSpacing(10);
             vbox6.setStyle("-fx-padding: 10;" + 
                     "-fx-border-style: solid inside;" + 
                     "-fx-border-width: 2;" +
                     "-fx-border-insets: 5;" + 
                     "-fx-border-radius: 5;" );
             
             vbox6.setVisible(false);
             
            
 //order table 
             
             vbox7=new VBox();
             
             vbox7.setLayoutX(40);
             vbox7.setLayoutY(125);        
     		 vbox7.setBackground(new Background(new BackgroundFill(Color.DARKCYAN ,null,null)));

             
              idOr=new TextField();
             idOr.setPromptText("id");
             
             
              date=new TextField();
             date.setPromptText("Date");
             

              customer = new ComboBox<>( gett("id","customer"));
             customer.setPrefWidth(200);
             customer.setPromptText("Customer");
             
              carOr = new ComboBox<>(gett("name","car"));
             carOr.setPrefWidth(200);
             carOr.setPromptText("Car");
             
             vbox7.getChildren().addAll(idOr,date,customer,carOr);
             vbox7.setSpacing(10);
             vbox7.setStyle("-fx-padding: 10;" + 
                     "-fx-border-style: solid inside;" + 
                     "-fx-border-width: 2;" +
                     "-fx-border-insets: 5;" + 
                     "-fx-border-radius: 5;" );
             
             vbox7.setVisible(false);
           
             
 // changes in tables list  
             
               StringBuilder sql = new StringBuilder("SELECT * FROM  ");
               
               
               tablesList.setOnAction((event) -> {
                   tableview.setVisible(true);
            	   tableview.getItems().clear();
                   tableview.getColumns().clear();
                   grid.setVisible(true);
                   clearExit.setVisible(true);
                   full.setVisible(false);
                    try {
						getConnection();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                   
            	 if(tablesList.getValue().equals("Address Table")) {
            	   vbox1.setVisible(true);vbox2.setVisible(false);vbox3.setVisible(false);vbox4.setVisible(false);vbox5.setVisible(false);vbox6.setVisible(false);vbox7.setVisible(false);
            	   sql.replace(13,sql.length(), " address");
            	   method(sql.toString());
            	   update.setDisable(false);
            	 }
            	 
            	 else if(tablesList.getValue().equals("Car Table")) {
              	   vbox1.setVisible(false);vbox2.setVisible(true);vbox3.setVisible(false);vbox4.setVisible(false);vbox5.setVisible(false);vbox6.setVisible(false);vbox7.setVisible(false);
              	   sql.replace(13,sql.length(), " car");
               	   method(sql.toString()); 
            	   update.setDisable(false);
            	 }
            	 
            	 else if(tablesList.getValue().equals("Car_Part Table")) {
              	   vbox1.setVisible(false);vbox2.setVisible(false);vbox3.setVisible(true);vbox4.setVisible(false);vbox5.setVisible(false);vbox6.setVisible(false);vbox7.setVisible(false);
              	   sql.replace(13,sql.length(), " car_part");
              	   method(sql.toString());
              	   update.setDisable(true);
            	 }
            	 
            	 else if(tablesList.getValue().equals("Customer Table")) {
              	   vbox1.setVisible(false);vbox2.setVisible(false);vbox3.setVisible(false);vbox4.setVisible(true);vbox5.setVisible(false);vbox6.setVisible(false);vbox7.setVisible(false);
              	   
              	   sql.replace(13,sql.length(), " customer");
                   method(sql.toString());
            	   update.setDisable(false);

                 }
      	 
            	 else if(tablesList.getValue().equals("Device Table")) {
              	   vbox1.setVisible(false);vbox2.setVisible(false);vbox3.setVisible(false);vbox4.setVisible(false);vbox5.setVisible(true);vbox6.setVisible(false);vbox7.setVisible(false);
              	   sql.replace(13,sql.length(), " device");
              	   method(sql.toString());
            	   update.setDisable(false);

            	 }
            	 
            	 else if(tablesList.getValue().equals("Manufacture Table")) {
              	   vbox1.setVisible(false);vbox2.setVisible(false);vbox3.setVisible(false);vbox4.setVisible(false);vbox5.setVisible(false);vbox6.setVisible(true);vbox7.setVisible(false);
              	   sql.replace(13,sql.length(), " manufacture");
              	   method(sql.toString());
            	   update.setDisable(false);

            	 }
 
            	 else {
              	   vbox1.setVisible(false);vbox2.setVisible(false);vbox3.setVisible(false);vbox4.setVisible(false);vbox5.setVisible(false);vbox6.setVisible(false);vbox7.setVisible(true);     	   
              	   sql.replace(13,sql.length(), " orders");
              	   method(sql.toString());
            	   update.setDisable(false);

                 }
            	 
             });

   
               
             
//buttons events
               
 //exit Button Action
               
               exit.setOnAction( new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                           
                       Alert alert=new Alert(AlertType.CONFIRMATION);
                       alert.setTitle("Exit");
                       alert.setHeaderText("Are you sure you want to exit ?");
                       if(alert.showAndWait().get()==ButtonType.OK) {
                          tableview.getItems().clear(); // Clear previous data 
                          stage.setScene(scene2);
                       }
                   } 
                   });
 
               
 //all Button Action
               all.setOnAction( new EventHandler<ActionEvent>() { 
                   public void handle(ActionEvent event) {
                   
                	   StringBuilder sql =new StringBuilder("Select * from  ");  
                	   if(tablesList.getValue().equals("Address Table")) {sql.replace(13,sql.length(), " address  ");}
                	   else if (tablesList.getValue().equals("Car Table")) {sql.replace(13,sql.length(), " car  ");}
                	   else if (tablesList.getValue().equals("Car_Part Table")) {sql.replace(13,sql.length(), " car_part  ");}
                	   else if (tablesList.getValue().equals("Customer Table")) {sql.replace(13,sql.length(), " customer  ");}
                	   else if (tablesList.getValue().equals("Orders Table")) {sql.replace(13,sql.length(), " orders  ");}
                	   else if (tablesList.getValue().equals("Device Table")) {sql.replace(13,sql.length(), " device  ");}
                	   else if (tablesList.getValue().equals("Manufacture Table")) {sql.replace(13,sql.length(), " manufacture  ");}
                	   
                	   tableview.getItems().clear();
  	                   tableview.getColumns().clear();
  	                   method(sql.toString());
                 }});
               
               
 //search Button Action
               
                   search.setOnAction( new EventHandler<ActionEvent>() { 
                   public void handle(ActionEvent event) {
                   
                	   StringBuilder sql =new StringBuilder("Select * from  ");
                	   
                	   
 /*address*/     if(tablesList.getValue().equals("Address Table")) {
    	            sql.replace(13,sql.length(), " address  WHERE");
    	              if(id.getText().isEmpty() && buidling.getText().isEmpty() && city.getText().isEmpty() && country.getText().isEmpty()&&street.getText().isEmpty()) {
    	                 (sql.toString()).replace("WHERE","") ;
    	                  sql.setLength(sql.length() - 5);
    	                     }

    	              else {
    	                       if (!id.getText().isEmpty())       {if(checkIfInteger("ID" ,id)) { sql.append(" id = '").append(id.getText().trim()).append("' AND ");}else{ sql.append(" id = '' AND ");}}

    	                       if (!street.getText().isEmpty())   {sql.append(" street = '").append(street.getText().trim()).append("' AND ");}

    	                       if (!city.getText().isEmpty())     {sql.append(" city = '").append(city.getText().trim()).append("' AND "); }

    	                       if (!buidling.getText().isEmpty()) {if(checkIfInteger("Buidling",buidling)) { sql.append(" buidling = '").append(buidling.getText().trim()).append("' AND ");}else{ sql.append(" buidling = '' AND ");}}
    	                      
    	                       if (!country.getText().isEmpty())  {sql.append(" country = '").append(country.getText().trim()).append("' AND ");}
    	                      
    	                  } }
      
       
       
 /*car*/   if(tablesList.getValue().equals("Car Table")) {
               sql.replace(13,sql.length(), " car  WHERE");
           
           if(name.getText().isEmpty() && model.getText().isEmpty()&& year.getText().isEmpty() && made.getSelectionModel().isEmpty() ) {
           	 (sql.toString()).replace("WHERE","") ;
           	 sql.setLength(sql.length() - 5);}

           else {
                if (!name.getText().isEmpty())      {sql .append(" name  = '").append(name.getText().trim()).append("' AND ");}

                if (!model.getText().isEmpty())     { sql.append(" model = '").append(model.getText().trim()).append("' AND ");}
              
                if (!year.getText().isEmpty())      { sql.append(" year = '").append(year.getText().trim()).append("' AND ");}
                
                if (!made.getSelectionModel().isEmpty())  { sql.append(" made  = '").append(made.getValue()).append("' AND ");}
              
              }}
 
 
 
 /*car_ Part*/   if(tablesList.getValue().equals("Car_Part Table")) {
                    sql.replace(13,sql.length(), " car_part  WHERE");
 
                 if(car.getSelectionModel().isEmpty() && part.getSelectionModel().isEmpty()) {
 	               (sql.toString()).replace("WHERE","") ;
 	                sql.setLength(sql.length() - 5);}

                 else {
                    if (!car.getSelectionModel().isEmpty())  {sql.append(" car = '").append(car.getValue()).append("' AND ");}

                    if (!part.getSelectionModel().isEmpty()) { sql.append(" part = '").append(part.getSelectionModel().getSelectedItem()).append("' AND ");}
  
   }}
 
 
 /*customer*/     if(tablesList.getValue().equals("Customer Table")) {
                   sql.replace(13,sql.length(), " customer  WHERE");
           
                  if(idC.getText().isEmpty() && job.getText().isEmpty() && address.getSelectionModel().isEmpty() && fName.getText().isEmpty()&&lname.getText().isEmpty()) {
                	  (sql.toString()).replace("WHERE","") ;
                      sql.setLength(sql.length() - 5);
                                     }
 
                  else {
                    if (!idC.getText().isEmpty())       {if(checkIfInteger("ID",idC)) { sql.append(" id = '").append(idC.getText().trim()).append("' AND ");}else{ sql.append(" id = '' AND ");}}

                    if (!job.getText().isEmpty())   {sql.append(" job = '").append(job.getText().trim()).append("' AND ");}

                    if (!address.getSelectionModel().isEmpty())   {sql.append(" address = '").append(address.getSelectionModel().getSelectedItem()).append("' AND ");}
             
                    if (!fName.getText().isEmpty()) {sql.append(" f_name = '").append(fName.getText().trim()).append("' AND ");}
               
                    if (!lname.getText().isEmpty())  {sql.append(" l_name = '").append(lname.getText().trim()).append("' AND ");}
               
 }}
 
 
 
/*device*/     if(tablesList.getValue().equals("Device Table")) {
                   sql.replace(13,sql.length(), " device  WHERE");

                 if(no.getText().isEmpty() && nameDe.getText().isEmpty() && price.getText().isEmpty() && weight.getText().isEmpty()&&madeDe.getSelectionModel().isEmpty()) {
                    (sql.toString()).replace("WHERE","") ;
                     sql.setLength(sql.length() - 5);}

                 else {
                      if (!no.getText().isEmpty())   {if(checkIfInteger("no",no)) { sql.append(" no = '").append(no.getText().trim()).append("' AND ");}else{ sql.append(" no = '' AND ");}}

                      if (!nameDe.getText().isEmpty())   {sql.append(" name = '").append(nameDe.getText().trim()).append("' AND ");}

                      if (!price.getText().isEmpty())  {if(checkIfInteger("Price",price)) { sql.append(" price = '").append(price.getText().trim()).append("' AND ");}else{ sql.append(" price = '' AND ");}}

                      if (!weight.getText().isEmpty())  {if(checkIfInteger("Weight",weight)) { sql.append(" weight = '").append(weight.getText().trim()).append("' AND ");}else{ sql.append(" weight = '' AND ");}}
 
                      if (!madeDe.getSelectionModel().isEmpty()) {sql.append(" made = '").append(madeDe.getValue()).append("' AND ");}
}}



/*manufacture*/   

              if(tablesList.getValue().equals("Manufacture Table")) {
                  sql.replace(13,sql.length(), " manufacture  WHERE");

               if(nameManu.getText().isEmpty() && type.getText().isEmpty() && cityManu.getText().isEmpty() && countryManu.getText().isEmpty()) {
                  (sql.toString()).replace("WHERE","") ;
                   sql.setLength(sql.length() - 5);}

               else {
                  if (!nameManu.getText().isEmpty())   {sql.append(" name = '").append(nameManu.getText().trim()).append("' AND ");}

                  if (!type.getText().isEmpty())   {sql.append(" type = '").append(type.getText().trim()).append("' AND ");}

                  if (!countryManu.getText().isEmpty()) {sql.append(" country = '").append(countryManu.getText().trim()).append("' AND ");}

                  if (!cityManu.getText().isEmpty()) {sql.append(" city = '").append(cityManu.getText().trim()).append("' AND ");}

}}
              
              
 /*order*/   

               if(tablesList.getValue().equals("Orders Table")) {
                  sql.replace(13,sql.length(), " orders  WHERE");

               if(idOr.getText().isEmpty() && customer.getSelectionModel().isEmpty()&& date.getText().isEmpty() && carOr.getSelectionModel().isEmpty()) {
                  (sql.toString()).replace("WHERE","") ;
                   sql.setLength(sql.length() - 5);}

               else {
                  if (!idOr.getText().isEmpty())    {if(checkIfInteger("ID",idOr)) { sql.append(" id = '").append(idOr.getText().trim()).append("' AND ");}else{ sql.append(" id = '' AND ");}}

                  if (!customer.getSelectionModel().isEmpty())  { sql.append(" customer = '").append(customer.getSelectionModel().getSelectedItem()).append("' AND ");}

                  if (!date.getText().isEmpty())  {if(checkIfInteger("Data",date)) { sql.append(" date = '").append(date.getText().trim()).append("' AND ");}else{ sql.append(" date = '' AND ");}}

                  if (!carOr.getSelectionModel().isEmpty()) {sql.append(" car = '").append(carOr.getValue()).append("' AND ");}

}}
       
                         // Remove the trailing " AND " from the SQL query
                         if (sql.toString().endsWith(" AND "))  sql.setLength(sql.length() - 5);
                         tableview.getItems().clear();
    	                 tableview.getColumns().clear();
    	                 method(sql.toString());
} });    
           
                   
                   
                   

               
 //clear Button Action
             
               
               
                   clear.setOnAction( new EventHandler<ActionEvent>() { 
                       public void handle(ActionEvent event) {
                    	   
                    	 
                    	   if(tablesList.getValue().equals("Address Table")) {id.clear();street.clear();buidling.clear();city.clear();country.clear();}
                    	   if(tablesList.getValue().equals("Car Table")) {name.clear();made.setValue(null);year.clear();model.clear();}
                    	   if(tablesList.getValue().equals("Car_Part Table")) {car.setValue(null);part.setValue(null);;}
                    	   if(tablesList.getValue().equals("Customer Table")) {idC.clear();fName.clear();lname.clear();address.setValue(null);;job.clear();}
                    	   if(tablesList.getValue().equals("Manufacture Table")) {nameManu.clear();type.clear();cityManu.clear();countryManu.clear();}
                    	   if(tablesList.getValue().equals("Orders Table")) {idOr.clear();customer.setValue(null);;date.clear();carOr.setValue(null);;}
                    	   if(tablesList.getValue().equals("Device Table")) {no.clear();price.clear();nameDe.clear();weight.clear();madeDe.setValue(null);;}
                    	   
                       }
                   });
               
                            
               
//delete Button Action
                   
                   
                   delete.setOnAction( new EventHandler<ActionEvent>() { 
                       public void handle(ActionEvent event) {
 
                    	   StringBuilder sql =new StringBuilder("Delete from ");                 	   
                    	   String choice=tablesList.getValue().toString().replace("Table", "");
                    	   
                    	   
/*Address table*/       if(tablesList.getValue().equals("Address Table")) {
	
	
                               sql.append("address where");
                               
                             if(id.getText().isEmpty() && buidling.getText().isEmpty() && street.getText().isEmpty() && city.getText().isEmpty()&&country.getText().isEmpty()) {
                                  Alert alert=new Alert(AlertType.CONFIRMATION);
                                  alert.setTitle("Delete all");
                                  alert.setHeaderText("Are you sure you want to delete all values in "+choice +" Table !?");  
                                  if(alert.showAndWait().get()==ButtonType.OK) {
                                	 tableview.getItems().clear(); // Clear previous data     
                                    (sql.toString()).replace("where","") ;
                                    sql.setLength(sql.length() - 5);  }
                                  else {sql=null; System.out.println("You Did'nt Delete all Table Values !");  }
                                  
                             }

                             
                             else {

                               if (!id.getText().isEmpty())   {if(checkIfInteger("ID",id)) {sql.append(" id = ").append(id.getText().trim()).append(" AND ");}else{ sql.append(" id = '' AND ");}}

                               if (!buidling.getText().isEmpty())   {if(checkIfInteger("Buidling" ,buidling)) {sql.append(" buidling = '").append(buidling.getText().trim()).append("' AND ");}else{ sql.append(" buidling = '' AND ");}}                    

                               if (!street.getText().isEmpty()) {sql.append(" street = '").append(street.getText().trim()).append("' AND ");}

                               if (!city.getText().isEmpty()) {sql.append(" city = '").append(city.getText().trim()).append("' AND ");}
          
                               if (!country.getText().isEmpty()) {sql.append(" country = '").append(country.getText().trim()).append("' AND ");}
                   
                               if (sql.toString().endsWith(" AND "))  sql.setLength(sql.length() - 5);
                               
                               
                               
                                }

                    	   }
                    	   
                    	   
                    	   
/*Car Table*/               if(tablesList.getValue().equals("Car Table")) {
                               sql.append("car where");
                               
                               
                             if(name.getText().isEmpty() && model.getText().isEmpty() && year.getText().isEmpty() && made.getSelectionModel().isEmpty()) {
                            	  Alert alert=new Alert(AlertType.CONFIRMATION);
                                  alert.setTitle("Delete all");
                                  alert.setHeaderText("Are you sure you want to delete all values in "+choice +" Table !?");  
                                  
                                  if(alert.showAndWait().get()==ButtonType.OK) {
                                	 tableview.getItems().clear(); // Clear previous data     
                                     (sql.toString()).replace("where","") ;
                                      sql.setLength(sql.length() - 5);  }
                                  else {sql=null; System.out.println("You Did'nt Delete all Table Values !");  }}

                             else {
                            	 
                               if (!name.getText().isEmpty())   {sql.append(" name = '").append(name.getText().trim()).append("' AND ");}

                               if (!model.getText().isEmpty())   {sql.append(" model = '").append(model.getText().trim()).append("' AND ");}

                               if (!year.getText().isEmpty())    {if(checkIfInteger("Year",year)) {sql.append(" year = '").append(year.getText().trim()).append("' AND ");} else{ sql.append(" year = '' AND ");}}

                               if (!made.getSelectionModel().isEmpty()) {sql.append(" made = '").append(made.getSelectionModel().getSelectedItem()).append("' AND ");}
                             
                               if (sql.toString().endsWith(" AND "))  sql.setLength(sql.length() - 5);
                                }

                            
                    	   }
                    	   
                    	   
 /*Car-Part Table*/          if(tablesList.getValue().equals("Car_Part Table")) {
                                   sql.append("car_part where");
     
     
                                 if( part.getSelectionModel().isEmpty()&& car.getSelectionModel().isEmpty()) {
  	                                   Alert alert=new Alert(AlertType.CONFIRMATION);
                                        alert.setTitle("Delete all");
                                        alert.setHeaderText("Are you sure you want to delete all values in "+choice +" Table !?");  
                                        
                                           if(alert.showAndWait().get()==ButtonType.OK) {
      	                                       tableview.getItems().clear(); // Clear previous data     
                                               (sql.toString()).replace("where","") ;
                                                sql.setLength(sql.length() - 5);  }
                                          else {sql=null; System.out.println("You Did'nt Delete all Table Values !");  }
                                }
 
                               else {
                                	   
                                	  if (!car.getSelectionModel().isEmpty()) {sql.append(" car = '").append(car.getSelectionModel().getSelectedItem()).append("' AND ");}
                                	  if (!part.getSelectionModel().isEmpty()) {sql.append(" part = '").append(part.getSelectionModel().getSelectedItem()).append("' AND ");}

                                       if (sql.toString().endsWith(" AND "))  sql.setLength(sql.length() - 5);
                              }
}            	   
                    


 /*Customer Table*/    if(tablesList.getValue().equals("Customer Table")) {
                          sql.append("customer where");
     
     
                         if(idC.getText().isEmpty() && fName.getText().isEmpty() && lname.getText().isEmpty() && address.getSelectionModel().isEmpty()&&job.getText().isEmpty()) {
  	                          Alert alert=new Alert(AlertType.CONFIRMATION);
                              alert.setTitle("Delete all");
                              alert.setHeaderText("Are you sure you want to delete all values in "+choice +" Table !?");  
                              
                               if(alert.showAndWait().get()==ButtonType.OK) {
      	                           tableview.getItems().clear(); // Clear previous data     
                                   (sql.toString()).replace("where","") ;
                                    sql.setLength(sql.length() - 5);  }
                               else {sql=null; System.out.println("You Did'nt Delete all Table Values !");  }}

                        else {
                               if (!idC.getText().isEmpty())   {if(checkIfInteger("ID",idC)){sql.append(" id = '").append(idC.getText().trim()).append("' AND ");}else{ sql.append(" id = '' AND ");}

                               if (!fName.getText().isEmpty())   {sql.append(" f_name = '").append(fName.getText().trim()).append("' AND ");}
 
                               if (!lname.getText().isEmpty()) {sql.append(" l_name = '").append(lname.getText().trim()).append("' AND ");}

                               if (!address.getSelectionModel().isEmpty()) {sql.append(" address = ").append(address.getSelectionModel().getSelectedItem()).append(" AND ");}
        
                               if (!job.getText().isEmpty()) {sql.append(" job = '").append(job.getText().trim()).append("' AND ");}
   
                          if (sql.toString().endsWith(" AND "))  sql.setLength(sql.length() - 5);
      }
}
 
 
 } 
 /*Device Table*/   if(tablesList.getValue().equals("Device Table")) {
                         sql.append("device where");


                    if(no.getText().isEmpty() && nameDe.getText().isEmpty() && price.getText().isEmpty() && madeDe.getSelectionModel().isEmpty()&&weight.getText().isEmpty()) {
                      Alert alert=new Alert(AlertType.CONFIRMATION);
                     alert.setTitle("Delete all");
                     alert.setHeaderText("Are you sure you want to delete all values in "+choice +" Table !?");
    
                   if(alert.showAndWait().get()==ButtonType.OK) {
                      tableview.getItems().clear(); // Clear previous data     
                      (sql.toString()).replace("where","") ;
                       sql.setLength(sql.length() - 5);  }
                  else {sql=null; System.out.println("You Did'nt Delete all Table Values !");  }}

               else {

                 if (!no.getText().isEmpty())   {if(checkIfInteger("no",no)){sql.append(" no = '").append(no.getText().trim()).append("' AND ");}else sql.append(" no = '' AND ");}
 
                 if (!nameDe.getText().isEmpty())   {sql.append(" name = '").append(nameDe.getText().trim()).append("' AND ");}

                 if (!price.getText().isEmpty()) {if(checkIfInteger("Price",price)){sql.append(" price = '").append(price.getText().trim()).append("' AND ");}else sql.append(" price = '' AND ");}

                 if (!madeDe.getSelectionModel().isEmpty()) {sql.append(" made = '").append(madeDe.getSelectionModel().getSelectedItem()).append("' AND ");}

                 if (!weight.getText().isEmpty()) {if(checkIfInteger("Weight",weight)){sql.append(" weight = '").append(weight.getText().trim()).append("' AND ");}else sql.append(" weight = '' AND ");}

                 if (sql.toString().endsWith(" AND "))  sql.setLength(sql.length() - 5);
        }

}
 
 
 
 /*Manufacture Table*/  if(tablesList.getValue().equals("Manufacture Table")) {
                            sql.append("manufacture where");


                       if(nameManu.getText().isEmpty() && type.getText().isEmpty() && cityManu.getText().isEmpty() && countryManu.getText().isEmpty()) {
                             Alert alert=new Alert(AlertType.CONFIRMATION);
                             alert.setTitle("Delete all");
                             alert.setHeaderText("Are you sure you want to delete all values in "+choice +" Table !?"); 
   
                     if(alert.showAndWait().get()==ButtonType.OK) {
                         tableview.getItems().clear(); // Clear previous data     
                        (sql.toString()).replace("where","") ;
                         sql.setLength(sql.length() - 5);  }
                    else {sql=null; System.out.println("You Did'nt Delete all Table Values !");  }}

                    else {

                       if (!nameManu.getText().isEmpty())   {sql.append(" name = '").append(nameManu.getText().trim()).append("' AND ");}

                       if (!type.getText().isEmpty())   {sql.append(" type = '").append(type.getText().trim()).append("' AND ");}

                       if (!cityManu.getText().isEmpty()) {sql.append(" city = '").append(cityManu.getText().trim()).append("' AND ");}
 
                       if (!countryManu.getText().isEmpty()) {sql.append(" country = '").append(countryManu.getText().trim()).append("' AND ");}

                       if (sql.toString().endsWith(" AND "))  sql.setLength(sql.length() - 5);
                      }

}
 
 
 /*Order Table*/    if(tablesList.getValue().equals("Orders Table")) {
                       sql.append("orders where");
System.out.println(sql.toString());

                    if(idOr.getText().isEmpty() && date.getText().isEmpty() &&  customer.getSelectionModel().isEmpty()&&carOr.getSelectionModel().isEmpty()) {
                        Alert alert=new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Delete all");
                        alert.setHeaderText("Are you sure you want to delete all values in "+choice +" Table !?");alert.show();  
                        
                        if(alert.showAndWait().get()==ButtonType.OK) {
                           tableview.getItems().clear(); // Clear previous data     
                           (sql.toString()).replace("where","") ;
                            sql.setLength(sql.length() - 5);  }
                        else {sql=null; System.out.println("You Did'nt Delete all Table Values !");  }
                        
                    }
                       else {
                            if (!idOr.getText().isEmpty())   {if(checkIfInteger("ID",idOr)) {sql.append(" id = '").append(idOr.getText().trim()).append("' AND ");}else sql.append(" id = '' AND ");}
                            if (!date.getText().isEmpty())   {if(checkIfInteger("Date",date)) {sql.append(" date = '").append(date.getText().trim()).append("' AND ");}else sql.append(" date = '' AND ");}
                            if (!customer.getSelectionModel().isEmpty()) {sql.append(" customer = ").append(customer.getSelectionModel().getSelectedItem()).append(" AND ");}
                            if (!carOr.getSelectionModel().isEmpty()) {sql.append(" car = '").append(carOr.getSelectionModel().getSelectedItem()).append("' AND ");}

                            if (sql.toString().endsWith(" AND "))  sql.setLength(sql.length() - 5);
                         }
        

 }
 
                     
                    	 tableview.getItems().clear();
      	                 tableview.getColumns().clear();

      	                 if(sql==null) method("Select * from "+choice);
      	                 else  up(sql.toString());
                        
      	               // Refresh the ComboBox in the Orders table for cars
                         refreshComboBox(carOr, "name", "Car") ;  
                         refreshComboBox(customer, "id", "Customer");    
                         refreshComboBox(madeDe, "name", "Manufacture");
                         refreshComboBox(address, "id", "address");
                         refreshComboBox(car, "name", "Car");
                         refreshComboBox(part, "no", "Device");
                         refreshComboBox(made, "name", "Manufacture");
                         
                         
                         StringBuilder sql2 =new StringBuilder("Select * from  ");  
                         
                  	   if(tablesList.getValue().equals("Address Table")) {sql2.replace(13,sql2.length(), " address  ");}
                  	   else if (tablesList.getValue().equals("Car Table")) {sql2.replace(13,sql2.length(), " car  ");}
                  	   else if (tablesList.getValue().equals("Car_Part Table")) {sql2.replace(13,sql2.length(), " car_part  ");}
                  	   else if (tablesList.getValue().equals("Customer Table")) {sql2.replace(13,sql2.length(), " customer  ");}
                  	   else if (tablesList.getValue().equals("Orders Table")) {sql2.replace(13,sql2.length(), " orders  ");}
                  	   else if (tablesList.getValue().equals("Device Table")) {sql2.replace(13,sql2.length(), " device  ");}
                  	   else if (tablesList.getValue().equals("Manufacture Table")) {sql2.replace(13,sql2.length(), " manufacture  ");}
                  	   
                  	       tableview.getItems().clear();
    	                   tableview.getColumns().clear();
    	                   method(sql2.toString());

 }});
               
               
               
               
//update Button Action
                   
                   
                   update.setOnAction( new EventHandler<ActionEvent>() { 
                       public void handle(ActionEvent event) {          
                   
                    	   StringBuilder sql =new StringBuilder("UPDATE ");

  /*Address Table  */     	 if(tablesList.getValue().equals("Address Table")) {
	
                               sql.append(" Address SET");    
                               
                             if(!id.getText().isEmpty()&&buidling.getText().isEmpty()&&street.getText().isEmpty()&&city.getText().isEmpty()&&country.getText().isEmpty()) {
                            		   Alert alert=new Alert(AlertType.INFORMATION);
                	                   alert.setTitle("Update Process");
                	                   alert.setHeaderText("You must add the value that you want to update it !"); 
                	                   alert.show(); 
 }
                            else {
                            	   
                               if(!id.getText().isEmpty()) {  
                            	   
                            	   boolean forbuilding=true;
                            	   
                            	  
                            	   if (!buidling.getText().isEmpty()){if(checkIfInteger("Buidling",buidling)) {sql.append(" buidling = '").append(buidling.getText().trim()).append("' , ");}else forbuilding=false;} 
                            	   if (!street.getText().isEmpty())  {sql.append(" street = '").append(street.getText().trim()).append("' , ");} 
                            	   if (!city.getText().isEmpty())    {sql.append(" city = '").append(city.getText().trim()).append("' , ");} 
                            	   if (!country.getText().isEmpty()) {sql.append(" country = '").append(country.getText().trim()).append("'  , ");} 
                            	   
                            	   if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                            	   
                            	   if(checkIfInteger("ID",id)&&forbuilding==true) {  
                            	       sql.append(" where id = ").append(id.getText().trim());   tableview.getItems().clear();
                	                   tableview.getColumns().clear();
                	                   up(sql.toString()) ;
                            	   
                	                   Alert alert=new Alert(AlertType.INFORMATION);
                	                   alert.setTitle("Update new Value");
                	                   alert.setHeaderText("Your value has been successfully updated."); 
                	                   alert.show();
                            	   }
                            	   else{forbuilding=false;}
 }                 
                               else { 
                            	    Alert alert=new Alert(AlertType.WARNING);
                            	    alert.setTitle("ERROR");
                            	    alert.setHeaderText(" You must add the ( id ) to update !! "); alert.show();
                            	  } 
   }}
  
  
  /*Car Table  */     	 if(tablesList.getValue().equals("Car Table")) {
	                             sql.append(" Car SET");

	                             
	                      if(!name.getText().isEmpty()&&model.getText().isEmpty()&&year.getText().isEmpty()&&made.getSelectionModel().isEmpty()) {
                          		   Alert alert=new Alert(AlertType.INFORMATION);
              	                   alert.setTitle("Update Process");
              	                   alert.setHeaderText("You must add the value that you want to update it !"); 
              	                   alert.show(); 
}
                          else {
                       
                             if(!name.getText().isEmpty()) {
                            	 
                            	 boolean forError=true;
                            	 
   	                             if (!model.getText().isEmpty()){sql.append(" model = '").append(model.getText().trim()).append("' , ");} 
   	                             if (!year.getText().isEmpty()){if(checkIfInteger("year", year)) {sql.append(" year = ").append(year.getText().trim()).append(" , ");}else forError=false; }
   	                             if (!made.getSelectionModel().isEmpty()){sql.append(" made = '").append(made.getSelectionModel().getSelectedItem()).append("' , ");} 
   	   
   	                             if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
   	   
   	                             sql.append(" where name = '").append(name.getText().trim()).append("'");

   	                             if(forError==true) {  
   	                             tableview.getItems().clear();
                                 tableview.getColumns().clear();
                                 up(sql.toString()) ; 
                                 
                                 Alert alert=new Alert(AlertType.INFORMATION);
              	                 alert.setTitle("Update new Value");
              	                 alert.setHeaderText("Your value has been successfully updated."); 
              	                 alert.show();
              	                 
                              }else forError=false;
                           
                           }else{ 
   	                            Alert alert=new Alert(AlertType.WARNING);
   	                            alert.setTitle("ERROR");
   	                            alert.setHeaderText(" You must add the ( name ) to update !! "); alert.show();
                           	 }                   	   
}}
  
  /*Orders Table  */   
                            if(tablesList.getValue().equals("Orders Table")) {
                                 sql.append(" Orders SET");
                                 
                                 if(!idOr.getText().isEmpty()&&date.getText().isEmpty()&&customer.getSelectionModel().isEmpty()&&car.getSelectionModel().isEmpty()) {
                            		   Alert alert=new Alert(AlertType.INFORMATION);
                	                   alert.setTitle("Update Process");
                	                   alert.setHeaderText("You must add the value that you want to update it !"); 
                	                   alert.show(); 
  }
                            else {

       if(!idOr.getText().isEmpty()) {
    	   
   	      boolean forError=true;
   	                
   	     if (!date.getText().isEmpty()){if(checkIfInteger("date", date)) {sql.append(" date = ").append(date.getText().trim()).append(" , ");}else forError=false; }
   	     if (!customer.getSelectionModel().isEmpty()){sql.append(" customer = ").append(customer.getSelectionModel().getSelectedItem()).append(" , ");} 
   	     if (!car.getSelectionModel().isEmpty()){sql.append(" car = '").append(car.getSelectionModel().getSelectedItem()).append("' , ");} 
   	   
   	   if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
   	   
   	    sql.append(" where id = ").append(idOr.getText().trim());
   	 
   	 if(checkIfInteger("ID",idOr)&&forError==true) {  
   	      tableview.getItems().clear();
          tableview.getColumns().clear();
          up(sql.toString()) ;
          
          Alert alert=new Alert(AlertType.INFORMATION);
          alert.setTitle("Update new Value");
          alert.setHeaderText("Your value has been successfully updated."); 
          alert.show();
   	   
      } else forError=false;
       }
      else { 
   	    Alert alert=new Alert(AlertType.WARNING);
   	    alert.setTitle("ERROR");
   	    alert.setHeaderText(" You must add the ( id ) to update !! "); alert.show();
   	  }                   	   
} }                
                            
                            
    /*Device Table  */   
                                 if(tablesList.getValue().equals("Device Table")) {
                                       sql.append(" Device SET");

                                 if(!no.getText().isEmpty()&&nameDe.getText().isEmpty()&&price.getText().isEmpty()&&madeDe.getSelectionModel().isEmpty()&& weight.getText().isEmpty()) {
                                		   Alert alert=new Alert(AlertType.INFORMATION);
                    	                   alert.setTitle("Update Process");
                    	                   alert.setHeaderText("You must add the value that you want to update it !"); 
                    	                   alert.show(); 
 
                                 }  else {
                      
                                 if(!no.getText().isEmpty()) {
                              	      boolean forError=true;
                 	   
                             	     if (!nameDe.getText().isEmpty()){sql.append(" name = '").append(nameDe.getText().trim()).append("' , ");} 
                             	     if (!price.getText().isEmpty()) {if(checkIfInteger("price", price)){sql.append(" price = ").append(price.getText().trim()).append(" , ");}else forError=false; }
                             	     if (!madeDe.getSelectionModel().isEmpty()){sql.append(" made = '").append(madeDe.getSelectionModel().getSelectedItem()).append("' , ");} 
                             	     if (!weight.getText().isEmpty()){if(checkIfInteger("weight", weight)){sql.append(" weight = ").append(weight.getText().trim()).append(" , ");}else forError=false; }

                             	   if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3); 
                             	   
                             	    sql.append(" where no = ").append(no.getText().trim());
                             	 
                            if(checkIfInteger("no",no)&&forError==true) {  
                             	     tableview.getItems().clear();
                                    tableview.getColumns().clear();
                                    up(sql.toString()) ;
                                    
                                    Alert alert=new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Update new Value");
                                    alert.setHeaderText("Your value has been successfully updated."); 
                                    alert.show();
                             	   
                                } else forError=false;  
                            
                                 } else { 
                             	    Alert alert=new Alert(AlertType.WARNING);
                             	    alert.setTitle("ERROR");
                             	    alert.setHeaderText(" You must add the ( no ) to update !! "); alert.show();
                             	  }                   	   
                          }}    
                                                 
   /*Customer Table  */   
                                 if(tablesList.getValue().equals("Customer Table")) {
                                       sql.append(" Customer SET");

                                       boolean forError=true;

                                       if(!idC.getText().isEmpty()&&fName.getText().isEmpty()&&lname.getText().isEmpty()&&address.getSelectionModel().isEmpty()&& job.getText().isEmpty()) {
                                      		   Alert alert=new Alert(AlertType.INFORMATION);
                          	                   alert.setTitle("Update Process");
                          	                   alert.setHeaderText("You must add the value that you want to update it !"); 
                          	                   alert.show(); 
       
                                       }  else {
                            
                                    	   
                                 if(!idC.getText().isEmpty()) {
                             	                            	   
                             	     if (!fName.getText().isEmpty()){sql.append(" f_name = '").append(fName.getText().trim()).append("' , ");} 
                             	     if (!lname.getText().isEmpty()){sql.append(" l_name = '").append(lname.getText().trim()).append("' , ");} 
                             	     if (!address.getSelectionModel().isEmpty()){sql.append(" address = ").append(address.getSelectionModel().getSelectedItem()).append(" , ");} 
                             	     if (!job.getText().isEmpty()){sql.append(" job = ").append(job.getText().trim()).append(" , ");} 

                             	  if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                             	   
                             	    sql.append(" where id = ").append(idC.getText().trim());
                             	 
                                    if(checkIfInteger("id",idC)&&forError==true) {  

                             	      tableview.getItems().clear();
                                      tableview.getColumns().clear();
                                      up(sql.toString()) ;
                                    
                                      Alert alert=new Alert(AlertType.INFORMATION);
                                      alert.setTitle("Update new Value");
                                      alert.setHeaderText("Your value has been successfully updated."); 
                                      alert.show();
                             	   
                                }else forError=false;}                 
                                else { 
                             	    Alert alert=new Alert(AlertType.WARNING);
                             	    alert.setTitle("ERROR");
                             	    alert.setHeaderText(" You must add the ( id ) to update !! "); alert.show();
                             	  }                   	   
                          }
                                                                              
                                 }                            
  
                                 
  /*Manufacture Table  */     	 if(tablesList.getValue().equals("Manufacture Table")) {
                            	    sql.append(" Manufacture SET");

                            	    boolean forError=true;

                                    if(!nameManu.getText().isEmpty()&&type.getText().isEmpty()&&cityManu.getText().isEmpty()&&countryManu.getText().isEmpty()) {
                                   		   Alert alert=new Alert(AlertType.INFORMATION);
                       	                   alert.setTitle("Update Process");
                       	                   alert.setHeaderText("You must add the value that you want to update it !"); 
                       	                   alert.show(); 
    
                                    }  else {
                                if(!nameManu.getText().isEmpty()) {
                             	                            	   
                             	   if (!type.getText().isEmpty()){sql.append(" type = '").append(type.getText().trim()).append("' , ");} 
                             	   if (!cityManu.getText().isEmpty()){sql.append(" city = '").append(cityManu.getText().trim()).append("' , ");} 
                             	   if (!countryManu.getText().isEmpty()){sql.append(" country = '").append(countryManu.getText().trim()).append("'  , ");} 
                             	   
                             	   if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                             	   
                             	   sql.append(" where name = '").append(nameManu.getText().trim()).append("'");
                             	 
                             	   if(forError==true) {
                             	   tableview.getItems().clear();
                 	               tableview.getColumns().clear();
                 	               up(sql.toString()) ;
                 	               
                 	               Alert alert=new Alert(AlertType.INFORMATION);
                 	               alert.setTitle("Update new Value");
                 	               alert.setHeaderText("Your value has been successfully updated."); 
                 	               alert.show();
                             	   
                                } else forError=false;}                
                                else { 
                             	    Alert alert=new Alert(AlertType.WARNING);
                             	    alert.setTitle("ERROR");
                             	    alert.setHeaderText(" You must add the ( name ) to update !! "); alert.show();
                             	  }                   	   
   }}
   
  
  StringBuilder sql2 =new StringBuilder("Select * from  ");  
  
	   if(tablesList.getValue().equals("Address Table")) {sql2.replace(13,sql2.length(), " address  ");}
	   else if (tablesList.getValue().equals("Car Table")) {sql2.replace(13,sql2.length(), " car  ");}
	   else if (tablesList.getValue().equals("Car_Part Table")) {sql2.replace(13,sql2.length(), " car_part  ");}
	   else if (tablesList.getValue().equals("Customer Table")) {sql2.replace(13,sql2.length(), " customer  ");}
	   else if (tablesList.getValue().equals("Orders Table")) {sql2.replace(13,sql2.length(), " orders  ");}
	   else if (tablesList.getValue().equals("Device Table")) {sql2.replace(13,sql2.length(), " device  ");}
	   else if (tablesList.getValue().equals("Manufacture Table")) {sql2.replace(13,sql2.length(), " manufacture  ");}
	   
	       tableview.getItems().clear();
      tableview.getColumns().clear();
      method(sql2.toString());
      
 }});

                                  
 //Insert Button Action  
                   
               
                   insert.setOnAction( new EventHandler<ActionEvent>() { 
                       public void handle(ActionEvent event) {          
                   
                    	   StringBuilder sql =new StringBuilder("INSERT INTO ");

                    	   
//customer table            
                    	   if(tablesList.getValue().equals("Customer Table")) {
                    		   
                               sql.append("customer (id,f_name,l_name,address,job) values( ");
                               boolean allfound=true;
                               boolean top=true;
                               
                               if(idC.getText().isEmpty()||fName.getText().isEmpty()||lname.getText().isEmpty()||address.getSelectionModel().isEmpty()||job.getText().isEmpty()) allfound=false;
                               
                               if (!idC.getText().isEmpty())   {if(checkIfInteger("ID", idC)) {sql.append(idC.getText().trim()).append(" , ");}else{top=false;}}

                               if (!fName.getText().isEmpty())   {sql.append("'").append(fName.getText().trim()).append("' , ");}

                               if (!lname.getText().isEmpty()) {sql.append("'").append(lname.getText().trim()).append("' , ");}
    
                               if (!address.getSelectionModel().isEmpty()) {sql.append(address.getSelectionModel().getSelectedItem()).append(" , ");}
          
                               if (!job.getText().isEmpty()) {sql.append("'").append(job.getText().trim()).append("' , ");}
                   
                               if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                               
                             
                               if(allfound==true&&top==true) { insertProcessInteger(sql,"id","customer", idC); refreshComboBox(customer, "id", "Customer"); }
                               
                               else if(allfound==false) {
                            	   
                            	   Alert alert=new Alert(AlertType.INFORMATION); 
                                   alert.setTitle("Faild Insert");
                                   alert.setHeaderText("You must add all Values !!");// line 4
                                   alert.setWidth(200);                                   
                                   alert.show();  
                               }
                               
                               
                               
    }
                    	   
                    	   
//address table            
                    	   if(tablesList.getValue().equals("Address Table")) {
                               sql.append("address (id,buidling,street,city,country) values( ");
                               
                               boolean allfound=true;
                               boolean top=true;
                               
                               if(id.getText().isEmpty()||buidling.getText().isEmpty()||street.getText().isEmpty()||city.getText().isEmpty()||country.getText().isEmpty()) allfound=false;
                               
                               if (!id.getText().isEmpty())   {if(checkIfInteger("ID", id)) {sql.append(id.getText().trim()).append(" , ");}else top=false;}

                               if (!buidling.getText().isEmpty())   {if(checkIfInteger("Buidling", buidling)) {sql.append(buidling.getText().trim()).append(" , ");}else top=false;}

                               if (!street.getText().isEmpty()) {sql.append("'").append(street.getText().trim()).append("' , ");}
    
                               if (!city.getText().isEmpty()) {sql.append("'").append(city.getText().trim()).append("' , ");}
          
                               if (!country.getText().isEmpty()) {sql.append("'").append(country.getText().trim()).append("' , ");}
                   
                               if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                               
                               
                               if(allfound==true&&top==true) { insertProcessInteger(sql,"id","address", id);    refreshComboBox(address, "id", "address");}
                               
                               else if(allfound==false){
                            	   
                            	   Alert alert=new Alert(AlertType.INFORMATION); 
                                   alert.setTitle("Faild Insert");
                                   alert.setHeaderText("You must add all Values !!");// line 4
                                   alert.setWidth(200);                                   
                                   alert.show();  
                               }
                               
 }                 	   

                        

//car table            
                    	   if(tablesList.getValue().equals("Car Table")) {
                               sql.append("car (name,model,year,made) values( ");
                               
                               boolean allfound=true;
                               boolean top=true;
                               
                               if(name.getText().isEmpty()||model.getText().isEmpty()||year.getText().isEmpty()||made.getSelectionModel().isEmpty()) allfound=false;
                               
                               if (!name.getText().isEmpty())   {sql.append("'").append(name.getText().trim()).append("' , ");}

                               if (!model.getText().isEmpty())   {sql.append("'").append(model.getText().trim()).append("' , ");}

                               if (!year.getText().isEmpty()) {if(checkIfInteger("Year", year)) {sql.append(year.getText().trim()).append(" , ");}else top=false;}
    
                               if (!made.getSelectionModel().isEmpty()) {sql.append("'").append(made.getSelectionModel().getSelectedItem()).append("' , ");}
          
                      
                   
                               if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                               
                               if(allfound==true && top==true) { insertProcessString(sql,"name","car", name);refreshComboBox(car, "name", "Car");refreshComboBox(carOr, "name", "Car") ;  }
                               
                               else if(allfound==false) {
                            	   Alert alert=new Alert(AlertType.INFORMATION); 
                                   alert.setTitle("Faild Insert");
                                   alert.setHeaderText("You must add all Values !!");// line 4
                                   alert.setWidth(200);                                   
                                   alert.show();  
                               }
   }  
                    	   
                    	   
//car part table            
                    	   if(tablesList.getValue().equals("Car_Part Table")) {
                               sql.append("car_part (car,part) values( ");
                               
                               boolean allfound=true;
                               if(part.getSelectionModel().isEmpty()||car.getSelectionModel().isEmpty()) allfound=false;
                               
                         
                               if (!car.getSelectionModel().isEmpty())   {sql.append("'").append(car.getValue()).append("' , ");}

                               if (!part.getSelectionModel().isEmpty())   {sql.append(part.getValue()).append(" , ");}

                   
                               if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);  
                               
                              if(allfound==true) {  
                              sql.append(")");                                       
                              tableview.getItems().clear();
                              tableview.getColumns().clear();
                              up(sql.toString()); 
                              
                              Alert alert=new Alert(AlertType.INFORMATION);
                              alert.setTitle("Insert new row");
                              alert.setHeaderText("Your record has been successfully added."); 
                              alert.show();
                              
                              
                              } 
                               
                               else {
                            	   Alert alert=new Alert(AlertType.INFORMATION); 
                                   alert.setTitle("Faild Insert");
                                   alert.setHeaderText("You must add all Values !!");// line 4
                                   alert.setWidth(200);                                   
                                   alert.show();  
                               }
 }                     	   

                    	   
                    	   
 //device table            
                                    if(tablesList.getValue().equals("Device Table")) {
                                        sql.append("device (no,name,price,weight,made) values( ");
                                      
                                        boolean allfound=true;
                                        boolean top=true;
                                        if(no.getText().isEmpty()||nameDe.getText().isEmpty()||price.getText().isEmpty()||weight.getText().isEmpty()||madeDe.getSelectionModel().isEmpty()) allfound=false;
                                              
                                          
                                           if (!no.getText().isEmpty())   {if(checkIfInteger("no", no)) {sql.append(no.getText().trim()).append(" , ");}else top=false;}
 
                                           if (!nameDe.getText().isEmpty())   {sql.append("'").append(nameDe.getText().trim()).append("' , ");}

                                           if (!price.getText().isEmpty()) {if(checkIfInteger("Price", price)) {sql.append(price.getText().trim()).append(" , ");}else top=false;}
                    
                                           if (!weight.getText().isEmpty()) {if(checkIfInteger("Weight", weight)) {sql.append(weight.getText().trim()).append(" , ");}else top=false;}
                                           
                                           if (!madeDe.getSelectionModel().isEmpty()) {sql.append("'").append(madeDe.getSelectionModel().getSelectedItem()).append("' , ");}
                                  
                                         if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                                         
                                         if(allfound==true&&top==true) {  insertProcessInteger(sql,"no","device", no);   refreshComboBox(part, "no", "Device");} 
                                         
                                         else if(allfound==false){
                                      	   Alert alert=new Alert(AlertType.INFORMATION); 
                                             alert.setTitle("Faild Insert");
                                             alert.setHeaderText("You must add all Values !!");// line 4
                                             alert.setWidth(200);                                   
                                             alert.show();  
                                         }
 }
                                    
                                             
 //manufacture table            
                                 if(tablesList.getValue().equals("Manufacture Table")) {
                                       sql.append("manufacture (name,type,city,country) values( ");
                                            boolean allfound=true;
                                            if(nameManu.getText().isEmpty()||type.getText().isEmpty()||cityManu.getText().isEmpty()||countryManu.getText().isEmpty()) allfound=false;
                                                  
                                          
                                           if (!nameManu.getText().isEmpty())   {sql.append("'").append(nameManu.getText().trim()).append("' , ");}
 
                                           if (!type.getText().isEmpty())   {sql.append("'").append(type.getText().trim()).append("' , ");}

                                           if (!cityManu.getText().isEmpty()) {sql.append("'").append(cityManu.getText().trim()).append("' , ");}
                    
                                           if (!countryManu.getText().isEmpty()) {sql.append("'").append(countryManu.getText().trim()).append("' , ");}
                                         
                                        
                                         if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                                         
                                        
                                         if(allfound==true) {  insertProcessString(sql,"name","manufacture", nameManu);
                                         refreshComboBox(made, "name", "Manufacture");    refreshComboBox(madeDe, "name", "Manufacture");      
                                         }
                                         
                                         
                                         else {
                                      	   Alert alert=new Alert(AlertType.INFORMATION); 
                                             alert.setTitle("Faild Insert");
                                             alert.setHeaderText("You must add all Values !!");// line 4
                                             alert.setWidth(200);                                   
                                             alert.show();  
                                         }
                                                
 }                    
                   
                           
                   
 //order table            
                                 if(tablesList.getValue().equals("Orders Table")) {
                                      sql.append("orders (id,date,customer,car) values( ");
                                            
                                      boolean allfound=true;
                                      boolean top=true;
                                      if(idOr.getText().isEmpty()||date.getText().isEmpty()||carOr.getSelectionModel().isEmpty()||customer.getSelectionModel().isEmpty()) allfound=false;
                                            
                                          
                                           if (!idOr.getText().isEmpty())   {if(checkIfInteger("ID", idOr)) {sql.append(idOr.getText().trim()).append(" , ");}else top=false;}
 
                                           if (!date.getText().isEmpty())   {if(checkIfInteger("Date", date)) {sql.append(date.getText().trim()).append(" , ");}else top=false;}

                                           if (!customer.getSelectionModel().isEmpty()) {sql.append(customer.getSelectionModel().getSelectedItem()).append(" , ");}
                                           
                                           if (!carOr.getSelectionModel().isEmpty()) {sql.append("'").append(carOr.getSelectionModel().getSelectedItem()).append("' , ");}
                            
                                
                                         if (sql.toString().endsWith(" , "))  sql.setLength(sql.length() - 3);
                                         
                                         if(allfound==true&&top==true)  insertProcessString(sql,"id","orders", idOr); 
                                         
                                         else if(allfound==false) {
                                      	   Alert alert=new Alert(AlertType.INFORMATION); 
                                             alert.setTitle("Faild Insert");
                                             alert.setHeaderText("You must add all Values !!");// line 4
                                             alert.setWidth(200);                                   
                                             alert.show();  
                                         }
                                                                                              
  }               
                                 
                                 StringBuilder sql2 =new StringBuilder("Select * from  ");  
                                 
                          	   if(tablesList.getValue().equals("Address Table")) {sql2.replace(13,sql2.length(), " address  ");}
                          	   else if (tablesList.getValue().equals("Car Table")) {sql2.replace(13,sql2.length(), " car  ");}
                          	   else if (tablesList.getValue().equals("Car_Part Table")) {sql2.replace(13,sql2.length(), " car_part  ");}
                          	   else if (tablesList.getValue().equals("Customer Table")) {sql2.replace(13,sql2.length(), " customer  ");}
                          	   else if (tablesList.getValue().equals("Orders Table")) {sql2.replace(13,sql2.length(), " orders  ");}
                          	   else if (tablesList.getValue().equals("Device Table")) {sql2.replace(13,sql2.length(), " device  ");}
                          	   else if (tablesList.getValue().equals("Manufacture Table")) {sql2.replace(13,sql2.length(), " manufacture  ");}
                          	   
                          	    tableview.getItems().clear();
                                tableview.getColumns().clear();
                                method(sql2.toString());
                                
                                
 
                       }});
                   
                   
        
// add nodes to group
	                  group.getChildren().addAll(leftBox,grid,clearExit,tablesList,line,header,tableview,database,full,line2,line3,vbox1,vbox2,vbox3,vbox4,vbox5,vbox6,vbox7);

	                  go.setOnAction( new EventHandler<ActionEvent>() { 
	                      public void handle(ActionEvent event) {
	                      	stage.setScene(scene1);
	                      }});
	                    
                   
                       
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                           Platform.exit();
                           System.exit(0);
                            }
                        });
                        
                       stage.show();
 }
    
    
    
    
    public void method (String sql) {
    	try {
             c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
             rs = c.createStatement().executeQuery(sql.toString());

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(
                                    CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "]");
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }

            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
	    
    }
   
    
    
    


    public void up (String sql) {
    	try {
          c =getConnection();
          int rowsAffected = c.createStatement().executeUpdate(sql);    
          
          
          if (rowsAffected > 0) 
              System.out.println("Data inserted successfully.");
           else
              System.out.println("Failed to insert data.");
          
          
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(
                                    CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "]");
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }

            tableview.setItems(data);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
	    
    }
    
    
    
    
    
 public ObservableList gett(String col, String table) {
	 	    ObservableList<Object> v = FXCollections.observableArrayList();
	try {
		 c = getConnection();
     ResultSet rs = c.createStatement().executeQuery("SELECT "+col+ " FROM " + table+" ;");
     while (rs.next()) {
         v.add(rs.getObject(col));
     }	
}
 catch (Exception e) {
     e.printStackTrace();
     System.out.println("Error on Gett");
 }
	return v;
 }
 
 
 
 public void insertProcessInteger(StringBuilder sql,String col,String table,TextField tf) {
     	   
    	 	try {  

    	   String sqlQuery = " SELECT "+col+" FROM "+table  ;							
           PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery);
		 
           // Execute the query
           ResultSet resultSet = preparedStatement.executeQuery();

           // Fetch values and add them to the ListView
           List<Integer>list=new ArrayList<Integer>();
           while (resultSet.next()) {
               String columnValue = resultSet.getString(col);
               list.add(Integer.valueOf(columnValue));    
               }
           
        if(list.contains(Integer.valueOf(tf.getText().trim()))) { //if id already exist
           Alert alert=new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("The "+col+" already exist"); 
           alert.setContentText("You must change the "+ col);
           alert.show();}
           
        else { //if id not exist
    	    sql.append(")");                                       
            tableview.getItems().clear();
             tableview.getColumns().clear();
             up(sql.toString());    
            
            Alert alert=new Alert(AlertType.INFORMATION);
           alert.setTitle("Insert new row");
           alert.setHeaderText("Your record has been successfully added."); 
           alert.show();}
    	   
    	   }catch (SQLException e) {e.printStackTrace();}
       
       }
     
 
 
 public void insertProcessString(StringBuilder sql,String col,String table,TextField tf) {
	   
	 	try {  

	   String sqlQuery = " SELECT "+col+" FROM "+table  ;							
       PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery);
	 
    // Execute the query
    ResultSet resultSet = preparedStatement.executeQuery();

    // Fetch values and add them to the ListView
    List<String>list=new ArrayList<String>();
    while (resultSet.next()) {
        String columnValue = resultSet.getString(col);
        list.add(String.valueOf(columnValue));    
        }
    
 if(list.contains(String.valueOf(tf.getText().trim()))) { //if id already exist
    Alert alert=new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("The "+col+" already exist"); 
    alert.setContentText("You must change the "+ col);
    alert.show();}
    
 else { //if id not exist
	    sql.append(")");                                       
     tableview.getItems().clear();
      tableview.getColumns().clear();
      up(sql.toString());    
     
     Alert alert=new Alert(AlertType.INFORMATION);
    alert.setTitle("Insert new row");
    alert.setHeaderText("Your record has been successfully added."); 
    alert.show();}
	   
	   }catch (SQLException e) {e.printStackTrace();}

}
 
 
 
 
 public boolean  checkIfInteger(String fieldName,TextField tf) {

	  try {
          int intValue = Integer.parseInt(tf.getText());
          return true;
      } catch (NumberFormatException e) {
    	  Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Invalid Input");
	        alert.setHeaderText("Invalid " + fieldName + " Value");
	        alert.setContentText("Please enter an integer for " + fieldName + ".");
	        alert.showAndWait();
    	     return false;
      }
}
 

 
 // Refresh ComboBox data for the specified column and table
 private void refreshComboBox(ComboBox<String> comboBox, String columnName, String tableName) {
     try {
         ObservableList<String> data = gett(columnName, tableName);
         comboBox.setItems(data);
     } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Error refreshing ComboBox");
     }
 }
 

 
 }

 
