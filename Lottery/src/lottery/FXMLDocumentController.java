package lottery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
  private int numberFieldsSize = 7;
  private int[] inputNumbers = new int[ numberFieldsSize ];
  private int[] winningNumbers;
  private Seven99 lotto;

  private TextField[] inputFields = new TextField[numberFieldsSize];
  private TextField[] winFields = new TextField[numberFieldsSize];

  @FXML
  private Button submit;

  @FXML
  private TextField number3;

  @FXML
  private TextField number4;

  @FXML
  private TextField number1;

  @FXML
  private TextField number2;

  @FXML
  private TextField number7;

  @FXML
  private TextField number5;

  @FXML
  private TextField number6;

  @FXML
  private TextField winningNumber4;

  @FXML
  private TextField winningNumber3;

  @FXML
  private TextField winningNumber2;

  @FXML
  private TextField winningNumber1;

  @FXML
  private TextField winningNumber7;

  @FXML
  private TextField winningNumber6;

  @FXML
  private TextField winningNumber5;

  @FXML
  void checkWinning(ActionEvent event) {
    // Collate inputs into an integer array
    inputIntoInt();

    // Determine if the input is winning or not and use the result to show an alert.
    new LotteryAlert( matchInputWithWinning() );

    // Set the input as the next winning numbers and save it.
    lotto.setNumbers( inputNumbers );
    lotto.saveNumbers();

    showWinningNumbers();
    submit.setDisable( true );
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    arrayInputFields();
    arrayWinFields();
    initSeven99();
  }

  private void initSeven99() {
    lotto = new Seven99( "numbers.txt" );

    if (lotto.parseFile() == false) {
      lotto.generate();
      lotto.saveNumbers();
    }

    // Prevent lotto.getNumbers() from passing by reference and just clone the array instead.
    winningNumbers = lotto.getNumbers().clone();
  }

  private void arrayInputFields() {
    inputFields[0] = number1;
    inputFields[1] = number2;
    inputFields[2] = number3;
    inputFields[3] = number4;
    inputFields[4] = number5;
    inputFields[5] = number6;
    inputFields[6] = number7;
  }

  private void arrayWinFields() {
    winFields[0] = winningNumber1;
    winFields[1] = winningNumber2;
    winFields[2] = winningNumber3;
    winFields[3] = winningNumber4;
    winFields[4] = winningNumber5;
    winFields[5] = winningNumber6;
    winFields[6] = winningNumber7;
  }

  private void showWinningNumbers() {
    for (int i = 0; i < winningNumbers.length; i++) {
      winFields[i].setText(String.valueOf(winningNumbers[i]));
    }
  }

  private void inputIntoInt() {
    for (int i = 0; i < inputFields.length; i++) {
      inputNumbers[i] = Integer.parseInt( inputFields[i].getText() );
    }
  }

  private boolean matchInputWithWinning() {
    boolean win = true;

    for (int i = 0; i < winningNumbers.length; i++) {
      int search = Arrays.binarySearch( inputNumbers, winningNumbers[i] );

      if ( search < 0 ) {
        win = false;
        break;
      }
    }

    return win;
  }
}