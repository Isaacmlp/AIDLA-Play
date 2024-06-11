package Controller;

import View.View;
import Model.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Controller implements WindowListener, ActionListener, ChangeListener , MouseListener {
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
           view.mediaPlayerComponent.mediaPlayer().controls().skipTime(1000); // Saltar 10 segundos
       } else if (e.getSource() == view.getRewindButton()) {
           view.mediaPlayerComponent.mediaPlayer().controls().skipTime(-1000); // Rebobinar 10 segundos
       } else if (e.getSource() == view.Item) {
           view.OpenMusic();
       } else if ( e.getSource() == view.Repeat) {

           if (view.Repeat.getText() == "Repeat On"){
               view.NoRepeat();
           } else {
               view.Repeat.setText("Repeat On");
               view.mediaPlayerComponent.mediaPlayer().controls().setRepeat(true);
           }


       }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == view.getVolumen() ) {
            view.audioPlayerComponent.mediaPlayer().audio().setVolume(view.getVolumenSlider().getValue());
        } else if (e.getSource() == view.ProgressBar) {
            view.mediaPlayerComponent.mediaPlayer().controls().setPosition(view.ProgressBar.getValue());
            view.ProgressBar.revalidate();
            view.ProgressBar.repaint();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.Item) {
            view.fileChooser.showOpenDialog(view.fileChooser);

            view.mediaPlayerComponent.mediaPlayer().media().play(view.fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
