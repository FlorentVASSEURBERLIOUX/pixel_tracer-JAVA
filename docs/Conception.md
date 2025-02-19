# Conception pixel_tracer

## Introduction

Pixel Tracer est un programme en langage C permettant de réaliser du dessin vectoriel en mode textuel. Il utilise une interface en ligne de commande. L’application fonctionne en représentant les formes géométriques sous forme de structures (`struct`), en les stockant dynamiquement et en les affichant via une matrice de pixels (`Area`).

Le but de notre projet est donc la translation de ce code C en code JAVA.


## Sommaire

- Introduction

- Conception du code C

   1. **Structures de données et gestion des formes**
        1. *Représentation des formes géométriques*
        2. *Structure générique Shape*

   2. **Gestion et affichage des formes**
        1. *Représentation mémoire et affichage*
        2. *Algorithmes de tracé*

   3. **Interface utilisateur et commandes**
        1. *Liste des commandes principales*
        2. *Implémentation des commandes*

- Conception du code JAVA


## Conception du code C

### 1. Structures de données et gestion des formes

**1.1. Représentation des formes géométriques**

Chaque forme est représentée en mémoire à l’aide d’une `struct` spécifique. Voici les structures principales :

- `Point` : Défini par deux coordonnées `(x, y)`.
- `Line` : Défini par deux points `p1` et `p2`.
- `Circle` : Défini par un centre `Point* center` et un rayon `radius`.
- `Square` et `Rectangle` : Un point d’origine `(x, y)`, une largeur et une hauteur.
- `Polygon` : Une liste dynamique de `Point*` stockant les sommets.

Exemple de structure en C pour un cercle :
```c
typedef struct {
    Point *center;
    int radius;
} Circle;
```


**1.2. Structure générique Shape**

Afin de gérer toutes les formes avec un seul type, le projet implémente un type générique `Shape`, qui permet de manipuler différentes formes sans distinction :

```c
typedef enum { POINT, LINE, SQUARE, RECTANGLE, CIRCLE, POLYGON } SHAPE_TYPE;

typedef struct shape {
    int id;                   // Identifiant unique
    SHAPE_TYPE shape_type;    // Type de forme
    void *ptrShape;           // Pointeur vers une forme spécifique
} Shape;
```

Les fonctions associées permettent de créer et manipuler chaque type de `Shape` :
```c
Shape *create_circle_shape(int x, int y, int radius);
void delete_shape(Shape *shape);
void print_shape(Shape *shape);
```
Ce système facilite l’extensibilité du projet, permettant d’ajouter de nouvelles formes sans modifier le reste du code. Pour ajouter de nouvelles formes, il suffit de créer une nouvelle structure, de l'ajouter à la liste des `SHAPE_TYPE` et de lui créer ses fonctions de création, de suppression et d'affichage.

---

### 2. Gestion et affichage des formes

**2.1. Représentation mémoire et affichage**

L’application utilise une matrice de pixels (`Area`) pour afficher le dessin sous forme ASCII.  
Chaque pixel est représenté par un caractère. Ces caractère sont changeable par l'utilisateur afin de redéfinir `EMPTY_CHAR` et `FULL_CHAR`.

Exemple de matrice de pixels :
```
.....................
..####..............
.##..##.............
.#....#.............
.##..##.............
..####..............
.....................
```

La structure `Area` permet de gérer cette zone de dessin.
Il s'agit de la représentation d'une fenêtre de dessin :
```c
typedef struct area {
    unsigned int width, height;    // Dimensions de l’aire
    int mat;                       // Matrice de pixels
    Shape* shapes[100];            // Liste des formes stockées
    int nb_shape;                  // Nombre de formes présentes
} Area;
```

Cette structure possède aussi ses propre constructeurs `create` , `delete` et `print` :
```c
Area* create_area(unsigned int width, unsigned int height);
void draw_area(Area* area);
void print_area(Area* area);
void delete_area(Area* area);
```

L'affichage se fait en transformant chaque forme en une liste de pixels et en coloriant la matrice avec le `FULL_CHAR`.

**2.2. Algorithmes de tracé**

Différents algorithmes spécialisés sont utilisés pour convertir les formes en pixels :

- Lignes : Algorithme de Bresenham
- Cercles : Algorithme de Bresenham pour les arcs
- Polygones : Traçage segment par segment
- Courbes : Algorithme de Casteljau pour les courbes de Bézier

Exemple de l’algorithme de Bresenham :
```c
void pixel_line(Line* line, Pixel pixel, int* nb_pixels) {
    int x1 = line->p1->x, y1 = line->p1->y;
    int x2 = line->p2->x, y2 = line->p2->y;
    int dx = abs(x2 - x1), dy = abs(y2 - y1);
    int sx = (x1 < x2) ? 1 : -1, sy = (y1 < y2) ? 1 : -1;
    int err = dx - dy;
    
    while (x1 != x2 || y1 != y2) {
        pixel[*nb_pixels] = create_pixel(x1, y1);
        (*nb_pixels)++;
        int e2 = 2 * err;
        if (e2 > -dy) { err -= dy; x1 += sx; }
        if (e2 < dx) { err += dx; y1 += sy; }
    }
}
```

