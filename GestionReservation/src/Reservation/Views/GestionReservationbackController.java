/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation.Views;


import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import javafx.util.converter.IntegerStringConverter;
import reservation.entities.Reservation;

import reservation.services.ReservationCRUD;

/**
 * FXML Controller class
 *
 * @author Sejir
 */
public class GestionReservationbackController implements Initializable {

    @FXML
    private TableColumn<Reservation,Integer> IdREScol;
    @FXML
    private TableColumn<Reservation, String> NomCcol;
    @FXML
    private TableColumn<Reservation, String> prenomCCol;
    @FXML
    private TableColumn<Reservation, Integer> IdActCOL;

    @FXML
    private TableColumn<Reservation, Integer> NbreCOL;
    @FXML
    private TableColumn<Reservation, Integer> NumCabCol;
    @FXML
    private TableColumn<Reservation,Float> PrixCabCol;
    @FXML
    private TableView<Reservation> TableResBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //CabineCRUD resc=new CabineCRUD();
     ActionEvent event= new ActionEvent();
         AfficheResBack(event);
       TableResBack.setEditable(true);
       
        TableResBack.getSelectionModel().setCellSelectionEnabled(true);
        
        //IdREScol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       
          NomCcol.setCellFactory(TextFieldTableCell.forTableColumn());
          prenomCCol.setCellFactory(TextFieldTableCell.forTableColumn());
           IdActCOL.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        NbreCOL.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
           NumCabCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
   
         AfficheResBack(event);

        
    }    

    @FXML
    private void AfficheResBack(ActionEvent event) {
        ReservationCRUD reService = new ReservationCRUD();
        List<Reservation> liste = reService.listerResevation();
        ObservableList<Reservation> olist = FXCollections.observableArrayList(liste);  
        IdREScol.setCellValueFactory(new PropertyValueFactory<>("IdRes"));
        NomCcol.setCellValueFactory(new PropertyValueFactory<>("NomClient"));
        prenomCCol.setCellValueFactory(new PropertyValueFactory<>("PrenomC"));
        IdActCOL.setCellValueFactory(new PropertyValueFactory<>("IdAct"));
         NbreCOL.setCellValueFactory(new PropertyValueFactory<>("Nbre_Perso"));
         NumCabCol.setCellValueFactory(new PropertyValueFactory<>("NumC"));
       
        
        TableResBack.setItems(olist);
    }

    @FXML
    private void SupprimerResBack(ActionEvent event) {
          Reservation re=TableResBack.getSelectionModel().getSelectedItem();  
        ReservationCRUD vService = new ReservationCRUD();
        vService.deleteResevation(re);
        
      AfficheResBack(event);
    }

    @FXML
    private void gotocabines(ActionEvent event) throws IOException {
         FXMLLoader Loder = new FXMLLoader();
                        Loder.setLocation(getClass().getResource("GestionCabine.fxml"));
                        Loder.load();
                        Parent AnchorPane = Loder.getRoot();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                          Scene scene = new Scene(AnchorPane);
                         stage.setScene(scene);
                        stage.showAndWait();
                    
    }

  

    @FXML
    private void onEditNomc(TableColumn.CellEditEvent<Reservation, String> event) {
           Reservation re=TableResBack.getSelectionModel().getSelectedItem();       
        re.setNomClient(event.getNewValue());
          ReservationCRUD reService = new ReservationCRUD();
        reService.modifierResevation(re);
    }

    @FXML
    private void onEditPrenomC(TableColumn.CellEditEvent<Reservation, String> event) {
           Reservation re=TableResBack.getSelectionModel().getSelectedItem();       
        re.setPrenomC(event.getNewValue());
         ReservationCRUD reService = new ReservationCRUD();
        reService.modifierResevation(re);
    }

    @FXML
    private void onEditidIdAct(TableColumn.CellEditEvent<Reservation, Integer> event) {
           Reservation re=TableResBack.getSelectionModel().getSelectedItem();       
        re.setIdAct(event.getNewValue());
          ReservationCRUD reService = new ReservationCRUD();
        reService.modifierResevation(re);
    }



    @FXML
    private void onEditnbre(TableColumn.CellEditEvent<Reservation, Integer> event) {
           Reservation re=TableResBack.getSelectionModel().getSelectedItem();       
        re.setNbre_Perso(event.getNewValue());
         ReservationCRUD reService = new ReservationCRUD();
        reService.modifierResevation(re);
    }

    @FXML
    private void onEditNumCab(TableColumn.CellEditEvent<Reservation, Integer> event) {
           Reservation re=TableResBack.getSelectionModel().getSelectedItem();       
        re.setNumCabR(event.getNewValue());
        ReservationCRUD reService = new ReservationCRUD();
        reService.modifierResevation(re);
    }


}
