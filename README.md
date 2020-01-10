# CollectionPhoto

Application permettant de faire un suivi sur la sauvegarde et replication des repertoires contenant des données 

à sauvegarder.

## Pré requis

Avoir une clé de programmation sur un utiliateur IAM AWS permettant d'accéder aux droit DynamoDBFullAccess.

Pour un demarrage en stand alone (sans etre deployer sur un cloud aws via ansible - terraform),

Placer le fichier credentials ici :

```
C:/Users/<USER WINDOWS>/.aws/credentials.aws
```

## Démarrage

```
cd CollectionPhoto
docker run -p 8888:8888 -v /c/Users/<USER WINDOWS>/.aws:/credentials/ collectionapi

```

## Pour un déploiement sur AWS

Voir le projet https://github.com/alexgit95/InstanceAWSConfig





