/**
 * @file CommandProcessor.java
 * @brief Classe permettant de traiter et exécuter les commandes utilisateur.
 *
 * Cette classe analyse les commandes saisies et exécute les actions appropriées
 * en fonction du contexte de l'application.
 */

package Command;

import Shapes.*;
import PixelTracer.PixelTracerApp;
import Areas.Area;
import Layers.Layer;

import java.util.Scanner;

/**
 * @class CommandProcessor
 * @brief Gère l'exécution des commandes utilisateur.
 */
public class CommandProcessor {
    private static int errorNum = 0; ///< Code d'erreur associé à la dernière commande exécutée.
    private static PixelTracerApp app; ///< Référence vers l'application principale.

    /// Liste des messages d'erreur associés aux codes d'erreur.
    private static final String[] ERROR_MESSAGES = {
            "",
            "Commande inconnue",
            "Commande manquante",
            "Erreur paramètres, consulter la commande help",
            "exit",
            "clear",
            "plot",
            "~~~ Help ~~~",
            "done",
            "ID inconnu dans la liste"
    };

    /**
     * @brief Associe une instance de l'application au processeur de commandes.
     * @param application Instance de l'application principale.
     */
    public static void setApp(PixelTracerApp application) {
        app = application;
    }

    /**
     * @brief Lit et exécute une commande.
     * @param cmd Commande à exécuter.
     * @return Code d'erreur résultant de l'exécution.
     */
    public static int readExecCommand(Command cmd) {
        errorNum = 1; // Par défaut, commande inconnue
        if (cmd.stringParams.isEmpty()) {
            errorNum = 2;
            return errorNum;
        }

        switch (cmd.name) {
            case "exit":
                if (!checkNbParams(cmd, 1, 0)) {
                    errorNum = 3;
                    return errorNum;
                }
                errorNum = 4; // Quitter le programme
                break;

            case "clear":
                if (!checkNbParams(cmd, 1, 0)) {
                    errorNum = 3;
                    return errorNum;
                }
                errorNum = 5; // Effacer l'écran
                break;

            case "plot":
                if (!checkNbParams(cmd, 1, 0)) {
                    errorNum = 3;
                    return errorNum;
                }
                errorNum = 6; // Dessiner
                break;

            case "help":
                if (!checkNbParams(cmd, 1, 0)) {
                    errorNum = 3;
                    return errorNum;
                }
                printHelp();
                errorNum = 7; // Afficher l'aide
                break;

            // === Gestion des listes ===
            case "list":
                if (!checkNbParams(cmd, 2, 0)) {
                    errorNum = 3;
                    return errorNum;
                }
                switch (cmd.stringParams.get(1)) {
                    case "layers":
                        for (Layer layer : app.getCurrentArea().getLayersList()) {
                            System.out.println("Layer ID: " + layer.getId() + ", Name: " + layer.getName());
                        }
                        break;
                    case "areas":
                        for (Area area : app.getListAreas()) {
                            System.out.println("Area ID: " + area.getId() + ", Name: " + area.getName());
                        }
                        break;
                    case "shapes":
                        for (Shape shape : app.getCurrentLayer().getShapes()) {
                            System.out.println("Shape ID: " + shape.getId() + ", Shape: " + shape);
                        }
                        break;
                    default:
                        errorNum = 3;
                        return errorNum;
                }
                errorNum = 0;
                break;

            // === Sélection ===
            case "select":
                if (!checkNbParams(cmd, 2, 1)) {
                    errorNum = 3;
                    return errorNum;
                }
                int id = cmd.intParams.get(0);
                switch (cmd.stringParams.get(1)) {
                    case "area":
                        for (Area area : app.getListAreas()) {
                            if (area.getId() == id) {
                                app.setCurrentArea(area);
                                System.out.println("Current area set to: " + area.getName());
                                errorNum = 0;
                                return errorNum;
                            }
                        }
                        break;
                    case "layer":
                        for (Layer layer : app.getCurrentArea().getLayersList()) {
                            if (layer.getId() == id) {
                                app.setCurrentLayer(layer);
                                System.out.println("Current layer set to: " + layer.getName());
                                errorNum = 0;
                                return errorNum;
                            }
                        }
                        break;
                    default:
                        errorNum = 3;
                        return errorNum;
                }
                errorNum = 9; // ID inconnu
                break;

            // === Suppression ===
            case "delete":
                System.out.println("DELETE");
                System.out.println(cmd.stringParams.get(1));
                System.out.println(cmd.intParams.get(0));
                if (!checkNbParams(cmd, 2, 1)) {
                    errorNum = 3;
                    return errorNum;
                }
                int deleteId = cmd.intParams.get(0);

                switch (cmd.stringParams.get(1)) {
                    case "area":
                        app.getListAreas().removeIf(area -> area.getId() == deleteId);
                        System.out.println("Area deleted: " + deleteId);
                        break;
                    case "layer":
                        app.getCurrentArea().getLayersList().removeIf(layer -> layer.getId() == deleteId);
                        System.out.println("Layer deleted: " + deleteId);
                        break;
                    case "shape":
                        app.getCurrentLayer().getShapes().removeIf(shape -> shape.getId() == deleteId);
                        System.out.println("Shape deleted: " + deleteId);
                        break;
                    default:
                        errorNum = 3;
                        return errorNum;
                }
                errorNum = 0;
                break;

            // === Création ===
            case "new":
                if (!checkNbParams(cmd, 2, 0)) {
                    errorNum = 3;
                    return errorNum;
                }
                switch (cmd.stringParams.get(1)) {
                    case "area":
                        Area newArea = new Area(80, 20, "New Area");
                        app.getListAreas().add(newArea);
                        app.setCurrentArea(newArea);
                        System.out.println("New area created: " + newArea.getName());
                        break;
                    case "layer":
                        Layer newLayer = new Layer("New Layer");
                        app.getCurrentArea().getLayersList().add(newLayer);
                        app.setCurrentLayer(newLayer);
                        System.out.println("New layer created: " + newLayer.getName());
                        break;
                    default:
                        errorNum = 3;
                        return errorNum;
                }
                errorNum = 0;
                break;

            // === Paramètres ===
            case "set":
                System.out.println("SET");
                if (!checkNbParams(cmd, 3, 1)) {
                    System.out.println("godfkjgopdkfogkdfop");
                    errorNum = 3;
                    return errorNum;
                }
                switch (cmd.stringParams.get(1)) {
                    case "char":
                        switch (cmd.stringParams.get(2)) {
                            case "border":
                                app.getCurrentArea().setFullChar(Character.toString((char) cmd.intParams.get(0).intValue()));
                                System.out.println("Border character set.");
                                return 0;
                            case "background":
                                app.getCurrentArea().setEmptyChar(Character.toString((char) cmd.intParams.get(0).intValue()));
                                System.out.println("Background character set.");
                                return 0;
                            default:
                                errorNum = 3;
                                System.out.println("NNNNNNNNNNNNNNN");
                                return errorNum;
                        }
                    case "layer":
                        if (!checkNbParams(cmd, 3, 1)) {
                            errorNum = 3;
                            return errorNum;
                        }
                        int layerId = cmd.intParams.get(0);
                        for (Layer layer : app.getCurrentArea().getLayersList()) {
                            if (layer.getId() == layerId) {
                                if (cmd.stringParams.get(2).equals("visible")) {
                                    layer.setVisible(true);
                                    System.out.println("Layer " + layerId + " is now visible.");
                                } else if (cmd.stringParams.get(2).equals("invisible")) {
                                    layer.setVisible(false);
                                    System.out.println("Layer " + layerId + " is now invisible.");
                                } else {
                                    errorNum = 3;
                                    return errorNum;
                                }
                                errorNum = 0;
                                return errorNum;
                            }
                        }
                        errorNum = 9; // ID inconnu
                        break;
                    default:
                        System.out.println("PPPPPPPPPPPPPPPPPPPP");
                        errorNum = 3;
                        return errorNum;
                }
                break;

            case "point":
                if (!checkNbParams(cmd, 1, 2)) {
                    errorNum = 3;
                    return errorNum;
                }
                Point point = new Point(cmd.intParams.get(0), cmd.intParams.get(1));
                app.getCurrentLayer().addShape(point);
                errorNum = 0;
                break;

            case "line":
                if (!checkNbParams(cmd, 1, 4)) {
                    errorNum = 3;
                    return errorNum;
                }
                Point p1 = new Point(cmd.intParams.get(0), cmd.intParams.get(1));
                Point p2 = new Point(cmd.intParams.get(2), cmd.intParams.get(3));
                Line line = new Line(p1, p2);
                app.getCurrentLayer().addShape(line);
                errorNum = 0;
                break;

            case "circle":
                if (!checkNbParams(cmd, 1, 3)) {
                    errorNum = 3;
                    return errorNum;
                }
                Point center = new Point(cmd.intParams.get(0), cmd.intParams.get(1));
                int radius = cmd.intParams.get(2);
                if (radius <= 0) {
                    errorNum = 3;
                    return errorNum;
                }
                Circle circle = new Circle(center, radius);
                app.getCurrentLayer().addShape(circle);
                errorNum = 0;
                break;

            case "rectangle":
                if (!checkNbParams(cmd, 1, 4)) {
                    errorNum = 3;
                    return errorNum;
                }
                Point rectCenter = new Point(cmd.intParams.get(0), cmd.intParams.get(1));
                int width = cmd.intParams.get(2);
                int height = cmd.intParams.get(3);
                if (width <= 0 || height <= 0) {
                    errorNum = 3;
                    return errorNum;
                }
                Rectangle rectangle = new Rectangle(rectCenter, width, height);
                app.getCurrentLayer().addShape(rectangle);
                errorNum = 0;
                break;

            case "polygon":
                if (!checkNbParamsPolygon(cmd)) {
                    errorNum = 3;
                    return errorNum;
                }
                Point[] polygonPoints = new Point[cmd.intParams.size() / 2];
                for (int i = 0; i < cmd.intParams.size(); i += 2) {
                    polygonPoints[i / 2] = new Point(cmd.intParams.get(i), cmd.intParams.get(i + 1));
                }
                Polygon polygon = new Polygon(polygonPoints);
                app.getCurrentLayer().addShape(polygon);
                errorNum = 0;
                break;

            case "curve":
                if (!checkNbParams(cmd, 1, 8)) {
                    errorNum = 3;
                    return errorNum;
                }
                Point cp1 = new Point(cmd.intParams.get(0), cmd.intParams.get(1));
                Point cp2 = new Point(cmd.intParams.get(2), cmd.intParams.get(3));
                Point cp3 = new Point(cmd.intParams.get(4), cmd.intParams.get(5));
                Point cp4 = new Point(cmd.intParams.get(6), cmd.intParams.get(7));
                Curve curve = new Curve(cp1, cp2, cp3, cp4);
                app.getCurrentLayer().addShape(curve);
                errorNum = 0;
                break;

            default:
                errorNum = 1;
                break;
        }

        System.out.println(ERROR_MESSAGES[errorNum]);
        return errorNum;
    }

