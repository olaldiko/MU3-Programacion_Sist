package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class CronometerController implements Initializable, InvalidationListener{
	@FXML
	Button btnStart = new Button();
	@FXML
	Button btnStop = new Button();
	@FXML
	Label labelCrono = new Label();
	@FXML
	ProgressIndicator progress = new ProgressIndicator();
	
	Cronometer crono;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progress.styleProperty().bind(Bindings.createStringBinding(
            () -> {
                final double percent = progress.getProgress();
                if (percent < 0) return null; // progress bar went indeterminate
                //
                // poor man's gradient for stops: red, yellow 50%, green
                // Based on http://en.wikibooks.org/wiki/Color_Theory/Color_gradient#Linear_RGB_gradient_with_6_segments
                //
                final double m = (2d * percent);
                final int n = (int) m;
                final double f = m - n;
                final int t = (int) (255 * f);
                int r = 0, g = 0, b = 0;
                switch (n) {
                    case 0:
                        r = 255;
                        g = t;
                        b = 0;
                        break;
                    case 1:
                        r = 255 - t;
                        g = 255;
                        b = 0;
                        break;
                    case 2:
                        r = 0;
                        g = 255;
                        b = 0;
                        break;

                }
                final String style = String.format("-fx-progress-color: rgb(%d,%d,%d)", r, g, b);
                return style;
            },
            progress.progressProperty()
        ));
		btnStart.setOnAction((event) -> startCrono());
		btnStop.setOnAction((event) -> stopCrono());
	}
	public void startCrono() {
		crono = new Cronometer(this);
		crono.start();
	}
	public void stopCrono() {
		crono.stopCount();
	}
	@Override
	public void invalidated(javafx.beans.Observable observable) {
		String label = String.format("%02d:%02d:%02d.%02d", crono.hour.get(), crono.min.get(), crono.sec.get(), crono.msec.get());
		Platform.runLater(() -> {
			labelCrono.setText(label);
			progress.setProgress(((double)crono.msec.get())/1000);
		});
	}


}
