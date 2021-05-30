-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 07 mai 2021 à 20:03
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `chatbot_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `contenir`
--

CREATE TABLE `contenir` (
  `id_question` int(11) NOT NULL,
  `id_mot_cle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `contenir`
--

INSERT INTO `contenir` (`id_question`, `id_mot_cle`) VALUES
(1, 24),
(2, 25),
(3, 87),
(4, 87),
(6, 88),
(8, 79),
(11, 87),
(15, 87),
(42, 88),
(43, 85),
(53, 24),
(61, 79),
(64, 29),
(101, 85),
(106, 24),
(107, 79),
(108, 88),
(110, 28),
(111, 79),
(113, 98),
(115, 24),
(116, 31),
(117, 35),
(118, 32),
(119, 33),
(121, 31),
(122, 35),
(123, 32),
(124, 32),
(133, 89),
(134, 36),
(135, 37),
(136, 49),
(137, 38),
(138, 39),
(139, 40),
(140, 41),
(141, 24),
(142, 43),
(143, 90),
(144, 44),
(146, 76),
(148, 45),
(149, 46),
(150, 99),
(151, 47),
(152, 33),
(153, 48),
(154, 49),
(156, 50),
(157, 51),
(158, 52),
(159, 53),
(160, 54),
(161, 92),
(162, 91),
(163, 36),
(164, 56),
(165, 41),
(168, 58),
(170, 59),
(172, 61),
(173, 93),
(175, 62),
(176, 63),
(177, 64),
(180, 65),
(184, 87),
(185, 68),
(187, 69),
(190, 83),
(192, 80),
(194, 83),
(195, 70),
(196, 65),
(198, 71),
(199, 95),
(200, 72),
(202, 71),
(205, 73),
(207, 79),
(209, 94),
(213, 24),
(214, 54),
(215, 77),
(218, 24),
(220, 75),
(223, 24),
(225, 97),
(227, 97),
(228, 97),
(229, 97),
(233, 76),
(234, 77),
(235, 96),
(237, 79),
(239, 79),
(240, 80),
(242, 79),
(250, 100),
(327, 103);

-- --------------------------------------------------------

--
-- Structure de la table `mots_cles`
--

