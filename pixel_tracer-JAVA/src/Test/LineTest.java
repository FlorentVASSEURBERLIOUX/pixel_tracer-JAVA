package Test;

import PixelTracer.Pixel;
import Shapes.Point;
import Shapes.Line;
import java.util.ArrayList;

public class LineTest {
    public static void main(String[] args) {
        testDraw();
        testToString();
    }

    static void testDraw() {
        System.out.println("Test de draw()...");

        Point p1 = new Point(2, 3);
        Point p2 = new Point(6, 7);
        Line line = new Line(p1, p2);
        ArrayList<Pixel> pixels = line.draw();

        if (pixels == null) {
            System.out.println("Echec : Le résultat ne doit pas être null.");
            return;
        }

        boolean allPointsValid = true;

        // Vérifie que tous les pixels suivent une ligne droite avec l'équation de la droite y = mx + b
        int dx = p2.getX() - p1.getX();
        int dy = p2.getY() - p1.getY();
        double slope = (dx != 0) ? (double) dy / dx : Double.POSITIVE_INFINITY;

        for (Pixel pixel : pixels) {
            int x = pixel.getPx();
            int y = pixel.getPy();

            boolean isOnLine = (dx == 0) ? (x == p1.getX()) : (y == p1.getY() + (int) Math.round(slope * (x - p1.getX())));

            if (!isOnLine) {
                System.out.println("❌ Échec : Pixel incorrect (" + x + ", " + y + ")");
                allPointsValid = false;
            }
        }

        if (allPointsValid) {
            System.out.println("Succès : draw() fonctionne correctement.");
        }
    }

    static void testToString() {
        System.out.println("Test de toString()...");

        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 4);
        Line line = new Line(p1, p2);

        String expected = "Line: " + p1 + " to " + p2;
        String result = line.toString();

        if (result.equals(expected)) {
            System.out.println("Succès : toString() fonctionne correctement.");
        } else {
            System.out.println("Echec : toString() attendu = \"" + expected + "\", obtenu = \"" + result + "\"");
        }
    }
}
