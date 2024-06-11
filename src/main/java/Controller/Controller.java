package Controller;

import View.View;
import Model.Model;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Controller implements WindowListener, ActionListener, ChangeListener {
    View view;
    Model model;

    public Controller (View view) {
        this.view = view;
        this.model = new Model();
    }


        /* WindowsListener */

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        view.mediaPlayerComponent.release();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

        /* ActionListener*/

    @Override
    public void actionPerformed(ActionEvent e) {

       if (e.getSource() == view.getPlayButton()) {
           view.mediaPlayerComponent.mediaPlayer().controls().play(); // Reproducir
       } else if (e.getSource() == view.getPauseButton()) {
           view.mediaPlayerComponent.mediaPlayer().controls().pause(); // Pausar
       } else if (e.getSource() == view.getSkipButton()) {
           view.mediaPlayerComponent.mediaPlayer().controls().skipTime(-1000); // Rebobinar 10 segundos
       } else if (e.getSource() == view.getRewindButton()) {
           view.mediaPlayerComponent.mediaPlayer().controls().skipTime(1000); // Saltar 10 segundos
       } else if (e.getSource() == view.getItem()) {
          view.fileChooser.showOpenDialog(view);

           view.mediaPlayerComponent.mediaPlayer().media().play(String.valueOf(view.fileChooser.getSelectedFile()));
       }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.audioPlayerComponent.mediaPlayer().audio().setVolume(view.getVolumenSlider().getValue());
    }
}
