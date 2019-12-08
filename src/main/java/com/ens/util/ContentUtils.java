package com.ens.util;

import io.humble.video.Demuxer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentUtils {

    public static Dimension getImageDimension(String imagePath) {
        try {
            final BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            return new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
        } catch (IOException ex) {
            log.error("{}", ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static long getVideoDuration(String filePath) {
        Demuxer demuxer = null;
        try {
            demuxer = Demuxer.make();
            demuxer.open(filePath, null, false, true, null, null);
        } catch (InterruptedException e) {
            log.error("{}", e);
            //throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            log.error("{}", e);
            //throw new RuntimeException(e.getMessage());
        } catch (Exception ex) {
            log.error("{}", ex);
        } catch (Throwable te) {
            log.error("{}", te);
        }
        return demuxer == null ? 0
                : TimeUnit.MICROSECONDS.toSeconds(demuxer.getDuration());// converting to seconds
    }
}