*Explication de [L'algorithme de Bresenham](https://fr.wikipedia.org/wiki/Algorithme_de_trac%C3%A9_de_segment_de_Bresenham) sur Wikipédia.*

---

### 3. Interface utilisateur et commandes

Le programme fonctionne avec une interface en ligne de commande où l’utilisateur peut ajouter et manipuler des formes.

**3.1. Liste des commandes principales**

| Commande | Description |
|----------|------------|
| `point x y` | Ajoute un point |
| `line x1 y1 x2 y2` | Ajoute une ligne |
| `circle x y radius` | Ajoute un cercle |
| `square x y length` | Ajoute un carré |
| `polygon x1 y1 x2 y2 ...` | Ajoute un polygone |
| `list` | Affiche toutes les formes |
| `delete id` | Supprime une forme par ID |
| `erase` | Supprime toutes les formes |
| `plot` | Affiche l’image ASCII |
| `exit` | Quitte le programme |

**3.12 Implémentation des commandes**

Lorsqu'un utilisateur entre une commande, le programme la récupère à l'aide de `fgets()`, puis l'analyse en la découpant en tokens (mots-clés et paramètres). Ces tokens sont stockés dans une structure `Command` qui contient le nom de la commande et ses arguments. Ensuite, la fonction `execute_command()` execute les actions associés à la commandes et ses arguments.


---

## Conception du code JAVA


### 1. Différence JAVA et C

En Java, `Scanner` peut être utilisé à la place de `fgets()` pour récupérer les commandes utilisateur.

**1.1. Classes et Héritage**

Chaque structure `struct` du C doit être convertie en une classe Java avec des attributs privés et des méthodes publiques pour la gestion des données.
La structure `Shape` générique en C peut être transformée en une classe abstraite en Java, avec des sous-classes spécifiques pour `Point`, `Line`, `Circle`, etc.

**1.2. Gestion mémoire et Pointeur** 

Contrairement au C, où l’allocation et la libération de mémoire sont manuelles (`malloc` et `free`), Java gère automatiquement la mémoire avec son Garbage Collector.
Au lieu d’utiliser des tableaux statiques comme en C, il est recommandé d'utiliser `ArrayList<>` pour stocker dynamiquement les formes.
Ainsi le concepte de pointeur n'est plus nécessaire.


Exemple de conversion d’une structure en classe Java :

C :
```c
typedef struct {
    Point *center;
    int radius;
} Circle;
```

Java :
```java
public class Circle extends Shape {
    private Point center;
    private int radius;
    
    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }
}
```

### 2. Diagrammes de conception

**2.1 Diagramme de Classes**

<img src="Diagrammes/Diagramme de dependances.png">

<u>*Diagramme de Dependances*</u>

Ce diagramme illustre les interactions entre les fichiers.
Il a été créé a partir du code C, les relations de dépendances restent similaire pour la version JAVA.
Par exemple, `main.c` dépend de `pixel_tracer.h`, qui inclut plusieurs modules comme `shape.h`, `command.h`, `render.h` et `pixel.h`.

Chaque module a une responsabilité spécifique :
  - `shape.h` : Définit les formes.
  - `render.h` et `pixel.h` : Gèrent l’affichage des formes.
  - `command.h` : Gère les commandes utilisateur.

<img src="Diagrammes/Classe Area.png" width="300">

<u>*Diagramme du Composant Area*</u>

<img src="Diagrammes/Classe Command.png" width="300">

<u>*Diagramme du Composant Command*</u>

<img src="Diagrammes/Classe Layer.png" width="300">

<u>*Diagramme du Composant Layer*</u>

<img src="Diagrammes/Classe PixelTracer.png" width="300">

<u>*Diagramme du Composant PixelTracer*</u>

<img src="Diagrammes/Classe Shape.png">

<u>*Diagramme du Composant Shape*</u>

Ces diagrammes représentent la structure orientée objet utilisée pour modéliser les formes géométriques du projet.
On a `Shape`, la classe généraliste, et toutes les autres formes (`Circle`, `Rectangle`, `Square`, `Line`, `Polygon`, `Curve`) qui en héritent.
La structure `Point` sert de base pour définir les positions.

La classe `Area` implémente les zones de dessins.

La classe `Command` implémente les commandes consoles d'appel aux fonctionnalités du programme.

La classe `Layer` implémente les calques des dessins, les différentes couches sélectionnables.

La classe `PixelTracer` est l'application principale.

<img src="Diagrammes/Diagramme de composants.png">

<u>*Diagramme de composants*</u>

Ce diagramme de composants représente l’architecture modulaire du projet Pixel Tracer en mettant en évidence les relations entre ses principaux modules.

- `Pixel Tracer` : Composant central qui interagit avec plusieurs autres modules.
- `commande` : Gère les commandes utilisateur et se connecte à `Pixel Tracer`
- `main` : Point d’entrée du programme, dépend du module `commande`
- `area` : Gère les zones de dessin, interagit avec `Pixel Tracer`
- `layers` : Gère les calques et les différentes couches de rendu, connecté à `area`
- `shape` : Définit les structures de formes et leurs interactions, relié à `Pixel Tracer` et `layers`