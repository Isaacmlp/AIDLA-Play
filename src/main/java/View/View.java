package View;

import Controller.Controller;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.lib.LibVlc;
import uk.co.caprica.vlcj.binding.support.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.media.Media.*;
import uk.co.caprica.vlcj.factory.AudioApi.*;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.AudioApi;
import uk.co.caprica.vlcj.player.base.VideoApi.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


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


    public View () {
        fileChooser = new JFileChooser();
        audioPlayerComponent = new AudioPlayerComponent();

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent() {

            @Override
            public void playing(MediaPlayer mediaPlayer) {
                ProgressBar.setValue((int) mediaPlayerComponent.mediaPlayer().status().position());
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
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
    }

    private void Menu() {
        Barra = new JMenuBar();
        Item = new JMenuItem("Open File");
        Menu = new JMenu("File");
        Barra.add(Menu);
        Menu.add(Item);
        setJMenuBar(Barra);

        Item.addActionListener(control);

    }

    private void Contenido () {
        setTitle("AIDLA Play");
        setSize(1024,700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(3);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(mediaPlayerComponent,BorderLayout.CENTER);

        Controller control = new Controller(this); //Instancia del Controlador

        PlayButton = new JButton("Play");
        PauseButton = new JButton("Pause");
        SkipButton = new JButton("Skip");
        RewindButton = new JButton("Rewind");
        Volumen = new JSlider(0,100,50);
        ProgressBar = new JSlider();

        ProgressBar.setOrientation(SwingConstants.HORIZONTAL);

        PlayButton.addActionListener(control);
        PauseButton.addActionListener(control);
        SkipButton.addActionListener(control);
        RewindButton.addActionListener(control);
        Volumen.addChangeListener(control); // Conectar el slider al controlador

        ControlsPanel = new JPanel();

        ControlsPanel.add(ProgressBar);
        ControlsPanel.add(PlayButton);
        ControlsPanel.add(PauseButton);
        ControlsPanel.add(RewindButton);
        ControlsPanel.add(SkipButton);
        ControlsPanel.add(Volumen);
        ControlsPanel.add(ProgressBar);

        panel.add(ControlsPanel,BorderLayout.SOUTH);
        setContentPane(panel);
        setVisible(true);
        mediaPlayerComponent.mediaPlayer().media().play("C:\\Program Files\\Image-Line\\FL Studio 20\\Data\\Patches\\Packs\\Sacler Packs\\FREE GUITAR SAMPLE PACK\\80 Fm.wav");
        mediaPlayerComponent.mediaPlayer().status().position();

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

    public void updateSliderValue() {
        float position = mediaPlayerComponent.mediaPlayer().media().info().duration();
        int sliderValue = (int) (position * 100);
        ProgressBar.setValue(sliderValue);
    }

    public JMenuItem getItem () {

        return Item;
    }



}
