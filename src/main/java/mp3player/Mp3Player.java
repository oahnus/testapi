package mp3player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by jackstrom on 2016/4/10.
 */
public class Mp3Player {
    public static void main(String[] args) throws IOException, JavaLayerException {
        String mp3FilePath = "src/main/resources/mp3player/dream.mp3";
        PlayMusic(mp3FilePath);
    }

    /**
     * play mp3 music
     */
    public static void PlayMusic(String path) throws IOException, JavaLayerException {
        File mp3File = Paths.get(path).toFile();

        try (FileInputStream in = new FileInputStream(mp3File);
            BufferedInputStream bin = new BufferedInputStream(in)) {
            Player player = new Player(bin);
            System.out.println("Start Play Music ==> " + mp3File.getName());
            player.play();
            System.out.println("End");
        }
    }
}