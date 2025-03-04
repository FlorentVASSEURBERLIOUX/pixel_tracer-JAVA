package Test;

import PixelTracer.Pixel;
import Shapes.Point;
import Shapes.Polygon;
import java.util.ArrayList;

public class PolygonTest {
    public static void main(String[] args) {
        testDraw();
        testToString();
    }

    static void testDraw() {
        System.out.println("Test de draw()...");

        Point[] points = {
                new Point(0, 0),
                new Point(4, 0),
                new Point(4, 3),
                new Point(0, 3)
        };
        Polygon polygon = new Polygon(points);
        ArrayList<Pixel> pixels = polygon.draw();

        if (pixels == null) {
            System.out.println("Echec : Le résultat ne doit pas être null.");
            return;
        }

        boolean allPointsPresent = true;

        for (Point p : points) {
            boolean found = false;
            for (Pixel pixel : pixels) {
                if (pixel.getPx() == p.getX() && pixel.getPy() == p.getY()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Echec : Pixel attendu non trouvé : (" + p.getX() + ", " + p.getY() + ")");
                allPointsPresent = false;
            }
        }

        if (allPointsPresent) {
            System.out.println("Succès : draw() fonctionne correctement.");
        }
    }


    static void testToString() {
        System.out.println("Test de toString()...");

        Point[] points = {
                new Point(1, 2),
                new Point(3, 4)
        };
        Polygon polygon = new Polygon(points);

        String expected = "Polygon: (1, 2) (3, 4) ";
        String result = polygon.toString();

        if (result.equals(expected)) {
            System.out.println("Succès : toString() fonctionne correctement.");
        } else {
            System.out.println("Echec : toString() attendu = \"" + expected + "\", obtenu = \"" + result + "\"");
        }
    }
}
