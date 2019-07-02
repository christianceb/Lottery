package lottery;

import javafx.scene.control.Alert;

public class LotteryAlert {
  private Alert alert = new Alert( Alert.AlertType.INFORMATION );

  /**
   * Creates an alert based on the passed parameter.
   *
   * @param win If true, spawns an alert that the user has guessed the numbers. Else, spawn an alert that they failed.
   */
  public LotteryAlert ( boolean win ) {
    if ( win ) {
      alert.setHeaderText("You guessed the numbers!");
      alert.setContentText("An unlikely feat. Are you sure you're not cheating? :) Close the application to have a go at it again.");
    } else {
      alert.setAlertType( Alert.AlertType.ERROR );
      alert.setHeaderText("You did not guess all numbers");
      alert.setContentText("That's normal. Close the application and try again.");
    }
    alert.showAndWait();
  }
}