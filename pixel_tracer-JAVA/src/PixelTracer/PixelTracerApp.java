/**
 * @file PixelTracerApp.java
 * @brief Classe principale de l'application PixelTracer.
 *
 * Cette classe gère les zones, les couches et la structure principale de l'application.
 */

package PixelTracer;

import Areas.Area;
import Layers.Layer;
import Shapes.Shape;

import java.util.ArrayList;

/**
 * @class PixelTracerApp
 * @brief Classe principale de l'application de dessin vectoriel.
 */
public class PixelTracerApp {
    private ArrayList<Area> listArea; ///< Liste des zones créées dans l'application.
    private Area currentArea; ///< Zone actuellement sélectionnée.
    private Layer currentLayer; ///< Couche actuellement sélectionnée.
    private Shape currentShape; ///< Forme actuellement sélectionnée.

    /**
     * @brief Constructeur de la classe PixelTracerApp.
     *
     * Initialise l'application en créant une zone et une couche par défaut.
     */
    public PixelTracerApp() {
        initApp();
    }

    /**
     * @brief Initialise l'application avec une zone et une couche par défaut.
     */
    public void initApp() {
        listArea = new ArrayList<>();
        Area area = new Area(80, 20, "Area1");
        listArea.add(area);
        currentArea = area;

        ArrayList<Layer> layerList = new ArrayList<>();
        area.setLayersList(layerList);
        Layer layer = new Layer("Layer 1");
        layerList.add(layer);
        currentLayer = layer;
        currentShape = null;
    }

    /**
     * @brief Récupère la liste des zones de l'application.
     * @return Liste des zones enregistrées.
     */
    public ArrayList<Area> getListAreas() {
        return listArea;
    }

    /**
     * @brief Récupère la zone actuellement sélectionnée.
     * @return La zone en cours d'utilisation.
     */
    public Area getCurrentArea() {
        return currentArea;
    }

    /**
     * @brief Définit la zone actuellement sélectionnée.
     * @param area La nouvelle zone à sélectionner.
     */
    public void setCurrentArea(Area area) {
        this.currentArea = area;
    }

    /**
     * @brief Récupère la couche actuellement sélectionnée.
     * @return La couche en cours d'utilisation.
     */
    public Layer getCurrentLayer() {
        return currentLayer;
    }

    /**
     * @brief Définit la couche actuellement sélectionnée.
     * @param layer La nouvelle couche à sélectionner.
     */
    public void setCurrentLayer(Layer layer) {
        this.currentLayer = layer;
    }
}