    /**
     * @brief Vérifie si le nombre de paramètres d'une commande est correct.
     * @param cmd Commande à vérifier.
     * @param nbStr Nombre de paramètres texte attendus.
     * @param nbInt Nombre de paramètres numériques attendus.
     * @return Vrai si le nombre de paramètres est correct, faux sinon.
     */
    private static boolean checkNbParams(Command cmd, int nbStr, int nbInt) {
        return cmd.stringParams.size() == nbStr && cmd.intParams.size() == nbInt;
    }

    /**
     * @brief Vérifie si le nombre de paramètres pour la commande polygon est correct, car il peut avoir un grand nombre de paramètre mais doit avoir un nombre pair de paramètres.
     * @param cmd Commande à vérifier.
     * @return Vrai si le nombre de paramètres est correct, faux sinon.
     */
    private static boolean checkNbParamsPolygon(Command cmd) {
        return cmd.stringParams.size() == 1 && !cmd.intParams.isEmpty() && cmd.intParams.size() % 2 == 0;
    }

    /**
     * @brief Affiche l'aide des commandes disponibles.
     */
    private static void printHelp() {
        System.out.println("**************************************************");
        System.out.println("****         VECTOR TEXT-BASED EDITOR         ****");
        System.out.println("**************************************************");
        System.out.println("==== Control ====");
        System.out.println("clear : clear screen");
        System.out.println("exit : exit the program");
        System.out.println("help : print this help");
        System.out.println("plot : draw screen");
        System.out.println("==== Draw shapes ====");
        System.out.println("point px py : create point at position (px, py)");
        System.out.println("line x1 y1 x2 y2 : draw line from (x1, y1) to (x2, y2)");
        System.out.println("rectangle x1 y1 w h : draw rectangle at (x1, y1) with width w and height h");
        System.out.println("circle x y r : draw circle centered at (x, y) with radius r");
        System.out.println("polygon x1 y1 x2 y2 ... : draw polygon with given vertices");
        System.out.println("curve x1 y1 x2 y2 x3 y3 x4 y4 : draw Bezier curve with given control points");
        System.out.println("==== Draw manager ====");
        System.out.println("list {layers, areas, shapes}");
        System.out.println("select {area, layer} {id}");
        System.out.println("delete {area, layer, shape} {id}");
        System.out.println("new {area, layer}");
        System.out.println("==== Set ====");
        System.out.println("set char {border, background} ascii_code");
        System.out.println("set layer {visible, invisible} {id}");
    }
}
