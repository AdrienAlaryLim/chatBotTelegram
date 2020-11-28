# chatBotTelegram

Voici un projet qui s'intéresse à la mise en place d'un chatBot sur Telegram avec Java, la discussion sera définie sur un thème définit.</br>
Le but final est de trouver un système pour que le bot réponde intelligemment à des phrases qu'un utilisateur lui proposera, en fonction du contexte.</br>
A ce jour, une base de données est nécessaire pour stocker des mots clés, en rappot avec des phrases "questions", qui sont liées à des phrases "réponses", pour permettre au bot de répondre. </br>
Pour effectuer la mise en route de ce projet, il est nécessaire que vous possédiez votre propre bot.

## Création du bot
Pour créer votre bot, je vous conseille depuis votre application Telegram, de lancer une discussion avec "BotFather".</br>
Avec la commande /start, le bot va vous dire ce qu'il est capable de faire. Vous n'aurez qu'à suivre ses indications, c'est très intuitif !</br>
A l'issue de la création, botFather va vous fournir une <b>clé API, elle est unique, ne la communiquez pas !</b> </br>
Elle permet de modifier les comportements du bot nouvellement créé.

## Installation
Désormais, vous possédez votre bot. Vous pouvez récupérer le code source de ce dépôt, et le déposer dans un répertoire <b>/chatBotTelegram</b> </br>
Assurez vous de modifier les variables situées dans <b>chatBotTelegram/src/user/UserConstants.java</b> </br>
C'est à cet endroit que vous indiquerez le nom d'utilisateur de votre bot, sa clé API, et votre URL d'accès à votre base de données.</br>
```java
public class UserConstants 
{
	// Define your url to connect to your database with your database name
	// ex : jdbc:mysql://127.0.0.1:3306/mydb_name
	private static final String sqlUrl = "jdbc:mysql://127.0.0.1:3306/mydb_name";
	
	// Define your telegram bot token API key
	// ex: 1234567890:MyTelegramByAPIKeyHash
	private static final String botToken = "1234567890:MyTelegramByAPIKeyHash";
	
	// Define your telegram bot username
	// ex: MyTelegramBotUsername
	private static final String botUsername = "MyTelegramBotUsername";
  
	{...}
}
```

## Mise en route
Une fois installé vous n'aurez qu'à exécuter le fichier <b>src/core/Main.java</b> et votre bot sera fonctionnel.
