package am.aua.library.ui;

import am.aua.library.ui.views.MainView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public final class Helpers {
    public static BufferedImage generateQRCode(String data, int size) {
        try {
            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.MARGIN, 1);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Create the QR code matrix
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, size, size, hintMap);

            // Create buffered image to draw the QR code matrix
            BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            return image;

        } catch (WriterException e) {
            System.err.println("Could not generate QR code: " + e);
            return null;
        }
    }

    public static ImageIcon getRescaledImageIcon(String iconFilename, int w, int h) {
        ImageIcon imageIcon = new ImageIcon("./resources/assets/images/" + iconFilename);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    public static boolean isValidUsername(String input) {
        return !input.isEmpty() && !input.isBlank() && isAscii(input) && !input.contains(" ");
    }

    public static boolean isValidPassword(String input) {
        return input.length() >= 8 && !input.isBlank();
    }

    public static boolean isAscii(String input) {
        for (char c : input.toCharArray()) {
            if (c > 127) {
                return false;
            }
        }

        return true;
    }

    public static void handleLogout(JFrame target) {
        int input = JOptionPane.showConfirmDialog(target, "Are you sure you want to logout?");
        if (input == JOptionPane.YES_OPTION) {
            target.dispose();
            new MainView();
        }
    }
}
