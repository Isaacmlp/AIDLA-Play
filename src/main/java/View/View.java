package View;


import Controller.Controller;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.lib.LibVlc;
import uk.co.caprica.vlcj.binding.support.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.media.MediaFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.media.Media.*;
import uk.co.caprica.vlcj.factory.AudioApi.*;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.AudioApi;
import uk.co.caprica.vlcj.player.base.VideoApi.*;

import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class View extends JFrame  {
        /* Paneles */

    public JPanel panel;
    public JPanel ControlsPanel;
    public JPanel ProgressbarrPanel;

        /* Botones */

    public JButton PauseButton;
    public JButton SkipButton ;
    public JButton RewindButton;
    public JButton PlayButton;
    public JButton Repeat;

        /* Sliders*/

    public JSlider Volumen;
    public JSlider ProgressBar;

        /* Menu */

    public JMenuBar Barra;
    public JMenu Menu;
    public JMenuItem Item;

    public JFileChooser fileChooser;
        /* Objetos de la depencia VLCJ*/

    public MediaPlayer mediaplayer;
    public MediaPlayerFactory mediaplayerfactory;
    public EmbeddedMediaPlayerComponent mediaPlayerComponent;
    public AudioPlayerComponent audioPlayerComponent;

    Controller control;
    View vista;

    public View () {
        fileChooser = new JFileChooser();



        audioPlayerComponent = new AudioPlayerComponent();

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent() {

            @Override
            public void playing(MediaPlayer mediaPlayer) {
                Timer timer = new Timer();


                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        UpdateProgressBar();
                        UpdateVolumen();

                    }
                }, 0, 1);


            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to play media",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
            }

        };
        Menu();
        Contenido();
        OpenMusic();
    }

    private void Menu() {
        Barra = new JMenuBar();
        Item = new JMenuItem("Open File");
        Menu = new JMenu("File");

        Barra.add(Menu);
        Menu.add(Item);
        setJMenuBar(Barra);


    }

    private void Contenido () {
        setTitle("AIDLA Play");
        setSize(1024,700);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(3);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(mediaPlayerComponent,BorderLayout.CENTER);

        Controller control = new Controller(this); //Instancia del Controlador


        PlayButton = new JButton(ReescalarImagen("PlayIcon","C:\\Users\\Isaac León\\IdeaProjects\\AIDLA Play\\src\\main\\Img\\Play.png"));
        PauseButton = new JButton(ReescalarImagen("Pause","C:\\Users\\Isaac León\\IdeaProjects\\AIDLA Play\\src\\main\\Img\\circulo-de-pausa.png"));
        SkipButton = new JButton(ReescalarImagen("Skip","C:\\Users\\Isaac León\\IdeaProjects\\AIDLA Play\\src\\main\\Img\\tiempo-adelante-diez.png"));
        RewindButton = new JButton(ReescalarImagen("Rewind","C:\\Users\\Isaac León\\IdeaProjects\\AIDLA Play\\src\\main\\Img\\circulo-del-boton-de-rebobinar.png"));
        Repeat = new JButton(ReescalarImagen("Repeat","C:\\Users\\Isaac León\\IdeaProjects\\AIDLA Play\\src\\main\\Img\\flechas-repetir-1.png"));

        Volumen = new JSlider(0,100,50);
        ProgressBar = new JSlider();

        ProgressBar.setOrientation(SwingConstants.HORIZONTAL);

        PlayButton.addActionListener(control);
        PauseButton.addActionListener(control);
        SkipButton.addActionListener(control);
        RewindButton.addActionListener(control);
        Volumen.addChangeListener(control); // Conectar el slider al controlador
        Item.addActionListener(control);
        Repeat.addActionListener(control);

        PlayButton.setFocusable(false);
        PauseButton.setFocusable(false);
        SkipButton.setFocusable(false);
        RewindButton.setFocusable(false);
        Volumen.setFocusable(false);
        Item.setFocusable(false);
        Repeat.setFocusable(false);


        ControlsPanel = new JPanel();

        ControlsPanel.add(ProgressBar);
        ControlsPanel.add(PlayButton);
        ControlsPanel.add(PauseButton);
        ControlsPanel.add(RewindButton);
        ControlsPanel.add(SkipButton);
        ControlsPanel.add(Repeat);
        ControlsPanel.add(Volumen);


        panel.add(ControlsPanel,BorderLayout.SOUTH);
        setContentPane(panel);
        mediaPlayerComponent.mediaPlayer().audio().setVolume(50);
        requestFocus();
        setVisible(true);


    }

    private void Iniciar () {
        //mediaPlayerComponent.mediaPlayer().media().play("C:\\Users\\Isaac León\\Music\\Nueva carpeta\\fl 341.mp3");
       // mediaPlayerComponent.mediaPlayer().media().play("C:\\Users\\Isaac León\\Videos\\Videos MDS\\Filtrar.mp4");
    }

    public JButton getPauseButton () {
        return PauseButton;
    }

    public JButton getSkipButton () {
        return SkipButton;
    }

    public JButton getRewindButton () {
        return RewindButton;
    }

    public JButton getPlayButton () {
        return PlayButton;
    }

    public JSlider getVolumenSlider () {
        return Volumen;
    }

    public void UpdateVolumen () {
        int sliderValue =  mediaPlayerComponent.mediaPlayer().audio().volume();
        Volumen.setValue(sliderValue);
        Volumen.revalidate();
        Volumen.repaint();
    }

    public void updateSliderValue() {
        float position = mediaPlayerComponent.mediaPlayer().status().position();
        int sliderValue = (int) (position * 100);
        ProgressBar.setValue(sliderValue);
    }

    public JMenuItem getItem () {

        return Item;
    }

    public JSlider getVolumen () {
        return Volumen;
    }

    public JSlider getProgressBar () {
        return ProgressBar;
    }

    public void NextMusic () {
            dispose();
            vista = new View();
    }

    public void OpenMusic () {
        fileChooser.showOpenDialog(fileChooser);
        mediaPlayerComponent.mediaPlayer().media().play(fileChooser.getSelectedFile().getAbsolutePath());
    }

    /* Reescalar Iconos e Imagenes */

    public ImageIcon ReescalarImagen(String nombreicono, String Ruta){
        try {
            File file = new File(Ruta);
            BufferedImage originalImage = ImageIO.read(file);

            int newWidth = 20; // nuevo ancho deseado
            int newHeight = 20; // nuevo alto deseado

            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            return new ImageIcon(scaledImage);

            // Ahora puedes usar el ImageIcon en tu GUI
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon();
    }


    public void UpdateProgressBar() {
        updateSliderValue();
        ProgressBar.revalidate();
        ProgressBar.repaint();
    }

    public void NoRepeat() {
        Repeat.setText("");
        mediaPlayerComponent.mediaPlayer().controls().setRepeat(false);
    }
}