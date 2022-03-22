<h1 align="center">MAUJA Adventures </h1>  

## Description

MAUJA Adventures est un jeu en deux dimensions basé sur les énigmes, l'aventure et le combat. Nous avons réalisé l'entièreté du moteur de jeu.  

![image](https://gitlab.iut-clermont.uca.fr/jetremblay/mauja-adventures/-/blob/main/images/trou.png)

A la manière des RPG traditionnels, il se sert de cartes réalisées avec le système de *tile-mapping* et propose une expérience de jeu divertissante.
Il est possible de vous déplacer dans des cartes reliées entre elles et qui sont composées d'un système de calques permettant d'amener de la profondeur au jeu. Les ennemis possèdent différents comportements et peuvent attaquer au corps à corps ou à distance. Le joueur peut affronter différents types d'ennemis via des attaques à l'épée, se défendre de manière à renvoyer des projectiles et perdre de la vie.  

Bien évidemment, toutes les entités du jeu sont soumises aux collisions du monde qui les entoure et un mécanisme permettant d'en faire apparaître est également présent.  
Un système de scrolling a été mis en place de manière à ce que la caméra soit centrée sur le joueur et sur la zone d'action.  

Jouable au clavier depuis un ordinateur, ce jeu est scriptable et il est possible de créer vos propres cartes, règles et évènements, ainsi que de configurer vos touches (voir la section **Comment scripter ?**).  

Ce jeu a été réalisé en Java et nous nous sommes servi de JavaFX pour la partie visuelle.

## Features
- [x] Un système de déplacement dans des cartes composées de plusieurs calques qui amènent une sensation de 3D.
- [x] Des cartes communiquantes entre elles et possibilité de voyager entre les différents mondes.
- [x] Un scrolling avec une caméra qui suit le personnage durant tous ses déplacements.
- [x] Des entités soumises aux collisions et à leurs propres collisions dans le monde.
- [x] Des ennemis aux comportements variés et originaux.
- [x] Un système d'attaque et de défense permettant au joueur de combattre les villes créatures qui peuplent le monde.
- [x] Des touches configurables.
- [x] La possibilité de créer et d'incorporer ses propres cartes, et de les relier comme ce que l'on souhaite.
- [x] Des règles du jeu complètement personnalisables permettant de créer des interactions uniques.
- [x] Des menus et une navigation fluide à travers le jeu.
- [x] Une conception permettant une grande évolutivité du programme, et laissant la possibilité d'ajouter encore plus de contenus, d'évènements, d'interactions et d'éléments interactifs.

## Plateformes compatibles

Ce jeu à été testé et fonctionne sous Windows et Linux.

## Comment l'installer ?
### Préparation
Pour installer MAUJA Adventures, vous devrez tout d'abord télécharger et installer IntelliJ IDEA depuis le site de [jetbrains](https://www.jetbrains.com/fr-fr/idea/download/). Il s'agit d'un environnement de développement intégré qui vous permettera à la fois de programmer et de contribuer au projet, mais également de le lancer.  

----------------------

Il vous faudra aussi installer la bibilothèque JavaFX depuis le site dédié : [openjfx](https://openjfx.io/) (En bas de la page sélectionner `Download` et sélectionner la bonne version).

----------------------

Pour finir, il vous faudra télécharger la bibilothèque TiledReader pour pouvoir assurer le chargement et l'affichage des cartes. Vous pourrez vous le procurer sur le site du créateur [Alex Heyman](http://www.alexheyman.org/tiledreader/).

### Lancement

⚠️⚠️⚠️
> Si vous avez déjà un dossier ".mauja" dans votre home (`/home/nom_utilisateur` sous Linux et `C:/Users/nom_utilisateur` sous Windows) il est grandement conseillé de le **supprimer** (sauf si vous avez modifié des fichiers à l'intérieur), car les données du jeu sont chargées depuis ce dossier. Autrement dit, si vous ne le supprimez pas, vous n'aurez peut-être pas la dernière version des cartes et transitions qui se trouvent sur ce dépôt.

Vous pourrez ensuite cloner ce repository sur votre ordinateur. Ouvrez alors le projet avec IntelliJ IDEA (Depuis le menu principal, cliquer sur `Open`, puis sélectionner le dossier `source` depuis le dossier où le projet a été téléchargé). Le projet s'ouvre, mais ne peut pas être lancé en l'état, il faut configurer l'espace de travail.

Configuration :
1. Aller dans `File`, sélectionner `Project Structure`.
2. Dans `Project`, vérifier que le JDK utilisé est supérieur ou égal à 16. IntelliJ vous propose normalement de le télécharger au moment de la sélection si vous ne l'avez pas.
3. Aller dans l'onglet `Libraries` et cliquer sur le `+` pour ajouter une librairie de projet `Java`. Sélectionner le dossier `lib` de la bibliothèque JavaFX et valdier.
4. Cliquer de nouveau sur le `+` et ajouter la bibliothèque TiledReader cette fois-ci. Valider en faisant `Apply`.
5. Aller dans `Modules`. Si les bibliothèques "lib" et TiledReader n'aparaissent pas, alors il faut les ajouter en cliquant sur le `+` au dessus de `Export`, puis les sélectionner et valider.
6. Si dans l'onglet `Sources` il n'y a aucun chemin de spécifié, en ajouter un en cliquant sur `Add Content Root` et sélectionner le dossier `source` du repository cloné. Sélectionner le dossier `ressources` et cliquer sur `Resource`, il doit changer de couleur.
7. Fermer le menu contextuel et aller dans `Add Condiguration` et sélectionner `Application`. Lui donner éventuellement un nom et préciser en `Main class` le fichier `com.mauja.maujaadventures.Launcheur`. Cliquer sur `Modify Option` et `Add VM Options`. Préciser cette ligne dans le champ qui apparaît : 
```
--module-path CHEMIN --add-modules javafx.controls,javafx.fxml
```
En remplaçant `CHEMIN` par le chemin vers la bibliothèque JavaFX.

8. Cliquer sur `Run` (flèche verte) et le jeu devrait se lancer.

En cas de problème, se référer sur la documentation d'opnjfx qui est très détaillée sur la manière de lancer un projet : [documentation](https://openjfx.io/openjfx-docs/).

## Comment scripter ?

### Création de cartes avec Tiled
Si vous avez envie de participer à l'avancement de ce projet ou si vous souhaitez simplement créer vos propres évènements, vous pouvez scripter ce jeu. Un dossier ".mauja" doit se trouver dans votre home (`/home/nom_utilisateur` sous Linux et `C:/Users/nom_utilisateur` sous Windows) car il est ajouté automatiquement lors du premier lancement du jeu. Il contient toutes les données relatives aux cartes, scénarios et configurations.  

Vous avez aussi la possibilité d'ajouter des choses dans ce dossier et de créer vos propres cartes, jeux de tuiles et scripts. Tous les documents ajoutés dans ce dossier seront pris en compte automatiquement, dès que le programme sera relancé. Le dossier se compose normalement de la hiérarchie suivante :

```
.mauja
├───cartes
├───images
│   └───tilesets
├───scripts
└───tilesets
```

Dans le dossier cartes, vous pouvez ajouter toutes vos cartes créées depuis le logiciel Tiled (cartes au format `.tmx`). Pour pouvoir créer vos propres cartes, vous devez pour cela installer ce logiciel de création de cartes en téléchargeant la version `1.4.3` ici : [Tiled](https://github.com/mapeditor/tiled/releases/tag/v1.4.3).  

La [documentation](https://doc.mapeditor.org/en/stable/) de Tiled décrit comment créer une carte pas à pas, c'est très simple.

> ⚠️ Si vous installez une verison supérieure au logiciel, vous aurez très probablement des conflits avec la bibliothèque TiledReader qui ne prend en charge que les cartes créées sous Tiled avec une version inférieure ou égale à `1.4.3`. 

Vous pourrez ensuite enregistrer vos tilesets générés par Tiled (qui contiendront les collisions de vos cartes) dans le dossier `tilesets`. Cela doit être des fichiers de type `.tsx`. 

⚠️⚠️⚠️ Le jeu ne supporte pas les cartes isomériques, seulement orthogonales.  

Enfin, dans le dossier `images/tilesets/` vous pourrez placer les fichier des images des tilesets qui seront utilisés en jeu, et **qui doivent correspondre aux images utilisées par Tiled quand la carte est créée**.

### Transitions entre les cartes
Le fichier de transitions entre les cartes `transitions.txt` situé à la racine du dossier `.mauja` contient toutes les transitions. Vous pouvez l'éditer pour préciser quelle est la première carte où le joueur va apparaître, et comment il va pouvoir se déplacer entre les différentes cartes du monde. La syntaxe a utiliser pour préciser ces informations est spécifiée dans le fichier et s'il est supprimé il sera recréé automatiquement. 

### Configurer ses touches
Le fichier `configurationTouches.txt` précise quelles touches vont être associées aux actions qu'il sera possible de réaliser en jeu. La syntaxe a utiliser pour préciser ces informations est spécifiée dans le fichier et s'il est supprimé il sera recréé automatiquement. 

### Scnéarios et règles du jeu
Enfin, le dossier `scripts` contient des fichiers XML qui seront lus au lancement du projet. Ces scénarios se dérouleront conformément aux conditions et actions définies par les utilisateurs dans les fichiers de script.

Pour écrire un tel scénario, se référer à l'exemple fourni dans le dossier. Il s'agit d'un enchainement de balises d'`Action`, de `Condition`s et d'`ElementInteractif`s.

### Pour aller plus loin

Sachez qu'il est possible d'ajouter des classes qui dérivent de `Action` ou encore `Condition` et que vous pourrez les inclure dans ces fichiers de script. Ainsi, ils seront évalués et vous pourrez réaliser tout un tas d'interactions et d'énigmes dans le jeu.

Si vous voulez créer vos propres ennemis, vous pouvez créer vos comportements en étandant l'interface `Comportement` et en précisant le comportement de l'ennemi. Il est aussi possible d'étendre la classe `Evenement` et de créer ses propres évènements relatifs à des situations particulières et de les déclencher dans des situations bien précises. Ils seront automatiquement traités par le Gestionnaire d'Interactions.

## Contribuez !

N'hésitez pas à mettre une étoile si ce projet vous plaît.

## Auteurs
👤 **Jérémy TREMBLAY**
* [@jérémy-tremblay2](https://fr.linkedin.com/in/j%C3%A9r%C3%A9my-tremblay2)
* [@JeremyTremblay2](https://github.com/JeremyTremblay2)

👤 **Antoine VITON**

* [@anviton](https://github.com/anviton)

👤 **Ugo VIGNON**

* [@xxx](https://fr.wikipedia.org/wiki/Sp%C3%A9cial:Page_au_hasard)
* [@xxx](https://fr.wikipedia.org/wiki/Sp%C3%A9cial:Page_au_hasard)

👤 **Adrien COUDOUR**

* [@xxx](https://fr.wikipedia.org/wiki/Sp%C3%A9cial:Page_au_hasard)
* [@xxx](https://fr.wikipedia.org/wiki/Sp%C3%A9cial:Page_au_hasard)

👤 **Maxime WISSOCQ**

* [@xxx](https://www.linkedin.com/in/maxime-wissocq-207066220/)
* [@xxx](https://github.com/MaximeWq)

Remerciements spéciaux à notre enseignant **M. PROVOT Laurent** qui nous a aidé et guidé durant ce projet.

## Licence

CC-BY-SA 3.0: [En savoir plus](http://creativecommons.org/licenses/by-sa/3.0/)
