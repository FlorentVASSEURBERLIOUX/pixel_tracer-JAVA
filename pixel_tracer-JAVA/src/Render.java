/**
 * @file Render.java
 * @brief Classe de rendu pour l'application PixelTracer.
 *
 * La classe `Render` est responsable du rendu des zones et des couches de l'application PixelTracer.
 * Elle gère l'affichage à l'écran des différentes formes dessinées et fournit des méthodes pour effacer l'écran,
 * dessiner les zones, et manipuler les couches.
 */

import java.util.ArrayList;
import java.util.List;

import Areas.Area;
import Layers.Layer;
import PixelTracer.Pixel;
import PixelTracer.PixelTracerApp;
import Shapes.Shape;

public class Render {

    /** Référence à l'application PixelTracer. */
    private static PixelTracerApp app;

    /**
     * @brief Définit l'application à utiliser pour le rendu.
     *
     * Cette méthode permet de lier l'application PixelTracer à la classe `Render`.
     *
     * @param application L'application PixelTracer à lier.
     */
    public static void setApp(PixelTracerApp application) {
        app = application;
    }

    /**
     * @brief Effectue le rendu d'une zone donnée.
     *
     * Cette méthode est utilisée pour dessiner la zone spécifiée.
     *
     * @param area La zone à rendre.
     */
    public static void renderArea(Area area) {
        drawArea(area);  // Dessiner la zone
    }

    /**
     * @brief Dessine une zone à l'écran.
     *
     * Cette méthode parcourt la zone donnée et affiche son contenu à l'écran en utilisant des caractères définis
     * pour les cellules vides et pleines.
     *
     * @param area La zone à dessiner.
     */
    public static void drawArea(Area area) {
        String EmptyChar = app.getCurrentArea().getEmptyChar();  // Caractère pour les cellules vides
        String FullChar = app.getCurrentArea().getFullChar();    // Caractère pour les cellules pleines

        System.out.println();

        // Parcours de la zone et affichage de chaque cellule
        for (int i = 0; i < area.getHeight(); i++) {
            for (int j = 0; j < area.getWidth(); j++) {
                String cell = area.getCell(i, j);

                if (cell.equals(EmptyChar)) {
                    System.out.print(EmptyChar);
                } else if (cell.equals(FullChar)) {
                    System.out.print(FullChar);
                } else {
                    throw new IllegalStateException("Unexpected value: " + cell);  // Gestion des erreurs
                }
            }
            System.out.println();
        }
    }

    /**
     * @brief Efface l'écran de l'affichage.
     *
     * Cette méthode efface l'écran en fonction du système d'exploitation (Windows ou non-Windows).
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[J");  // Code ANSI pour effacer l'écran
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Dessine toutes les couches de la zone.
     *
     * Cette méthode efface d'abord la zone et dessine toutes les couches visibles de la zone donnée.
     *
     * @param area La zone dans laquelle dessiner les couches.
     */
    public static void drawAllLayers(Area area) {
        area.areaClear();  // Effacer la zone
        ArrayList<Layer> layers = area.getLayersList();  // Obtenir la liste des couches
        for (Layer layer : layers) {
            if (layer.isVisible()) {
                drawLayerShapes(area, layer);  // Dessiner les formes de la couche visible
            }
        }
    }

    /**
     * @brief Dessine les formes d'une couche donnée dans une zone.
     *
     * Cette méthode parcourt les formes de la couche spécifiée et dessine les pixels correspondants
     * dans la zone. Les pixels sont ajoutés à la zone en vérifiant leurs coordonnées.
     *
     * @param area La zone dans laquelle dessiner les formes.
     * @param layer La couche contenant les formes à dessiner.
     */
    public static void drawLayerShapes(Area area, Layer layer) {
        String FullChar = app.getCurrentArea().getFullChar();  // Caractère pour les pixels pleins
        List<Shape> shapes = layer.getShapes();  // Obtenir la liste des formes de la couche
        for (Shape shape : shapes) {
            List<Pixel> pixels = shape.draw();  // Obtenir les pixels de la forme
            for (Pixel pixel : pixels) {
                // Vérification des coordonnées des pixels
                if (pixel.getPx() < area.getHeight() && pixel.getPy() < area.getWidth() &&
                        pixel.getPx() >= 0 && pixel.getPy() >= 0) {
                    area.setCell(pixel.getPx(), pixel.getPy(), FullChar);  // Dessiner le pixel dans la zone
                }
            }
        }
    }
}