CREATE TABLE `mots_cles` (
  `id_mot_cle` int(11) NOT NULL,
  `mot` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `mots_cles`
--

INSERT INTO `mots_cles` (`id_mot_cle`, `mot`) VALUES
(24, 'quel/besoin/grimper/falaise/materiel/'),
(25, 'comment/entrainer/exercice/escalade/'),
(28, 'importance/force/escalade/'),
(29, 'necessite/gain/force/'),
(30, 'quel/equipement/grimpeur/'),
(31, 'choisir/corde/'),
(32, 'baudrier/choisi/choisir/'),
(33, 'choisir/equipier/'),
(34, 'choisir/collegue/grimpe/'),
(35, 'choisir/chausson/'),
(36, 'est/quoi/dalle/'),
(37, 'est/quoi/devers/'),
(38, 'est/quoi/difference/voie/bloc/differe/'),
(39, 'combien/pointure/chausson/vraie/'),
(40, 'equipement/special/bloc/specifique/materiel/'),
(41, 'pangu/pan/gullish/gu/gulish/entrainement/temps/repetition/'),
(42, 'parois/humide/paroie/voie/'),
(43, 'est/quoi/crashpad/'),
(44, 'choisir/crashpad/'),
(45, 'blessure/chausson/soin/soigner/'),
(46, 'free/solo/danger/'),
(47, 'est/quoi/yaniro/'),
(48, 'est/quoi/dulfer/'),
(49, 'est/quoi/escalade/'),
(50, 'grimper/faille/technique/methode/'),
(51, 'casque/falaise/securite/utile/'),
(52, 'manipulation/haut/voie/'),
(53, 'duree/vie/corde/'),
(54, 'faire/noeud/huit/8/'),
(55, 'marque/chausson/forme/'),
(56, 'installer/rappel/place/'),
(57, 'grimper/vue/'),
(58, 'spot/escalade/grimper/toulouse/partir/aller/'),
(59, 'salle/escalade/toulouse/'),
(60, 'lire/voie/lis/mur/lit/'),
(61, 'travailler/pince/pincee/travail/exercice/'),
(62, 'taille/escalade/grand/petit/etre/'),
(63, 'efficace/muscu/musculation/grimpe/est/quoi/'),
(64, 'tenir/reglette/'),
(65, 'meilleur/grimpeur/monde/'),
(66, 'traction/un/doigt/'),
(67, 'site/spot/escalade/connu/france/meilleur'),
(68, 'prise/arquee/tenue/doigt/danger/'),
(69, 'moquer/nul/gens/moins/mauvais/'),
(70, 'echauffement/grimper/avant/sol/'),
(71, 'difficulte/bloc/cotation/niveau/estimer/'),
(72, 'est/quoi/psychobloc/psycho/bloc/psychoblock/'),
(73, 'ouvreur/estimer/cotation/difficulte/'),
(74, 'meilleur/noeud/'),
(75, 'couleur/mousqueton/'),
(76, 'recuperer/degaine/voie/nature/perdre/'),
(77, 'choisir/noeud/8/chaise/'),
(78, 'faire/noeud/chaise/'),
(79, 'gagner/en/force/'),
(80, 'est/meilleur/grimpeur/'),
(83, '10/degre/horizontal/'),
(85, 'defaut/gain/force/'),
(87, 'bonjour/'),
(88, 'english/hi/'),
(89, 'dalle/ou/devers/'),
(90, 'paroi/humide/'),
(91, 'marque/meilleure/chausson/'),
(92, 'duree/vie/chausson/chaussons/'),
(93, 'flash/flasher/voie/'),
(94, 'gagner/en/technique/'),
(95, 'est/quoi/crux/'),
(96, 'est/quoi/magnesie/pof/'),
(97, 'bye/'),
(98, 'startMessage/'),
(99, 'date/ouverture/salle/reouverture/'),
(100, 'meilleur/temps/falaise/exterieur/meteo/'),
(101, 'est/quoi/baudrier/'),
(102, 'est/quoi/degaine/'),
(103, 'grimper/glace/cascade/'),
(105, 'piaulet/acheter/conseil/materiel/');

-- --------------------------------------------------------

--
-- Structure de la table `questions`
--

CREATE TABLE `questions` (
  `id_question` int(11) NOT NULL,
  `question` text NOT NULL,
  `date_question` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `questions`
--

INSERT INTO `questions` (`id_question`, `question`, `date_question`) VALUES
(1, 'De quoi j ai besoin pour grimper en falaise ', '2020-11-09'),
(2, 'Comment s entraîner pour l escalade ', '2020-11-09'),
(3, 'Bonjour', NULL),
(4, 'Bonjour !', NULL),
(6, 'Hi', '2020-12-15'),
(8, 'comment dois je faire pour ameliorer ma force ', '2020-12-27'),
(11, 'salut', '2020-12-27'),
(15, 'salut !', '2020-12-27'),
(42, 'Hello', '2020-12-30'),
(43, 'pourquoi pas gagner en force', '2020-12-30'),
(53, 'Quel materiel pour débuter en falaise ', '2020-12-30'),
(61, 'comment gagner en force ', '2021-01-01'),
(64, 'Importance de la force en escalade', '2021-01-01'),
(101, 'est-ce nécessaire de gagner en force ', '2021-01-01'),
(106, 'materiel escalade falaise', '2021-01-01'),
(107, 'ameliorer ma force', '2021-01-03'),
(108, 'hi !', '2021-01-03'),
(110, 'se muscler en escalade', '2021-01-05'),
(111, 'gagner en force en escalade', '2021-01-05'),
(113, '/start', '2021-02-01'),
(115, 'quels sont les principaux équipements du grimpeur ', '2021-02-01'),
(116, 'comment choisit on une corde', '2021-02-01'),
(117, 'comment choisit on des chaussons', '2021-02-01'),
(118, 'comment choisit on un baudrier', '2021-02-01'),
(119, 'comment choisit on un équipier', '2021-02-01'),
(121, 'omment choisit on une corde ', '2021-02-01'),
(122, 'comment choisit on des chaussons ', '2021-02-01'),
(123, 'comment choisit on un équipier ', '2021-02-01'),
(124, 'comment choisit on un baudrier ', '2021-02-01'),
(126, 'que peux tu me dire sur l escalade ', '2021-02-01'),
(127, 'question courte ', '2021-02-01'),
(128, 'ceci est une question longue peux tu y répondre ', '2021-02-01'),
(131, 'Tu fais combien de tractions max ', '2021-02-01'),
(133, 'Tu préfères la dalle ou le dévers ', '2021-02-01'),
(134, 'C est quoi la dalle ', '2021-02-01'),
(135, 'C est quoi le dévers ', '2021-02-01'),
(136, 'C est quoi l escalade ', '2021-02-01'),
(137, 'La différence entre la voie et le bloc ', '2021-02-01'),
(138, 'Pour les chaussons, t as combien de pointures en dessous de ta vraie pointure ', '2021-02-01'),
(139, 'Quel équipement faut-il pour le bloc ', '2021-02-01'),
(140, 'Combien de temps tu passe au pangu ', '2021-02-01'),
(141, 'Quels équipements faut-il pour grimper en falaise', '2021-02-01'),
(142, 'C est quoi un crashpad ', '2021-02-01'),
(143, 'T as déjà grimpé sur paroi humide Bah c est pas facile', '2021-02-01'),
(144, 'comment choisir son crashpad ', '2021-02-01'),
(145, 'Tu préfères bloc ou diff ', '2021-02-01'),
(146, 'Comment ne pas perdre de dégaine sur une voie ', '2021-02-01'),
(147, 'Selon toi l escalade de vitesse c est vraiment de la grimpe ', '2021-02-01'),
(148, 'Comment soigner des blessures dûes aux chaussons d escalade ', '2021-02-01'),
(149, 'Pourquoi ne pas faire de free solo ', '2021-02-01'),
(150, 'Quand est-ce que les salles rouvrent ', '2021-02-01'),
(151, 'c est quoi un Yaniro ', '2021-02-01'),
(152, 'Comment choisir son compagnon de grimpe ', '2021-02-01'),
(153, 'Grimper en dülfer ', '2021-02-01'),
(154, 'C est quoi l escalade ', '2021-02-01'),
(155, 'J ai pas de bras et pas de jambes. Puis-je faire de l escalade quand même ', '2021-02-01'),
(156, 'Quel technique pour grimper une faille ', '2021-02-01'),
(157, 'Le casque en falaise, c est vraiment utile ', '2021-02-01'),
(158, 'Quelle manipulation faire en haut de voie ', '2021-02-01'),
(159, 'C est combien de temps la durée de vie d une corde ', '2021-02-01'),
(160, 'Comment faire un noeud de huit ', '2021-02-01'),
(161, 'C est combien de temps la durée de vie des chaussons ', '2021-02-01'),
(162, 'C est quoi la meilleure marque de chaussons ', '2021-02-01'),
(163, 'la dalle c est quoi ', '2021-02-01'),
(164, 'Comment installer un rappel ', '2021-02-01'),
(165, 'Comment progresser sur le pangulish ', '2021-02-01'),
(167, 'Si je suis aveugle, puis-je grimper à vue ', '2021-02-01'),
(168, 'Quels sont les spots d escalade autour de Toulouse ', '2021-02-01'),
(169, 'Est ce que ça pu plus des pieds en grimpant en chausson ou pieds nus ', '2021-02-01'),
(170, 'Quelles sont les salles d escalade dans Toulouse ', '2021-02-01'),
(171, 'Comment on lis une voie ', '2021-02-01'),
(172, 'comment travailler les pinces ', '2021-02-01'),
(173, 'Flasher une voie, c est la faire très vite ', '2021-02-01'),
(175, 'Est que être grand c est tricher ', '2021-02-01'),
(176, 'Pour progresser, le plus efficace c est la muscu ou la grimpe ', '2021-02-01'),
(177, 'Comment tenir sur les réglettes ', '2021-02-01'),
(180, 'Qui est le meilleur grimpeur au monde ', '2021-02-01'),
(181, 'Faire une traction à un doigt est-il utile ', '2021-02-01'),
(183, 'Où est le meilleur site de grimpe en France ', '2021-02-01'),
(184, 'Coucou', '2021-02-01'),
(185, 'quels sont les danger de prendre une pris en arquée ', '2021-02-01'),
(186, 'Quel est le secteur le plus proche de Toulouse ', '2021-02-01'),
(187, 'Puis je me moquer des gros nuls qui font du 4 ', '2021-02-01'),
(189, 'Hola', '2021-02-01'),
(190, 'Peut-on comparer le 10e degré à un micro horizontal ', '2021-02-01'),
(191, 'Quelle est la différence entre un bon et un mauvais grimpeur ', '2021-02-01'),
(192, 'Qui est le meilleur grimpeur ', '2021-02-01'),
(193, 'Comment passer une 7a ', '2021-02-01'),
(194, 'Peut-on comparer le 10e degré à une glace horizontal ', '2021-02-01'),
(195, 'quels échauffements faire avant de grimper ', '2021-02-01'),
(196, 'Qui est le meilleur grimpeur ', '2021-02-01'),
(197, 'Grimper rend-il cool ', '2021-02-01'),
(198, 'Connaitre la difficulté du bloc ', '2021-02-01'),
(199, 'Qu est ce que c est un crux ', '2021-02-01'),
(200, 'C est quoi le psychobloc ', '2021-02-01'),
(201, 'Comment fait gollum pour grimper avec de si petits bras ', '2021-02-01'),
(202, 'Les niveaux de difficulté dans l escalade ', '2021-02-01'),
(205, 'Comment les ouvreurs font pour estimer la cotation d une voie ', '2021-02-01'),
(207, 'Comment gagner en force ', '2021-02-01'),
(209, 'Comment gagner en technique ', '2021-02-01'),
(212, 'Ou acheter une hangboard ', '2021-02-01'),
(213, 'Quels matériel pour grimper en falaise ', '2021-02-01'),
(214, 'Comment faire un nœud de 8 ', '2021-02-01'),
(215, 'Quel est le meilleur, le noeud de 8 ou le noeud de chaise ', '2021-02-01'),
(216, 'Considére-t-on qu une limace est forte en escalade si elle arrive en haut d une voie difficile ', '2021-02-01'),
(218, 'Matériel pour la falaise ', '2021-02-01'),
(219, 'La corde est-elle indispensable ', '2021-02-01'),
(220, 'La couleur des mousquetons a-t-elle de l importance ', '2021-02-01'),
(222, 'L escalade c est bien ', '2021-02-01'),
(223, 'Quel matériel pour grimper en falaise ', '2021-02-01'),
(224, 'Quand sera la prochaine compétition d escalade ', '2021-02-01'),
(225, 'Au-revoir', '2021-02-01'),
(226, 'Bisous', '2021-02-01'),
(227, 'Bye', '2021-02-01'),
(228, 'Bonne nuit', '2021-02-01'),
(229, 'Aurevoir', '2021-02-01'),
(233, 'Comment récupérer une dégaine sur une voie ', '2021-02-02'),
(234, 'Comment choisir entre le noeud de chaise et le nœud de 8 ', '2021-02-02'),
(235, 'À quoi sert la magnésie ', '2021-02-02'),
(237, 'Comment je dois faire pour gagner en force ', '2021-02-15'),
(239, 'gagner en force', '2021-02-16'),
(240, 'Qui est le meilleur ', '2021-02-16'),
(242, 'Que dois-je faire pour gagner en force ', '2021-02-21'),
(244, 'Gravir le mont COVID', '2021-02-21'),
(245, 'Comment faire pour gagner en force ', '2021-02-28'),
(246, 'Ou puis aller pour grimper vers Toulouse ', '2021-02-28'),
(247, 'grimper vers toulouse', '2021-02-28'),
(248, 'spot sur toulouse', '2021-02-28'),
(249, 'Quels exercices pour gagner en force ', '2021-03-01'),
(250, 'Quel est le meilleur temps pour aller en falaise ', '2021-03-01'),
(251, 'comment choisir on un baudrier', '2021-03-01'),
(252, 'Qui est le meilleur grimpeur au monde ', '2021-03-01'),
(253, 'c est quoi l escalade ', '2021-03-01'),
(254, 'Est ce que l escalade est un sport masculin ', '2021-03-10'),
(255, 'à quoi sert une dégaine', '2021-03-10'),
(256, 'à quoi sert un baudrier ', '2021-03-10'),
(257, 'qu est-ce qu un baudrier ', '2021-03-10'),
(258, 'qu est-ce qu une dégaine ', '2021-03-10'),
(259, 'Quels sont les classements en compétition ', '2021-03-10'),
(261, 'peut on faire escalader un élan à un pigeon ', '2021-03-10'),
(262, 'Comment sont classées les voies', '2021-03-10'),
(263, 'quels genre de handi-escalade existe-t il ', '2021-03-11'),
(280, 'quel matériel acheter pour faire de l escalade ', '2021-03-30'),
(281, 'Faut-il autre chose ', '2021-03-30'),
(282, 'dois-je acheter un baudrier ', '2021-03-30'),
(283, 'quel type de piolet pour escalader une cascade de glace ', '2021-03-30'),
(284, 'Etes vous content de me voir ', '2021-04-05'),
(285, '/replayUnanswered', '2021-04-06'),
(286, 'Comment améliorer sa force ', '2021-04-24'),
(307, 'comment gagner en force efficacement ', '2021-04-25'),
(326, 'comment choisir ses chaussons d escalade ', '2021-04-25'),
(327, 'comment grimper sur de la glace ', '2021-04-26'),
(329, 'les mousquetons diffèrent-ils selon leur couleur', '2021-04-26'),
(330, ';select * from questions', '2021-04-26'),
(331, 'comment puis je ameliorer ma force', '2021-05-07');

-- --------------------------------------------------------

--
-- Structure de la table `repondre`
--

CREATE TABLE `repondre` (
  `id_reponse` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `confiance` int(11) NOT NULL,
  `mots_cles_associes` text DEFAULT NULL,
  `date_reponse` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `repondre`
--

INSERT INTO `repondre` (`id_reponse`, `id_question`, `confiance`, `mots_cles_associes`, `date_reponse`) VALUES
(1, 1, 100, NULL, '2020-11-09'),
(1, 53, 100, NULL, '2021-01-03'),
(1, 106, 100, NULL, '2021-01-01'),
(1, 115, 100, NULL, '2021-02-27'),
(1, 141, 100, NULL, '2021-02-01'),
(1, 213, 100, NULL, '2021-02-01'),
(1, 218, 100, NULL, '2021-02-01'),
(1, 223, 100, NULL, '2021-02-01'),
(2, 2, 100, NULL, '2020-11-09'),
(2, 8, 100, NULL, '2021-01-03'),
(2, 61, 100, NULL, '2021-01-03'),
(2, 107, 100, NULL, '2021-01-03'),
(2, 110, 100, NULL, '2021-02-27'),
(2, 111, 100, NULL, '2021-03-30'),
(2, 148, 11, '{25,28,45,49,58,59,62,67,92}', '2021-04-06'),
(2, 153, 50, '{79,94}', '2021-04-06'),
(2, 155, 11, '{25,28,49,54,58,59,62,67,78}', '2021-04-06'),
(2, 165, 100, NULL, '2021-02-01'),
(2, 172, 50, '{25,61}', '2021-04-06'),
(2, 207, 100, NULL, '2021-02-01'),
(2, 209, 100, NULL, '2021-02-01'),
(2, 224, 14, '{25,28,49,58,59,62,67}', '2021-04-06'),
(2, 237, 100, NULL, '2021-02-27'),
(2, 239, 100, NULL, '2021-02-27'),
(2, 242, 100, NULL, '2021-02-27'),
(2, 245, 100, NULL, '2021-02-28'),
(2, 249, 100, NULL, '2021-03-06'),
(2, 251, 100, NULL, '2021-03-01'),
(2, 259, 50, '{79,94}', '2021-03-10'),
(2, 286, 0, '{28,29,79,85}', '2021-04-24'),
(2, 307, 80, '{79,94}', '2021-04-25'),
(2, 326, 41, '{25,28,31,32,33,34,35,44,49,59,77,92}', '2021-04-25'),
(2, 331, 24, '{28,29,79,85}', '2021-05-07'),
(3, 3, 100, NULL, '2020-12-26'),
(3, 4, 100, NULL, '2020-12-26'),
(3, 11, 100, NULL, '2020-12-27'),
(3, 15, 100, NULL, '2020-12-27'),
(3, 184, 100, NULL, '2021-02-01'),
(44, 6, 100, NULL, '2021-01-03'),
(44, 108, 100, NULL, '2021-02-27'),
(45, 64, 100, NULL, '2021-01-03'),
(64, 43, 100, NULL, '0000-00-00'),
(65, 42, 100, NULL, '2021-01-05'),
(66, 101, 100, NULL, '2021-01-01'),
(67, 106, 100, NULL, '2021-02-27'),
(67, 116, 100, NULL, '2021-02-27'),
(67, 121, 100, NULL, '2021-02-01'),
(67, 201, 20, '{24,50,57,58,70}', '2021-04-06'),
(67, 219, 50, '{31,53}', '2021-04-06'),
(67, 280, 100, NULL, '2021-03-30'),
(67, 283, 50, '{24,30}', '2021-03-30'),
(68, 117, 100, NULL, '2021-02-27'),
(68, 122, 100, NULL, '2021-02-01'),
(68, 138, 100, NULL, '2021-02-02'),
(68, 169, 11, '{35,39,45,55,79,89,91,92,94}', '2021-04-06'),
(69, 123, 100, NULL, '2021-02-01'),
(70, 124, 100, NULL, '2021-02-01'),
(70, 257, 100, NULL, '2021-03-27'),
(71, 180, 100, NULL, '2021-03-06'),
(71, 192, 100, NULL, '2021-02-01'),
(71, 196, 100, NULL, '2021-02-01'),
(71, 240, 100, NULL, '2021-02-27'),
(71, 252, 100, NULL, '2021-03-27'),
(74, 229, 100, NULL, '2021-02-01'),
(75, 187, 100, NULL, '2021-02-01'),
(76, 127, 100, NULL, '2021-02-01'),
(77, 131, 100, NULL, '2021-02-01'),
(78, 135, 100, NULL, '2021-02-01'),
(79, 226, 100, NULL, '2021-02-01'),
(80, 222, 100, NULL, '2021-02-02'),
(81, 146, 100, NULL, '2021-02-02'),
(81, 233, 100, NULL, '2021-02-27'),
(82, 157, 100, NULL, '2021-02-02'),
(83, 176, 100, NULL, '2021-02-02'),
(83, 183, 33, '{63,67,80}', '2021-04-06'),
(84, 128, 7, '{36,37,38,43,47,48,49,63,72,80,95,96,101,102}', '2021-04-06'),
(84, 134, 100, NULL, '2021-02-02'),
(84, 163, 100, NULL, '2021-02-27'),
(85, 139, 100, NULL, '2021-02-02'),
(86, 133, 100, NULL, '2021-02-02'),
(86, 145, 100, NULL, '2021-02-02'),
(87, 142, 100, NULL, '2021-02-02'),
(88, 149, 100, NULL, '2021-02-02'),
(89, 147, 100, NULL, '2021-02-02'),
(90, 171, 100, NULL, '2021-02-02'),
(91, 160, 100, NULL, '2021-02-02'),
(91, 214, 100, NULL, '2021-02-02'),
(92, 220, 100, NULL, '2021-02-02'),
(92, 329, 66, NULL, '2021-04-26'),
(93, 119, 100, NULL, '2021-02-27'),
(93, 152, 100, NULL, '2021-02-02'),
(94, 161, 100, NULL, '2021-02-02'),
(95, 168, 100, NULL, '2021-02-02'),
(95, 186, 100, NULL, '2021-02-02'),
(95, 246, 100, NULL, '2021-03-06'),
(95, 247, 100, NULL, '2021-02-27'),
(95, 248, 100, NULL, '2021-02-27'),
(96, 140, 100, NULL, '2021-02-02'),
(97, 162, 100, NULL, '2021-02-02'),
(98, 200, 100, NULL, '2021-02-02'),
(99, 199, 100, NULL, '2021-02-02'),
(100, 173, 100, NULL, '2021-02-02'),
(101, 113, 100, NULL, '2021-02-28'),
(102, 225, 100, NULL, '2021-03-01'),
(102, 227, 100, NULL, '2021-02-27'),
(102, 228, 100, NULL, '2021-02-27'),
(103, 235, 100, NULL, '2021-03-01'),
(104, 151, 100, NULL, '2021-03-01'),
(105, 126, 100, NULL, '2021-02-27'),
(105, 136, 100, NULL, '2021-03-01'),
(105, 154, 100, NULL, '2021-02-27'),
(105, 253, 100, NULL, '2021-03-06'),
(105, 254, 100, NULL, '2021-03-10'),
(106, 191, 100, NULL, '2021-03-01'),
(107, 137, 100, NULL, '2021-03-01'),
(107, 216, 33, '{38,49,52}', '2021-04-06'),
(108, 156, 100, NULL, '2021-03-01'),
(109, 170, 100, NULL, '2021-03-01'),
(110, 255, 100, NULL, '2021-03-10'),
(110, 258, 100, NULL, '2021-03-10'),
(111, 118, 100, NULL, '2021-03-20'),
(111, 282, 33, '{32,66,101}', '2021-03-30'),
(112, 144, 100, NULL, '2021-03-20'),
(113, 261, 100, NULL, '2021-03-27'),
(114, 175, 100, NULL, '2021-03-27'),
(115, 198, 100, NULL, '2021-03-27'),
(115, 202, 100, NULL, '2021-03-27'),
(115, 205, 50, '{71,73}', '2021-04-06');

-- --------------------------------------------------------

--
-- Structure de la table `reponses`
--

CREATE TABLE `reponses` (
  `id_reponse` int(11) NOT NULL,
  `response` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reponses`
--

INSERT INTO `reponses` (`id_reponse`, `response`) VALUES
(1, 'Pour pratiquer en falaise, il faut pouvoir se procurer un baudrier, une corde d\'escalade, un descendeur (auto-bloquant ou non), et connaitre les bases de sécurités suite à une initiation par exemple.'),
(2, 'Pour gagner en force, on peut faire des exerices au sol, des pompes, des exerices sur les triceps,  du pan gullish, des squats, et même des exercices de souplesse !'),
(3, 'Bonjour ! J\'espère que vous êtes en forme pour parler d\'escalade !'),
(44, 'Hello there ! I\'m sorry, but I only speak french for now :/'),
(45, 'Dans ce sport, la force est un plus qui permet en premier lieu de rattraper des erreurs à bas niveau. En soit, elle s\'acquiert seule, en pratiquant.'),
(64, 'Gagner de la force est bien, prendre du volume de muscles peut-être désavantageux et gêner certains mouvements.'),
(65, 'Bonjour  je ne parle pas anglais'),
(66, 'Pour s\'entrainer au sol, tout est possible et bénéfique ! Travailler son endurance, renforcer sa musculature, gagner en souplesse... Et c\'est bien !'),
(67, 'Une corde d\'escalade se choisit chez un professionnel, il existe plusieurs calibres, pour de la falaise, généralement on utilise du 10mm, d\'une longueur de 60 mètres, étant un minimum '),
(68, 'Chaque pieds possède son chausson adapté. Il est conseillé pour du long terme et des bonnes performance d\'opter pour des chaussons de pointures -2,  afin de verrouiller les mouvements du pieds.'),
(69, 'A tout niveaux, tout équipier apporte un bénéfice. S\'il est meilleur que vous, il va vous tirer vers le haut avec des conseils, s\'il est moins bon que vous, vous allez vous mettre à sa place pour analyser des solutions.'),
(70, 'Un baudrier ? Pour quoi faire ?'),
(71, 'C\'est vous !'),
(74, 'A bientôt ! J\'ai hâte de progresser avec vous !'),
(75, 'Tout le monde à son niveau ! Les aider à progresser vous fera gagner en lecture de voie !'),
(76, 'Réponse courte'),
(77, 'Je suis un bot ! Faire une traction est déjà un exploit !'),
(78, 'Il s\'agit d\'un pan de parois dont l\'aplomb est négatif'),
(79, 'Bisous à vous aussi !'),
(80, 'L\'escalade est un sport très prenant, en tant que @Escaladobot, j\'ajouterai que c\'est le meilleur sport du monde !'),
(81, 'Pour récupérer une dégaine, je vous conseille de déscendre en moulinette depuis une voie voisine, et déclipser la dégaine une fois à portée. On peut aussi envisager le rappel.'),
(82, 'Le casque prévient de la chute de cailloux. Il suffit d\'une fois pour comprendre que c\'est utile !'),
(83, 'La musculation aide à préparer le corps pour l\'effort, mais le plus important c\'est de grimper régulièrement. C\'est un sport à sensations, gérer son corps sur une parois sera toujours plus bénéfique.'),
(84, 'La dalle est une section de parois qui est globalement d\'aplomb.'),
(85, 'Pour s\'équiper en bloc, il faut se munir de chaussons d\'escalade, de crashpad si l\'on part en extérieur, et de partir à plusieurs pour parer chaque grimpeur.'),
(86, 'Je préfère quand ça monte !'),
(87, 'Un crashpad est un équipement qui est utilisé dans l\'escalade bloc. C\'est un tapis d\'environs 10 à 20 cm d\'épaisseur qui permet d\'amortir les chutes du grimpeur.'),
(88, 'Le free solo est une activité dangeureuse ! Selon la hauteur visée, la moindre chute,  le moindre passage de faiblesse peut-être mortel !'),
(89, 'Je pense que oui.'),
(90, 'Pour lire une voi il faut se placer en face de la voie, et s\'imaginer dessus, avec sa propre . Imaginer chaque recoins, chaque posture que l\'on peut adopter en fonction des appuis visible. C\'est un exercice qui demande beaucoup de pratique !'),
(91, 'Je dois expliquer ça !? Essayez peut-être ce lien-ci https://grimpavranches.com/techniques/les-noeuds/noeud-en-huit/'),
(92, 'La couleur des mousquetons à une immense importance pour les grimpeurs : le style ! (Et ne pas les confondre avec ceux du collègue un peu)'),
(93, 'Il faut choisir une personne sensibilisée aux techniques d\'assurage, et aux manipulations de tête. Sinon n\'importe quel BG fera l\'affaire !'),
(94, 'Tout dépend de la dureté de la gomme et de la fréquence d\'utilisation. Un chausson peut durer entre 6 mois et 2 ans généralement.'),
(95, 'Le coin à falaise le plus proche de Toulouse est à Saint-Antonin noble-val. '),
(96, 'Le pan gullich est un entrainement qui peut-être fait une à deux fois par semaines, par des session de 30 minutes en variant ses exercices.'),
(97, 'Les chaussons étant une appropriation personnelle, on ne peut pas généraliser quelle est la meilleure marque. Chaque marque à sa forme de pieds, son confort, ses qualités et défauts, qui seront surement propres à chacun.'),
(98, 'Le psychobloc est une pratique particulière de l\'escalade bloc, elle consiste à pratiquer le bloc au dessus d\'un point d\'eau, la chute entraînant un bain rafraîchissant !'),
(99, 'Le \"crux\" est un terme signifiant un passage complexe dans une ascension.'),
(100, 'Flasher une voie signifie réussir une voie du premier essais.'),
(101, 'Bonjour ! Je ferai mon possible pour répondre à vos interrogations sur le thème de l\'escalade !'),
(102, 'A bientôt pour de nouvelles questions !'),
(103, 'La magnésie (ou pof) est une poudre qui permet d\'assécher la peau. Elle est beaucoup utilisée dans les sports où il faut garder les mains sèches pour éviter de chuter.'),
(104, 'Le Yaniro est une méthode de repos / de création d\'appuis qui consiste à tenir une prise d\'escalade d\'une main, et à poser le genou opposé sur le coude du bras utilisé.'),
(105, 'L\'escalade est un sport qui consiste à gravir des parcours (des voies) dans de différentes difficultés. Il existe notamment les pratiques de falaises (ou voie si c\'est en salle), de bloc et de vitesse.'),
(106, 'On bon grimpeur sait lire une voie depuis le sol, il anticipe. Il a un bonne poigne puissante, et doit être capable de se reposer sur un mur. La souplesse et la musculature sont un plus.'),
(107, 'La voie est la pratique la plus classique de l\'escalade, qui consiste à atteindre un point assez haut sur une paroi \"verticale\", assuré par une corde très souvent un coéquipier. \r\nLe bloc en revanche les points à atteindre sont plus bas (4 mètres généralement) et les passages sont généralement plus dynamiques.'),
(108, 'Tout dépend de la faille, le serrage de poing, le coincement de pieds / de genoux peuvent fonctionner. Tenir en opposition peut aussi fonctionner.'),
(109, 'Dans Toulouse il existe les salles de Bloc-out, Solo escalade et Starting bloc pour la pratique du bloc.\r\nPour la voie il y a Altissimo. \r\nCertaines des salles permettent la pratique des deux disciplines.'),
(110, 'Une dégaine est un double mousqueton qui permet en falaise de garantir une sécurité. Un premier mousqueton est fixé sur le pyton de la paroi, et le second mousqueton permet de faire coulisser la corde du grimpeur. En cas de chute, le grimpeur se retrouve sous le dernier pyton atteint, retenu par la dégaine.'),
(111, 'Si vous restez longtemps en suspension, le confort est plus indispensable qu’en escalade en salle. De même si vous grimpez en glace et en falaise, un baudrier réglable serait mieux pour pouvoir l’ajuster en fonction des couches de vêtements que vous porterez.\r\nSi vous souhaitez pratiquer régulièrement, ne partez pas sur un baudrier « entrée de gamme ». Ces baudriers sont destinés à une pratique plus ponctuelle et sont moins confortables. '),
(112, 'Il existe plusieurs tailles et épaisseur de crashpads. Les plus épais protègent mieux lors des chutes, mais sont plus lourd à transporter a pieds de spots à blocs. \r\nIl faut s\'assurer lors des sorties d\'avoir suffisamment de crashpads pour recouvrir les zones à risques'),
(113, 'Ouais, c\'est pas faux !'),
(114, 'La taille en escalade peut être facteur d\'avantage et de défaut. Être trop grand peut présenter quelques inconforts lors de  l’ascension.  Bien sur être petit n\'avantage pas le grimpeur, pour autant cela permet de forcer la discussion et de trouver une solution pour passer des crux en collaborant.'),
(115, 'La cotation en escalade se constitue d\'un nombre, suivit d\'une lettre. Le nombre donne la difficulté globale de la voie. Il est compris entre 3 et 10 en général. Le 10 étant un niveau extrêmement élevé. Ensuite une lettre comprise entre a, b ou c permet d\'affiner la définition de la difficulté. Le a étant le plus simple, et le c, le plus complexe. Voici un exemple, 6c, une voie moyennement complexe,  qui tend vers du niveau 7.'),
(116, 'Un baudrier sert à attacher le grimpeur à sa corde lors de son ascension. Il existe différents modèles plus ou moins confortables selon la morphologie, et de taille reglable.'),
(117, 'Pour grimper sur de la glace il faut se munir de crampons, de piolets et généralement un matériel de sécurité adapté. Prévoir des habits chaud malgré l\'effort.');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `contenir`
--
ALTER TABLE `contenir`
  ADD PRIMARY KEY (`id_question`,`id_mot_cle`),
  ADD KEY `contenir_mots_cles0_FK` (`id_mot_cle`);

--
-- Index pour la table `mots_cles`
--
ALTER TABLE `mots_cles`
  ADD PRIMARY KEY (`id_mot_cle`);

--
-- Index pour la table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id_question`);

--
-- Index pour la table `repondre`
--
ALTER TABLE `repondre`
  ADD PRIMARY KEY (`id_reponse`,`id_question`),
  ADD KEY `repondre_questions0_FK` (`id_question`);

--
-- Index pour la table `reponses`
--
ALTER TABLE `reponses`
  ADD PRIMARY KEY (`id_reponse`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `mots_cles`
--
ALTER TABLE `mots_cles`
  MODIFY `id_mot_cle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- AUTO_INCREMENT pour la table `questions`
--
ALTER TABLE `questions`
  MODIFY `id_question` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=332;

--
-- AUTO_INCREMENT pour la table `reponses`
--
ALTER TABLE `reponses`
  MODIFY `id_reponse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `contenir`
--
ALTER TABLE `contenir`
  ADD CONSTRAINT `contenir_mots_cles0_FK` FOREIGN KEY (`id_mot_cle`) REFERENCES `mots_cles` (`id_mot_cle`),
  ADD CONSTRAINT `contenir_questions_FK` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id_question`);

--
-- Contraintes pour la table `repondre`
--
ALTER TABLE `repondre`
  ADD CONSTRAINT `repondre_questions0_FK` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id_question`),
  ADD CONSTRAINT `repondre_reponses_FK` FOREIGN KEY (`id_reponse`) REFERENCES `reponses` (`id_reponse`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
