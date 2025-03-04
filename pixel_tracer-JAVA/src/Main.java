/**
 * @file Main.java
 * @brief Point d'entrée de l'application PixelTracer.
 *
 * Cette classe contient la méthode `main` qui sert de point d'entrée à l'application PixelTracer.
 * Elle initialise l'application, gère les commandes de l'utilisateur, et effectue les rendus graphiques.
 * Elle interagit avec les composants de l'application, comme `PixelTracerApp`, `Render`, et `CommandProcessor`.
 */

import java.util.Scanner;
import PixelTracer.PixelTracerApp;
import Command.Command;
import Command.CommandProcessor;
import static Command.CommandProcessor.readExecCommand;

public class Main {

    /**
     * @brief Point d'entrée de l'application.
     *
     * Cette méthode initialise l'application, connecte le `CommandProcessor` à l'application,
     * et gère l'entrée de l'utilisateur. Elle lit les commandes depuis la console et effectue
     * l'exécution des commandes tout en affichant le rendu graphique. Le programme continue à exécuter
     * les commandes jusqu'à ce que l'utilisateur choisisse de quitter.
     *
     * @param args Arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        PixelTracerApp app = new PixelTracerApp();
        app.initApp();  // Initialisation de l'application

        // Connecter CommandProcessor à l'application
        CommandProcessor.setApp(app);

        // Configurer le rendu
        Render.setApp(app);
        Render.clearScreen();  // Effacer l'écran
        Render.drawAllLayers(app.getCurrentArea()); // Dessiner toutes les couches
        Render.drawArea(app.getCurrentArea());  // Dessiner la zone actuelle

        // Créer un objet scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Boucle principale pour traiter les commandes
        while (true) {
            Command cmd = new Command(); // Créer un nouvel objet Command
            readFromStdin(scanner, cmd); // Lire la commande depuis l'entrée standard

            // Exécuter la commande lue
            int err = readExecCommand(cmd); // Passer l'objet Command à readExecCommand
            switch (err) {
                case 0:
                case 6:
                    // Effacer l'écran et redessiner toutes les couches
                    Render.clearScreen();
                    Render.drawAllLayers(app.getCurrentArea());
                    Render.drawArea(app.getCurrentArea());
                    break;
                case 4: // Commande "exit"
                    scanner.close();  // Fermer le scanner
                    return; // Quitter la boucle et terminer le programme
                case 5: // Commande "clear"
                    Render.clearScreen();  // Effacer l'écran
                    break;
                case 7: // Commande "help"
                case 8: // Commande "done"
                    continue; // Continuer à l'itération suivante
                default:
                    // Gestion d'une erreur de commande inconnue
                    System.out.println("Erreur de commande inconnue.");
                    break;
            }
        }
    }

    /**
     * @brief Lit les entrées depuis l'utilisateur et les ajoute à l'objet Command.
     *
     * Cette méthode lit la ligne de commande entrée par l'utilisateur, la découpe en tokens et les
     * analyse pour remplir l'objet `Command` avec des paramètres appropriés (chaînes ou entiers).
     *
     * @param scanner Scanner pour lire l'entrée de l'utilisateur.
     * @param cmd Objet Command à remplir avec les paramètres extraits de l'entrée.
     */
    private static void readFromStdin(Scanner scanner, Command cmd) {
        System.out.print("~> ");
        String line = scanner.nextLine().trim().toLowerCase(); // Lire la ligne et la nettoyer
        if (line.isEmpty()) {
            return;
        }
        String[] tokens = line.split("\\s+"); // Découper la ligne en tokens
        if (tokens.length == 0) return;

        for (String token : tokens) {
            if (isWord(token)) {
                cmd.stringParams.add(token); // Ajouter un mot
            } else if (isInt(token)) {
                cmd.intParams.add(Integer.parseInt(token)); // Ajouter un entier
            } else {
                System.out.println("Erreur de saisie: " + token); // Gestion des erreurs de saisie
                return;
            }
        }
        cmd.name = cmd.stringParams.get(0); // Le premier paramètre devient le nom de la commande
    }

    /**
     * @brief Vérifie si une chaîne représente un entier valide.
     *
     * Cette méthode essaie de parser la chaîne en un entier et retourne true si c'est possible,
     * sinon elle retourne false.
     *
     * @param str La chaîne à vérifier.
     * @return boolean Vrai si la chaîne est un entier valide, faux sinon.
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @brief Vérifie si une chaîne est un mot composé de lettres minuscules.
     *
     * Cette méthode utilise une expression régulière pour vérifier si la chaîne ne contient que
     * des lettres minuscules (a-z).
     *
     * @param str La chaîne à vérifier.
     * @return boolean Vrai si la chaîne est un mot valide, faux sinon.
     */
    private static boolean isWord(String str) {
        return str.matches("[a-z]+");
    }
}
