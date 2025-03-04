/**
 * @file Area.java
 * @brief Classe représentant une zone avec plusieurs couches (layers).
 *
 * Cette classe permet de créer une zone avec une largeur, une hauteur et un nom.
 * Elle gère également une liste de couches et fournit des méthodes pour modifier 
 * l'affichage et les paramètres de la zone.
 */

package Areas;

import Layers.Layer;
import java.util.ArrayList;

/**
 * @class Area
 * @brief Représente une zone dans l'application.
 */
public class Area {
    private int width;           ///< Largeur de la zone.
    private int height;          ///< Hauteur de la zone.
    private static int nextId = 0; ///< ID statique unique pour chaque zone.
    private int id;              ///< Identifiant unique de la zone.
    private String name;         ///< Nom de la zone.
    private ArrayList<Layer> lstLayers; ///< Liste des couches associées à la zone.
    private String emptyChar = "."; ///< Caractère représentant une case vide.
    private String fullChar = "#";  ///< Caractère représentant une case pleine.
    private String[][] area;     ///< Tableau 2D représentant la zone.

    /**
     * @brief Constructeur de la classe Area.
     * @param width Largeur de la zone.
     * @param height Hauteur de la zone.
     * @param name Nom de la zone.
     */
    public Area(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.id = nextId++;
        this.name = name;
        this.lstLayers = new ArrayList<>();
        this.area = new String[height][width];

        // Remplit la zone avec le caractère vide par défaut.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.area[i][j] = emptyChar;
            }
        }
    }

    /// Définit la liste des couches.
    public void setLayersList(ArrayList<Layer> lstLayers) { this.lstLayers = lstLayers; }

    /// Retourne la largeur de la zone.
    public int getWidth() { return width; }

    /// Retourne la hauteur de la zone.
    public int getHeight() { return height; }

    /// Retourne l'ID de la zone.
    public int getId() { return id; }

    /// Retourne le nom de la zone.
    public String getName() { return name; }

    /// Retourne le contenu d'une cellule spécifique.
    public String getCell(int x, int y) { return this.area[x][y]; }

    /// Modifie le contenu d'une cellule spécifique.
    public void setCell(int x, int y, String cell) { area[x][y] = cell; }

    /// Réinitialise la zone avec le caractère vide par défaut.
    public void areaClear () {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                area[i][j] = emptyChar;
            }
        }
    }

    /// Retourne le caractère représentant une case vide.
    public String getEmptyChar() { return emptyChar; }

    /// Modifie le caractère représentant une case vide.
    public void setEmptyChar(String emptyChar) { this.emptyChar = emptyChar; }

    /// Retourne le caractère représentant une case pleine.
    public String getFullChar() { return fullChar; }

    /// Modifie le caractère représentant une case pleine.
    public void setFullChar(String fullChar) { this.fullChar = fullChar; }

    /// Retourne la liste des couches de la zone.
    public ArrayList<Layer> getLayersList() { return lstLayers; }

    /// Supprime la zone en réinitialisant son tableau interne.
    public void deleteArea() { this.area = null; }
}
