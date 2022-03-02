/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3a15.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
 import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import edu.connexion3a15.entities.activites;
import edu.connexion3a15.services.activitescrud;
import edu.connexion3a15.utiles.Myconnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
 import org.controlsfx.control.textfield.AutoCompletionBinding;

/**
 * FXML Controller class
 *
 * @author chiha
 */
public class GestionactiviteController implements Initializable {

    @FXML
    private TextField txnomact;
    @FXML
    private DatePicker calddebut;
    @FXML
    private DatePicker caldfinal;
    @FXML
    private TextField txemplacement;
    @FXML
    private TextField txdescirptionact;
    @FXML
    private Button btnajouteract;
    @FXML
    private Button btnmodifieract;
    @FXML
    private Button btnsupprimeract;
    @FXML
    private TableColumn<activites, String> tvnomact;
    @FXML
    private TableColumn<activites, Date> tvdebut;
    @FXML
    private TableColumn<activites, Date> tvdatefinal;
    @FXML
    private TableColumn<activites, String> tvdescact;
    @FXML
    private TableView<activites> tvactivites;
    @FXML
    private TableColumn<activites, String> tvemplacement;
    @FXML
    private TextField filterField;
  ObservableList<activites> listM;
    ObservableList<activites> dataList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showactivites ();
        search_act();
               // System.out.print("test");

        
        
     }    

    
    
    
    
    
    
    
    
    
    @FXML
    private void modifieractivite(ActionEvent event) {
        
         activitescrud a =new activitescrud();
         activites z  =tvactivites.getSelectionModel().getSelectedItem();
        int selectedID = tvactivites.getSelectionModel().getSelectedIndex();
        String nom_act = txnomact.getText();
        String description = txdescirptionact.getText();
        String emplacament = txemplacement.getText() ; 
        Date  d_debut = Date.valueOf(calddebut.getValue());
        Date d_fin = Date.valueOf(caldfinal.getValue());
         
activites act = new activites(nom_act, description,d_debut,d_fin, emplacament,1);   

         if ((calddebut.getValue() == null)||( caldfinal.getValue()==null)|| (caldfinal.getValue()==null)) {
             System.out.println("erreur date");   
        }else {
       a.modifieractivites(act,z.getId_act());
        showactivites ();
     }}

    @FXML
    private void ajouteractivite(ActionEvent event) {        
        
        String nom_act = txnomact.getText();
        String description = txdescirptionact.getText();
        String emplacament = txemplacement.getText() ; 
        Date  d_debut = Date.valueOf(calddebut.getValue());
        Date d_fin = Date.valueOf(caldfinal.getValue());
       // System.out.println(formatter.format(d_fin.getTime()));
        
         
         
activites act = new activites(nom_act, description,d_debut,d_fin, emplacament,1);   
    
         if ((calddebut.getValue() == null)||( caldfinal.getValue()==null)|| (caldfinal.getValue()==null)) {
             System.out.println("erreur null");   
        }else {  activitescrud a = new activitescrud() ;
      a.ajouteractivites(act);
       showactivites ();
        }}
     
    
    

    @FXML
    private void supprimeractivite(ActionEvent event) {
         activitescrud a =new activitescrud();
         activites act  =tvactivites.getSelectionModel().getSelectedItem();
         a.delete(act.getId_act());
       int selectedID = tvactivites.getSelectionModel().getSelectedIndex();
        tvactivites.getItems().remove(selectedID);
         showactivites ();
    }
 
    public void showactivites (){
     activitescrud act = new activitescrud();
     
      ObservableList <activites> list = act.listeract();
        tvnomact.setCellValueFactory(new PropertyValueFactory<activites,String>("nom_act"));
        tvdebut.setCellValueFactory(new PropertyValueFactory<activites,Date>("d_debut"));
        tvdatefinal.setCellValueFactory(new PropertyValueFactory<activites,Date>("d_fin"));
        tvemplacement.setCellValueFactory(new PropertyValueFactory<activites,String>("emplacement"));
         tvdescact.setCellValueFactory(new PropertyValueFactory<activites,String>("description"));


        //System.out.print("test");
        tvactivites.setItems(list);
    }

    @FXML
    private void selectrow(MouseEvent event) {
        activites act  =tvactivites.getSelectionModel().getSelectedItem();
        System.out.println("id"+act.getId_act());
        
        txnomact.setText(act.nom_act);
        calddebut.setValue(LocalDate.parse(act.d_debut.toString()));
        caldfinal.setValue(LocalDate.parse(act.d_fin.toString()));
        txemplacement.setText(act.emplacement);
        txdescirptionact.setText(act.description);
        Date.valueOf(calddebut.getValue());
        
        
    }
   
    
    
    void search_act() {      
               activitescrud act = new activitescrud();
     
      ObservableList <activites> list = act.listeract();
        tvnomact.setCellValueFactory(new PropertyValueFactory<activites,String>("nom_act"));
        tvdebut.setCellValueFactory(new PropertyValueFactory<activites,Date>("d_debut"));
        tvdatefinal.setCellValueFactory(new PropertyValueFactory<activites,Date>("d_fin"));
        tvemplacement.setCellValueFactory(new PropertyValueFactory<activites,String>("emplacement"));
         tvdescact.setCellValueFactory(new PropertyValueFactory<activites,String>("description"));


        //System.out.print("test");
        tvactivites.setItems(list);
            dataList = act.listeract();
        
         FilteredList<activites> filteredData = new FilteredList<>(dataList, b -> true);  
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(activites -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (activites.getNom_act().toLowerCase().contains(lowerCaseFilter) ) {
     return true; // Filter matches username
    } else if (activites.getEmplacement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    } 
         else  
          return false; // Does not match.
   });
  });  
  SortedList<activites> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tvactivites.comparatorProperty());  
  tvactivites.setItems(sortedData);      
    }
    }
    

   


