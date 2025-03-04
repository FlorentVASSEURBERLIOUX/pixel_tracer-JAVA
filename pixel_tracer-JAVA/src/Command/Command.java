/**
 * @file Command.java
 * @brief Classe représentant une commande utilisateur.
 *
 * Cette classe stocke le nom d'une commande ainsi que ses paramètres sous forme
 * de listes d'entiers et de chaînes de caractères.
 */

package Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @class Command
 * @brief Représente une commande avec des paramètres.
 */
public class Command {
    public String name;           ///< Nom de la commande.
    public List<Integer> intParams; ///< Liste des paramètres numériques.
    public List<String> stringParams; ///< Liste des paramètres textuels.

    /**
     * @brief Constructeur de la classe Command.
     * Initialise les listes de paramètres.
     */
    public Command() {
        name = "";
        intParams = new ArrayList<>();
        stringParams = new ArrayList<>();
    }
}
